package com.rbac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.entity.Role;
import com.rbac.repo.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	public void deleteRole(Long roleId) {
		roleRepository.deleteById(roleId);
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
}