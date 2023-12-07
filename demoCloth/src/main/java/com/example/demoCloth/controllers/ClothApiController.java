package com.example.demoCloth.controllers;

import com.example.demoCloth.model.responses.ItemResponse;
import com.example.demoCloth.services.ClothService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Controller that contains all the operation of the Rest API.
 */
@Validated
@RestController
@RequestMapping(value = "/cloths")
@Slf4j
public class ClothApiController implements IClothApiController {

    @Autowired
    private ClothService clothService;

    @Override
    @GetMapping(produces = "application/json")
    @RateLimiter(name = "rateLimitingFindItem")
    public ResponseEntity<List<ItemResponse>> findItem(String version, String date, String productId, String brandId) {

        if (version.equals("v1")) {
            List<ItemResponse> itemResponseList = clothService.findItem(date, productId, brandId);
            if (itemResponseList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(itemResponseList);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("version", version);
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Wrong version number", headers, null, Charset.defaultCharset());
        }
    }
}
