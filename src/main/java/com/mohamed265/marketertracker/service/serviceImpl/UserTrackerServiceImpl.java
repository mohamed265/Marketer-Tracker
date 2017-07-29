package com.mohamed265.marketertracker.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohamed265.marketertracker.dao.UserTrackerDao;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.UserTracker;
import com.mohamed265.marketertracker.service.UserTrackerService;

@Service("UserTrackerService")
public class UserTrackerServiceImpl implements UserTrackerService {

	private final static Logger logger = Logger.getLogger(UserTrackerServiceImpl.class);

	@Autowired
	private UserTrackerDao userTrackerDao;

	@Override
	public boolean save(UserTracker userTracker) {

		try {
			userTrackerDao.save(userTracker);
			return true;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	@Override
	public List<UserTracker> getAllUserTrackerByUser(User user) {
		return userTrackerDao.getAllUserTrackersByUser(user.getId());

	}

	@Override
	public List<UserTracker> getAllUserTrackerByDate(Date date) {
		return userTrackerDao.getAllUserTrackerByDate(date);
	}

	@Override
	public List<UserTracker> getAllUserTrackerByUserAndDate(User user, Date date) {
		return userTrackerDao.getAllUserTrackersByUser(user.getId(), date);
	}

}
