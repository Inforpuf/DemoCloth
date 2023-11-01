package com.example.demoCloth.commons.mappers;

import com.example.demoCloth.model.Brand;
import com.example.demoCloth.model.Item;
import com.example.demoCloth.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Contains all mappings related with Item class that represents the response of the query.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {

    /**
     * Maps the Pojo of the embedded database table Prices to the query response.
     * @param source Pojo of the embedded database table Prices.
     * @return query response.
     */
    @Mapping(target = "brandId", source = "brand.id")
    Item priceToItem(Price source, Brand brand);
}
