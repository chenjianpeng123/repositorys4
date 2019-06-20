package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;

import java.awt.*;
import java.util.List;


public interface UserMapper {
    /**
     * 用户权限登陆
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.itcast.ssm.mapper.RoleMapper.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     *
     * @throws Exception
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据id查询用户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.itcast.ssm.mapper.RoleMapper.findRoleByUserId"))
    })
    public UserInfo finById(String id) throws Exception;

    /**
     * 查询没有角色的用户
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOthreRoles(String userId)throws Exception;

    /**
     * 添加用户角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId)throws Exception;



}
