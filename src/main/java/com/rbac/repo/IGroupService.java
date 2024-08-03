package com.rbac.repo;

import java.util.List;

import com.rbac.entity.Group;

public interface IGroupService {

	public Group addGroup();

	public Group getGroupById(int groupId);

	public Group getGroupByName(String groupName);

	public List<Group> getGroups();

	public Group deleteGroupById(int groupId);

	public Group deleteGroupByName(String GroupName);
}
