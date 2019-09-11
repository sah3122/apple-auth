package me.toy.appleauth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dongchul on 2019-09-11.
 */
@RestController
public class RoleController {
    @PostAuthorize("hasAnyAuthority('USER')")
    @GetMapping
    public ResponseEntity<String> user(Authentication authentication) {
        System.out.println("Authorities : " + authentication.getAuthorities().toString());
        System.out.println("Principal : " + authentication.getPrincipal());
        return new ResponseEntity<String>("User", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> admin(Authentication authentication) {
        System.out.println("Authorities : " + authentication.getAuthorities().toString());
        System.out.println("Principal : " + authentication.getPrincipal());
        return new ResponseEntity<String>("Admin", HttpStatus.OK);
    }
}
