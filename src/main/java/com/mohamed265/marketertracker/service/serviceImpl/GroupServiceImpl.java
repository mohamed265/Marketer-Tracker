package com.mohamed265.marketertracker.service.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohamed265.marketertracker.dao.GroupDao;
import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.GroupUser;
import com.mohamed265.marketertracker.service.GroupService;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {

	final private static Logger logger = Logger.getLogger(GroupServiceImpl.class);

	@Autowired
	private GroupDao groupDao;

	@Override
	public boolean save(Group group) {
		try {
			groupDao.save(group);
			return true;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	@Override
	public void update(Group group) {
		groupDao.update(group);
	}

	@Override
	public List<Group> getAllGroups() {
		return groupDao.getAllGroups();
	}

	@Override
	public void delete(Group group) {
		try {
			groupDao.deleteGroupUserByGroup(group.getId());
			groupDao.deleteAllGroupVideoByGroup(group.getId());
			groupDao.delete(group);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public Group getGroupById(int id) {
		return groupDao.getGroupById(id);
	}

	@Override
	public GroupUser save(GroupUser groupUser) {
		try {
			return groupDao.save(groupUser);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public void delete(GroupUser groupUser) {
		groupDao.delete(groupUser);
	}

	@Override
	public List<GroupUser> getAllGroupUsers() {
		return groupDao.getAllGroupUsers();
	}

}
