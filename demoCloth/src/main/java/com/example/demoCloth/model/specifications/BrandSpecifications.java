package com.example.demoCloth.model.specifications;

import com.example.demoCloth.model.entities.Brand;
import com.example.demoCloth.model.entities.Price;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class BrandSpecifications {

    public static Specification<Brand> hasProductId(String productId) {
        return (root, query, criteriaBuilder) -> {
            Join<Price, Brand> priceBrandJoin = root.join("prices");
            return criteriaBuilder.equal(priceBrandJoin.get("productId"), productId);
        };
    }
}
