package me.toy.appleauth.services;

import me.toy.appleauth.auth.UserDetailsAdaptor;
import me.toy.appleauth.domain.Member;
import me.toy.appleauth.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by dongchul on 2019-09-10.
 */
public class AjaxUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElse(null);

        if(member == null) {
            throw new UsernameNotFoundException(username + "라는 사용자가 없습니다.");
        }

        return new UserDetailsAdaptor(member, AuthorityUtils.createAuthorityList(member.getRole()));
    }
}
