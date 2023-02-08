package com.teachmeskills.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentsController {

    @GetMapping
    protected String doGet() {
        return "main";
    }
}
