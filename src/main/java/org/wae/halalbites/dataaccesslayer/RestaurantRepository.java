package org.wae.halalbites.dataaccesslayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RestaurantRepository extends ReactiveMongoRepository<Restaurant, String> {

    Flux<Restaurant> findAllRestaurants();
}
