package com.schindlerperformance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schindlerperformance.model.Role;
import com.schindlerperformance.model.User;
import com.schindlerperformance.model.UserType;

public interface UserDao extends JpaRepository<User, Integer> {

	public List<User> getUserList();

	public boolean addUser(User user);

	public List<User> loginUser(User user);

	public void deleteUser(int user_id);

	public List<Role> roleUser();

	public List<UserType> getUserType();

	public List<User> findAl();

}
