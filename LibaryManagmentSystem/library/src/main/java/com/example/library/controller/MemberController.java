package com.example.library.controller;

import com.example.library.entity.Member;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.service.MemberService;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // add member
    @PostMapping("/api/members")
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    // get al member
    @GetMapping("/api/members")
    public List<Member> getAllMember() {
        return memberService.getAllMember();
    }

    // update
    @GetMapping("/api/members/{id}")
    public Member getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    // delete
    @DeleteMapping("/api/members/{id}")
    public void deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
    }

}
