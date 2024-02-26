package com.example.demo.Repository;

import com.example.demo.Entity.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByMemberId(String id);
}
