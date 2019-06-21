package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询所有的产品信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "5")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 添加产品信息
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public String save(Product product) throws Exception {
     productService.save(product);
     return "redirect:findAll";
    }
}

