package com.rest.API.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@CrossOrigin
@ApiIgnore
public class IndexController  {
    @GetMapping("/")
    public String index(ModelMap Model) {
        return "index.html";
    }
    @GetMapping("/html/index")
    public String greeting(ModelMap Model) {
        return "index.html";
    }
    @GetMapping("/html/admin.html")
    public String admin(ModelMap Model) {
        return "admin.html";
    }
}
