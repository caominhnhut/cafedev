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

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "ROLE")
public class Role implements GrantedAuthority {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	EUserRoleName name;

	public Role(){
		
	}
	
	public Role(Long id, EUserRoleName name) {
		this.id = id;
		this.name = name;
	}

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
