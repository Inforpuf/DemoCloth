package com.example.demoCloth.controllers;

import com.example.demoCloth.model.Item;
import com.example.demoCloth.services.ClothService;
import io.swagger.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that contains all the operation of the Rest API.
 */
@Validated
@RestController
@RequestMapping(value = "/cloths")
@Slf4j
public class ClothApiController {

    @Autowired
    private ClothService clothService;

    /**
     * Queries the database to find the list of items that match the parameters passed.
     *
     * @param date      date that should be between start date and end date of the item.
     * @param productId product identifier.
     * @param brandId   brand identifier.
     * @return A list of items containing product identifier, brand identifier, price list, date start and end, and price.
     */
    @GetMapping()
    public ResponseEntity<List<Item>> findItem(@Parameter(in = "QUERY", description = "Date provided to check item availability", required = true)
                                               @NotNull @NotEmpty @Valid @RequestParam(value = "date") String date,
                                               @Parameter(in = "QUERY", description = "Identifier of the product", required = true)
                                               @NotNull @NotEmpty @Valid @RequestParam(value = "productId") String productId,
                                               @Parameter(in = "QUERY", description = "Identifier of the brand", required = true)
                                               @NotNull @NotEmpty @Valid @RequestParam(value = "brandId") String brandId) {
        List<Item> itemList = clothService.findItem(date, productId, brandId);
        if (itemList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemList);
    }

}
