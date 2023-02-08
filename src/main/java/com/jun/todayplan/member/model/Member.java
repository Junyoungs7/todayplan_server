package com.jun.todayplan.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(length = 50, unique = true)
    private String username;

    @Column(length = 50)
    private String email;

    @JsonIgnore
    private String password;

    @Column(length = 50)
    private String nickname;
    private String address;
    private int age;

    @JsonIgnore
    private boolean activated;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;

    @Builder
    public Member(String username, String password, String nickname, String address, int age, Set<Authority> authority, boolean activated){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.age = age;
        this.authorities = authority;
        this.activated = activated;
    }


}
