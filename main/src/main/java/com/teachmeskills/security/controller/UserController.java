package com.teachmeskills.security.controller;

import com.teachmeskills.security.client.dto.AppUserDto;
import com.teachmeskills.security.client.dto.UsersListDto;
import com.teachmeskills.security.client.dto.VerifyResultDto;
import com.teachmeskills.security.config.jwt.Jwt;
import com.teachmeskills.security.converter.UserConverter;
import com.teachmeskills.security.dto.AuthResultDto;
import com.teachmeskills.security.dto.CredentialsDto;
import com.teachmeskills.security.dto.UserDto;
import com.teachmeskills.security.model.User;
import com.teachmeskills.security.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@Tag(name = "registration", description = "Users API")
@RequestMapping("/registration")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final Jwt jwt;

    @GetMapping()
    protected String getUsers(@RequestParam(defaultValue = "1", name = "page", required = false) Integer pageNo,
                              @RequestParam(defaultValue = "5", name = "pageSize", required = false) Integer pageSize,
                              Model model) {
        final UsersListDto dto = userService.getUsers(pageNo, pageSize);
        List<AppUserDto> listUsers = dto.getListUsers();
        model.addAttribute("users", listUsers);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", dto.getTotalPages());
        model.addAttribute("totalItems", dto.getTotalElements());
        return "reg";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createUser(@Valid @ModelAttribute("dto") final AppUserDto dto) {
        userService.saveUser(dto);
        log.info("User {} registered", dto.getLogin());
        return "redirect:/registration";
    }


//    @PostMapping("/registration")
//    public AppUserDto registerUser(@RequestBody final CredentialsDto credentials) {
//        final AppUserDto user = userConverter.fromDto(credentials);
//        return userService.saveUser(user);
//    }

    @PostMapping("/auth")
    public ResponseEntity authorize(@RequestBody final CredentialsDto credentials) {
        final VerifyResultDto result = userService.verifyUser(credentials.getLogin(), credentials.getPassword());

        if (!result.isValid()) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid credentials");
        }

        final String token = jwt.generateToken(credentials.getLogin());

        return ResponseEntity
                .ok(new AuthResultDto(token));
    }

    @GetMapping("/me")
    public AppUserDto getCurrenUser() {
        final org.springframework.security.core.userdetails.User authorized =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUser(authorized.getUsername());
    }
}
