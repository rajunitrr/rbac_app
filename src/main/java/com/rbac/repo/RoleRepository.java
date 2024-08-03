package com.rbac.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbac.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}