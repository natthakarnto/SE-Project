package th.ac.ku.cakeRunner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.cakeRunner.model.Order;
import th.ac.ku.cakeRunner.service.OrderService;
import th.ac.ku.cakeRunner.service.CakesService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;
    private List<Order> cart = new ArrayList<>();
    @Autowired
    private CakesService cakesService;

    @GetMapping
    public String getCheckPage(Model model, Authentication authentication){
        model.addAttribute("ringslist", service.getDummy(authentication.getName()));

        return "orders";
    }

    @GetMapping("/add")
    public String getAddForm(){
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addOrder(order);

        return "redirect:/order";
    }

    @GetMapping("/list/edit/{id}")
    public String editPayment(@PathVariable UUID id, Model model,Authentication authentication){
        //System.out.println(service.getDummyByID(id).getCartList());
        Order set = service.getOneById(id);
        Calendar calendar = Calendar.getInstance();
        set.setPayment(calendar.getTime());
        set.setStatus("Payment");
        cakesService.updateCart(service.getDummyByID(id).getCartList());
        service.update(set);
        return "redirect:/order";
    }

    @GetMapping("/list/remove/{id}")
    public String removePayment(@PathVariable UUID id, Model model,Authentication authentication){
        Order set = service.getOneById(id);
        service.delete(set);
        return "redirect:/order";
    }

}
