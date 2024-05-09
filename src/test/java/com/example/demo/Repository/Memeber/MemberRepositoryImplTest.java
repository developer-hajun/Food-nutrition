package com.example.demo.Repository.Memeber;

import com.example.demo.Entity.Material;
import com.example.demo.Entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberRepositoryImplTest {
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    void findByMemberId() {
    }

    @Test
    @Rollback(value = false)
    void findMemberMaterial() {
        Material material = new Material("간장");
        Material material2 = new Material("소금");
        Member member = new Member("abc","efg","hajun");
        em.persist(member);
        material.setMember(member);
        material2.setMember(member);
        em.persist(material);
        em.persist(material2);
        em.clear();
        List<String> memberMaterial = memberRepository.findMemberMaterial(member.getNo());

    }
}