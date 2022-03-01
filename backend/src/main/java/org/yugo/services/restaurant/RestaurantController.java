package org.yugo.services.restaurant;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    
    @GET
    public List<Restaurant> findAll() {
        return restaurantService.getAll();
    }
    
    @POST
    public List<Restaurant> findRestaurants(RestaurantSearchParameter searchParameter) {
        return restaurantService.findBestRestaurant(searchParameter);
    }

}
