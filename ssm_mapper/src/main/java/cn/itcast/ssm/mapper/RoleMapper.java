package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据id查询出用户对应的角色
     * 还有角色对应的权限信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Select(" select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.itcast.ssm.mapper.PermissionMapper.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有角色
     *
     * @return
     */
    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc)values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId)throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId)throws Exception;
   @Insert("insert into role_permission (roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId)throws Exception;
}
