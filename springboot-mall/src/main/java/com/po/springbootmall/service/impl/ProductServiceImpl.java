package com.po.springbootmall.service.impl;

import com.po.springbootmall.dao.ProductDao;
import com.po.springbootmall.model.Product;
import com.po.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer prouductId) {
        return productDao.getProductById(prouductId);
    }
}
