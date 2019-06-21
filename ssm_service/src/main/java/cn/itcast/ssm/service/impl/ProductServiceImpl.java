package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.mapper.ProductMapper;
import cn.itcast.ssm.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page, size);
        return productMapper.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productMapper.save(product);
    }
}
