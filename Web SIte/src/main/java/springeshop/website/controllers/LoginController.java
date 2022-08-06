package springeshop.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springeshop.website.dto.UserDTO;
import springeshop.website.service.UserService;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String newUser (Model model) {
        model.addAttribute("title", "Login page");
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError (Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
