package com.example.demoCloth;

import com.example.demoCloth.controllers.ClothApiController;
import com.example.demoCloth.model.Item;
import com.example.demoCloth.services.ClothService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClothApiController.class)
public class ClothApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClothService service;

    @Test
    void checkControllerOK() throws Exception {
        Item item = Item.builder().build();
        List<Item> itemList = List.of(item);
        when(service.findItem(any(), any(), any())).thenReturn(itemList);
        MvcResult result =
        this.mockMvc.perform(get("/cloths?date=2020-06-14T10.00.00&productId=35455&brandId=1")).andDo(print()).andExpect(status().isOk()).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Item> resultList = Arrays.stream(objectMapper.readValue(result.getResponse().getContentAsString(), Item[].class)).toList();
        Assertions.assertThatList(resultList).isEqualTo(itemList);
    }

    @Test
    void checkControllerKO_NoParametersPassed() throws Exception {
        Item item = Item.builder().build();
        List<Item> itemList = List.of(item);
        when(service.findItem(any(), any(), any())).thenReturn(itemList);
        this.mockMvc.perform(get("/cloths")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    void checkControllerKO_WrongUrl() throws Exception {
        Item item = Item.builder().build();
        List<Item> itemList = List.of(item);
        when(service.findItem(any(), any(), any())).thenReturn(itemList);
        this.mockMvc.perform(get("/item")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    void checkControllerKO_WrongUrl2() throws Exception {
        Item item = Item.builder().build();
        List<Item> itemList = List.of(item);
        when(service.findItem(any(), any(), any())).thenReturn(itemList);
        this.mockMvc.perform(get("/cloths/item")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
    }


}
