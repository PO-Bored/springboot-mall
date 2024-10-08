package com.po.springbootmall.service;

import com.po.springbootmall.dto.CreateOrderRequest;
import com.po.springbootmall.dto.OrderQueryParams;
import com.po.springbootmall.model.Order;

import java.util.List;


public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById (Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
