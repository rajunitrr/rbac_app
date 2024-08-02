package com.rbac.config;

import java.io.IOException;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CasbinConfiguration {

	@Bean
	public Enforcer casbinEnforcer() {
		String modelPath = null;
		String policyPath = null;		
		try {
			modelPath = new ClassPathResource("acs.conf").getFile().getAbsolutePath();
			policyPath = new ClassPathResource("casbin_policy.csv").getFile().getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Enforcer(modelPath, policyPath);

	}
}