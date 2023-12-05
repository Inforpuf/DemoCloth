package com.example.demoCloth.controllers;

import com.example.demoCloth.model.Item;
import io.swagger.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Cloth", description = "the Cloth Api")
public interface IClothApiController {

    /**
     * Queries the database to find the list of items that match the parameters passed.
     *
     * @param date      date that should be between start date and end date of the item.
     * @param productId product identifier.
     * @param brandId   brand identifier.
     * @return A list of items containing product identifier, brand identifier, price list, date start and end, and price.
     */
    @Operation(
            summary = "Find if any item of the product is available at that date, at that store.",
            description = "Find if any item of the product is available at that date, at that store.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "204", description = "No product available for that date, store and product",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid data provided",
                    content = @Content(schema = @Schema()))
    })
    ResponseEntity<List<Item>> findItem(@Parameter(in = "QUERY", description = "Date provided to check item availability", required = true)
                                               @NotNull @NotEmpty @Valid @RequestParam(value = "date") String date,
                                               @Parameter(in = "QUERY", description = "Identifier of the product", required = true)
                                               @NotNull @NotEmpty @Valid @RequestParam(value = "productId") String productId,
                                               @Parameter(in = "QUERY", description = "Identifier of the brand", required = true)
                                               @NotNull @NotEmpty @Valid @RequestParam(value = "brandId") String brandId) ;
}
