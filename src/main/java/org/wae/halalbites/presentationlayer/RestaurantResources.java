package org.wae.halalbites.presentationlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.wae.halalbites.businesslogiclayer.RestaurantService;
import org.wae.halalbites.dataaccesslayer.RestaurantDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public class RestaurantResources {

    @Autowired
    private RestaurantService SERVICE;

    @PostMapping("/restaurants")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RestaurantDTO> createRestaurant(@Valid @RequestBody Mono<RestaurantDTO> restaurantDTO) {
        return SERVICE.CreateRestaurant(restaurantDTO);
    }
    @GetMapping(value = "/restaurants")
    public Flux<RestaurantDTO> findAllRestaurants() {
        return SERVICE.GetAllRestaurants();
    }
}
