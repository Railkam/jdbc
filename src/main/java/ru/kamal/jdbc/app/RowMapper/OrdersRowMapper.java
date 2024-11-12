package ru.kamal.jdbc.app.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import ru.kamal.jdbc.app.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersRowMapper implements RowMapper {
    @Override
    public List<Order> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Order> orders = new ArrayList<>();
        Order order;
        do {
            order = new Order();
            order.setId(rs.getLong("id"));
            order.setNumber_order(rs.getString("number_order"));
            order.setSum_order(rs.getLong("sum_order"));
            order.setDate_order(LocalDate.from(rs.getTimestamp("date_order").toLocalDateTime()));
            order.setCustomer(rs.getString("customer"));
            order.setAdress(rs.getString("adress"));
            order.setPayment_type(rs.getString("payment_type"));
            order.setDelivery_type(rs.getString("delivery_type"));
            orders.add(order);
        }
        while(rs.next());
        return orders;
    }
}
