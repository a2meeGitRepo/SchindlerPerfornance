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

import com.schindlerperformance.model.Sale;
@Transactional
public class SaleDaoImpl extends GenericDaoImpl<Sale, Integer> implements SaleDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	public boolean addSale(Sale sale) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(sale);
		return false;
	}

	public boolean updateSale(Sale sale) {
		hibernateTemplate.saveOrUpdate(sale);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Sale> getSale() {
		// TODO Auto-generated method stud
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Sale.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Sale> saleList = criteria.list();
		return saleList;
	}

	
	@Override
	public List<Sale> getSaleByName(String loc_description) {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Sale.class);
		criteria.add(Restrictions.eq("deletes", 1));
		criteria.add(Restrictions.eq("sale_no",loc_description));
		List<Sale> fsmaList = criteria.list();
		return fsmaList;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Sale> getSale1() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Sale.class);
		criteria.add(Restrictions.eq("deletes", 0));
		List<Sale> saleList = criteria.list();
		return saleList;
	}

	
	
	/*public boolean deleteSale(int sale_id) {
		// TODO Auto-generated method stub
		Sale sale = new Sale();
		sale.setSale_id(sale_id);
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.delete(sale);
		tx.commit();
		session.close();
		return false;
	}*/
	
	public boolean deleteSale(int sale_id) {
		// TODO Auto-generated method stub
		Sale tchanger = new Sale();
		tchanger.setSale_id(sale_id);
		Query q=hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("update Sale set deletes=:zero where sale_id=:sale_id");  
		q.setParameter("sale_id",tchanger.getSale_id()); 
		q.setParameter("zero",0);
		
		int status=q.executeUpdate();  
		System.out.println(status);  
		return false;
	}

	
	public boolean deleteSale1(int sale_id) {
		// TODO Auto-generated method stub
		Sale tchanger = new Sale();
		tchanger.setSale_id(sale_id);
		hibernateTemplate.delete(tchanger);
		return false;
	}

	public List<Sale> check(Sale sale) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Sale.class);
		criteria.add(Restrictions.eq("sales_id", sale.getSale_id()));
		List<Sale> checkList = criteria.list();
		return checkList;
	}

		
	/*@Override
	public List<Sale> getSaleByActive(Sale sale) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Sale.class);
		if (sale.getActive() == 0 ) {
			criteria.add(Restrictions.eq("active", 0)); 
		} else if ( sale.getActive() == 1) {
			criteria.add(Restrictions.eq("active", 1));
		}
		List<Sale> saleList = criteria.list();
		return saleList;
	}*/
	
	
	@Override
	public List<Sale> searchSale(Sale sale) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Sale.class);
		criteria.add(Restrictions.eq("acp", sale.getAcp()));
		List<Sale> saleList = criteria.list();
		return saleList;
	}
	
	
	
	@Override
	public List<Sale> searchSale1(Sale sale) {
		
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Sale.class);
		
		criteria.add(Restrictions.eq("deletes", 0));
		
		List<Sale> saleList =  null;
		
		String sd = sale.getStartDate();
		String ed = sale.getEndDate();
		
		System.out.println(sd);
		System.out.println(ed);
		
		criteria.add(Restrictions.between("disaptch_date", sd, ed));
		saleList = criteria.list();
	
		return saleList;
		
	}
	
		

}