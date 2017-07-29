package com.mohamed265.marketertracker.dao;

import java.util.Date;
import java.util.List;

import com.mohamed265.marketertracker.entity.UserTracker;

public interface UserTrackerDao {

	public void save(UserTracker userTracker) throws Exception;

	public List<UserTracker> getAllUserTrackerByDate(Date date);

	public List<UserTracker> getAllUserTrackersByUser(int id);

	public List<UserTracker> getAllUserTrackersByUser(int id, Date date);

}
