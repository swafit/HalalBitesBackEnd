package org.wae.halalbites.businesslayer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.wae.halalbites.businesslogiclayer.RestaurantService;
import org.wae.halalbites.dataaccesslayer.Restaurant;
import org.wae.halalbites.dataaccesslayer.RestaurantDTO;
import org.wae.halalbites.dataaccesslayer.RestaurantRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RestaurantServiceImplTest {
    @MockBean
    RestaurantRepository repo;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void test_GetAllBundles() {
        Restaurant restaurantEntity = buildRestaurant();

        when(repo.findAll()).thenReturn(Flux.just(restaurantEntity));

        Flux<RestaurantDTO> restaurantDTOFlux = restaurantService.GetAllRestaurants();

        StepVerifier.create(restaurantDTOFlux)
                .consumeNextWith(foundRestaurant -> {
                    assertNotNull(foundRestaurant);
                })
                .verifyComplete();
    }
    @Test
    public void test_CreateRestaurant(){

        Restaurant restaurantEntity = buildRestaurant();

        Mono<Restaurant> restaurantMono = Mono.just(restaurantEntity);
        RestaurantDTO restaurantDTO = buildRestaurantDTO();

        when(repo.insert(any(Restaurant.class))).thenReturn(restaurantMono);

        Mono<RestaurantDTO> returnedRestaurant = restaurantService.CreateRestaurant(Mono.just(restaurantDTO));

        StepVerifier.create(returnedRestaurant)
                .consumeNextWith(monoDTO -> {
                    assertEquals(restaurantEntity.getRestaurantUUID(), monoDTO.getRestaurantUUID());
                    assertEquals(restaurantEntity.getName(), monoDTO.getName());
                    assertEquals(restaurantEntity.getHalalType(), monoDTO.getHalalType());
                    assertEquals(restaurantEntity.getLocation(), monoDTO.getLocation());
                    assertEquals(restaurantEntity.getPhoneNumber(), monoDTO.getPhoneNumber());
                })
                .verifyComplete();

    }
    private Restaurant buildRestaurant() {

        return Restaurant.builder().id("Id").restaurantUUID("restaurantUUID").name("name").halalType("Halal").location("location").phoneNumber("phoneNumber").website(null).build();
    }

    private RestaurantDTO buildRestaurantDTO() {

        return RestaurantDTO.builder().name("name").halalType("Halal").location("location").phoneNumber("phoneNumber").build();
    }
}
