package org.yugo.services.restaurant;

import java.util.Comparator;

public class RestaurantComparator implements Comparator<Restaurant> {

    /**
     * Compare two {@code Restaurant} following the rules below:
     * <ul>
     * <li>Sort the restaurants by Distance first.</li>
     * <li>After the above process, if two matches are still equal, then the restaurant with a higher customer rating wins.</li>
     * <li>After the above process, if two matches are still equal, then the restaurant with a lower price wins.</li>
     * <li>After the above process, if two matches are still equal, then you can randomly decide the order.</li>
     * <li>
     *   Example: if the input is Customer Rating = 3 and Price = 15. Mcdonald’s is 4 stars with an average spend = $10, and it is 1 mile away.
     *   And KFC is 3 stars with an average spend = $8, and it is 1 mile away. Then we should consider Mcdonald’s as a better match than KFC. 
     *   (They both matches the search criteria -> we compare distance -> we get a tie -> we then compare customer rating -> Mcdonald’s wins)
     * </li>
     * </ul>
     */
    @Override
    public int compare(Restaurant rest1, Restaurant rest2) {

        if (rest1.getDistance() != rest2.getDistance())
            return rest1.getDistance().compareTo(rest2.getDistance());

        if (rest1.getCustomerRating() != rest2.getCustomerRating())
            return rest1.getCustomerRating().compareTo(rest2.getCustomerRating());

        if (rest1.getPrice() != rest2.getPrice())
            return rest1.getPrice().compareTo(rest2.getPrice());
        
        // For testing purpose, to avoid inconsistent order result, if all criteria 
        // matches with equal value, return the order by the restaurant name
        return rest1.getName().compareTo(rest2.getName());
    }
    
}
