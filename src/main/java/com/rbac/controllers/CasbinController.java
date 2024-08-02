package com.rbac.controllers;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CasbinController {

	@Autowired
	private Enforcer casbinEnforcer; // Inject Casbin Enforcer bean

	@GetMapping("/checkPermission")
	public String checkPermission(@RequestParam String user, @RequestParam String resource, @RequestParam String object,
			@RequestParam String action) {
		// Check permission using injected Casbin Enforcer
		boolean enforce = casbinEnforcer.enforce(user, resource, object, action);

		return enforce ? "Access Granted" : "Access Denied";
	}

	// Additional endpoints for managing policies, roles, etc.
}
