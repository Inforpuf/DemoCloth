package com.example.demoCloth;

import com.example.demoCloth.model.Brand;
import com.example.demoCloth.model.Item;
import com.example.demoCloth.model.Price;
import com.example.demoCloth.repositories.BrandRepository;
import com.example.demoCloth.services.ClothServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ClothServiceImplTest {

    private static final String PRODUCT_ID = "35455";
    private static final LocalDateTime START_DATE = LocalDateTime.of(2023,10,10,10,0);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2023,12,20,10,0);

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private ClothServiceImpl clothService;

    @BeforeEach
    public void setup() {
        Brand item = getBrand();
        Mockito.when(brandRepository.findAll(any(Specification.class))).thenReturn(List.of(item));
    }

    @Test
    void findCustomerAllTest() {
        List<Item> itemList = clothService.findItem("2023-12-15-12.00.00", PRODUCT_ID, "1");
        Assertions.assertEquals(getItem(), itemList.get(0));
    }

    private Item getItem() {
        return Item.builder()
                .brandId(1L)
                .productId(Long.parseLong(PRODUCT_ID))
                .endDate(END_DATE)
                .startDate(START_DATE)
                .build();
    }

    private Brand getBrand(){
        return Brand.builder()
                .id(1L)
                .prices(List.of(Price.builder()
                        .productId(Long.parseLong(PRODUCT_ID))
                                .endDate(END_DATE)
                                .startDate(START_DATE)
                        .build()))
                .build();
    }

}
