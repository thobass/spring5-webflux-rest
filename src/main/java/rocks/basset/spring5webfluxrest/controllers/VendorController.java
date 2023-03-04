package rocks.basset.spring5webfluxrest.controllers;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rocks.basset.spring5webfluxrest.domain.Vendor;
import rocks.basset.spring5webfluxrest.reprositories.VendorRepository;

@RestController
@RequiredArgsConstructor
public class VendorController {

    private final VendorRepository vendorRepository;

    @GetMapping("/api/v1/vendors")
    public Flux<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @GetMapping("/api/v1/vendors/{id}")
    public Mono<Vendor> findById(@PathVariable String id){
        return vendorRepository.findById(id);
    }

    @PostMapping("/api/v1/vendors")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> create(@RequestBody Publisher<Vendor> vendorPublisher){
        return vendorRepository.saveAll(vendorPublisher).then();
    }

    @PutMapping("/api/v1/vendors/{id}")
    Mono<Vendor> update(@PathVariable String id, @RequestBody Vendor vendor){
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @PatchMapping("/api/v1/vendors/{id}")
    Mono<Vendor> patch(@PathVariable String id, @RequestBody Vendor vendor){
        boolean changes = false;

        vendor.setId(id);

        Vendor findedVendor = vendorRepository.findById(id).block();

        if(findedVendor.getFirstName() != vendor.getFirstName()){
            findedVendor.setFirstName(vendor.getFirstName());
            changes = true;
        }

        if(findedVendor.getLastName() != vendor.getLastName()){
            findedVendor.setLastName(vendor.getLastName());
            changes = true;
        }

        if(changes){
            return vendorRepository.save(vendor);
        }

        return Mono.just(findedVendor);
    }
}
