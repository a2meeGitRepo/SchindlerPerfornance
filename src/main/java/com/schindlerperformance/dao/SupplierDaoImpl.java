package com.schindlerperformance.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.schindlerperformance.model.Supplier;
@Transactional
public class SupplierDaoImpl extends GenericDaoImpl<Supplier, Integer> implements SupplierDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public synchronized boolean addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(supplier);
		return false;
	}

	@Override
	public synchronized List<Supplier> getSuppliers() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplier.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Supplier> supplierList = criteria.list();

//		SQLQuery query = session
//				.createSQLQuery("set global max_connections=:con");  
//				query.setParameter("con",7000); 
//		
//		
//		int status=query.executeUpdate();  
		
		return supplierList;

	}

	/*--------------------unique serch data---------------------*/
	@Override
	public List<Supplier> getSuppliers1() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplier.class);
		criteria.add(Restrictions.eq("deletes", 1));

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("supplier_name"));

		criteria.setProjection(Projections.distinct(projList));

		List<Supplier> supplierList = criteria.list();

		return supplierList;

	}
	/*--------------------unique serch data---------------------*/

	@Override
	public boolean deleteSupplier(int supplier_id) {
		// TODO Auto-generated method stub
		Supplier supplier = new Supplier();
		supplier.setSupplier_id(supplier_id);

		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("update Supplier set deletes=:zero where supplier_id=:supplier_id");
		q.setParameter("supplier_id", supplier.getSupplier_id());
		q.setParameter("zero", 0);

		int status = q.executeUpdate();
		System.out.println(status);

		// session.delete(supplier);
		return false;
	}

	@Override
	public List<Supplier> getSupplierByName(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplier.class);
		criteria.add(Restrictions.eq("name", name));
		List<Supplier> supplierList = criteria.list();
		return supplierList;

	}

	@Override
	public Supplier getSupplier(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplier.class);
		criteria.add(Restrictions.eq("name", name));
		Supplier supplier = (Supplier) criteria.uniqueResult();
		return supplier;
	}

	@Override
	public synchronized List<Supplier> searchSupplier1(Supplier supplier) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplier.class);

		if (supplier.getSupplier_name() != null) {
			criteria.add(Restrictions.eq("supplier_name", supplier.getSupplier_name()));
		}
		if (supplier.getSupplier_type() != null) {
			criteria.add(Restrictions.eq("supplier_type", supplier.getSupplier_type()));
		}
		if (supplier.getSupplier_address() != null) {
			criteria.add(Restrictions.eq("supplier_address", supplier.getSupplier_address()));
		}

		if (supplier.getSupplier_region() != null) {
			criteria.add(Restrictions.eq("supplier_region", supplier.getSupplier_region()));
		}

		List<Supplier> supplierList = criteria.list();
		return supplierList;

	}
	/*@Override
	public synchronized List<Supplier> getSuppNameSuppTypeSuppCode(String supplierCode, String supplierName, String supplierType) {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Criteria cr= session.createCriteria(Supplier.class);
		
		Criterion suppCode = Restrictions.gt("supplier_code", supplierCode);
		Criterion suppName = Restrictions.gt("supplier_name", supplierName);
		Criterion suppType = Restrictions.gt("supplier_type", supplierType);

		LogicalExpression andExp1 = Restrictions.and(suppCode, suppName);
		LogicalExpression andExp2 = Restrictions.and(suppName, suppType);
		cr.add(andExp1).add(andExp2);
		List <Supplier> supplier =  cr.list();
		return supplier;
	}
*/
	@Override
	public synchronized List<Supplier> getSuppNameSuppTypeSuppCode(String supplierCode, String supplierName, String supplierType) {
		Criteria cr= hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplier.class)
				.add(Restrictions.eq("supplier_code", supplierCode))
				//.add(Restrictions.eq("supplier_name", supplierName))
				.add(Restrictions.eq("supplier_type", supplierType));
		List <Supplier> supplier =  cr.list();
		return supplier;
	}

	@Override
	public List<Supplier> getSuppilerBySuppCodeSuppType(String supplierCode, String supplierType) {
		Criteria cr= hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Supplier.class)
				.add(Restrictions.eq("supplier_code", supplierCode))
				.add(Restrictions.eq("supplier_type", supplierType));
		List <Supplier> supplier =  cr.list();
		return supplier;
	}
	
}
