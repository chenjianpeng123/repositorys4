package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Orders;

import java.util.List;

public interface OrdersMapper {

    public List<Orders> findAll() throws Exception;
}
