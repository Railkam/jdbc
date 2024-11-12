package ru.kamal.jdbc.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.kamal.jdbc.app.entity.Detail_order;
import ru.kamal.jdbc.app.entity.Order;
import ru.kamal.jdbc.app.repository.Detail_orderRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Detail_orderController.class)

class Detail_orderControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @MockBean
    private Detail_orderRepository detail_orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void createDetail_order() throws Exception{
        Long id=5L;
        Long orders_id=2L;
        Detail_order detail_order = new Detail_order(5L,"12345","Молоко",123452L,567890L,2L);
        when(detail_orderRepository.createDetail_order(orders_id, detail_order)).thenReturn(id);
        mockMvc.perform(post("/order/{orders_id}/detail", orders_id, detail_order)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detail_order)))
                .andExpect(status().isOk());
    }
}