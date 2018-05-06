package com.example.myweb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")   //这个用于解决跨域访问的问题
public class ProductController {
    private ProductRepository productRepository;
    @Autowired
    public ProductController(
            ProductRepository productRepository) {
        this.productRepository= productRepository;
    }

    @RequestMapping("/all")
    public List allProducts(Model model) {
        List<Product> productList =
                productRepository.findAll();
        return productList;
    }

    @GetMapping("/productdetail/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        Product product =
                productRepository.getOne(id);
        return product;
    }

    @RequestMapping(value = "/updateproduct", method = RequestMethod.PUT)
    public void updateProduct(
            @RequestBody Product product) {
        Product updatedProduct = new Product();
        updatedProduct.setId(product.getId());
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        this.productRepository.save(updatedProduct);
    }

    @RequestMapping(value = "/createproduct", method = RequestMethod.PUT)
    public void createProduct(
            @RequestBody Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        this.productRepository.save(newProduct);
    }

    @RequestMapping(value = "/deleteproduct", method = RequestMethod.PUT)
    public void deleteProduct(
            @RequestBody Product product) {
        this.productRepository.deleteById(product.getId());
    }
}
