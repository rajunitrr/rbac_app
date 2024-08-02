package com.rbac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.entity.Application;
import com.rbac.repo.ApplicationRepository;

@Service
public class ApplicationService {
	@Autowired
	private ApplicationRepository applicationRepository;

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