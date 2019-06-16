package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.mapper.OrdersMapper;
import cn.itcast.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public List<Orders> findAll() throws Exception {
        return ordersMapper.findAll();
    }
}
