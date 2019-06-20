package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {


    public List<Permission> findAll(int page,int size) throws Exception;

    void save(Permission permission)throws Exception;
}
