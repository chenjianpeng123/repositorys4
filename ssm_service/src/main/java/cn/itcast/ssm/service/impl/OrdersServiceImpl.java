package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.mapper.OrdersMapper;
import cn.itcast.ssm.service.OrdersService;
import com.github.pagehelper.PageHelper;
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
    public List<Orders> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return ordersMapper.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersMapper.findById(id);
    }
}
