package com.rbac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbac.beans.ApplicationResponse;
import com.rbac.beans.RbacConstants;
import com.rbac.beans.RbacResponse;
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
	public RbacResponse configureApplication(Map<String, Object> request) {

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

		RbacResponse rbacResponse = new RbacResponse();

		if (app != null) {
			rbacResponse.setStatus(RbacConstants.SUCCESS);
			rbacResponse.setErrorCode(RbacConstants.SUCCESS_STATUS_CODE);
			ApplicationResponse appRes = new ApplicationResponse();
			appRes.setApplicationId(app.getApplicationId());
			appRes.setApplicationName(app.getApplicationName());
			rbacResponse.setResponse(appRes);
			boolean addPolicyStatus = false;
			// add the policy info to CasbinConfiguration
			StringBuilder sb = new StringBuilder();
			for (Policy policy : application.getPolicies()) {
				addPolicyStatus = casbinService.addPolicy(policy.getSub(), policy.getDom(), policy.getObj(),
						policy.getAct(), policy.getEft());
				if (!addPolicyStatus) {
					rbacResponse.setErrorCode(RbacConstants.POLICY_FAILURE_CODE);
					rbacResponse.setErrorMessage("Few policies are not able to add to CasbinConfiguration");
				}
				System.out.println("AppService.configureApp adding policy to CasbinConfig for " + policy.getSub() + ", "
						+ policy.getDom() + ", " + policy.getObj() + ", " + policy.getAct() + ", " + policy.getEft()
						+ "-- status " + addPolicyStatus);
			}
		} else {
			rbacResponse.setStatus(RbacConstants.FAILED);
			rbacResponse.setErrorCode(RbacConstants.APP_FAILURE_CODE);
			rbacResponse.setErrorMessage("Unable to add application");
		}
		System.out.println("AppService.configureApp adding Application to DB " + rbacResponse);
		return rbacResponse;
	}

	public RbacResponse getAllApplications() {
		RbacResponse rbacResponse = new RbacResponse();
		List<Application> appList = applicationRepository.findAll();
		if( appList != null && appList.size() >0) {
			rbacResponse.setStatus(RbacConstants.SUCCESS);
			rbacResponse.setErrorCode(RbacConstants.SUCCESS_STATUS_CODE);
			rbacResponse.setResponse(appList);
		}else {
			rbacResponse.setStatus(RbacConstants.FAILED);
			rbacResponse.setErrorCode(RbacConstants.APP_FAILURE_CODE);
		}
		return rbacResponse;
	}

	public RbacResponse getApplicationById(Long id) {
		RbacResponse rbacResponse = new RbacResponse();
		Optional<Application> opt = applicationRepository.findById(id);
		Application app = null;
		if (opt != null && opt.isPresent()) {
			app = opt.get();
			if (app != null) {
				rbacResponse.setStatus(RbacConstants.SUCCESS);
				rbacResponse.setErrorCode(RbacConstants.SUCCESS_STATUS_CODE);
				rbacResponse.setResponse(app);
			}else {
				rbacResponse.setStatus(RbacConstants.FAILED);
				rbacResponse.setErrorCode(RbacConstants.APP_NOT_FOUND);
			}
		}else {
			rbacResponse.setStatus(RbacConstants.FAILED);
			rbacResponse.setErrorCode(RbacConstants.APP_FAILURE_CODE);
		} 
		return rbacResponse;
	}

	public Application createApplication(Application application) {
		return applicationRepository.save(application);
	}

	public RbacResponse deleteApplication(Long id) {
		RbacResponse rbacResponse = new RbacResponse();
		applicationRepository.deleteById(id);
		rbacResponse.setStatus(RbacConstants.SUCCESS);
		rbacResponse.setErrorCode(RbacConstants.SUCCESS_STATUS_CODE);
		return rbacResponse;
	}

	public RbacResponse  updateApplication(Long id, Application application) {
		RbacResponse rbacResponse = new RbacResponse();
		Application app = applicationRepository.save(application);
		rbacResponse.setStatus(RbacConstants.SUCCESS);
		rbacResponse.setErrorCode(RbacConstants.SUCCESS_STATUS_CODE);
		return rbacResponse;
	}
}