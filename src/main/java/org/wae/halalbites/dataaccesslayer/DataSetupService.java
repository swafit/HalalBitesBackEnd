package org.wae.halalbites.dataaccesslayer;

import org.springframework.boot.CommandLineRunner;
import org.wae.halalbites.businesslogiclayer.RestaurantService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DataSetupService implements CommandLineRunner {

    private RestaurantService restaurantService;

    @Override
    public void run(String... args) throws Exception {
        RestaurantDTO r1 = new RestaurantDTO("Halala","Halal","somewhere","123");
        RestaurantDTO r2 = new RestaurantDTO("Kish","Kosher","in a galaxy","456");
        RestaurantDTO r3 = new RestaurantDTO("Kash","Kosher","far","789");
        RestaurantDTO r4 = new RestaurantDTO("Kosh","Kosher","far away","000");

        Flux.just(r1,r2,r3,r4)
                .flatMap(r -> restaurantService.CreateRestaurant(Mono.just(r))
                        .log(r.toString()))
                .subscribe();
    }
}
