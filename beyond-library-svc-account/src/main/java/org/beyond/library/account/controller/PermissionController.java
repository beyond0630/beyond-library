package org.beyond.library.account.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.beyond.library.account.model.dto.PermissionDTO;
import org.beyond.library.account.model.vo.PermissionQueryVO;
import org.beyond.library.account.model.vo.PermissionUpdateVO;
import org.beyond.library.account.model.vo.PermissionVO;
import org.beyond.library.account.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public String save(@Valid @RequestBody PermissionVO vO) {
        return permissionService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        permissionService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody PermissionUpdateVO vO) {
        permissionService.update(id, vO);
    }

    @GetMapping("/{id}")
    public PermissionDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return permissionService.getById(id);
    }

    @GetMapping
    public Page<PermissionDTO> query(@Valid PermissionQueryVO vO) {
        return permissionService.query(vO);
    }
}
