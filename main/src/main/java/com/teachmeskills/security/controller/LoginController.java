package com.teachmeskills.security.controller;

import com.teachmeskills.security.config.jwt.Jwt;
import com.teachmeskills.security.dto.UserDto;
import com.teachmeskills.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final Jwt jwt;

    @GetMapping
    protected String doGet(final Model model) {
        model.addAttribute("dto", new UserDto());
        return "login";
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String doPost(@Valid @ModelAttribute("dto") final UserDto dto, HttpServletResponse response) {
        final String login = dto.getLogin();
        final String password = dto.getPassword();
        if (userService.verifyUser(login, password).isValid()) {
            final String token = jwt.generateToken(dto.getLogin());
            final Cookie cookie = new Cookie("myToken", token);
            response.addCookie(cookie);
            log.info("User {} logged in", login);
//            return "main";
            return "redirect:/documents";
        } else {
            final PrintWriter out = response.getWriter();
            out.println("Username or password error");
            return "login";
        }
    }
}
