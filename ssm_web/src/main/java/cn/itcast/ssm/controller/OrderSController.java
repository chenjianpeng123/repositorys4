package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderSController {
    @Autowired
    private OrdersService ordersService;
   @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
       List<Orders> ordersList = ordersService.findAll();
       return null;
    }
}
