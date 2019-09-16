package me.toy.appleauth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.toy.appleauth.domain.Member;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * Created by dongchul on 2019-09-10.
 */
public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public AjaxAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, ObjectMapper objectMapper) {
        super(requiresAuthenticationRequestMatcher);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(isJson(request)) {
            Member member = objectMapper.readValue(request.getReader(), Member.class);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
            return getAuthenticationManager().authenticate(authentication);
        } else {
            throw new AccessDeniedException("Don't use content type for " + request.getContentType());
        }
    }

    private boolean isJson(HttpServletRequest request) {
        return MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType());
    }
}
