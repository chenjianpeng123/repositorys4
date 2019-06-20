package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.mapper.UserMapper;
import cn.itcast.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 用户权限登陆
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userMapper.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()== 0 ? false :true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }
    public List<SimpleGrantedAuthority>  getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userMapper.findAll();
    }

    /**
     * 添加用户
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userMapper.save(userInfo);
    }

    /**
     * 根据id查询用户详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String id) throws Exception {
        return userMapper.finById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId)throws Exception {
        return userMapper.findOthreRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds)throws Exception {
    for (String roleId : roleIds){
        userMapper.addRoleToUser(userId,roleId);
    }
    }


}
