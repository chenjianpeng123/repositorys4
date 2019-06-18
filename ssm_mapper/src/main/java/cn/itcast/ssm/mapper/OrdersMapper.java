package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Member;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface OrdersMapper {

    public List<Orders> findAll() throws Exception;


    @Select("select * from orders where id =#{ordersId}")
     @Results({
             @Result(id =true,property = "id",column ="id"),
             @Result(column = "orderNum",property = "orderNum"),
             @Result(column = "orderTime",property = "orderTime"),
             @Result(column = "orderStatus",property = "orderStatus"),
             @Result(column = "peopleCount",property = "peopleCount"),
             @Result(column = "payType",property = "payType"),
             @Result(column = "orderDesc",property = "orderDesc"),
             @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "cn.itcast.ssm.mapper.ProductMapper.findById")),
             @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(select = "cn.itcast.ssm.mapper.MemberMapper.findById")),
             @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select = "cn.itcast.ssm.mapper.TravellerMapper.findById"))
     })
    public Orders findById(String id) throws Exception;

}
