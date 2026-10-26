package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.entity.Member;
import com.example.library.repository.IssueRepository;
import com.example.library.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final IssueRepository issueRepository;

    public MemberService(MemberRepository memberRepository,
            IssueRepository issueRepository) {
        this.memberRepository = memberRepository;
        this.issueRepository = issueRepository;
    }

    // Add member
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    // Get all active members
    public List<Member> getAllMember() {
        return memberRepository.findByDeletedFalse();
    }

    // Get member by ID
    public Member getMemberById(int id) {
        return memberRepository.findById(id).orElse(null);
    }

    // Delete member only if there is no issue history
    public void deleteMember(int id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found."));
        if (member.isDeleted()) {
            throw new RuntimeException("Member is already deleted.");
        }
        if (issueRepository.existsByMember_IdAndStatusIgnoreCase(id, "ISSUED")) {
            throw new RuntimeException("Cannot delete member. Please return all issued books first.");
        }
        member.setDeleted(true);
        memberRepository.save(member);
    }
}
