package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrdersService {

    public List<Orders> findAll(int page, int size) throws Exception;
    public Orders findById(String id) throws Exception;

}
