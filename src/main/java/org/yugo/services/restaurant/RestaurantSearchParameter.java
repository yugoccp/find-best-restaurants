package org.yugo.services.restaurant;

public class RestaurantSearchParameter {
    private String name;
    private Integer customerRating;
    private Integer distance;
    private Integer price;
    private String cuisine;

    public RestaurantSearchParameter() {}

    public RestaurantSearchParameter(
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