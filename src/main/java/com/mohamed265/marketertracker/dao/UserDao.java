package com.mohamed265.marketertracker.dao;

import java.util.List;

import com.mohamed265.marketertracker.entity.User;

public interface UserDao {

	public void save(User user) throws Exception;

	public void update(User user);

	public List<User> getAllUsers();

	public void delete(User user);

	public User getUserById(int id);

	public User loginByEmail(String email, String password);
	
	public String selecColumntByIDNative(String columnName, Object columnValue);

}
