package com.mohamed265.marketertracker.service;

import java.util.List;

import com.mohamed265.marketertracker.entity.User;

public interface UserService {

	public boolean save(User user);

	public void update(User user);

	public List<User> getAllUsers();

	public void delete(User user);

	public User getUserById(int id);

	public User loginByEmail(String email, String password);

	public Boolean isUniqeEmail(String email);
}