package org.yugo.services.restaurant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private RestaurantSearchParameterValidator restaurantSearchParameterValidator;
    private RestaurantComparator restaurantComparator;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantSearchParameterValidator = new RestaurantSearchParameterValidator();
        this.restaurantComparator = new RestaurantComparator();
    }

    /**
     * Return the first 5 best {@code Restaurant} matches following the rules below:
     * <ul>
     *     <li>A Restaurant Name match is defined as an exact or partial String match with what users provided. For example, “Mcd” would match “Mcdonald’s”.</li>
     *     <li>A Customer Rating match is defined as a Customer Rating equal to or more than what users have asked for. For example, “3” would match all the 3 stars restaurants plus all the 4 stars and 5 stars restaurants.</li>
     *     <li>A Distance match is defined as a Distance equal to or less than what users have asked for. For example, “2” would match any distance that is equal to or less than 2 miles from your company.</li>
     *     <li>A Price match is defined as a Price equal to or less than what users have asked for. For example, “15” would match any price that is equal to or less than $15 per person.</li>
     *     <li>A Cuisine match is defined as an exact or partial String match with what users provided. For example, “Chi” would match “Chinese”. You can find all the possible Cuisines in the **cuisines.csv** file. *You can assume each restaurant offers only one cuisine.*</li>
     *     <li>The five parameters are holding an “AND” relationship. For example, if users provide Name = “Mcdonald’s” and Distance = 2, you should find all “Mcdonald’s” within 2 miles.</li>
     * </ul>
     * @param searchParameter
     * @return a list best matches for the given searchParameter
     */
    public List<Restaurant> findBestRestaurant(RestaurantSearchParameter searchParameter) throws IllegalArgumentException {

        restaurantSearchParameterValidator.validate(searchParameter);

        Stream<Restaurant> restaurantStream = restaurantRepository.getAllRestaurants().stream();

        if(searchParameter.getName().isPresent()) {
            var searchName = searchParameter.getName().get().toLowerCase();
            restaurantStream = restaurantStream.filter(restaurant -> restaurant.getName().toLowerCase().contains(searchName));
        }

        if(searchParameter.getCustomerRating().isPresent()) {
            var searchCustomerRating = searchParameter.getCustomerRating().get();
            restaurantStream = restaurantStream.filter(restaurant -> restaurant.getCustomerRating() >= searchCustomerRating);
        }

        if(searchParameter.getDistance().isPresent()) {
            var searchDistance = searchParameter.getDistance().get();
            restaurantStream = restaurantStream.filter(restaurant -> restaurant.getDistance() <= searchDistance);
        }

        if(searchParameter.getPrice().isPresent()) {
            var searchPrice = searchParameter.getPrice().get();
            restaurantStream = restaurantStream.filter(restaurant -> restaurant.getPrice() <= searchPrice);
        }

        if(searchParameter.getCuisine().isPresent()) {
            var searcCuisine = searchParameter.getCuisine().get().toLowerCase();
            restaurantStream = restaurantStream.filter(restaurant -> restaurant.getCuisine().toLowerCase().contains(searcCuisine));
        }

        return restaurantStream
            .sorted(restaurantComparator)
            .limit(5)
            .collect(Collectors.toList());

    }

}