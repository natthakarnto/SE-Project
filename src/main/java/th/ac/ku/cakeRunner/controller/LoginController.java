package th.ac.ku.cakeRunner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.cakeRunner.model.User;
import th.ac.ku.cakeRunner.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;
    private User username;

    @RequestMapping("/register")
    public String getRegister(){
        return "register";
    }
    @PostMapping("/register")
    public String addUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs, @ModelAttribute("repassword") String repassword){

        if (!registerCheck(user,repassword)){
            redirectAttrs.addFlashAttribute("error","Please correct all information");
        }
        else {
            if (!exits(user)){
                redirectAttrs.addFlashAttribute("error","This email or Username has already exist");
            }
            else {
                if (!checkPassword(user.getPassword(),repassword)){
                    redirectAttrs.addFlashAttribute("error","Your password is not same");
                }
                else {
                    userService.addUser(user);
                    return "redirect:/login";
                }
            }
        }
        return "redirect:/register";
    }
    public boolean registerCheck(User user,String repassword){

        if ((user.getEmail().equals("")) || (user.getPassword().equals("")) || (user.getUsername().equals(""))
                || user.getName().equals("") || (repassword.equals(""))){
            return false;
        }
        return true;
    }
    public boolean checkPassword(String password, String repassword){
        if (!password.equals(repassword)){
            return false;
        }
        return true;
    }
    public boolean exits(User user){
        for (int i = 0; i < userService.getAll().size();i++){
            if (user.getEmail().equals(userService.getAll().get(i).getEmail()) || user.getUsername().equals(userService.getAll().get(i).getUsername())){
                return false;
            }
        }
        return true;
    }
}
