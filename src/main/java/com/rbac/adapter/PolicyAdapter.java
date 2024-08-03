package com.rbac.adapter;

import com.rbac.entity.Policy;
import com.rbac.repo.PolicyRepository;
import com.rbac.service.PolicyService;

import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PolicyAdapter implements Adapter {

    @Autowired
    private PolicyRepository policyRepository;
    
    @Autowired
    private PolicyService policyService;


    @Override
    public void loadPolicy(Model model) {
    	
    	List<Policy>  policyList = policyService.getAllPolicies();
    	
    	 for (Policy policy : policyList) {
             String line = String.format("p, %s, %s, %s, %s, %s",
                     policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
             loadPolicyLine(line, model);
         }
    	 
    	 
        List<Policy> policies = policyRepository.findAll();
        for (Policy policy : policies) {
            String line = String.format("p, %s, %s, %s, %s, %s",
                    policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
            loadPolicyLine(line, model);
        }
    }

    @Override
    public void savePolicy(Model model) {
        // Optionally implement this method if you want to save policies back to the database.
    }

    @Override
    public void addPolicy(String sec, String ptype, List<String> rule) {
        // Optionally implement this method if you want to add policies to the database.
    }

    @Override
    public void removePolicy(String sec, String ptype, List<String> rule) {
        // Optionally implement this method if you want to remove policies from the database.
    }

    @Override
    public void removeFilteredPolicy(String sec, String ptype, int fieldIndex, String... fieldValues) {
        // Optionally implement this method if you want to remove filtered policies from the database.
    }

    private void loadPolicyLine(String line, Model model) {
        String[] tokens = line.split(", ");
        String key = tokens[0];
        model.model.get("p").get(key).policy.add(Arrays.asList(tokens));
    }
}
