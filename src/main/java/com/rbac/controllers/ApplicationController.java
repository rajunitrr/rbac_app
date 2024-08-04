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

import com.rbac.beans.ApplicationResponse;
import com.rbac.beans.DBOperationResponse;
import com.rbac.beans.RbacConstants;
import com.rbac.beans.RbacResponse;
import com.rbac.entity.Application;
import com.rbac.service.ApplicationService;

@RestController
@RequestMapping("/api/v1")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;

	@PostMapping("/create-app")
	public RbacResponse configureApplication(@RequestBody Map<String, Object> request) {
		RbacResponse rbacResponse = applicationService.configureApplication(request);
		return rbacResponse;
	}

	@GetMapping("/getallapps")
	public RbacResponse getAllApplications() {
		return applicationService.getAllApplications();
	}

	@GetMapping("/getappbyid/{id}")
	public RbacResponse getApplicationById(@PathVariable("id") Long id) {
		RbacResponse rbacResponse = applicationService.getApplicationById(id);		
		return rbacResponse;
	}

	@DeleteMapping("/{id}")
	public RbacResponse deleteApplication(@PathVariable("id") Long id) {
		return applicationService.deleteApplication(id);
	}

	@PutMapping("/{id}")
	public RbacResponse updateApplication(@PathVariable("id") Long id, @RequestBody Application application) {
		return applicationService.updateApplication(id, application);
	}
}