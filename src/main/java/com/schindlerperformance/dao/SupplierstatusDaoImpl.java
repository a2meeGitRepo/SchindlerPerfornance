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

import com.schindlerperformance.model.Supplierstatus;
@Transactional
public class SupplierstatusDaoImpl extends GenericDaoImpl<Supplierstatus, Integer> implements SupplierstatusDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	@Override
	public synchronized boolean addSupplierstatus(Supplierstatus supplierstatus) {
		// TODO Auto-generated method stub
		System.out.println("IN Dao add Status ");
		hibernateTemplate.saveOrUpdate(supplierstatus);
		return false;
	}

	@Override
	public synchronized List<Supplierstatus> getSupplierstatuss() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplierstatus.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Supplierstatus> supplierstatusList = criteria.list();

		return supplierstatusList;

	}

	@Override
	public boolean deleteSupplierstatus(int supplierstatus_id) {
		// TODO Auto-generated method stub
		Supplierstatus supplierstatus = new Supplierstatus();
		supplierstatus.setSupplierstatus_id(supplierstatus_id);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("update Supplierstatus set deletes=:zero where supplierstatus_id=:supplierstatus_id");
		q.setParameter("supplierstatus_id", supplierstatus.getSupplierstatus_id());
		q.setParameter("zero", 0);

		int status = q.executeUpdate();
		System.out.println(status);

		// session.delete(supplierstatus);
		return false;
	}

	@Override
	public List<Supplierstatus> getSupplierstatusByName(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplierstatus.class);
		criteria.add(Restrictions.eq("name", name));
		List<Supplierstatus> supplierstatusList = criteria.list();
		return supplierstatusList;

	}

	@Override
	public Supplierstatus getSupplierstatus(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplierstatus.class);
		criteria.add(Restrictions.eq("name", name));
		Supplierstatus supplierstatus = (Supplierstatus) criteria.uniqueResult();
		return supplierstatus;
	}

	@Override
	public List<Supplierstatus> searchSupplierstatus1(Supplierstatus supplierstatus) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplierstatus.class);

		if (supplierstatus.getSupplierstatus_suppliername() != null) {
			criteria.add(
					Restrictions.eq("supplierstatus_suppliername", supplierstatus.getSupplierstatus_suppliername()));
		}
		if (supplierstatus.getSupplierstatus_suppliertype() != null) {
			criteria.add(
					Restrictions.eq("supplierstatus_suppliertype", supplierstatus.getSupplierstatus_suppliertype()));
		}
		if (supplierstatus.getSupplierstatus_year() != null) {
			criteria.add(Restrictions.eq("supplierstatus_year", supplierstatus.getSupplierstatus_year()));
		}
		if (supplierstatus.getSupplierstatus_otd() != null) {
			criteria.add(Restrictions.eq("supplierstatus_otd", supplierstatus.getSupplierstatus_otd()));
		}
		if (supplierstatus.getSupplierstatus_ppm() != null) {
			criteria.add(Restrictions.eq("supplierstatus_ppm", supplierstatus.getSupplierstatus_ppm()));
		}
		if (supplierstatus.getSupplierstatus_fpy() != null) {
			criteria.add(Restrictions.eq("supplierstatus_fpy", supplierstatus.getSupplierstatus_fpy()));
		}

		List<Supplierstatus> supplierstatusList = criteria.list();
		return supplierstatusList;

	}

	@Override
	public List<Supplierstatus> suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(String supplierCode,
			String supplerType, String supplerYear) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplierstatus.class)
				.add(Restrictions.eq("supplierstatus_suppliercode", supplierCode))
				.add(Restrictions.eq("supplierstatus_suppliertype", supplerType))
				.add(Restrictions.eq("supplierstatus_year", supplerYear));
		List<Supplierstatus> supplierstatus = criteria.list();
		return supplierstatus;
	}

	@Override
	public List<Supplierstatus> getSupplierstatusByNameTypeYear(String supplierCode, String supplierType, String year) {
		// TODO Auto-generated method stub
		System.out.println("IND GET LIST SUPPLIER STATUS ");
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplierstatus.class);
		criteria.add(Restrictions.eq("supplierstatus_suppliercode", supplierCode));
		criteria.add(Restrictions.eq("supplierstatus_suppliertype", supplierType));
		criteria.add(Restrictions.eq("supplierstatus_year", year));
		List<Supplierstatus> supplierstatusList = criteria.list();
		return supplierstatusList;
	}

}
