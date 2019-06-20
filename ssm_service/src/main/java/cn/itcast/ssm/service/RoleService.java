package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有角色
     *
     * @return
     * @throws Exception
     */
    public List<Role> findAll() throws Exception;

    /**
     * 添加角色
     *
     * @param role
     * @throws Exception
     */
    void save(Role role) throws Exception;

    //根据roleId查询role
    Role findById(String roleId)throws Exception;

    //根据roleId查询可添加权限
    List<Permission> findOtherPermissions(String roleId)throws Exception;


    void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;
}
