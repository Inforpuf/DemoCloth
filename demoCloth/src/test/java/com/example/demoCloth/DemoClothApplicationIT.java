package com.example.demoCloth;

import com.example.demoCloth.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoClothApplicationIT {

    @Autowired
    private MockMvc mockMvc;

    //petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test1() throws Exception {

        MvcResult result =
                this.mockMvc.perform(get("/cloths?date=2020-06-14-10.00.00&?productId=35455&?brandId=1"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Item> itemList = Arrays.stream(objectMapper.readValue(result.getResponse().getContentAsString(), Item[].class)).toList();

        itemList.forEach(item -> {
            Assertions.assertEquals(35455L, item.getProductId());
            Assertions.assertEquals(1L, item.getBrandId());
            Assertions.assertEquals(1, item.getPriceList());
            Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), item.getStartDate());
            Assertions.assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), item.getEndDate());
            Assertions.assertEquals("35.50", item.getPrice());
        });
    }

    // petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test2() throws Exception {

        MvcResult result =
                this.mockMvc.perform(get("/cloths?date=2020-06-14-16.00.00&?productId=35455&?brandId=1"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Item> itemList = Arrays.stream(objectMapper.readValue(result.getResponse().getContentAsString(), Item[].class)).toList();

        itemList.forEach(item -> {
            Assertions.assertEquals(35455L, item.getProductId());
            Assertions.assertEquals(1L, item.getBrandId());
            if (1 == item.getPriceList()) {
                Assertions.assertEquals(1, item.getPriceList());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), item.getStartDate());
                Assertions.assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), item.getEndDate());
                Assertions.assertEquals("35.50", item.getPrice());
            } else {
                Assertions.assertEquals(2, item.getPriceList());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 15, 0), item.getStartDate());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 18, 30), item.getEndDate());
                Assertions.assertEquals("25.45", item.getPrice());
            }
        });
    }

    // petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test3() throws Exception {

        MvcResult result =
                this.mockMvc.perform(get("/cloths?date=2020-06-14-21.00.00&?productId=35455&?brandId=1"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Item> itemList = Arrays.stream(objectMapper.readValue(result.getResponse().getContentAsString(), Item[].class)).toList();

        itemList.forEach(item -> {
            Assertions.assertEquals(35455L, item.getProductId());
            Assertions.assertEquals(1L, item.getBrandId());
            Assertions.assertEquals(1, item.getPriceList());
            Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), item.getStartDate());
            Assertions.assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), item.getEndDate());
            Assertions.assertEquals("35.50", item.getPrice());
        });
    }

    // petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test4() throws Exception {

        MvcResult result =
                this.mockMvc.perform(get("/cloths?date=2020-06-15-10.00.00&?productId=35455&?brandId=1"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Item> itemList = Arrays.stream(objectMapper.readValue(result.getResponse().getContentAsString(), Item[].class)).toList();

        itemList.forEach(item -> {
            Assertions.assertEquals(35455L, item.getProductId());
            Assertions.assertEquals(1L, item.getBrandId());
            if (1 == item.getPriceList()) {
                Assertions.assertEquals(1, item.getPriceList());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), item.getStartDate());
                Assertions.assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), item.getEndDate());
                Assertions.assertEquals("35.50", item.getPrice());
            } else {
                Assertions.assertEquals(3, item.getPriceList());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 15, 0, 0), item.getStartDate());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 15, 11, 0), item.getEndDate());
                Assertions.assertEquals("30.50", item.getPrice());
            }
        });
    }

    // petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test5() throws Exception {

        MvcResult result =
                this.mockMvc.perform(get("/cloths?date=2020-06-16-21.00.00&?productId=35455&?brandId=1"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Item> itemList = Arrays.stream(objectMapper.readValue(result.getResponse().getContentAsString(), Item[].class)).toList();

        itemList.forEach(item -> {
            Assertions.assertEquals(35455L, item.getProductId());
            Assertions.assertEquals(1L, item.getBrandId());
            if (1 == item.getPriceList()) {
                Assertions.assertEquals(1, item.getPriceList());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), item.getStartDate());
                Assertions.assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), item.getEndDate());
                Assertions.assertEquals("35.50", item.getPrice());
            } else {
                Assertions.assertEquals(4, item.getPriceList());
                Assertions.assertEquals(LocalDateTime.of(2020, 6, 15, 16, 0), item.getStartDate());
                Assertions.assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), item.getEndDate());
                Assertions.assertEquals("38.95", item.getPrice());
            }
        });
    }
}
