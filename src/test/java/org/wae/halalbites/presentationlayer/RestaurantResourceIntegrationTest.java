package org.wae.halalbites.presentationlayer;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.wae.halalbites.dataaccesslayer.Restaurant;
import org.wae.halalbites.dataaccesslayer.RestaurantRepository;
import reactor.test.StepVerifier;

import static reactor.core.publisher.Mono.just;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantResourceIntegrationTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private RestaurantRepository repo;
    @Test
    void createBundle() {

        Restaurant restaurantEntity = buildRestaurant();

        Publisher<Restaurant> setup = repo.deleteAll().thenMany(repo.save(restaurantEntity));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        client.post()
                .uri("/restaurants")
                .body(just(restaurantEntity), Restaurant.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();

        client.get()
                .uri("/restaurants")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].name").isEqualTo(restaurantEntity.getName())
                .jsonPath("$[0].halalType").isEqualTo(restaurantEntity.getHalalType())
                .jsonPath("$[0].location").isEqualTo(restaurantEntity.getLocation());
    }
    @Test
    void findAllBundles() {

        Restaurant restaurantEntity = buildRestaurant();

        Publisher<Restaurant> setup = repo.deleteAll().thenMany(repo.save(restaurantEntity));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        client.get()
                .uri("/restaurants")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].name").isEqualTo(restaurantEntity.getName())
                .jsonPath("$[0].halalType").isEqualTo(restaurantEntity.getHalalType())
                .jsonPath("$[0].location").isEqualTo(restaurantEntity.getLocation());
    }
    private Restaurant buildRestaurant() {

        return Restaurant.builder().id("Id").restaurantUUID("restaurantUUID").name("name").halalType("Halal").location("location").phoneNumber("phoneNumber").website(null).build();
    }
}
