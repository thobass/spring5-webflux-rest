package rocks.basset.spring5webfluxrest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rocks.basset.spring5webfluxrest.domain.Category;
import rocks.basset.spring5webfluxrest.reprositories.CategoryRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    WebTestClient webTestClient;
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    CategoryController controller;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void list() {
        //GIVEN
        given(categoryRepository.findAll()).willReturn(Flux.just(Category.builder().build(),
                Category.builder().build()));

        //WHEN
        //THEN
        webTestClient.get()
                .uri("/api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getById() {
        //GIVEN
        given(categoryRepository.findById(anyString())).willReturn(Mono.just(Category.builder().build()));

        //WHEN
        //THEN
        webTestClient.get()
                .uri("/api/v1/categories/someid")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    void create() {
        //GIVEN
        given(categoryRepository.saveAll(any(Publisher.class))).willReturn(Flux.just(Category.builder().build()));

        Mono<Category> categoryMono = Mono.just(Category.builder().description("category").build());

        //WHEN
        //THEN
        webTestClient.post()
                .uri("/api/v1/categories")
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void update() {
        //GIVEN
        given(categoryRepository.save(any(Category.class))).willReturn(Mono.just(Category.builder().build()));

        Mono<Category> categoryMono = Mono.just(Category.builder().description("category").build());

        //WHEN
        //THEN
        webTestClient.put()
                .uri("/api/v1/categories/1")
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
}