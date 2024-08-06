package ngod.toy.community.talktalk.controller;

import jakarta.servlet.http.HttpSession;
import ngod.toy.community.talktalk.entity.Account;
import ngod.toy.community.talktalk.model.LoginUserForm;
import ngod.toy.community.talktalk.model.RegisterUserForm;
import ngod.toy.community.talktalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/api/user")
    @ResponseBody
    Account getUser() {
        Account account = new Account();
        account.setUserName("seungho");
        Long userId = service.registerUser(account);
        return service.getUser(userId);
    }

    @PostMapping("/api/user/join")
    @ResponseBody
    Account joinUser(@RequestBody RegisterUserForm form, Model model, HttpSession session) {
        Long joinUserId = service.registerUser(form.getUserName(), form.getUserId(), form.getPassword());
        return service.getUser(joinUserId);
    }

    @PostMapping("/api/user/login")
    @ResponseBody
    Account loginUser(
            @RequestBody LoginUserForm form,
            HttpSession session
    ){
        return service.login(form.getUserId(),form.getPassword());
    }

}
