package com.teachmeskills.security.controller;

import com.teachmeskills.security.client.dto.AppUserDto;
import com.teachmeskills.security.dto.CreateUserDto;
import com.teachmeskills.security.client.dto.PageDto;
import com.teachmeskills.security.client.dto.UsersListDto;
import com.teachmeskills.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    protected String doGet(final Model model) {
        final org.springframework.security.core.userdetails.User authorized =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUserDto user = userService.getUser(authorized.getUsername());
        model.addAttribute("dto", user);
        return "reg";
    }

    @GetMapping
    protected String getUsers(@RequestParam(defaultValue = "1", name = "page", required = false) Integer pageNo,
                              @RequestParam(defaultValue = "5", name = "pageSize", required = false) Integer pageSize,
                              Model model) {
        model.addAttribute("createUserDto", new CreateUserDto());
        final PageDto pageDto = PageDto.builder().pageNo(pageNo).pageSize(pageSize).build();
        final UsersListDto dto = userService.getUsers(pageDto);
        model.addAttribute("users", dto.getListUsers());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", dto.getTotalPages());
        model.addAttribute("totalItems", dto.getTotalElements());
        return "reg";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createUser(@ModelAttribute("createUserDto") final CreateUserDto createUserDto, Model model) {
        userService.createUser(createUserDto);
        final PageDto pageDto = PageDto.builder().pageNo(1).pageSize(5).build();
        final UsersListDto dto = userService.getUsers(pageDto);
        model.addAttribute("users", dto.getListUsers());
        log.info("User {} registered", createUserDto.getLogin());
        return "redirect:/users";
    }
}
