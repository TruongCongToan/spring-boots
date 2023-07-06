package com.example.security.service;

import com.example.security.entity.Role;
import com.example.security.entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
}
