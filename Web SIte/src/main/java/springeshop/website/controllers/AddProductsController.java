package springeshop.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springeshop.website.domain.Products;
import springeshop.website.repository.ProductsRepository;

import java.math.BigDecimal;

@Controller
public class AddProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    /* Controller for Page to add item in database*/
    @GetMapping("/products/add")
    public String products(Model model) {
        model.addAttribute("title", "Add products");
        return "products_add";
    }

    @PostMapping("/products/add")
    public String productsAdd(@RequestParam(name = "title") String title,
                          @RequestParam(name = "price") Double price,
                          @RequestParam(name = "numbers") Long numbers,
                          Model model) {
        Products item = new Products(title, price, numbers);
        productsRepository.save(item);
        return "products_add";
    }
}
