package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_no")
    private Long no;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_no")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getMaterialList().add(this);

    }
}
