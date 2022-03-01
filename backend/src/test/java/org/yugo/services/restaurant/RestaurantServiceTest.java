package org.yugo.services.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
 
    @BeforeEach
    public void init() {
        restaurantRepository = mock(RestaurantRepository.class);
        when(restaurantRepository.getAll()).thenReturn(RestaurantServiceMockData.getRestaurantMockData());

        restaurantService = new RestaurantService(restaurantRepository);
    }
    
    @Test
    public void When_InvalidCustomerRating_Should_ReturnInvalidMessage() {
        var invalidCustomerRating = RestaurantServiceMockData.getInvalidCustomerRatingParameter();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantService.findBestRestaurant(invalidCustomerRating);
        });

        try {
            restaurantService.findBestRestaurant(invalidCustomerRating);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid Customer Rating value"));
        }
    }
    
    @Test
    public void When_InvalidPrice_Should_ReturnInvalidMessage() {
        var invalidPrice = RestaurantServiceMockData.getInvalidPriceParameter();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantService.findBestRestaurant(invalidPrice);
        });

        try {
            restaurantService.findBestRestaurant(invalidPrice);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid Price value"));
        }
    }

    @Test
    public void When_InvalidDistance_Should_ReturnInvalidMessage() {
        var invalidDistance = RestaurantServiceMockData.getInvalidDistanceParameter();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            restaurantService.findBestRestaurant(invalidDistance);
        });

        try {
            restaurantService.findBestRestaurant(invalidDistance);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("Invalid Distance value"));
        }
    }

    @Test
    public void When_NoMatch_Should_ReturnEmptyList() {
        var noMatch = RestaurantServiceMockData.getNoMatchParameter();

        Assertions.assertEquals(0,
            restaurantService.findBestRestaurant(noMatch)
            .size()
        );
    }

    @Test
    public void When_LessThan5Matches_Should_ReturnAll() {
        RestaurantSearchParameter lessThan5Matches = RestaurantServiceMockData.getLessThan5MatchesParameter();

        Assertions.assertEquals(2,
            restaurantService.findBestRestaurant(lessThan5Matches)
            .size()
        );
    }

    @Test
    public void When_MoreThan5Matches_Should_Return5Matches() {
        var moreThan5Matches = RestaurantServiceMockData.getMoreThan5MatchesParameter();

        Assertions.assertEquals(5,
            restaurantService.findBestRestaurant(moreThan5Matches)
            .size()
        );
    }

    @Test
    public void When_FindMatches_Should_ContainsName() {
        var match = RestaurantServiceMockData.getMatchParameter();

        var result = restaurantService.findBestRestaurant(match);

        Assertions.assertTrue(result.size() > 0);

        result.forEach(restaurant -> Assertions.assertTrue(!restaurant.getName().isEmpty()));

    }

    @Test
    public void When_FindMatches_Should_ReturnExactOrPartialhName() {
        var match = RestaurantServiceMockData.getMatchParameter();
        var searchName = match.getName();

        var result = restaurantService.findBestRestaurant(match);

        Assertions.assertTrue(result.size() > 0);

        result.forEach(restaurant -> {
            Assertions.assertTrue(!restaurant.getName().isEmpty());
            Assertions.assertTrue(restaurant.getName().toLowerCase().contains(searchName));
        });
    }

    @Test
    public void When_FindMatches_Should_ReturnEqualOrMoreThanCustomerRating() {
        var match = RestaurantServiceMockData.getMatchParameter();
        var searchCustomerRating = match.getCustomerRating();

        var result = restaurantService.findBestRestaurant(match);

        Assertions.assertTrue(result.size() > 0);

        result.forEach(restaurant -> {
            Assertions.assertTrue(restaurant.getCustomerRating() >= searchCustomerRating);
        });
    }

    @Test
    public void When_FindMatches_Should_ReturnEqualOrLessThanPrice() {
        var match = RestaurantServiceMockData.getMatchParameter();
        var searchPrice = match.getPrice();

        var result = restaurantService.findBestRestaurant(match);

        Assertions.assertTrue(result.size() > 0);

        result.forEach(restaurant -> {
            Assertions.assertTrue(restaurant.getPrice() <= searchPrice);
        });
    }

    @Test
    public void When_FindMatches_Should_ReturnEqualOrLessThanDistance() {
        var match = RestaurantServiceMockData.getMatchParameter();
        var searchDistance = match.getDistance();

        var result = restaurantService.findBestRestaurant(match);

        Assertions.assertTrue(result.size() > 0);

        result.forEach(restaurant -> {
            Assertions.assertTrue(restaurant.getDistance() <= searchDistance);
        });
    }

    @Test
    public void When_FindMatches_Should_ReturnExactOrPartialCuisine() {
        var match = RestaurantServiceMockData.getMatchParameter();
        var searchCuisine = match.getCuisine();

        var result = restaurantService.findBestRestaurant(match);

        Assertions.assertTrue(result.size() > 0);

        result.forEach(restaurant -> {
            Assertions.assertTrue(restaurant.getCuisine().toLowerCase().contains(searchCuisine));
        });
    }

    @Test
    public void When_MultipleMatches_Should_MatchOrder() {
        var match = RestaurantServiceMockData.getMatchParameter();

        var result = restaurantService.findBestRestaurant(match);

        Assertions.assertEquals(4, result.size());

        result.get(0).getName().equals("name_1_BBB");
        result.get(1).getName().equals("name_1_CCC");
        result.get(2).getName().equals("name_2_DDD");
        result.get(3).getName().equals("name_2_EEE");

    }
}
