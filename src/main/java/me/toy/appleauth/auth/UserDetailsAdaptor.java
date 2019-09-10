package me.toy.appleauth.auth;

import me.toy.appleauth.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by dongchul on 2019-09-10.
 */
public class UserDetailsAdaptor extends User {

    public UserDetailsAdaptor(String username, List<GrantedAuthority> authorities) {
        super(username, "", authorities);
    }

    public UserDetailsAdaptor(Member member, List<GrantedAuthority> authorities) {
        super(member.getId(), member.getPassword(), authorities);
    }
}
