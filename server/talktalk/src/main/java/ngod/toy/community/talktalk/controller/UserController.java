package ngod.toy.community.talktalk.controller;

import jakarta.servlet.http.HttpSession;
import ngod.toy.community.talktalk.model.User;
import ngod.toy.community.talktalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    String userPage(){
        return "user";
    }

    @GetMapping("/user")
    @ResponseBody
    User getUser() {
        User user = new User();
        user.setUserName("seungho");
        Long userId = service.registerUser(user);
        return service.getUser(userId);
    }

    @PostMapping("/user/join")
    String joinUser(@RequestParam String userName, @RequestParam String userId, @RequestParam String password, Model model, HttpSession session) {
        Long joinUserId = service.registerUser(userName, userId, password);
        return "redirect:/";
    }

    @PostMapping("/user/login")
    String loginUser(
            @RequestParam String userId,
            @RequestParam String password,
            HttpSession session
    ){
        User user = service.login(userId,password);
        session.setAttribute("user",user);
        return "redirect:/home";
    }

}
