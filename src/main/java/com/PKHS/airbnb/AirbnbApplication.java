package com.PKHS.airbnb;

import com.PKHS.airbnb.controller.PostRentController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class AirbnbApplication {

    public static void main(String[] args)  {
        new File(PostRentController.uploadDirectory).mkdir();
        SpringApplication.run(AirbnbApplication.class, args);
    }

}
