package springeshop.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
//    @Autowired
//    private ItemsRepository itemsRepository;

    @RequestMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("title", "index");
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

//    /* Controller for HOME PAGE*/
//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("title", "Home");
//        return "home";
//    }
//
//    /* Controller for Products PAGE*/
//    @GetMapping("/products")
//    public String products(Model model) {
////        Iterable<Items> items = itemsRepository.findAll();
//        model.addAttribute("items", items);
//        return "products";
//    }
//
//    /* Controller for About PAGE*/
//    @GetMapping("/about")
//    public String about(Model model) {
//        model.addAttribute("title", "About us");
//        return "about";
//    }

}
