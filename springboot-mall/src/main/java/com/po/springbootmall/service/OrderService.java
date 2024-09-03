package com.po.springbootmall.service;

import com.po.springbootmall.dto.CreateOrderRequest;
import com.po.springbootmall.model.Order;

public interface OrderService   {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer usrId, CreateOrderRequest createOrderRequest);
}
