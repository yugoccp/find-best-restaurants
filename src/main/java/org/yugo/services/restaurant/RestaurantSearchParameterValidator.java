package org.yugo.services.restaurant;

public class RestaurantSearchParameterValidator {

    /** 
     * Validates {@code RestaurantSearchParameter} values boundaries:
     * <ul>
     * <li>Customer Rating(1 star ~ 5 stars)</li>
     * <li>Distance(1 mile ~ 10 miles)</li>
     * <li>Price(how much one person will spend on average, $10 ~ $50)</li>
     * </ul>
     * @throws IllegalArgumentException if one of the values are invalid
    */
    public void validate(RestaurantSearchParameter restaurantSearchParameter) throws IllegalArgumentException {
        if (restaurantSearchParameter.getCustomerRating().isPresent()) {
            var customerRating = restaurantSearchParameter.getCustomerRating().get();
            if (customerRating < 1 || customerRating > 5) {
                throw new IllegalArgumentException("Invalid Customer Rating value, please provide values between 1 star to 5 stars");
            }
        };

        if (restaurantSearchParameter.getDistance().isPresent()) {
            var distance = restaurantSearchParameter.getDistance().get();
            if (distance < 1 || distance > 10) {
                throw new IllegalArgumentException("Invalid Distance value, please provide values between 1 mile and 10 miles");
            }
        };

        if (restaurantSearchParameter.getPrice().isPresent()) {
            var price = restaurantSearchParameter.getPrice().get();
            if (price < 10 || price > 50) {
                throw new IllegalArgumentException("Invalid Price value, please provide values between $10 and $50");
            }
        };        
    }
    
}
