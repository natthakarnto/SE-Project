package th.ac.ku.cakeRunner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.cakeRunner.model.Cakes;
import th.ac.ku.cakeRunner.service.CakesService;
import th.ac.ku.cakeRunner.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/cakes")
public class CakesController {

    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;


    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Cakes cakes = cakesService.getOneById(id);
        model.addAttribute("cakes", cakes);
        return "cakes-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Cakes cakes, Model model) {
        cakesService.update(cakes);
        return "redirect:/cakes";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication)
    {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("cakes", cakesService.getAll());
        return "cakes";
    }

    @GetMapping("/add")
    public String getAddForm(){
        return "cakes-add";
    }

    @PostMapping("/add")
    public String addCakes(@ModelAttribute Cakes cakes, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        cakesService.addCakes(cakes);

        return "redirect:/cakes";
    }


}
