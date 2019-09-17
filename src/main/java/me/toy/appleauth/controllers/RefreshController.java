package me.toy.appleauth.controllers;

import me.toy.appleauth.auth.UserDetailsAdaptor;
import me.toy.appleauth.utils.JWTUtil;
import me.toy.appleauth.utils.JwtInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/refresh")
public class RefreshController {

    @GetMapping
    public ResponseEntity<String> refreshToken(Authentication authentication) {
        UserDetails userDetails = new UserDetailsAdaptor(authentication.getPrincipal().toString(), new ArrayList<>(authentication.getAuthorities()));

        String token = JWTUtil.refreshToken(userDetails);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtInfo.HEADER_NAME, token);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();

    }
}
