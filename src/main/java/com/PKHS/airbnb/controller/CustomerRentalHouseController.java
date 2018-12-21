package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.Order;
import com.PKHS.airbnb.model.RentalHouse;
import com.PKHS.airbnb.model.User;
import com.PKHS.airbnb.service.CustomerRentalHouseService;
import com.PKHS.airbnb.service.OrderService;
import com.PKHS.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CustomerRentalHouseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerRentalHouseService customerRentalHouseService;

    //get id user login
    @ModelAttribute("myName")
    public Integer listUser(Principal principal) {
        if(principal != null) {
            String myUser = principal.getName();
            Iterable<User> users = this.userService.findAll();
            for (User user : users) {
                if (myUser.equals(user.getUsername())) {
                    return user.getId();
                }
            }
        }
        return null;
    }

    @GetMapping("/house")
    public ModelAndView showListHousesForm(@RequestParam("price")Optional<String> price, Pageable pageable) {
        Page<RentalHouse> houses;
        if(price.isPresent()) {
            houses = customerRentalHouseService.findAllByPriceContaining(price, pageable);
        } else {
            houses = customerRentalHouseService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("customer_rental_house/list");
        modelAndView.addObject("houses", houses);
        return modelAndView;
    }

    @GetMapping("/house/detail/{id}")
    public ModelAndView showHouseDetail(@PathVariable Integer id) {
        RentalHouse house = customerRentalHouseService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer_rental_house/viewDetail");
        modelAndView.addObject("house", house);
        modelAndView.addObject("order", new Order());
        return modelAndView;
    }

    @PostMapping("/house/action")
    public String accessOrder(@ModelAttribute("order")Order order) {
        System.out.println(order.getPrice());
        this.orderService.save(order);
        return "redirect:/house";
    }

    @GetMapping("/order_list")
    public String order_list(Model model) {
        model.addAttribute("orders", this.orderService.findAll());
        return "customer_rental_house/order-list";
    }

    @GetMapping("/order_detail/{id}")
    public String order_detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", this.orderService.findById(id));
        return "customer_rental_house/order-detail";
    }

}
