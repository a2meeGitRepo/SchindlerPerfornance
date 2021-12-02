package com.schindlerperformance.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.schindlerperformance.model.Fpy;

public class FpyDaoImpl extends GenericDaoImpl<Fpy, Integer> implements FpyDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public synchronized boolean addFpy(Fpy fpy) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(fpy);
		return true;
	}

	@Override
	public synchronized List<Fpy> getFpys() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Fpy> fpyList = criteria.list();

		return fpyList;

	}

	@Override
	public boolean deleteFpy(int fpy_id) {
		// TODO Auto-generated method stub
		Fpy fpy = new Fpy();
		fpy.setFpy_id(fpy_id);

		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("delete from Fpy where fpy_id=:fpy_id");
		q.setParameter("fpy_id", fpy.getFpy_id());

		int status = q.executeUpdate();
		System.out.println(status);

		// session.delete(fpy);
		return false;
	}

	@Override
	public List<Fpy> getFpyByName(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		criteria.add(Restrictions.eq("name", name));
		List<Fpy> fpyList = criteria.list();
		return fpyList;

	}

	@Override
	public Fpy getFpy(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		criteria.add(Restrictions.eq("name", name));
		Fpy fpy = (Fpy) criteria.uniqueResult();
		return fpy;
	}

	@Override
	public synchronized List<Fpy> fpyUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType, String supplierYear) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class)
				.add(Restrictions.eq("fpy_suppliercode", supplierCode))
				.add(Restrictions.eq("fpy_suppliertype", suplierType))
				.add(Restrictions.eq("fpy_year", supplierYear));
		List<Fpy> fpy = criteria.list();
		return fpy;
	}


	
	@Override
	public List<Fpy> searchFpy1(Fpy fpy) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);

		if (fpy.getFpy_suppliername() != null) {
			criteria.add(Restrictions.eq("fpy_suppliername", fpy.getFpy_suppliername()));
		}
		if (fpy.getFpy_suppliertype() != null) {
			criteria.add(Restrictions.eq("fpy_suppliertype", fpy.getFpy_suppliertype()));
		}
		if (fpy.getFpy_ytd() !=  0.00) {
			criteria.add(Restrictions.eq("fpy_ytd", fpy.getFpy_ytd()));
		}
		if (fpy.getFpy_year() != null) {
			criteria.add(Restrictions.eq("fpy_year", fpy.getFpy_year()));
		}

		List<Fpy> fpyList = criteria.list();
		return fpyList;

	}
	
	/*--------------------unique serch data---------------------*/
	@Override
	public List<Fpy> getFpys1() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		criteria.add(Restrictions.eq("deletes", 1));

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("fpy_year"));
 
		criteria.setProjection(Projections.distinct(projList));

		List<Fpy> fpyList = criteria.list();

		return fpyList;

	}
	/*--------------------unique serch data---------------------*/

	@Override
	public List<Fpy> getHighPerForFpy(String year, String fpyPF, String supplierCode, String suplierType) {
		double conver = Double.parseDouble(fpyPF.substring(0, fpyPF.length() - 1));
		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		cr.add(Restrictions.ge("fpy_ytd", conver)).add(Restrictions.eq("fpy_suppliercode", supplierCode))
				.add(Restrictions.eq("fpy_suppliertype", suplierType)).add(Restrictions.eq("fpy_year", year));
		List<Fpy> list = cr.list();
		return list;
	}
	

	/*@Override
	public List<Fpy> getLowPerForFpy(String year, String fpyPF) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		String conver = fpyPF.substring(0, fpyPF.length() - 1);
		Query q = session.createQuery("FROM Fpy WHERE fpy_year=:fpy_year AND fpy_ytd  < :conver");
		q.setString("conver", conver);
		q.setParameter("fpy_year", year);
		List<Fpy> list = q.list();
		return list;
	}*/

	@Override
	public List<Fpy> getLowPerForFpy(String year, String fpyPF, String supplierCode, String suplierType) {
		double conver = Double.parseDouble(fpyPF.substring(0, fpyPF.length() - 1));
		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		cr.add(Restrictions.lt("fpy_ytd", conver)).add(Restrictions.eq("fpy_suppliercode", supplierCode))
				.add(Restrictions.eq("fpy_suppliertype", suplierType)).add(Restrictions.eq("fpy_year", year));
		List<Fpy> list = cr.list();
		return list;
	}
	
	
	@Override
	public List<Fpy> getAvg() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		String yr = Integer.toString(year);

		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		cr.add(Restrictions.eq("fpy_year", yr));
		cr.add(Restrictions.eq("fpy_suppliertype", "occ").ignoreCase());
		cr.setProjection(Projections.avg("fpy_ytd"));

		List<Fpy> list = cr.list();
		return list;
	}
	
	@Override
	public List<Fpy> getAvg4() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		String yr = Integer.toString(year);

		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		cr.add(Restrictions.eq("fpy_year", yr));
		cr.add(Restrictions.eq("fpy_suppliertype", "kw").ignoreCase());
		cr.setProjection(Projections.avg("fpy_ytd"));

		List<Fpy> list = cr.list();
		return list;
	}
	
	@Override
	public List<Object> getMonthly() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);

		String yr = Integer.toString(year);
		String m = Integer.toString(month);

		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		cr.add(Restrictions.eq("fpy_year", yr));

		if (month == 0) {

			cr.add(Restrictions.isNotNull("fpy_jan"));
		}

		if (month == 1) {

			cr.add(Restrictions.isNotNull("fpy_feb"));
		}

		if (month == 2) {

			cr.add(Restrictions.isNotNull("fpy_mar"));
		}

		if (month == 3) {

			cr.add(Restrictions.isNotNull("fpy_apr"));
		}

		if (month == 4) {

			cr.add(Restrictions.isNotNull("fpy_may"));
		}

		if (month == 5) {

			cr.add(Restrictions.isNotNull("fpy_june"));
		}

		if (month == 6) {

			cr.add(Restrictions.isNotNull("fpy_july"));
		}

		if (month == 7) {

			cr.add(Restrictions.isNotNull("fpy_aug"));
		}

		if (month == 8) {

			cr.add(Restrictions.isNotNull("fpy_sep"));
		}

		if (month == 9) {

			cr.add(Restrictions.isNotNull("fpy_oct"));
		}

		if (month == 10) {

			cr.add(Restrictions.isNotNull("fpy_nov"));
		}

		if (month == 11) {

			cr.add(Restrictions.isNotNull("fpy_dec"));
		}

		cr.add(Restrictions.eq("fpy_suppliertype", "kw").ignoreCase());

		Criteria cr1 = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Fpy.class);
		cr1.add(Restrictions.eq("fpy_year", yr));

		if (month == 0) {

			cr1.add(Restrictions.isNotNull("fpy_jan"));
		}

		if (month == 1) {

			cr1.add(Restrictions.isNotNull("fpy_feb"));
		}

		if (month == 2) {

			cr1.add(Restrictions.isNotNull("fpy_mar"));
		}

		if (month == 3) {
			cr1.add(Restrictions.isNotNull("fpy_apr"));
		}

		if (month == 4) {

			cr1.add(Restrictions.isNotNull("fpy_may"));
		}

		if (month == 5) {

			cr1.add(Restrictions.isNotNull("fpy_june"));
		}

		if (month == 6) {

			cr1.add(Restrictions.isNotNull("fpy_july"));
		}

		if (month == 7) {

			cr1.add(Restrictions.isNotNull("fpy_aug"));
		}

		if (month == 8) {

			cr1.add(Restrictions.isNotNull("fpy_sep"));
		}

		if (month == 9) {

			cr1.add(Restrictions.isNotNull("fpy_oct"));
		}

		if (month == 10) {

			cr1.add(Restrictions.isNotNull("fpy_nov"));
		}

		if (month == 11) {

			cr1.add(Restrictions.isNotNull("fpy_dec"));
		}

		cr1.add(Restrictions.eq("fpy_suppliertype", "occ").ignoreCase());

		int kw = cr.list().size();
		int occ = cr1.list().size();

		List<Object> arr = new ArrayList<>();

		arr.add(0, kw);
		arr.add(1, occ);

		return arr;

	}



}
