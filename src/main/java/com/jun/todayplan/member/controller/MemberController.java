package com.jun.todayplan.member.controller;

import com.jun.todayplan.member.dto.SignUpDto;
import com.jun.todayplan.member.model.Member;
import com.jun.todayplan.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody SignUpDto sign){
        return ResponseEntity.ok(memberService.signUp(sign));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Member> getMyMemberInfo(){
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Member> getMemberInfo(@PathVariable String username){
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(username).get());
    }
}
