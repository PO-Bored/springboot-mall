package com.po.springbootmall.dao.impl;

import com.po.springbootmall.constant.ProductCategory;
import com.po.springbootmall.dao.ProductDao;
import com.po.springbootmall.dto.ProductQueryParams;
import com.po.springbootmall.dto.ProductRequest;
import com.po.springbootmall.model.Product;


import com.po.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "SELECT COUNT(*) FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilterSql(sql,map,productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map,Integer.class);//第三個參數將返回值轉成Integer類型
        return total;
    }
    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id,product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date FROM product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilterSql(sql,map,productQueryParams);

        //排序
        sql+=" ORDER BY "+productQueryParams.getOrderBy() + " "+productQueryParams.getSort();

        //分頁
        sql+=" LIMIT :limit OFFSET :offset";;
        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map,new ProductRowMapper());

        return productList;

    }

    @Override
    public Product getProductById(Integer prouductId) {


        String sql = "SELECT product_id,product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", prouductId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(productList.size() > 0){
            return productList.get(0);
        }else{
            return null;
        }

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO product (product_name, category, image_url," +
                " price, stock, description, created_date, last_modified_date) " +
                "VALUES(:productName, :category, :imageUrl, :price, :stock," +
                " :description, :created_date, :last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl," +
                " price = :price, stock = :stock, description = :description, " +
                "last_modified_date = :last_modified_date WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("last_modified_date", new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        String sql = "Update product SET stock = :stock, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("stock", stock);
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql,map);


    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilterSql(String sql,Map<String,Object> map,ProductQueryParams productQueryParams){
        if (productQueryParams.getCategory() != null) {
            sql += " AND category = :category";//AND前面+空白鍵才不會跟SQL的1=1連在一起
            map.put("category", productQueryParams.getCategory().name());
        }

        if (productQueryParams.getSearch() != null) {
            sql += " AND product_name LIKE :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        return sql;
    }
}
