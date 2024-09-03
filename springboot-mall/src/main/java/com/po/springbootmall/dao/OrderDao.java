package com.po.springbootmall.dao;

import com.po.springbootmall.dto.OrderQueryParams;
import com.po.springbootmall.model.Order;
import com.po.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer OrderId);

    List<OrderItem> getOrderItemsByOrderId(Integer OrderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
