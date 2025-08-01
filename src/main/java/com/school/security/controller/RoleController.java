package com.school.security.controller;

import com.school.security.entity.Role;
import com.school.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/findAllRoles")
    public List<Role> findAllRoles() {
        return roleService.findAllRole();
    }

    @GetMapping("/findRoleById/{id}")
    public Role findRoleById(Long id) {
        return roleService.findRoleById(id);
    }

    @DeleteMapping("/deleteRole/{id}")
    public void deleteRole(Long id) {
        roleService.deleteRole(id);
    }

    @PostMapping("/insertRole")
    public Role insertRole(Role role) {
        return roleService.insertRole(role);
    }

    @GetMapping("/findRoleByName/{name}")
    public Role findRoleByName(@PathVariable String name) {
        return roleService.findRoleByname(name);
    }


}
