package com.rbac.config;

import java.io.IOException;
import java.util.List;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.rbac.entity.Policy;
import com.rbac.service.PolicyService;

@Configuration
public class CasbinConfiguration {

	@Autowired
	private PolicyService policyService;

	Enforcer enforcer;

	@Bean
	public Enforcer casbinEnforcer() {
		String modelPath = null;
		String policyPath = null;
		try {
			modelPath = new ClassPathResource("acs.conf").getFile().getAbsolutePath();
			// policyPath = new ClassPathResource("policy.csv").getFile().getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		enforcer = new Enforcer(modelPath);
		loadPolicies();
		return enforcer;
	}

	public void loadPolicies() {
		List<Policy> policies = policyService.getAllPolicies();
		for (Policy policy : policies) {
			addPolicy(policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
		}
	}

	public boolean addPolicy(String sub, String dom, String obj, String act, String eft) {
		return enforcer.addPolicy(sub, dom, obj, act, eft);
	}

	public Enforcer getEnforcer() {
		return enforcer;
	}
 
	
}