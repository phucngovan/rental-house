package com.PKHS.airbnb.service;


import com.PKHS.airbnb.model.Order;

public interface OrderService {
    Iterable<Order> findAll();

    Order findById(Integer id);

    void save(Order order);

    void remove(Integer id);
}
