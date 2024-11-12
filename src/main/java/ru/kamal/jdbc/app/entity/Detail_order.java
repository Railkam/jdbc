package ru.kamal.jdbc.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Detail_order {
    private long id;
    private String article;
    private String name;
    private long quantity;
    private long sum;
    private long order_id;
}
