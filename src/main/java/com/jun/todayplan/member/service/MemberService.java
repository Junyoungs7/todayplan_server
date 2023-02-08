package com.jun.todayplan.member.service;

import com.jun.todayplan.member.dto.SignUpDto;
import com.jun.todayplan.member.model.Authority;
import com.jun.todayplan.member.model.Member;
import com.jun.todayplan.member.repository.MemberRepository;
import com.jun.todayplan.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(SignUpDto sign){
        if (memberRepository.findOneWithAuthoritiesByUsername(sign.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(sign.getUsername())
                .password(passwordEncoder.encode(sign.getPassword()))
                .nickname(sign.getNickname())
                .authority(Collections.singleton(authority))
                .activated(true)
                .address(sign.getAddress())
                .age(sign.getAge())
                .build();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberWithAuthorities(String username){
        return memberRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMyMemberWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername);
    }
}
