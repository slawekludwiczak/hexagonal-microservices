package com.ludigi.priceflow.frontend.products;

import com.ludigi.priceflow.frontend.exception.NotFoundException;
import com.ludigi.priceflow.frontend.rest.client.OfferRestClient;
import com.ludigi.priceflow.frontend.rest.client.ProductRestClient;
import com.ludigi.priceflow.frontend.view.breadcrumbs.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
class ProductController {
    private final ProductRestClient productRestClient;
    private final OfferRestClient offerRestClient;

    public ProductController(ProductRestClient productRestClient, OfferRestClient offerRestClient) {
        this.productRestClient = productRestClient;
        this.offerRestClient = offerRestClient;
    }

    @GetMapping("")
    String getProducts(Model model) {
        List<ProductRestClient.ProductResponse> products = productRestClient.findAll();
        model.addAttribute("products", products);
        model.addAttribute("links", List.of(new Link("Products", "/products")));
        return "products/products";
    }

    @GetMapping("/{id}")
    String getProduct(@PathVariable("id") String id, Model model) {
        ProductRestClient.ProductResponse product = productRestClient
                .findById(id)
                .orElseThrow(NotFoundException::new);
        model.addAttribute("product", product);
        OfferRestClient.OffersResponse productOffers = offerRestClient.findOffersByProductId(id);
        model.addAttribute("offers", productOffers.offers());
        model.addAttribute("links", List.of(
                new Link("Products", "/products"),
                new Link(product.name(), "")
        ));
        return "products/product";
    }

    @PostMapping("/add")
    String addProduct(ProductRestClient.CreateProductRequest product) {
        productRestClient.addProduct(product);
        return "redirect:/products";
    }
}
