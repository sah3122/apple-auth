package me.toy.appleauth.auth;

import me.toy.appleauth.utils.JWTUtil;
import me.toy.appleauth.utils.JwtInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dongchul on 2019-09-10.
 */
@Component
public class BaseSecurityHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    //인증 성공시 Header에 토큰을 담아 보낸다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = new UserDetailsAdaptor(authentication.getPrincipal().toString(), new ArrayList<>(authentication.getAuthorities()));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setHeader(JwtInfo.HEADER_NAME, JWTUtil.createToken(userDetails));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
    }
}
