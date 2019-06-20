package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {
    @Select("select * from permission where id in (Select permissionId from role_permission where  roleId= #{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    /**
     * 查询权限
     *
     * @return
     * @throws Exception
     */
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
