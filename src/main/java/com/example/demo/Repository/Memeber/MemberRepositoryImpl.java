package com.example.demo.Repository.Memeber;

import com.example.demo.Entity.Material;
import com.example.demo.Entity.Member;
import com.example.demo.util.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Entity.QMaterial.material;
import static com.example.demo.Entity.QMember.member;


@Repository
@Getter
public class MemberRepositoryImpl extends Querydsl4RepositorySupport implements MemberRepositoryCustom{
    private final JPAQueryFactory query;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    public MemberRepositoryImpl(EntityManager em, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
        super(Member.class);
        this.query = new JPAQueryFactory(em);
        this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
    }

    @Override
    public Optional<Member> findByMemberId(String id) {
        return Optional.ofNullable(selectFrom(member).where(member.id.eq(id)).fetchOne());
    }
    public List<String> findMemberMaterial(Long no){
        List<Material> materialList = selectFrom(material).join(material.member, member).fetchJoin().where(member.no.eq(no)).fetch();
        for (Material material1 : materialList) {
            System.out.println(material1);
        }
        List<String> answer = new ArrayList<>();
        for(int i = 0; i< (materialList != null ? materialList.size() : 0); i++){
            Material material_value = materialList.get(i);
            answer.add(material_value.getName());
        }
        return answer;
    }
}
