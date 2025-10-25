package com.example.biblioteca;

import org.springframework.web.bind.annotation.GetMapping;

public class test {
    @GetMapping("/test")
    public String test() {
        return "API funcionando!";
    }

}
