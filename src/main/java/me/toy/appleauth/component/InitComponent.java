package me.toy.appleauth.component;

import me.toy.appleauth.domain.Member;
import me.toy.appleauth.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by dongchul on 2019-09-16.
 */
@Component
public class InitComponent implements ApplicationRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member user = new Member("dongchul", passwordEncoder.encode("1234"), "USER");
        memberRepository.save(user);

        Member admin = new Member("admin", passwordEncoder.encode("1234"), "ADMIN");
        memberRepository.save(admin);
    }
}
