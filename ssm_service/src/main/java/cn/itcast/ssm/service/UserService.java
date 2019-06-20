package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    public List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 查询用户详情
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userId)throws Exception;

    void addRoleToUser(String userId, String[] roleIds)throws Exception;
}
