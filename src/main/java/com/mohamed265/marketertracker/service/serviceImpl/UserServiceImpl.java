package com.mohamed265.marketertracker.service.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohamed265.marketertracker.dao.UserDao;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	final private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public boolean save(User user) {

		try {
			userDao.save(user);
			return true;
		} catch (Exception e) {
			logger.error(e);
		}

		return false;
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void delete(User user) {
		try {
			userDao.delete(user);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public User loginByEmail(String email, String password) {
		return userDao.loginByEmail(email, password);
	}

	@Override
	public Boolean isUniqeEmail(String email) {
		return (userDao.selecColumntByIDNative("email", email) == null ? true : false);
	}

}
