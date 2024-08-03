package com.rbac.controllers;

import java.util.List;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbac.entity.Policy;
import com.rbac.service.CasbinService;
import com.rbac.service.PolicyService;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    @Autowired
    private PolicyService service;

    @Autowired
	private CasbinService casbinSerivce;
	 
    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
    	System.out.println("Saving policy : "+policy);
        Policy savedPolicy = service.createPolicy(policy);
        System.out.println("Saved policy successfully. ");
        String line = String.format("p, %s, %s, %s, %s, %s",
                policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
        System.out.println("Adding policy : "+line);
        // since it is newly added policy, it will not be available in CasbinConfiguration object, we have add now.
        casbinSerivce.addPolicy( policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
        System.out.println("Added policy 22"); 
      
        return ResponseEntity.ok(savedPolicy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable("id") Long id, @RequestBody Policy policy) {
        Policy updatedPolicy = service.updatePolicy(id, policy);
        return ResponseEntity.ok(updatedPolicy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable("id") Long id) {
        service.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicy(@PathVariable("id") Long id) {
        Policy policy = service.getPolicy(id);
        return ResponseEntity.ok(policy);
    }

    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {
        List<Policy> policies = service.getAllPolicies();
        return ResponseEntity.ok(policies);
    }
}
