package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/action")
    public String newOrder() {
        return "order/detail";
    }

    @PostMapping("/action")
    public String accessOrder(@ModelAttribute("order")Order order) {
        return "customer_rental_house/viewDetail";
    }
}
