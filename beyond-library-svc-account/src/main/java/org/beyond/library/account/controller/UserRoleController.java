package org.beyond.library.account.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.beyond.library.account.model.dto.UserRoleDTO;
import org.beyond.library.account.model.vo.UserRoleQueryVO;
import org.beyond.library.account.model.vo.UserRoleUpdateVO;
import org.beyond.library.account.model.vo.UserRoleVO;
import org.beyond.library.account.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public String save(@Valid @RequestBody UserRoleVO vO) {
        return userRoleService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userRoleService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserRoleUpdateVO vO) {
        userRoleService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserRoleDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userRoleService.getById(id);
    }

    @GetMapping
    public Page<UserRoleDTO> query(@Valid UserRoleQueryVO vO) {
        return userRoleService.query(vO);
    }
}
