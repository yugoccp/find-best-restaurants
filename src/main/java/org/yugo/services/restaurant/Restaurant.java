package org.yugo.services.restaurant;

public class Restaurant {
    private final String name;
    private final Integer customerRating;
    private final Integer distance;
    private final Integer price;
    private final String cuisine;

    public Restaurant(
        String name,
        Integer customerRating,
        Integer distance,
        Integer price,
        String cuisine
    ) {
        this.name = name;
        this.customerRating = customerRating;
        this.distance = distance;
        this.price = price;
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public Integer getCustomerRating() {
        return customerRating;
    }

    public Integer getDistance() {
        return distance;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCuisine() {
        return cuisine;
    }
}
