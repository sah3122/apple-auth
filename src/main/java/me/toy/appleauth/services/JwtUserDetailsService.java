package me.toy.appleauth.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import me.toy.appleauth.auth.UserDetailsAdaptor;
import me.toy.appleauth.utils.JWTUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by dongchul on 2019-09-10.
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        DecodedJWT decodedJWT = JWTUtil.tokenToJwt(token);

        if(decodedJWT == null) {
            throw new BadCredentialsException("Not used Token");
        }

        String id = decodedJWT.getClaim("id").asString();
        String role = decodedJWT.getClaim("role").asString();

        return new UserDetailsAdaptor(id, AuthorityUtils.createAuthorityList(role));
    }
}
