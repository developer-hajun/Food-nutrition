package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;
    private String id;
    private String password;
    private String name;
    @CreationTimestamp
    private LocalDateTime localDateTime;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "member")
    private List<Material> materialList = new ArrayList<>();

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

}
