package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 带分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
   @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true,defaultValue = "1")int page,@RequestParam(name = "size",required = true,defaultValue = "5")int size) throws Exception {
       ModelAndView modelAndView = new ModelAndView();
       List<Orders> ordersList = ordersService.findAll(page, size);
       //pageinfo是一个分页的bean
       PageInfo pageInfo = new PageInfo(ordersList);
       modelAndView.addObject("pageInfo",pageInfo);
       modelAndView.setViewName("orders-list");
       return modelAndView;
    }

    /**
     * 详情
     * @param ordersId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
