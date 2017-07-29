package com.mohamed265.marketertracker.service;

import java.util.Date;
import java.util.List;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.UserTracker;

public interface UserTrackerService {

	public boolean save(UserTracker UserTracker);

	public List<UserTracker> getAllUserTrackerByUser(User user);

	public List<UserTracker> getAllUserTrackerByDate(Date date);

	public List<UserTracker> getAllUserTrackerByUserAndDate(User user, Date date);

}