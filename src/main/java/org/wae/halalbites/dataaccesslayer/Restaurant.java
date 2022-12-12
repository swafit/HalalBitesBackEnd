package org.wae.halalbites.dataaccesslayer;

import lombok.*;

import java.net.URL;

import org.springframework.data.annotation.Id;
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    private String id;
    private String restaurantUUID;
    private String name;
    private String halalType;
    private String location;
    private String phoneNumber;
    private URL website;
}
