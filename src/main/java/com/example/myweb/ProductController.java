package com.example.myweb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
public class ProductController {
    private ProductRepository productRepository;
    @Autowired
    public ProductController(
            ProductRepository productRepository) {
        this.productRepository= productRepository;
    }

    @CrossOrigin(origins ="http://localhost:4200")   //这个用于解决跨域访问的问题
    @RequestMapping("/all")
    public List allProducts(Model model) {
        List<Product>productList =
                productRepository.findAll();
        return productList;
    }
}
