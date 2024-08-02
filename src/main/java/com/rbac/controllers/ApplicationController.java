package com.rbac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbac.entity.Application;
import com.rbac.service.ApplicationService;

@RestController
@RequestMapping("/api/v1")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;

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