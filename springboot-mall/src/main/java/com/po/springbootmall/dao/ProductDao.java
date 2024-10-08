package com.po.springbootmall.dao;

import com.po.springbootmall.constant.ProductCategory;
import com.po.springbootmall.dto.ProductQueryParams;
import com.po.springbootmall.dto.ProductRequest;
import com.po.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productRequest);

    Product getProductById(Integer prouductId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void updateStock(Integer productId, Integer stock);

    void deleteProductById(Integer productId);


}
