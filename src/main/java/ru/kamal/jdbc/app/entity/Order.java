package ru.kamal.jdbc.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private long id;
    private String number_order;
    private long sum_order;
    private LocalDate date_order;
    private String customer;
    private String adress;
    private String payment_type;
    private String delivery_type;


}
