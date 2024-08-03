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

import com.rbac.entity.Group;
import com.rbac.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@PostMapping
	public Group createGroup(@RequestBody Group group) {
		return groupService.createGroup(group);
	}

	@PutMapping("/{id}")
	public Group updateGroup(@PathVariable Long id, @RequestBody Group group) {
		group.setId(id);
		return groupService.updateGroup(group);
	}

	@DeleteMapping("/{id}")
	public void deleteGroup(@PathVariable Long id) {
		groupService.deleteGroup(id);
	}

	@GetMapping
	public List<Group> getAllGroups() {
		return groupService.getAllGroups();
	}

	@PutMapping("/{groupId}/addUser/{userId}")
	public Group addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
		return groupService.addUserToGroup(groupId, userId);
	}

	@PutMapping("/{groupId}/removeUser/{userId}")
	public Group removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
		return groupService.removeUserFromGroup(groupId, userId);
	}
}
