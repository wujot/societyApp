package login.register.society.controller;

import login.register.society.model.Friend;
import login.register.society.model.Invitation;
import login.register.society.model.User;
import login.register.society.repository.FriendRepository;
import login.register.society.repository.InvitationRepository;
import login.register.society.repository.UserRepository;
import login.register.society.repository.UserRoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private InvitationRepository invitationRepository;
    private FriendRepository friendRepository;

    private int userID;
    private User presentUser;
    private User searchUser;
    private List<User> searchUsers;

    public AccountController(UserRepository userRepository, UserRoleRepository userRoleRepository, InvitationRepository invitationRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.invitationRepository = invitationRepository;
        this.friendRepository = friendRepository;
    }

    @GetMapping("/account")
    public String account(Principal principal, Model model) {

        String userRole = userRoleRepository.findByUsername(principal.getName()).getRole();
        presentUser = userRepository.findByUsername(principal.getName());
        userID = presentUser.getId();

        List<Invitation> invitations = presentUser.getInvitations();
        List<Friend> friends = presentUser.getFriends();

        model.addAttribute("username", principal.getName());
        model.addAttribute("user", presentUser);
        model.addAttribute("invitations", invitations);
        model.addAttribute("friends", friends);

        if (searchUsers == null) {
            model.addAttribute("searchUsers", new ArrayList<>());
        } else {
            model.addAttribute("searchUsers", searchUsers);
        }

        if (userRole.equals("ROLE_ADMIN")) {
            return "admin";
        } else
            return "user";
    }

    @PostMapping("/editUser")
    public String editUser(User user) {
        Optional<User> optionalUser = userRepository.findById(userID);

        if (optionalUser.isPresent()) {
            presentUser = optionalUser.get();
            presentUser.setUsername(presentUser.getUsername());
            presentUser.setEmail(user.getEmail());
            presentUser.setPassword(presentUser.getPassword());
            userRepository.save(presentUser);
            return "user";
        } else

        return "index";
    }

    @PostMapping("/searchByUsername")
    public String search(User user, Model model) {
        searchUser = userRepository.findByUsername(user.getUsername());
        searchUsers = new ArrayList<>();
        searchUsers.add(searchUser);
        model.addAttribute("searchUsers", searchUsers);
        //model.addAttribute("searchUser", searchUser);
        return "redirect:/account";
    }

    @PostMapping("/searchAllUsers")
    public String searchAll(Model model) {
        searchUsers = userRepository.findAll();
        model.addAttribute("searchUsers", searchUsers);
        return "redirect:/account";
    }

    @GetMapping("/invite")
    public String invite(@RequestParam int id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Invitation invitation = new Invitation();
            invitation.setUser(user);
            invitation.setUsername(presentUser.getUsername());
            invitationRepository.save(invitation);

            return "redirect:/account";
        } else

        return "index";
    }

    @GetMapping("/acceptRequest")
    public String acceptRequest(@RequestParam int id) {
        Optional<Invitation> optionalInvitation = invitationRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(userID);
        Optional<User> optionalUser1 = userRepository.findById(2);

        if (optionalInvitation.isPresent() && optionalUser.isPresent() && optionalUser1.isPresent()) {
            Invitation invitation = optionalInvitation.get();
            User user = optionalUser.get();
            invitation.setAccepted(true);
            Friend friend = new Friend();
            friend.setUsername(invitation.getUsername());
            friend.setUser(user);
            invitationRepository.delete(invitation);
            friendRepository.save(friend);

            User user1 = userRepository.findByUsername(friend.getUsername());
            Friend friend1 = new Friend();
            friend1.setUsername(user.getUsername());
            friend1.setUser(user1);
            List<Friend> friends = user1.getFriends();
            friends.add(friend1);
            user1.setFriends(friends);
            friendRepository.save(friend1);

            return "redirect:/account";
        }else
            return "index";
    }

    @GetMapping("/declineRequest")
    public String declineRequest(@RequestParam int id) {
        Optional<Invitation> optionalInvitation = invitationRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(userID);

        if (optionalInvitation.isPresent() && optionalUser.isPresent()) {
            Invitation invitation = optionalInvitation.get();
            User user = optionalUser.get();
            invitation.setAccepted(false);
            invitationRepository.delete(invitation);

            return "redirect:/account";
        }else
            return "index";

    }

    @GetMapping("/logout")
    public String logout() {
        searchUsers = null;
        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String username) {
        User deleteUser = userRepository.findByUsername(username);
        userRepository.delete(deleteUser);
        searchUsers = null;
        return "redirect:/account";
    }
}
