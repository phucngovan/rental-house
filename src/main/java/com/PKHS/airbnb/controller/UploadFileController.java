package com.PKHS.airbnb.controller;

import com.PKHS.airbnb.model.Image;
import com.PKHS.airbnb.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
    public static String uploadFileDirectory = System.getProperty("user.dir") + "/src/main/resources/static/image";

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("/image_summernote")
    public String uploadImage(Model model) {
        model.addAttribute("image", new Image());
        return "upload/upload_image";
    }

    @PostMapping("/image_summernote")
    public String saveImage(@RequestParam("ffiles") MultipartFile[] files, Model model) {
        for (MultipartFile file: files) {
            Path fileNameAndPath = Paths.get(uploadFileDirectory, file.getOriginalFilename());
            try {
                Files.write(fileNameAndPath, file.getBytes());
                String file_name = file.getOriginalFilename();
                String file_link = "image/" + file_name;
                this.uploadFileService.save(new Image(file_name, file_link));
                model.addAttribute("message", "Successful");
                System.out.println(file_name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "upload/upload_image";
    }
}
