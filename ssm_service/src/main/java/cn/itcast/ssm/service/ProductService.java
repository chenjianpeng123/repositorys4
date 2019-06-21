package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Product;

import java.util.List;


public interface ProductService {

    public List<Product> findAll(Integer page,Integer size) throws Exception;

    public void save(Product product) throws Exception;
}
