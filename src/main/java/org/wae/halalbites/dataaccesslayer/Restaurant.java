package org.wae.halalbites.dataaccesslayer;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.net.URL;
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
