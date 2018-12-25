package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.Comment;
import com.PKHS.airbnb.model.Order;
import com.PKHS.airbnb.model.RentalHouse;
import com.PKHS.airbnb.service.CommentService;
import com.PKHS.airbnb.service.CustomerRentalHouseService;
import com.PKHS.airbnb.service.OrderService;
import com.PKHS.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CustomerRentalHouseController extends GetIdUserController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerRentalHouseService customerRentalHouseService;

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
        Iterable<Comment> comments = this.commentService.findAll();
        ModelAndView modelAndView = new ModelAndView("customer_rental_house/viewDetail");
        modelAndView.addObject("house", house);
        modelAndView.addObject("order", new Order());
        modelAndView.addObject("cm", new Comment());
        modelAndView.addObject("comments",comments);
        return modelAndView;
    }

    @PostMapping("/house/action")
    public String accessOrder(@ModelAttribute("order")Order order) {
        System.out.println(order.getPrice());
        this.orderService.save(order);
        return "redirect:/order/my_order";
    }

    @PostMapping("/house/{id}/comment")
    public String createComment(@PathVariable("id") int id, @ModelAttribute("myName") int idn, @ModelAttribute("cm") Comment comment) {
        comment.setUser(this.userService.findById(idn));
        comment.setRentalHouse(this.customerRentalHouseService.findById(id));
        this.commentService.save(comment);
        return "redirect:/house/detail/{id}";
    }


    @GetMapping("/user/admin/manager-order-list")
    public String order_list(Model model) {
        model.addAttribute("orders", this.orderService.findAll());
        return "customer_rental_house/order-list";
    }

    @GetMapping("/order/order_detail/{id}")
    public String order_detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", this.orderService.findById(id));
        return "customer_rental_house/order-detail";
    }

    @GetMapping("/order/my_order")
    public String my_list_order(Model model) {
        model.addAttribute("myOrders", this.orderService.findAll());
        return "customer_rental_house/order-my-list";
    }

    @GetMapping("/order/destroy_order/{id}")
    public String order_destroy(@PathVariable("id") int id, RedirectAttributes attributes) {
        this.orderService.remove(id);
        attributes.addFlashAttribute("message", "Destroy order success");
        return "redirect:/order/my_order";
    }
}
