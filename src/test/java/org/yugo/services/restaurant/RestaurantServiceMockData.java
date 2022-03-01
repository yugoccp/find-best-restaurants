package org.yugo.services.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RestaurantServiceMockData {

    public static List<Restaurant> getRestaurantMockData() {
        var mockedRestaurants = new ArrayList<Restaurant>();
        mockedRestaurants.add(new Restaurant("name_1_AAA", 1, 5, 10, "cuisineAAA"));
        mockedRestaurants.add(new Restaurant("name_1_BBB", 2, 5, 10, "cuisineAAA"));
        mockedRestaurants.add(new Restaurant("name_1_CCC", 3, 5, 10, "cuisineBBB"));
        mockedRestaurants.add(new Restaurant("name_2_DDD", 4, 10, 20, "cuisineBBB"));
        mockedRestaurants.add(new Restaurant("name_2_EEE", 5, 10, 20, "cuisineCCC"));
        mockedRestaurants.add(new Restaurant("name_2_FFF", 5, 10, 30, "cuisineCCC"));

        Collections.shuffle(mockedRestaurants, new Random(0));
        
        return mockedRestaurants;
    }

    public static RestaurantSearchParameter getInvalidCustomerRatingParameter() {
        return new RestaurantSearchParameter(
            "name", 
            99, 
            10, 
            10, 
            "cuisine");
    }
        
    public static RestaurantSearchParameter getInvalidPriceParameter() {
        return new RestaurantSearchParameter(
            "name", 
            5, 
            10, 
            0, 
            "cuisine");
    }

    public static RestaurantSearchParameter getInvalidDistanceParameter() {
        return new RestaurantSearchParameter(
            "name", 
            5, 
            0, 
            10, 
            "cuisine");
    }

    public static RestaurantSearchParameter getNoMatchParameter() {
        return new RestaurantSearchParameter(
            "undefined", 
            5, 
            10, 
            10, 
            "cuisine");
    }

    public static RestaurantSearchParameter getLessThan5MatchesParameter() {
        return new RestaurantSearchParameter(
            "name", 
            3, 
            10, 
            20, 
            "cuisineBBB");
    }

    public static RestaurantSearchParameter getMoreThan5MatchesParameter() {
        return new RestaurantSearchParameter(
            "name", 
            1, 
            10, 
            30, 
            "cuisine");
    }

    public static RestaurantSearchParameter getMatchParameter() {
        return new RestaurantSearchParameter(
            "name", 
            2, 
            10, 
            20, 
            "cuisine");
    }    
}
