package org.yugo.services.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantServiceMockData {

    public static List<Restaurant> getRestaurantMockData() {
        var mockedRestaurants = new ArrayList<Restaurant>();
        mockedRestaurants.add(new Restaurant("name_1_AAA", 1, 5, 10, "cuisineAAA"));
        mockedRestaurants.add(new Restaurant("name_1_BBB", 2, 5, 10, "cuisineAAA"));
        mockedRestaurants.add(new Restaurant("name_1_CCC", 3, 5, 10, "cuisineBBB"));
        mockedRestaurants.add(new Restaurant("name_2_DDD", 4, 10, 20, "cuisineBBB"));
        mockedRestaurants.add(new Restaurant("name_2_EEE", 5, 10, 20, "cuisineCCC"));
        mockedRestaurants.add(new Restaurant("name_2_FFF", 5, 10, 30, "cuisineCCC"));

        return mockedRestaurants;
    }

    public static RestaurantSearchParameter getInvalidCustomerRatingParameter() {
        return new RestaurantSearchParameter(
            Optional.of("name"), 
            Optional.of(99), 
            Optional.of(10), 
            Optional.of(10), 
            Optional.of("cuisine"));
    }
        
    public static RestaurantSearchParameter getInvalidPriceParameter() {
        return new RestaurantSearchParameter(
            Optional.of("name"), 
            Optional.of(5), 
            Optional.of(10), 
            Optional.of(0), 
            Optional.of("cuisine"));
    }

    public static RestaurantSearchParameter getInvalidDistanceParameter() {
        return new RestaurantSearchParameter(
            Optional.of("name"), 
            Optional.of(5), 
            Optional.of(0), 
            Optional.of(10), 
            Optional.of("cuisine"));
    }

    public static RestaurantSearchParameter getNoMatchParameter() {
        return new RestaurantSearchParameter(
            Optional.of("undefined"), 
            Optional.of(5), 
            Optional.of(10), 
            Optional.of(10), 
            Optional.of("cuisine"));
    }

    public static RestaurantSearchParameter getLessThan5MatchesParameter() {
        return new RestaurantSearchParameter(
            Optional.of("name"), 
            Optional.of(3), 
            Optional.of(10), 
            Optional.of(20), 
            Optional.of("cuisineBBB"));
    }

    public static RestaurantSearchParameter getMoreThan5MatchesParameter() {
        return new RestaurantSearchParameter(
            Optional.of("name"), 
            Optional.of(1), 
            Optional.of(10), 
            Optional.of(30), 
            Optional.of("cuisine"));
    }

    public static RestaurantSearchParameter getMatchParameter() {
        return new RestaurantSearchParameter(
            Optional.of("name"), 
            Optional.of(2), 
            Optional.of(10), 
            Optional.of(20), 
            Optional.of("cuisine"));
    }    
}
