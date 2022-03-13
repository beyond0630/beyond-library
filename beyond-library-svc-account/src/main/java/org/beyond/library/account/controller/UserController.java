package org.beyond.library.account.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.beyond.library.account.entity.User;
import org.beyond.library.account.model.dto.UserDTO;
import org.beyond.library.account.model.vo.UserQueryVO;
import org.beyond.library.account.model.vo.UserUpdateVO;
import org.beyond.library.account.model.vo.UserVO;
import org.beyond.library.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String save(@Valid @RequestBody UserVO vO) {
        return userService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody UserUpdateVO vO) {
        userService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public Page<User> query(UserQueryVO vO) {
        return userService.query(vO);
    }
}
