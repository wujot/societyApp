package login.register.society.controller;

import login.register.society.model.User;
import login.register.society.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class OtherController {

    private UserRepository userRepository;

    public OtherController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/other")
    public String other(@RequestParam String username, Model model) {

        User other = userRepository.findByUsername(username);
        model.addAttribute("user", other);
        return "other";
    }
}
