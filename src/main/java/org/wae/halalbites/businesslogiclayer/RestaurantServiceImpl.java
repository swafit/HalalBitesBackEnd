package org.wae.halalbites.businesslogiclayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wae.halalbites.dataaccesslayer.RestaurantDTO;
import org.wae.halalbites.dataaccesslayer.RestaurantRepository;
import org.wae.halalbites.other.utils.EntityDTOUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    //public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {this.restaurantRepository=restaurantRepository;}
    @Override
    public Flux<RestaurantDTO> GetAllRestaurants() {
        return restaurantRepository.findAll().map(EntityDTOUtil::toDto);
    }

    @Override
    public Mono<RestaurantDTO> CreateRestaurant(Mono<RestaurantDTO> model) {
        return model
                .map(EntityDTOUtil::toEntity)
                .doOnNext(e -> e.setRestaurantUUID(EntityDTOUtil.generateUUID()))
                .flatMap(restaurantRepository::insert)
                .map(EntityDTOUtil::toDto);
    }
}
