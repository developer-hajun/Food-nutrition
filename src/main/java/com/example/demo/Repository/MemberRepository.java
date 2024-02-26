package com.example.demo.Repository;

import com.example.demo.Entity.Member;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberRepositoryCustom {
}
