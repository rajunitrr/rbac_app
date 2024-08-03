package com.rbac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.entity.Group;
import com.rbac.entity.User;
import com.rbac.repo.GroupRepository;
import com.rbac.repo.UserRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	public Group createGroup(Group group) {
		return groupRepository.save(group);
	}

	public Group updateGroup(Group group) {
		return groupRepository.save(group);
	}

	public void deleteGroup(Long groupId) {
		groupRepository.deleteById(groupId);
	}

	public Group addUserToGroup(Long groupId, Long userId) {
		Optional<Group> group = groupRepository.findById(groupId);
		Optional<User> user = userRepository.findById(userId);
		if (group.isPresent() && user.isPresent()) {
			group.get().getMembers().add(user.get());
			return groupRepository.save(group.get());
		}
		return null;
	}

	public Group removeUserFromGroup(Long groupId, Long userId) {
		Optional<Group> group = groupRepository.findById(groupId);
		Optional<User> user = userRepository.findById(userId);
		if (group.isPresent() && user.isPresent()) {
			group.get().getMembers().remove(user.get());
			return groupRepository.save(group.get());
		}
		return null;
	}

	public List<Group> getAllGroups() {
		return groupRepository.findAll();
	}
}