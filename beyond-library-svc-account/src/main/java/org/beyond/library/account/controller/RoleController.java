package org.beyond.library.account.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.beyond.library.account.model.dto.RoleDTO;
import org.beyond.library.account.model.vo.RoleQueryVO;
import org.beyond.library.account.model.vo.RoleUpdateVO;
import org.beyond.library.account.model.vo.RoleVO;
import org.beyond.library.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public String save(@Valid @RequestBody RoleVO vO) {
        return roleService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        roleService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody RoleUpdateVO vO) {
        roleService.update(id, vO);
    }

    @GetMapping("/{id}")
    public RoleDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return roleService.getById(id);
    }

    @GetMapping
    public Page<RoleDTO> query(@Valid RoleQueryVO vO) {
        return roleService.query(vO);
    }
}
