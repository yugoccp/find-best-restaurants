package org.yugo.services.restaurant;

import java.util.Optional;

public class RestaurantSearchParameter {
    private final Optional<String> name;
    private final Optional<Integer> customerRating;
    private final Optional<Integer> distance;
    private final Optional<Integer> price;
    private final Optional<String> cuisine;

    public RestaurantSearchParameter(
        Optional<String> name,
        Optional<Integer> customerRating,
        Optional<Integer> distance,
        Optional<Integer> price,
        Optional<String> cuisine
    ) {
        this.name = name;
        this.customerRating = customerRating;
        this.distance = distance;
        this.price = price;
        this.cuisine = cuisine;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<Integer> getCustomerRating() {
        return customerRating;
    }

    public Optional<Integer> getDistance() {
        return distance;
    }

    public Optional<Integer> getPrice() {
        return price;
    }

    public Optional<String> getCuisine() {
        return cuisine;
    }

}