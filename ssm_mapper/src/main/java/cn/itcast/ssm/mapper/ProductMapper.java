package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Product;

import java.util.List;

public interface ProductMapper {
   //查询
    public List<Product> findAll() throws Exception;
    //添加
    public void save(Product product) throws Exception;
}
