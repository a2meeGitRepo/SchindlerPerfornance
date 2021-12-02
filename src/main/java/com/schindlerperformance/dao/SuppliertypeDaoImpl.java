package com.schindlerperformance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.schindlerperformance.model.Suppliertype;
@Transactional
public class SuppliertypeDaoImpl extends GenericDaoImpl<Suppliertype, Integer> implements SuppliertypeDao{
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public boolean addSuppliertype(Suppliertype suppliertype) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(suppliertype);
		return false;
	}

	@Override
	public List<Suppliertype> getSuppliertypes() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Suppliertype.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Suppliertype> suppliertypeList = criteria.list();
		
		return suppliertypeList;
		
	}

	@Override
	public boolean deleteSuppliertype(int suppliertype_id) {
		// TODO Auto-generated method stub
		Suppliertype suppliertype = new Suppliertype();
		suppliertype.setSuppliertype_id(suppliertype_id);
		
		
		Query q=hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("update Suppliertype set deletes=:zero where suppliertype_id=:suppliertype_id");  
		q.setParameter("suppliertype_id",suppliertype.getSuppliertype_id()); 
		q.setParameter("zero",0);
		
		int status=q.executeUpdate();  
		System.out.println(status);  
		//session.delete(suppliertype);
		return false;
	}

	@Override
	public List<Suppliertype> getSuppliertypeByName(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Suppliertype.class);
		criteria.add(Restrictions.eq("name",name));
		List<Suppliertype> suppliertypeList = criteria.list();
		return suppliertypeList;

	}

	@Override
	public Suppliertype getSuppliertype(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Suppliertype.class);
		criteria.add(Restrictions.eq("name", name));
		Suppliertype suppliertype = (Suppliertype) criteria.uniqueResult();
		return suppliertype;
}

}
