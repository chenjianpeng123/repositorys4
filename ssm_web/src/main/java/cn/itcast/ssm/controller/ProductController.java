package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        List<Product> ps = productService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("productList", ps);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Product product) throws Exception {
     productService.save(product);
     return "redirect:findAll";
    }
}

