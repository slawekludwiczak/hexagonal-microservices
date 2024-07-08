package com.ludigi.priceflow.frontend.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRestClient productRestClient;

    public ProductController(ProductRestClient productRestClient) {
        this.productRestClient = productRestClient;
    }

    @GetMapping("")
    String getProducts(Model model) {
        List<ProductRestClient.ProductResponse> products = productRestClient.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/add")
    @ResponseBody
    String addProduct(ProductRestClient.CreateProductRequest product) {
        productRestClient.addProduct(product);
        return "added";
    }
}
