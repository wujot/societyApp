package login.register.society.controller;

import login.register.society.model.User;
import login.register.society.repository.UserRepository;
import login.register.society.repository.UserRoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/loginForm")
    public String login() {

        return "login";
    }

    @PostMapping("/processLogin")
    public String logUser(User user, Model model) {
        model.addAttribute("user", user);
        return "redirect:/account";
    }

}
