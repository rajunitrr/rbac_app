package com.rbac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbac.beans.DBOperationResponse;
import com.rbac.beans.RbacConstants;
import com.rbac.entity.Application;
import com.rbac.entity.Policy;
import com.rbac.repo.ApplicationRepository;

@Service
public class ApplicationService {
	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private CasbinService casbinService;

	@Transactional
	public DBOperationResponse configureApplication(Map<String, Object> request) {
		
		String applicationName = (String) request.get("applicationName");
		List<Map<String, String>> policiesData = (List<Map<String, String>>) request.get(RbacConstants.POLICIES);

		Application application = new Application();
		application.setApplicationName(applicationName);
		List<Policy> policyList = new ArrayList<Policy>();
		for (Map<String, String> policyData : policiesData) {
			Policy policy = new Policy();
			policy.setSub(policyData.get(RbacConstants.USER));
			policy.setDom(policyData.get(RbacConstants.DOMAIN));
			policy.setObj(policyData.get(RbacConstants.RESOURCE));
			policy.setAct(policyData.get(RbacConstants.ACTION));
			policy.setEft(policyData.get(RbacConstants.EFFECT));

			policyList.add(policy);
		}
		application.setPolicies(policyList);
		// save the data to DB
		Application app = applicationRepository.save(application);
		DBOperationResponse dbRes = new DBOperationResponse();
		if (app != null) {
			dbRes.setStatus(RbacConstants.SUCCESS);
			boolean addPolicyStatus = false;
			// add the policy info to CasbinConfiguration
			StringBuilder sb = new StringBuilder();
			for (Policy policy : application.getPolicies()) {
				addPolicyStatus = casbinService.addPolicy(policy.getSub(), policy.getDom(), policy.getObj(), policy.getAct(), policy.getEft());
				if(!addPolicyStatus) {
					dbRes.setErrorCode(RbacConstants.ADD_POLICY_FAILURE_CODE); 
					dbRes.setErrorMessage("Few policies are not able to add to CasbinConfiguration");
				}
				System.out.println("AppService.configureApp adding policy to CasbinConfig for "+policy.getSub()+", "+policy.getDom()
				+", "+policy.getObj()+", "+policy.getAct()+", "+policy.getEft()+"-- status "+addPolicyStatus);
			}
		}else {			
			dbRes.setStatus(RbacConstants.FAILED);
			dbRes.setErrorCode(RbacConstants.ADD_APPLICATION_FAILURE_CODE);
			dbRes.setErrorMessage("Unable to add application");
		}
		System.out.println("AppService.configureApp adding Application to DB "+dbRes); 
		return dbRes;
	}

	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

	public Application createApplication(Application application) {
		return applicationRepository.save(application);
	}

	public void deleteApplication(Long id) {
		applicationRepository.deleteById(id);
	}

	public Application updateApplication(Long id, Application application) {
		// Implement update logic based on your requirements
		return applicationRepository.save(application);
	}
}