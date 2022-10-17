package th.ac.ku.cakeRunner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    @RequestMapping("/contact")
    public String getContact(Model model) {

        return "contact";
    }
}