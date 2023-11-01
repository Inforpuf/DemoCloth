package com.example.demoCloth;

import com.example.demoCloth.model.Price;
import com.example.demoCloth.repositories.ClothRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ClothRepositoryTest {
    @Autowired
    private ClothRepository repository;

    @Test
    public void findByIDTest() {
        Optional<Price> resultList = repository.findById(1L);

        Assertions.assertNotNull(resultList);
        resultList.ifPresent(result -> Assertions.assertEquals(1L, result.getId()));
    }

}
