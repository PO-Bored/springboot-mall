package com.po.springbootmall.dao;

import com.po.springbootmall.dto.ProductRequest;
import com.po.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer prouductId);

    Integer createProduct(ProductRequest productRequest);


}
