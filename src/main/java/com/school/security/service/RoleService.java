package com.school.security.service;

import com.school.security.entity.Role;
import com.school.security.reposatory.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAllRole() {
        return roleRepo.findAll();
    }

    public Role findRoleById(Long id) {
        return roleRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }

    public Role insertRole(Role role) {
        return roleRepo.save(role);
    }

    public Role findRoleByname(String name) {
        return roleRepo.findByName(name);
    }


}
