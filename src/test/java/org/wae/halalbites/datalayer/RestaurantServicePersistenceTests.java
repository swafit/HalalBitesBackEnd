package org.wae.halalbites.datalayer;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.wae.halalbites.dataaccesslayer.Restaurant;
import org.wae.halalbites.dataaccesslayer.RestaurantRepository;
import reactor.test.StepVerifier;

public class RestaurantServicePersistenceTests {
    @Autowired
    RestaurantRepository repo;

    @Test
    void shouldSaveOneBundle(){

        Publisher<Restaurant> setup = repo.deleteAll().thenMany(repo.save(buildRestaurant()));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
    }
    @Test
    void shouldFindAllRestaurants(){

        Restaurant restaurant = buildRestaurant();

        Publisher<Restaurant> setup = repo.deleteAll().thenMany(repo.save(buildRestaurant()));
        Publisher<Restaurant> find = repo.findAll();

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        StepVerifier
                .create(find)
                .expectNextCount(1)
                .verifyComplete();

    }

    private Restaurant buildRestaurant() {

        return Restaurant.builder().id("Id").restaurantUUID("restaurantUUID").name("name").halalType("Halal").location("location").phoneNumber("phoneNumber").website(null).build();
    }
}
