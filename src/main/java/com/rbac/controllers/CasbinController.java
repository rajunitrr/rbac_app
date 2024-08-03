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
    public String checkPermission(
            @RequestParam(name = "user") String user,
            @RequestParam(name = "resource") String resource,
            @RequestParam(name = "object") String object,
            @RequestParam(name = "action") String action
            ,@RequestParam(name = "eft") String eft
            ) {

        // Check permission using injected Casbin Enforcer
        boolean enforce = casbinEnforcer.enforce(user, resource, object, action,eft);
        System.out.println("enforce: ["+user+", "+resource+", "+object+", "+action+", "+eft+" ] = "+enforce);
        return enforce ? "Access Granted" : "Access Denied";
    }

	// Additional endpoints for managing policies, roles, etc.
}
