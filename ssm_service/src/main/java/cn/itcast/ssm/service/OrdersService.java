package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {


    public List<Orders> findAll() throws Exception;
}
