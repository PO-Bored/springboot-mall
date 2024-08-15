package com.po.springbootmall.service;

import com.po.springbootmall.dto.ProductQueryParams;
import com.po.springbootmall.dto.ProductRequest;
import com.po.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer prouductId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}

