package com.po.springbootmall.service;

import com.po.springbootmall.dto.ProductRequest;
import com.po.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer prouductId);

    Integer createProduct(ProductRequest productRequest);
}

