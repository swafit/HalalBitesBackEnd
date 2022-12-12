package org.wae.halalbites.presentationlayer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.wae.halalbites.businesslogiclayer.RestaurantService;
import org.wae.halalbites.dataaccesslayer.RestaurantDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static reactor.core.publisher.Mono.when;
import static reactor.core.publisher.Mono.just;

@WebFluxTest(controllers = RestaurantResources.class)
public class RestaurantResourceUnitTest {
    private final RestaurantDTO dto = buildRestaurantDTO();
    private final String BUNDLE_UUID_OK = dto.getRestaurantUUID();
    private final String NAME_OK = dto.getName();

    @Autowired
    private WebTestClient client;

    @MockBean
    RestaurantService restaurantService;
    @Test
    void createRestaurant() {
        Mono<RestaurantDTO> restaurantDTOMono= Mono.just(dto);
        when(restaurantService.CreateRestaurant(restaurantDTOMono))
                .thenReturn(restaurantDTOMono);

         client.post()
                .uri("/restaurants")
                .body(restaurantDTOMono, RestaurantDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();

         Mockito.verify(restaurantService, times(1)).CreateRestaurant(any(Mono.class));

        //assertNotNull("this");
    }
    @Test
    void findAllRestaurants() {

        Mockito.when(restaurantService.GetAllRestaurants()).thenReturn(Flux.just(dto));

        client.get()
                .uri("/restaurants/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].name").isEqualTo(dto.getName())
                .jsonPath("$[0].halalType").isEqualTo(dto.getHalalType())
                .jsonPath("$[0].location").isEqualTo(dto.getLocation());

        Mockito.verify(restaurantService, times(1)).GetAllRestaurants();
    }
    private RestaurantDTO buildRestaurantDTO() {

        return RestaurantDTO.builder()
                .name("name")
                .halalType("Halal")
                .location("location")
                .phoneNumber("phoneNumber")
                .build();
    }
}
