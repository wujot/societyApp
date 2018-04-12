package login.register.society.controller;

import login.register.society.repository.UserRepository;
import login.register.society.repository.UserRoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AccountController {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public AccountController(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @GetMapping("/account")
    public String account(Principal principal) {

        String userRole = userRoleRepository.findByUsername(principal.getName()).getRole();

        if (userRole.equals("ROLE_ADMIN")) {
            return "admin";
        } else
            return "user";
    }
}
