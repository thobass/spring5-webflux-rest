package rocks.basset.spring5webfluxrest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rocks.basset.spring5webfluxrest.domain.Vendor;
import rocks.basset.spring5webfluxrest.reprositories.VendorRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    WebTestClient webTestClient;

    @InjectMocks
    VendorController controller;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void findAll() {
        //GIVEN
        given(vendorRepository.findAll()).willReturn(Flux.just(Vendor.builder().build(), Vendor.builder().build()));
        //WHEN

        //THEN
        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void findById() {
        //GIVEN
        given(vendorRepository.findById(anyString())).willReturn(Mono.just(Vendor.builder().build()));
        //WHEN

        //THEN
        webTestClient.get()
                .uri("/api/v1/vendors/someid")
                .exchange()
                .expectBody(Vendor.class);
    }
}