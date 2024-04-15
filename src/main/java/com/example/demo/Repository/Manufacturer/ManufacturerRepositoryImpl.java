package com.example.demo.Repository.Manufacturer;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.Manufacturer;
import com.example.demo.Entity.Material;
import com.example.demo.util.Querydsl4RepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.example.demo.Entity.QMember.member;
import static com.example.demo.Entity.QManufacturer.manufacturer;

@Repository
@Getter
public class ManufacturerRepositoryImpl extends Querydsl4RepositorySupport implements ManufacturerRepositoryCustom {
    private final JPAQueryFactory query;
    public ManufacturerRepositoryImpl(EntityManager em) {
        super(Manufacturer.class);
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Food> findManufacturerFoods(String name) {
        return select(manufacturer.foodList).from(manufacturer).where(manufacturer.name.eq(name)).fetchOne();
    }
}
