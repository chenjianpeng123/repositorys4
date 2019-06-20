package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户查询
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username == 'tom'")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll";
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //根据id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据id查询可添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        modelAndView.addObject("user", userInfo);
        modelAndView.addObject("roleList", otherRoles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
   @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleIds)throws Exception{
   userService.addRoleToUser(userId,roleIds);
    return "redirect:findAll";

   }
}

