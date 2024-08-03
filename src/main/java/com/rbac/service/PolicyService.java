package com.rbac.service;

import com.rbac.entity.Policy;
import com.rbac.repo.PolicyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    @Autowired
    private PolicyRepository repository;

    public Policy createPolicy(Policy policy) {
        return repository.save(policy);
    }

    public Policy updatePolicy(Long id, Policy policy) {
        policy.setId(id);
        return repository.save(policy);
    }

    public void deletePolicy(Long id) {
        repository.deleteById(id);
    }

    public Policy getPolicy(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Policy> getAllPolicies() {
        return repository.findAll();
    }
}
