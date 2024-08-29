package com.po.springbootmall.service.impl;

import com.po.springbootmall.dao.OrderDao;
import com.po.springbootmall.dao.ProductDao;
import com.po.springbootmall.dto.BuyItem;
import com.po.springbootmall.dto.CreateOrderRequest;
import com.po.springbootmall.model.Product;
import com.po.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        Integer totalAmount = 0;

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

        }




        //創建訂單
        Integer orderId = OrderDao.createOrder();

        orderDao.createOrderItem();

        return orderId;
    }
}
