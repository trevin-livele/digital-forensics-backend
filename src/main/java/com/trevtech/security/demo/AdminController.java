package com.trevtech.security.demo;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get(){
        return "Get:: Admin controller";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public String post(){
        return "POST:: Admin controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public String put(){
        return "PUT:: Admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public String delete(){
        return "DELETE:: Admin controller";
    }
}
