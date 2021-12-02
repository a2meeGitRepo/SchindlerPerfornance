package com.schindlerperformance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.schindlerperformance.model.Role;
import com.schindlerperformance.model.User;
import com.schindlerperformance.model.UserType;
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(user);
		return false;
	}
	
	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<UserType> userTypeList = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(UserType.class).list();
//		for (UserType userType : userTypeList) {
//			System.out.println(userType.getUser_type_id() + " , "
//					+ userType.getUser_type_name() + ", "
//					+ userType.getActive());
//		}
	//	List<Role> roleList = session.createQuery("from Role").list();
		 Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(User.class);
	//	List<User> userList = session.createCriteria(User.class).list();
	//	List<User> userList = session.
		List<User> userList = criteria.list();
		return userList;
	}

	@Override
	public List<User> loginUser(User user) {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("mobile_no", user.getMobile_no()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		List<User> userList = criteria.list();
		System.out.println(userList.size());
		return userList;
	}

	@Override
	public void deleteUser(int user_id) {
		
		User user = new User();
		user.setUser_id(user_id);
		hibernateTemplate.delete(user);
		
	}

	@Override
	public List<Role> roleUser() {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Role.class);
		List<Role> role = criteria.list();
		return role;
		
	}
	
	@Override
	public List<UserType> getUserType() {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(UserType.class);
		List<UserType> userType = criteria.list();
		return userType;
		
	}

	

}
