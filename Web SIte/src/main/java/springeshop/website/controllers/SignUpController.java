package springeshop.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springeshop.website.domain.Role;
import springeshop.website.domain.User;
import springeshop.website.repository.UserRepository;

import java.math.BigDecimal;

/* Controller for Sign up PAGE*/
@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/sign_up")
    public String sign_up(Model model) {
        model.addAttribute("title", "Sign up page");
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String addUser(User user,
                          @RequestParam(name = "fullname") String fullname,
                          @RequestParam(name = "email") String email,
                          @RequestParam(name = "phone") BigDecimal phone,
                          @RequestParam(name = "password") String password) {
        User userFromDb = userRepository.findUserByEmail(user.getEmail());

        if (userFromDb != null) {
            System.out.println("_ERROR_: " + "User exists!");
            return "sign_up";
        }

        User newUser = new User(fullname, email, phone, password);
        newUser.setStatus(true);
        newUser.setRole(Role.CLIENT);
        userRepository.save(newUser);

        return "redirect:/login";
    }
}
