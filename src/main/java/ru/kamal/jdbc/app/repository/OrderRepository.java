package ru.kamal.jdbc.app.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.kamal.jdbc.app.RowMapper.OrderRowMapper;
import ru.kamal.jdbc.app.RowMapper.OrdersRowMapper;
import ru.kamal.jdbc.app.configuration.NumberServer;
import ru.kamal.jdbc.app.entity.Order;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class OrderRepository {
    private final NamedParameterJdbcTemplate template;
    NumberServer numberServer = new NumberServer();


    public OrderRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    public Long createOrder (Order order) throws IOException {
        String sql = "INSERT INTO public.order" +
         "(number_order, sum_order, customer,adress, payment_type, delivery_type) VALUES (:number_order, :sum_order, :customer, :adress, :payment_type, :delivery_type) RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("number_order", numberServer.NumberServer())
                .addValue("sum_order", order.getSum_order())
                .addValue("customer", order.getCustomer())
                .addValue("adress", order.getAdress())
                .addValue("payment_type", order.getPayment_type())
                .addValue("delivery_type", order.getDelivery_type());
        return template.queryForObject(sql, parameterSource, Long.class);
    }
    public Order getOrderById (Long id) {
        String sql = "SELECT * FROM public.order " +
                "WHERE public.order.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return template.queryForObject(sql, parameterSource, new OrderRowMapper());

    }

    public List<Order> getOrderByDateAndSum(LocalDate date_order, Long sum_order) {
        String sql = "SELECT * FROM public.order " +
                "WHERE public.order.date_order = :date_order AND " +
                "public.order.sum_order > :sum_order";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("date_order", date_order)
                .addValue("sum_order", sum_order);
        return (List<Order>) template.queryForObject(sql, parameterSource, new OrdersRowMapper());
    }

    public List<Order> getOrderByDateAndNotNameDetailOrder(LocalDate date_orderStart, LocalDate date_orderEnd, String detail_orderName) {
        String sql = "SELECT o.* " +
                "FROM public.order o " +
                "WHERE (o.id " +
                "NOT IN " +
                "( " +
                "SELECT d.order_id " +
                "FROM public.detail_order d " +
                "INNER JOIN public.order o ON d.order_id = o.id " +
                "WHERE " +
                "d.name IN (:detail_orderName))) AND " +
                "(o.date_order > :date_orderStart AND o.date_order < :date_orderEnd) ";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("detail_orderName", detail_orderName)
                .addValue("date_orderStart", date_orderStart)
                .addValue("date_orderEnd", date_orderEnd);
        return (List<Order>) template.query(sql, parameterSource, new OrdersRowMapper());
    }
}
