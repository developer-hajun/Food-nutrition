package com.example.demo.Repository.Memeber;

import com.example.demo.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByMemberId(String id);
    List<String> findMemberMaterial(Long no);
}
