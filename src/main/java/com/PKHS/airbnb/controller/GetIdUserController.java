package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.User;
import com.PKHS.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class GetIdUserController {

    @Autowired
    private UserService userService;

    //get id user login
    @ModelAttribute("myName")
    public Integer listUser(Principal principal) {
        if (principal != null) {
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
}
