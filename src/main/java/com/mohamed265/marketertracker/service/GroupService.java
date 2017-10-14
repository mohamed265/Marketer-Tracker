package com.mohamed265.marketertracker.service;

import java.util.List;

import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.GroupUser;

public interface GroupService {

	public boolean save(Group group);

	public GroupUser save(GroupUser groupUser);

	public void update(Group group);

	public List<Group> getAllGroups();

	public List<GroupUser> getAllGroupUsers();

	public void delete(Group group);

	public void delete(GroupUser groupUser);

	public Group getGroupById(int id);

}