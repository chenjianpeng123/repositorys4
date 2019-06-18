package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.awt.*;


public interface UserMapper {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, column = "id",property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles",column = "id",javaType =java.util.List.class,many = @Many(select="cn.itcast.ssm.mapper.RoleMapper.findById"))
    })
    public UserInfo findByUsername(String username) throws Exception;
}
