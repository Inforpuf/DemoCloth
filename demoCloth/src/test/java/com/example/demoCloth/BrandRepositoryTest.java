package com.example.demoCloth;

import com.example.demoCloth.model.Brand;
import com.example.demoCloth.model.BrandSpecifications;
import com.example.demoCloth.repositories.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository repository;

    @Test
    public void findByIDTest() {
        Optional<Brand> resultList = repository.findById(1L);

        Assertions.assertNotNull(resultList);
        resultList.ifPresent(result -> Assertions.assertEquals(1L, result.getId()));
    }

    @Test
    public void findBySpecificationTest() {
        Specification<Brand> specification = BrandSpecifications.hasProductId("35455");

        List<Brand> brands = repository.findAll(specification);

        Assertions.assertNotNull(brands);
        Assertions.assertEquals(1L, brands.size());
    }
}
