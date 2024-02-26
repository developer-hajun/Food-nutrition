package com.example.demo.Repository;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.Member;
import com.example.demo.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.Entity.QFood.food;


@Repository
@Getter
public class MemberRepositoryImpl extends Querydsl4RepositorySupport implements MemberRepositoryCustom{
    private final JPAQueryFactory query;
    public MemberRepositoryImpl(EntityManager em) {
        super(Member.class);
        this.query = new JPAQueryFactory(em);
    }
}
