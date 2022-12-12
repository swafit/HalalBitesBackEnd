package org.wae.halalbites.businesslogiclayer;

import org.springframework.web.bind.annotation.RequestBody;
import org.wae.halalbites.dataaccesslayer.RestaurantDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RestaurantService {

    Flux<RestaurantDTO> GetAllRestaurants();
    Mono<RestaurantDTO> CreateRestaurant(@RequestBody Mono<RestaurantDTO> model);
}
