package org.beyond.library.account.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.beyond.library.account.model.dto.RolePermissionDTO;
import org.beyond.library.account.model.vo.RolePermissionQueryVO;
import org.beyond.library.account.model.vo.RolePermissionUpdateVO;
import org.beyond.library.account.model.vo.RolePermissionVO;
import org.beyond.library.account.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping
    public String save(@Valid @RequestBody RolePermissionVO vO) {
        return rolePermissionService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        rolePermissionService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody RolePermissionUpdateVO vO) {
        rolePermissionService.update(id, vO);
    }

    @GetMapping("/{id}")
    public RolePermissionDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return rolePermissionService.getById(id);
    }

    @GetMapping
    public Page<RolePermissionDTO> query(@Valid RolePermissionQueryVO vO) {
        return rolePermissionService.query(vO);
    }
}
