package com.mohamed265.marketertracker.dao;

import java.util.List;

import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.GroupUser;

public interface GroupDao {

	public void save(Group group) throws Exception;

	public void update(Group group);

	public List<Group> getAllGroups();

	public List<GroupUser> getAllGroupUsers();

	public List<GroupUser> getAllGroups(int groupId);

	public void delete(Group group);

	public Group getGroupById(int id);

	public String selecColumntByIDNative(String columnName, Object columnValue);

	public GroupUser save(GroupUser groupUser) throws Exception;

	public void deleteGroupUserByGroup(int groupUserId) throws Exception;

	public void delete(GroupUser groupUser);

	public void deleteAllGroupVideoByGroup(int groupId);

}
