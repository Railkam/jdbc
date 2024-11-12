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
import ru.kamal.jdbc.app.repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderConrtoller.class)
class OrderConrtollerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createOrder() throws Exception {
        LocalDate localDate =  LocalDate.of(2024,11,12);
        Long id=1L;
        Order order = new Order(1L,"12345",123452L,localDate,"Иванов Иван Иванович","Москва","Наличные","Самовывоз");
        when(orderRepository.createOrder(order)).thenReturn(id);
        mockMvc.perform(post("/order", order)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderById() throws Exception {
        LocalDate localDate =  LocalDate.of(2024,11,12);
        Long id=1L;
        Order order = new Order(1L,"12345",123452L,localDate,"Иванов Иван Иванович","Москва","Наличные","Самовывоз");
        when(orderRepository.getOrderById(id)).thenReturn(order);
        mockMvc.perform(get("/order/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.number_order").value("12345"))
                .andExpect(jsonPath("$.sum_order").value(123452L))
                .andExpect(jsonPath("$.date_order").value("2024-11-12"))
                .andExpect(jsonPath("$.customer").value("Иванов Иван Иванович"))
                .andExpect(jsonPath("$.adress").value("Москва"))
                .andExpect(jsonPath("$.payment_type").value("Наличные"))
                .andExpect(jsonPath("$.delivery_type").value("Самовывоз"));
    }


    @Test
    void getOrderByDateAndSum() throws Exception {
        LocalDate localDate =  LocalDate.of(2024,11,12);
        Long sum_order=123451L;
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1L,"12345",123452L,LocalDate.of(2024,11,12),"Иванов Иван Иванович","Москва","Наличные","Самовывоз");
        orders.add(order);
        when(orderRepository.getOrderByDateAndSum(localDate,sum_order)).thenReturn(orders);
        mockMvc.perform(get("/order/{date_order}/{sum_order}",localDate, sum_order ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].number_order").value("12345"))
                .andExpect(jsonPath("$[0].sum_order").value(123452L))
                .andExpect(jsonPath("$[0].date_order").value("2024-11-12"))
                .andExpect(jsonPath("$[0].customer").value("Иванов Иван Иванович"))
                .andExpect(jsonPath("$[0].adress").value("Москва"))
                .andExpect(jsonPath("$[0].payment_type").value("Наличные"))
                .andExpect(jsonPath("$[0].delivery_type").value("Самовывоз"));
    }

    @Test
    void getOrderByDateAndNotNameDetailOrder() throws Exception {
        LocalDate localDate =  LocalDate.of(2024,11,11);
        LocalDate localDate2 =  LocalDate.of(2024,11,13);
        String name = "Молоко";
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1L,"123453",123452L,
                LocalDate.of(2024,11,11),"Иванов Иван Иванович",
                "Москва","Наличные","Самовывоз");
        /*Order order2 = new Order(2L,"123454",123452L,
                LocalDate.of(2024,11,12),"Иванов Иван Иванович",
                "Москва","Наличные","Самовывоз");
        Order order3 = new Order(2L,"123455",123452L,
                LocalDate.of(2024,11,13),"Иванов Иван Иванович",
                "Москва","Наличные","Самовывоз");*/
        orders.add(order);
       /* List<Detail_order> detailOrders = new ArrayList<>();
        Detail_order detail_order = new Detail_order(1L, "123","Молоко",234L,2345L,1L);
        Detail_order detail_order2 = new Detail_order(1L, "123","Молоко",234L,2345L,3L);
        Detail_order detail_order3 = new Detail_order(1L, "1234","Сметана",234L,2345L,2L);
        Detail_order detail_order4 = new Detail_order(2L, "1235","Кефир",234L,2345L,1L);
        Detail_order detail_order5 = new Detail_order(2L, "1234","Сметана",234L,2345L,3L);
        Detail_order detail_order6 = new Detail_order(2L, "1235","Кефир",234L,2345L,2L);*/
        when(orderRepository.getOrderByDateAndNotNameDetailOrder(localDate, localDate2, name)).thenReturn(orders);
        mockMvc.perform(get("/order/{date_orderStart}/{date_orderEnd}/{detail_orderName}",localDate, localDate2, name ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].number_order").value("123453"))
                .andExpect(jsonPath("$[0].sum_order").value(123452L))
                .andExpect(jsonPath("$[0].date_order").value("2024-11-11"))
                .andExpect(jsonPath("$[0].customer").value("Иванов Иван Иванович"))
                .andExpect(jsonPath("$[0].adress").value("Москва"))
                .andExpect(jsonPath("$[0].payment_type").value("Наличные"))
                .andExpect(jsonPath("$[0].delivery_type").value("Самовывоз"));
    }
}