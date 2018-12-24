package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.Category;
import com.PKHS.airbnb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategory(Model model) {
        Iterable<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/create-category")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/create-category")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes attributes) {
        this.categoryService.save(category);
        attributes.addFlashAttribute("message", "Add category successful");
        return "redirect:/category";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable("id") int id, Model model) {
        Category category = this.categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit-category")
    public String updateCategory(@ModelAttribute("category") Category category ,RedirectAttributes attributes) {
        this.categoryService.save(category);
        attributes.addFlashAttribute("message", "Update category successful");
        return "redirect:/category";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") int id, RedirectAttributes attributes) {
        this.categoryService.remove(id);
        attributes.addFlashAttribute("message", "Delete Category Successful");
        return "redirect:/category";
    }

    @GetMapping("/{id}/{name}")
    public ModelAndView listOfCategory(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("category/listPostIsCategory");
        Category category = this.categoryService.findById(id);
        modelAndView.addObject("category", category);
        modelAndView.addObject("categories", this.categoryService.findAll());
        return modelAndView;
    }
}
