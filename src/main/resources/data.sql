CREATE TABLE IF NOT EXISTS public.order
(
    id              SERIAL PRIMARY KEY ,
    number_order    VARCHAR(20),
    sum_order       INTEGER,
    date_order      DATE DEFAULT now(),
    customer        TEXT,
    adress          TEXT,
    payment_type    VARCHAR(20),
    delivery_type   VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS detail_order (
    id              SERIAL,
    article         VARCHAR(20),
    name            VARCHAR(20),
    quantity        INTEGER,
    sum             INTEGER,
    order_id        INTEGER,
    PRIMARY KEY     (id, order_id),
    FOREIGN KEY     (order_id) REFERENCES public.order(id) ON DELETE CASCADE
)