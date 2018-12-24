package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.Role;
import com.PKHS.airbnb.model.User;
import com.PKHS.airbnb.repository.RoleRepository;
import com.PKHS.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController extends GetIdUserController{
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("auth_roles")
    public Iterable<Role> listRoles() {
        return this.roleRepository.findAll();
    }

    @Autowired
    private UserService userService;


    @GetMapping("/admin/manager_user")
    public ModelAndView viewHome(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5) Pageable pageable) {
        Page<User> users;
        if (s.isPresent()) {
            users = userService.findByName(s.get(), pageable);
        } else {
            users = userService.fillAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }


    @GetMapping("/admin/delete/{id}")
    public String viewDelete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        User user = this.userService.findById(id);
        this.userService.remove(id);
        attributes.addFlashAttribute("message", "Delete User '" + user.getUsername() + "' successful");
        return "redirect:/user/admin/manager_user";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView viewEdit(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("user/edit");
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ModelAndView modelAndView = new ModelAndView("redirect:/user/admin/manager_user");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        } else {
            userService.save(user);
            modelAndView.addObject("message", "user updated successfully");
            return modelAndView;
        }
    }

    @GetMapping("/create")
    public ModelAndView viewCreate() {
        ModelAndView modelAndView = new ModelAndView("user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes attributes) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        ModelAndView modelAndView = new ModelAndView("user/create");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        } else {
            userService.save(user);
            modelAndView = new ModelAndView("redirect:/user/" + user.getId() + "/" + user.getUsername());
            attributes.addFlashAttribute("message", "New user created successfully");
            modelAndView.addObject("user", new User());
            return modelAndView;
        }
    }

    @GetMapping("/{id}/{username}")
    public String userDetail(@PathVariable("id") int id, Model model) {
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "user/view";
    }
}
