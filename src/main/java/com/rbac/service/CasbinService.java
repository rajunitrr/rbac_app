package com.rbac.service;

import com.rbac.entity.Policy;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasbinService {
    @Autowired
    private Enforcer enforcer;

    @Autowired
    private PolicyService policyService;

    public void loadPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        for (Policy policy : policies) {
            addPolicy(policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
        }
    }

    public boolean enforce(String sub, String dom, String obj, String act) {
        return enforcer.enforce(sub, dom, obj, act);
    }
    
    public boolean addPolicy(String sub, String dom, String obj, String act, String eft) {
        return enforcer.addPolicy(sub, dom, obj, act, eft);
    }
}
