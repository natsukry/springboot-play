package com.natsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:natsukry
 * Date:2023-03-30
 * Time:20:45
 */
@RestController
public class NewsController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
