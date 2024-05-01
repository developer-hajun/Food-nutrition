package com.example.demo.Repository.Food;

import com.example.demo.Entity.Food;
import com.example.demo.util.Querydsl4RepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.Entity.QFood.food;


@Repository
@Getter
public class FoodRepositoryImpl extends Querydsl4RepositorySupport implements FoodRepositoryCustom{
    private final JPAQueryFactory query;
    public FoodRepositoryImpl(EntityManager em) {
        super(Food.class);
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Food> ingredient_not_included(List<String> materials) {
        BooleanBuilder whereBuilder = new BooleanBuilder();
        System.out.println(1);
        for (String material : materials) {
            whereBuilder.and(food.materials.notLike("%" + material + "%"));
        } // 다중 like문
        System.out.println(2);
        return selectFrom(food).where(whereBuilder).fetch();
    }

}
