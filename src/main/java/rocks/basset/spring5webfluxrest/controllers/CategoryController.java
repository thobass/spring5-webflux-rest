package rocks.basset.spring5webfluxrest.controllers;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rocks.basset.spring5webfluxrest.domain.Category;
import rocks.basset.spring5webfluxrest.reprositories.CategoryRepository;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/api/v1/categories")
    public Flux<Category> list(){
        return categoryRepository.findAll();
    }

    @GetMapping("/api/v1/categories/{id}")
    public Mono<Category> getById(@PathVariable String id){
        return categoryRepository.findById(id);
    }

    @PostMapping("/api/v1/categories")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> create(@RequestBody Publisher<Category> categoryPublisher){
        return categoryRepository.saveAll(categoryPublisher).then();
    }

    @PutMapping("/api/v1/categories/{id}")
    Mono<Category> update(@PathVariable String id, @RequestBody Category category){
        category.setId(id);
        return categoryRepository.save(category);
    }

    @PatchMapping("/api/v1/categories/{id}")
    Mono<Category> patch(@PathVariable String id, @RequestBody Category category){
        category.setId(id);

        Category foundedCategory = categoryRepository.findById(id).block();

        if(foundedCategory.getDescription() != category.getDescription()){
            foundedCategory.setDescription(category.getDescription());
            return categoryRepository.save(foundedCategory);
        }

        return Mono.just(foundedCategory);
    }
}
