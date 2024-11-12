package ru.kamal.jdbc.app.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kamal.jdbc.app.entity.Detail_order;

import java.util.HashMap;
import java.util.Map;

@Repository
public class Detail_orderRepository {
    private final NamedParameterJdbcTemplate template;
    public Detail_orderRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Long createDetail_order (Long order_id, Detail_order detail_order) {
        String sql = "INSERT INTO public.detail_order" +
                "(article, name, quantity,sum, order_id) VALUES (:article, :name, :quantity, :sum, :order_id) RETURNING ID";
        Map<String, Object> map = new HashMap<>();
        map.put("article", detail_order.getArticle());
        map.put("name", detail_order.getName());
        map.put("quantity", detail_order.getQuantity());
        map.put("sum", detail_order.getSum());
        map.put("order_id", detail_order.getOrder_id());
        return template.queryForObject(sql, map, Long.class);
    }
}
