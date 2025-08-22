package com.minhphung.taskmanagementsystem.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class AuthTestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN') or hasAuthority('UPDATE_USER')")
    public String userAccess() {
        return "User Access";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('MANAGER')")
    public String moderatorAccess() {
        return "Manager Access";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Access";
    }
}