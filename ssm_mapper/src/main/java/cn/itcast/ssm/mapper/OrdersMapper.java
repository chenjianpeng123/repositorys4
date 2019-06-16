package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Orders;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface OrdersMapper {

    public List<Orders> findAll() throws Exception;

    public Orders findById(String id) throws Exception;

}
