package com.schindlerperformance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schindlerperformance.dao.GenericDao;
import com.schindlerperformance.dao.UserDao;
import com.schindlerperformance.model.Role;
import com.schindlerperformance.model.User;
import com.schindlerperformance.model.UserType;

public class UserServicesImpl  implements UserServices {

	@Autowired
	UserDao userDao;
	
	
	
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}
	
	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

	@Override
	public List<User> loginUser(User user) {
		// TODO Auto-generated method stub
		return userDao.loginUser(user);
	}

	@Override
	public void deleteUser(int user_id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(user_id);
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return userDao.roleUser();
	}

	@Override
	public List<UserType> getUserType() {
		// TODO Auto-generated method stub
		return userDao.getUserType();
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.findAl();
	}

	

}
