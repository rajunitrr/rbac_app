package com.rbac.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbac.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
