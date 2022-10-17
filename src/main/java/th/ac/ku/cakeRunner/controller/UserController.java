package th.ac.ku.cakeRunner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.cakeRunner.model.User;
import th.ac.ku.cakeRunner.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService service;

    @GetMapping
    public String getUsers(Model model)
    {
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @GetMapping("/add")
    public String getAddForm(){
        return "user-register";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addUser(user);

        return "redirect:/user";
    }
}
