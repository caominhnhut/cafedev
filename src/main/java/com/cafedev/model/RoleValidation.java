package com.cafedev.model;

import java.util.ArrayList;
import java.util.List;

public class RoleValidation {

	private List<Role> validRoles = new ArrayList<Role>();
	
	private List<Role> invalidRoles = new ArrayList<Role>();

	public List<Role> getValidRoles() {
		return validRoles;
	}

	public List<Role> getInvalidRoles() {
		return invalidRoles;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder("[ ");
		for(Role role:invalidRoles){
			str.append(role.getName().name()+", ");
		}
		str.append(" ]");
		return str.toString();
	}
}
