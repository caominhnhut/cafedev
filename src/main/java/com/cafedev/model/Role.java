package com.cafedev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.cafedev.enums.EUserRoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ROLE")
public class Role implements GrantedAuthority {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated( EnumType.STRING)
    @Column(name="name")
    EUserRoleName name;

    @Override
    public String getAuthority() {
        return name.name();
    }

    public void setName(EUserRoleName name) {
        this.name = name;
    }

    @JsonIgnore
    public EUserRoleName getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
