package com.example.demoCloth.controllers;

import com.example.demoCloth.model.responses.ItemResponse;
import com.example.demoCloth.services.ClothService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that contains all the operation of the Rest API.
 */
@Validated
@RestController
@RequestMapping(value = "/cloths")
@Slf4j
public class ClothApiController implements IClothApiController{

    @Autowired
    private ClothService clothService;

    @Override
    @GetMapping(produces="application/json")
    public ResponseEntity<List<ItemResponse>> findItem(String date, String productId, String brandId) {
        List<ItemResponse> itemResponseList = clothService.findItem(date, productId, brandId);
        if (itemResponseList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemResponseList);
    }
}
