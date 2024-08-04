package com.rbac.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbac.beans.DBOperationResponse;
import com.rbac.beans.RbacResponse;
import com.rbac.entity.Application;
import com.rbac.service.ApplicationService;

@RestController
@RequestMapping("/api/v1")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;

	@PostMapping("/application")
	public RbacResponse configureApplication(@RequestBody Map<String, Object> request) {
		DBOperationResponse dbRes = applicationService.configureApplication(request);
		RbacResponse rbacResponse = new RbacResponse();
		rbacResponse.setStatus(dbRes.getStatus());
		rbacResponse.setErrorCode(dbRes.getErrorCode());
		rbacResponse.setResponse(dbRes.getErrorMessage());
		return rbacResponse; 
	}

	@GetMapping("/getallapps")
	public List<Application> getAllApplications() {
		return applicationService.getAllApplications();
	}

	@PostMapping("/create-app")
	public Application createApplication(@RequestBody Application application) {
		return applicationService.createApplication(application);
	}

	@DeleteMapping("/{id}")
	public void deleteApplication(@PathVariable Long id) {
		applicationService.deleteApplication(id);
	}

	@PutMapping("/{id}")
	public Application updateApplication(@PathVariable Long id, @RequestBody Application application) {
		return applicationService.updateApplication(id, application);
	}
}