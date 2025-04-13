package com.example.Inventory.Management.System.Example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping ("/Done")
    public String hello() {
        return "Done and Dusted";
    }
}
