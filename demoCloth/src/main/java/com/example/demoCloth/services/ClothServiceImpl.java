package com.example.demoCloth.services;

import com.example.demoCloth.commons.mappers.ItemMapper;
import com.example.demoCloth.model.responses.ItemResponse;
import com.example.demoCloth.model.entities.Brand;
import com.example.demoCloth.model.specifications.BrandSpecifications;
import com.example.demoCloth.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the service that calls a repository to query the embedded database.
 */
@Service
@RequiredArgsConstructor
public class ClothServiceImpl implements ClothService {

    @Autowired
    private BrandRepository brandRepository;

    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    /**
     * Queries the database to find the list of items that match the parameters passed.
     *
     * @param date      date that should be between start date and end date of the item.
     * @param productId product identifier.
     * @param brandId   brand identifier.
     * @return A list of items containing product identifier, brand identifier, price list, date start and end, and price.
     */
    @Override
    public List<ItemResponse> findItem(String date, String productId, String brandId) {
        LocalDateTime askedDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));
        List<ItemResponse> itemResponseList = new ArrayList<>();
        Specification<Brand> specification = BrandSpecifications.hasProductId(productId);

        List<Brand> brands = brandRepository.findAll(specification);

        brands.forEach(data -> data.getPrices()
                .stream()
                .filter(price -> data.getId().equals(Long.parseLong(brandId)) && askedDate.isAfter(price.getStartDate()) && askedDate.isBefore(price.getEndDate()))
                .forEach(item -> {
                    ItemResponse aux = itemMapper.priceToItem(item, data);
                    itemResponseList.add(aux);
                }));
        return itemResponseList;
    }
}
