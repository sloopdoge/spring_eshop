package springeshop.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springeshop.website.domain.Products;
import springeshop.website.repository.ProductsRepository;

@Controller
public class MainController {
    @Autowired
    private ProductsRepository productsRepository;

    /* Controller for HOME PAGE*/
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "home";
    }

    /* Controller for Products PAGE*/
    @GetMapping("/products")
    public String products(Model model) {
        Iterable<Products> products = productsRepository.findAll();
        model.addAttribute("title", "Products");
        model.addAttribute("products", products);
        return "products";
    }

    /* Controller for About PAGE*/
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About us");
        return "about";
    }

}
