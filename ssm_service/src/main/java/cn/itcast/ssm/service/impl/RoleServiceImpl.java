package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.mapper.RoleMapper;
import cn.itcast.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() throws Exception {
        return roleMapper.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleMapper.save(role);
    }

    @Override
    public Role findById(String roleId)throws Exception {
        return roleMapper.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId)throws Exception {
        return roleMapper.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds)throws Exception {
        for (String permissionId : permissionIds) {
            roleMapper.addPermissionToRole(roleId,permissionId);
        }
    }
}
