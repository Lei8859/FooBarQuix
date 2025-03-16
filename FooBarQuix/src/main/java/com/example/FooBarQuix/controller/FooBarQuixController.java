package com.example.FooBarQuix.controller;

import com.example.FooBarQuix.sevice.FooBarQuixService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FooBarQuixController {

    private final FooBarQuixService fooBarQuixService;

    public FooBarQuixController(FooBarQuixService fooBarQuixService) {
        this.fooBarQuixService = fooBarQuixService;
    }

    @GetMapping("/transform/{number}")
    public String transform(@PathVariable int number) {
        return fooBarQuixService.transformerNombre(number);
    }
}