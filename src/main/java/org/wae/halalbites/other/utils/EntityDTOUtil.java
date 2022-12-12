package org.wae.halalbites.other.utils;

import org.springframework.beans.BeanUtils;
import org.wae.halalbites.dataaccesslayer.Restaurant;
import org.wae.halalbites.dataaccesslayer.RestaurantDTO;

import java.util.UUID;

public class EntityDTOUtil {
    public static RestaurantDTO toDto (Restaurant restaurant){
        RestaurantDTO dto = new RestaurantDTO();
        BeanUtils.copyProperties(restaurant, dto);
        return dto;
    }

    public static Restaurant toEntity (RestaurantDTO dto){
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(dto, restaurant);
        return restaurant;
    }
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
