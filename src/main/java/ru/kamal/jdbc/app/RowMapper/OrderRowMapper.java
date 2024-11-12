package ru.kamal.jdbc.app.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import ru.kamal.jdbc.app.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setNumber_order(rs.getString("number_order"));
        order.setSum_order(rs.getLong("sum_order"));
        order.setDate_order(LocalDate.from(rs.getTimestamp("date_order").toLocalDateTime()));
        order.setCustomer(rs.getString("customer"));
        order.setAdress(rs.getString("adress"));
        order.setPayment_type(rs.getString("payment_type"));
        order.setDelivery_type(rs.getString("delivery_type"));
        return order;
    }
}
