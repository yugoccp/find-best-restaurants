package org.yugo.services.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestaurantRepositoryTest {

    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void init() {
        restaurantRepository = new RestaurantRepository();
    }

    @Test
    public void When_GetAllRestaurants_Should_ReturnValidList() {
        var result = restaurantRepository.getAll();

        Assertions.assertTrue(result.size() > 0);
        
        result.forEach(restaurant -> {
            Assertions.assertNotNull(restaurant.getName());
            Assertions.assertNotNull(restaurant.getDistance());
            Assertions.assertNotNull(restaurant.getPrice());
            Assertions.assertNotNull(restaurant.getCuisine());
            Assertions.assertNotNull(restaurant.getCustomerRating());
        });
    }
}
