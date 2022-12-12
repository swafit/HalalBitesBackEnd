package org.wae.halalbites.dataaccesslayer;


import lombok.*;

import java.net.URL;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private String restaurantUUID;
    private String name;
    private String halalType;
    private String location;
    private String phoneNumber;
    private URL website;

    public RestaurantDTO(String name, String halalType, String location, String phoneNumber, URL website){
        this.name=name;
        this.halalType=halalType;
        this.location=location;
        this.phoneNumber=phoneNumber;
        this.website=website;
    }
    public RestaurantDTO(String name, String halalType, String location, String phoneNumber){
        this.name=name;
        this.halalType=halalType;
        this.location=location;
        this.phoneNumber=phoneNumber;
    }
}
