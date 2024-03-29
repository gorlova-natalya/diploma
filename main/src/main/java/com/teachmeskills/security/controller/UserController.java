package com.teachmeskills.security.controller;

import com.teachmeskills.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.user.AppUserDto;
import org.example.common.dto.user.CreateUserDto;
import org.example.common.dto.user.PageDto;
import org.example.common.dto.user.UsersListDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    protected String getUsers(@RequestParam(defaultValue = "1", name = "page", required = false) Integer pageNo,
                              @RequestParam(defaultValue = "5", name = "pageSize", required = false) Integer pageSize,
                              Model model) {
        model.addAttribute("createUserDto", CreateUserDto.builder().build());
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
    protected String createUser(@Valid @ModelAttribute("createUserDto") final CreateUserDto createUserDto, Model model) {
        userService.createUser(createUserDto);
        final List<AppUserDto> dto = userService.getAllUsers();
        model.addAttribute("users", dto);
        log.info("User {} registered", createUserDto.getLogin());
        return "redirect:/users";
    }
}
