package org.yugo.services.restaurant;

import java.util.List;
import java.util.stream.Collectors;

import org.yugo.utils.CsvUtils;

public class RestaurantRepository {

    public List<Restaurant> getAllRestaurants() {
        var cuisineData = getCuisineData();
        var restaurantData = getRestaurantData(cuisineData);

        return restaurantData;
    }

    private List<Restaurant> getRestaurantData(List<Cuisine> cuisineData) {
        var cuisineMapById = cuisineData.stream().collect(Collectors.toMap(Cuisine::getId, Cuisine::getName));
        var csvData = CsvUtils.getCsvData(RestaurantCsvSettings.FILE_PATH);
        var restaurantData = CsvUtils.parseCsvData(csvData, (csvHeaderMap, csvRow) -> {
            var name = csvRow[csvHeaderMap.get(RestaurantCsvSettings.HEADER_NAME)];
            var customerRating = Integer.parseInt(csvRow[csvHeaderMap.get(RestaurantCsvSettings.HEADER_CUSTOMER_RATING)]);
            var distance = Integer.parseInt(csvRow[csvHeaderMap.get(RestaurantCsvSettings.HEADER_DISTANCE)]);
            var price = Integer.parseInt(csvRow[csvHeaderMap.get(RestaurantCsvSettings.HEADER_PRICE)]);
            var cuisineId = Integer.parseInt(csvRow[csvHeaderMap.get(RestaurantCsvSettings.HEADER_CUISINE_ID)]);
            var cuisine = cuisineMapById.get(cuisineId);
            return new Restaurant(name, customerRating, distance, price, cuisine);
        });

        return restaurantData;
    }

    private List<Cuisine> getCuisineData() {
        var csvData = CsvUtils.getCsvData(CuisineCsvSettings.FILE_PATH);     
        var cuisineData = CsvUtils.parseCsvData(csvData, (csvHeaderMap, csvRow) -> {
            var id = Integer.parseInt(csvRow[csvHeaderMap.get(CuisineCsvSettings.HEADER_ID)]);
            var name = csvRow[csvHeaderMap.get(CuisineCsvSettings.HEADER_NAME)];
            return new Cuisine(id, name);
        });

        return cuisineData;
    }
}
