package com.example.demo.Repository.Memeber;

import com.example.demo.Entity.Member;
import com.example.demo.util.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.demo.Entity.QMember.member;


@Repository
@Getter
public class MemberRepositoryImpl extends Querydsl4RepositorySupport implements MemberRepositoryCustom{
    private final JPAQueryFactory query;
    public MemberRepositoryImpl(EntityManager em) {
        super(Member.class);
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Member> findByMemberId(String id) {
        return Optional.ofNullable(selectFrom(member).where(member.id.eq(id)).fetchOne());
    }
}
