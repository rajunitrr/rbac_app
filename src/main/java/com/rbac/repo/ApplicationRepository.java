package com.rbac.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbac.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}