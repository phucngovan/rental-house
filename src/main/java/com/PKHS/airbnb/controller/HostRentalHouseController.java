package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.Category;
import com.PKHS.airbnb.model.Image;
import com.PKHS.airbnb.model.RentalHouse;
import com.PKHS.airbnb.model.User;
import com.PKHS.airbnb.service.CategoryService;
import com.PKHS.airbnb.service.HostRentalHouseService;
import com.PKHS.airbnb.service.UploadFileService;
import com.PKHS.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class HostRentalHouseController extends GetIdUserController {
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/image";

    @Autowired
    private HostRentalHouseService postRentService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadFileService uploadFileService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return this.categoryService.findAll();
    }

    @ModelAttribute("users")
    public Iterable<User> users() {
        return this.userService.findAll();
    }

    @GetMapping
    public ModelAndView listPostRents() {
        Iterable<RentalHouse> postRents = this.postRentService.findAll();
        ModelAndView modelAndView = new ModelAndView("host_rental_house/list");
        modelAndView.addObject("postRents", postRents);
        return modelAndView;
    }

    @GetMapping("/view-post/{id}")
    public ModelAndView viewPostRent(@PathVariable("id") int id) {
        RentalHouse rentalHouse = this.postRentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/host_rental_house/view-post");
        modelAndView.addObject("postRent", rentalHouse);
        return modelAndView;
    }

    @GetMapping("/delete-post-rent/{id}")
    public String deletePost(@PathVariable("id") int id, RedirectAttributes attributes) {
        if (this.postRentService.findById(id) != null) {
            this.postRentService.remove(id);
            attributes.addFlashAttribute("message", "Delete Successful");
            return "redirect:/post";
        }
        return "redirect:/errors-403";
    }

    @GetMapping("/edit-post-rent/{id}")
    public String editPost(@PathVariable("id") int id, Model model) {
        RentalHouse post = this.postRentService.findById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "host_rental_house/edit";
        }
        return "errors-403";
    }

    @PostMapping("/edit-post-rent")
    public String updatePost(@RequestParam("files") MultipartFile[] files, @ModelAttribute("post") RentalHouse post,
                             RedirectAttributes attributes) {
        List<Image> images = new ArrayList<Image>();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            try {
                if (!fileNameAndPath.equals("")) {
                    Files.write(fileNameAndPath, file.getBytes());
                    String file_name = file.getOriginalFilename();
                    String file_link = "image/" + file_name;
                    Image image = new Image(file_name, file_link);
                    image.setPost(post);
                    this.uploadFileService.save(image);
                    images.add(image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (images.size() > 0) {
            post.setImages(images);
        }
        this.postRentService.save(post);
        attributes.addFlashAttribute("message", "Update post successful");
        return "redirect:/post";
    }

    @GetMapping("/add-post-rent")
    public ModelAndView addPost() {
        RentalHouse post = new RentalHouse();
        ModelAndView modelAndView = new ModelAndView("host_rental_house/add");
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @PostMapping("/add-post-rent-new")
    public ModelAndView savePost(@RequestParam("files") MultipartFile[] files,
                           @ModelAttribute("post") RentalHouse post, @ModelAttribute("myName") Integer user_id,
                           RedirectAttributes attributes) {
        List<Image> images = new ArrayList<Image>();
        User user = this.userService.findById(user_id);
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            try {
                if (!fileNameAndPath.equals("")) {
                    Files.write(fileNameAndPath, file.getBytes());
                    String file_name = file.getOriginalFilename();
                    String file_link = "image/" + file_name;
                    Image image = new Image(file_name, file_link);
                    this.uploadFileService.save(image);
                    images.add(image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getOriginalFilename());
        }
        if (images.size() > 0) {
            post.setImages(images);
        }
        post.setUser(user);
        this.postRentService.save(post);
        attributes.addFlashAttribute("message", "Create post successfull");
        ModelAndView modelAndView = new ModelAndView("redirect:/post");
        return modelAndView;
    }
}
