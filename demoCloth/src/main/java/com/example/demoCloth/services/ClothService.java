package com.example.demoCloth.services;

import com.example.demoCloth.model.responses.ItemResponse;

import java.util.List;

/**
 * Interface of the service that calls a repository to query the embedded database.
 */
public interface ClothService {
    /**
     * Queries the database to find the list of items that match the parameters passed.
     *
     * @param date      date that should be between start date and end date of the item.
     * @param productId product identifier.
     * @param brandId   brand identifier.
     * @return A list of items containing product identifier, brand identifier, price list, date start and end, and price.
     */
    List<ItemResponse> findItem(String date, String productId, String brandId);
}
