package com.rbac.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.beans.RbacConstants;
import com.rbac.config.CasbinConfiguration;
import com.rbac.entity.Policy;

@Service
public class CasbinService {
    @Autowired
    private CasbinConfiguration casbinConfig;

    @Autowired
    private PolicyService policyService;

    public void loadPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        for (Policy policy : policies) {
            addPolicy(policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
        }
    }

    public boolean enforce(String sub, String dom, String obj, String act) {
        return casbinConfig.getEnforcer().enforce(sub, dom, obj, act);
    }
    
    public boolean addPolicy(String sub, String dom, String obj, String act, String eft) {
        return casbinConfig.addPolicy(sub, dom, obj, act, eft);
    }
    
    public void configurePolicies(Map<String, Object> request) {
        List<Map<String, String>> policies = (List<Map<String, String>>) request.get(RbacConstants.POLICIES);

        for (Map<String, String> policy : policies) {
            String user = policy.get(RbacConstants.USER);
            String domain = policy.get(RbacConstants.DOMAIN);
            String resource = policy.get(RbacConstants.RESOURCE);
            String action = policy.get(RbacConstants.ACTION);
            String effect = policy.get(RbacConstants.EFFECT);

            addPolicy(user, domain, resource, action, effect);
        }
    }
}
