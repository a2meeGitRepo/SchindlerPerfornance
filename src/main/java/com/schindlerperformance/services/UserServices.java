package com.schindlerperformance.services;

import java.util.List;

import com.schindlerperformance.model.Role;
import com.schindlerperformance.model.User;
import com.schindlerperformance.model.UserType;

public interface UserServices  {

	public List<User> getUserList();
	public boolean addUser(User user);
	public List<User> loginUser(User user);
	public void deleteUser(int user_id);
	public List<Role> getRoles();
	public List<UserType> getUserType();
	public List<User> getAll();
	

}
