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
import org.springframework.transaction.annotation.Transactional;

import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Ppm;
@Transactional
public class OtdDaoImpl extends GenericDaoImpl<Otd, Integer> implements OtdDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	static final Logger logger = Logger.getLogger(OtdDaoImpl.class);

	@Override
	public synchronized boolean addOtd(Otd otd) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(otd);
		return true;
	}

	@Override
	public synchronized List<Otd> getOtds() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Otd> otdList = criteria.list();

		return otdList;

	}

	@Override
	public boolean deleteOtd(int otd_id) {
		// TODO Auto-generated method stub
		Otd otd = new Otd();
		otd.setOtd_id(otd_id);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("delete from Otd where otd_id=:otd_id");
		q.setParameter("otd_id", otd.getOtd_id());
		
		int status = q.executeUpdate();
		System.out.println(status);

		// session.delete(otd);
		return false;
		
	}

	@Override
	public List<Otd> getOtdByName(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		criteria.add(Restrictions.eq("name", name));
		List<Otd> otdList = criteria.list();
		return otdList;

	}

	@Override
	public Otd getOtd(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		criteria.add(Restrictions.eq("name", name));
		Otd otd = (Otd) criteria.uniqueResult();
		return otd;
	}

	@Override
	public synchronized List<Otd> otdUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType,
			String supplierYear) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class).add(Restrictions.eq("otd_suppliercode", supplierCode))
				.add(Restrictions.eq("otd_suppliertype", suplierType)).add(Restrictions.eq("otd_year", supplierYear));
		List<Otd> otd = criteria.list();
		return otd;
	}

	

	@Override
	public List<Otd> searchOtd1(Otd otd) {

		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);

		if (otd.getOtd_suppliername() != null) {
			criteria.add(Restrictions.eq("otd_suppliername", otd.getOtd_suppliername()));
		}
		if (otd.getOtd_suppliertype() != null) {
			criteria.add(Restrictions.eq("otd_suppliertype", otd.getOtd_suppliertype()));
		}
		if (otd.getOtd_ytd() != null) {
			criteria.add(Restrictions.eq("otd_ytd", otd.getOtd_ytd()));
		}
		if (otd.getOtd_year() != null) {
			criteria.add(Restrictions.eq("otd_year", otd.getOtd_year()));
		}

		List<Otd> otdList = criteria.list();
		return otdList;

	}

	/*--------------------unique serch data---------------------*/
	@Override
	public List<Otd> getOtds1() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		criteria.add(Restrictions.eq("deletes", 1));

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("otd_year"));

		criteria.setProjection(Projections.distinct(projList));

		List<Otd> otdList = criteria.list();

		return otdList;

	}
	/*--------------------unique serch data---------------------*/

	@Override
	public synchronized List<Otd> getHighPerForOtd(String year, String otdPero, String supplierCode,
			String suplierType) {
		String converConv = otdPero.substring(0, otdPero.length() - 1);
		System.out.println("CONVERTED OTD    ====  "+converConv);
		System.out.println("CONVERTED YEAR    ====  "+year);
		System.out.println("CONVERTED Supp CODE    ====  "+supplierCode);
		System.out.println("CONVERTED Supp Type   ====  "+suplierType);
		
		double conver = Double.parseDouble(converConv);
		logger.info(conver);
		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		cr.add(Restrictions.ge("otd_ytd", conver)).add(Restrictions.eq("otd_suppliercode", supplierCode))
				.add(Restrictions.eq("otd_year", year)).add(Restrictions.eq("otd_suppliertype", suplierType));
		List<Otd> list = cr.list();
		return list;
	}

	/*
	 * @Override public synchronized List<Otd> getLowPerForOtd(String year,
	 * String otdPero) { session = sessionFactory.openSession(); tx =
	 * session.beginTransaction(); String conver = otdPero.substring(0,
	 * otdPero.length() - 1); logger.info(conver); Query q =
	 * session.createQuery(
	 * "FROM Otd WHERE otd_year=:otd_year AND otd_ytd < :conver");
	 * q.setString("conver", conver); q.setParameter("otd_year", year);
	 * List<Otd> list = q.list(); return list; }
	 */

	@Override
	public synchronized List<Otd> getLowPerForOtd(String year, String otdPero, String supplierCode,
			String suplierType) {
		String converConv = otdPero.substring(0, otdPero.length() - 1);
		double conver = Double.parseDouble(converConv);
		logger.info(conver);
		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		cr.add(Restrictions.lt("otd_ytd", conver)).add(Restrictions.eq("otd_suppliercode", supplierCode))
				.add(Restrictions.eq("otd_year", year)).add(Restrictions.eq("otd_suppliertype", suplierType));
		List<Otd> list = cr.list();
		return list;
	}

	@Override
	public List<Otd> getAvg() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		String yr = Integer.toString(year);

		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		cr.add(Restrictions.eq("otd_year", yr));
		cr.add(Restrictions.eq("otd_suppliertype", "occ").ignoreCase());
		cr.setProjection(Projections.avg("otd_ytd"));

		List<Otd> list = cr.list();
		return list;

	}

	@Override
	public List<Otd> getAvg3() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		String yr = Integer.toString(year);

		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		cr.add(Restrictions.eq("otd_year", yr));
		cr.add(Restrictions.eq("otd_suppliertype", "kw").ignoreCase());
		cr.setProjection(Projections.avg("otd_ytd"));

		List<Otd> list = cr.list();
		return list;

	}

	@Override
	public List<Object> getMonthly() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);

		String yr = Integer.toString(year);
		String m = Integer.toString(month);

		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		cr.add(Restrictions.eq("otd_year", yr));

		if (month == 0) {

			cr.add(Restrictions.isNotNull("otd_jan"));
		}

		if (month == 1) {

			cr.add(Restrictions.isNotNull("otd_feb"));
		}

		if (month == 2) {

			cr.add(Restrictions.isNotNull("otd_mar"));
		}

		if (month == 3) {

			cr.add(Restrictions.isNotNull("otd_apr"));
		}

		if (month == 4) {

			cr.add(Restrictions.isNotNull("otd_may"));
		}

		if (month == 5) {

			cr.add(Restrictions.isNotNull("otd_june"));
		}

		if (month == 6) {

			cr.add(Restrictions.isNotNull("otd_july"));
		}

		if (month == 7) {

			cr.add(Restrictions.isNotNull("otd_aug"));
		}

		if (month == 8) {

			cr.add(Restrictions.isNotNull("otd_sep"));
		}

		if (month == 9) {

			cr.add(Restrictions.isNotNull("otd_oct"));
		}

		if (month == 10) {

			cr.add(Restrictions.isNotNull("otd_nov"));
		}

		if (month == 11) {

			cr.add(Restrictions.isNotNull("otd_dec"));
		}

		cr.add(Restrictions.eq("otd_suppliertype", "kw").ignoreCase());

		Criteria cr1 = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Otd.class);
		cr1.add(Restrictions.eq("otd_year", yr));

		if (month == 0) {

			cr1.add(Restrictions.isNotNull("otd_jan"));
		}

		if (month == 1) {

			cr1.add(Restrictions.isNotNull("otd_feb"));
		}

		if (month == 2) {

			cr1.add(Restrictions.isNotNull("otd_mar"));
		}

		if (month == 3) {
			cr1.add(Restrictions.isNotNull("otd_apr"));
		}

		if (month == 4) {

			cr1.add(Restrictions.isNotNull("otd_may"));
		}

		if (month == 5) {

			cr1.add(Restrictions.isNotNull("otd_june"));
		}

		if (month == 6) {

			cr1.add(Restrictions.isNotNull("otd_july"));
		}

		if (month == 7) {

			cr1.add(Restrictions.isNotNull("otd_aug"));
		}

		if (month == 8) {

			cr1.add(Restrictions.isNotNull("otd_sep"));
		}

		if (month == 9) {

			cr1.add(Restrictions.isNotNull("otd_oct"));
		}

		if (month == 10) {

			cr1.add(Restrictions.isNotNull("otd_nov"));
		}

		if (month == 11) {

			cr1.add(Restrictions.isNotNull("otd_dec"));
		}

		cr1.add(Restrictions.eq("otd_suppliertype", "occ").ignoreCase());

		int kw = cr.list().size();
		int occ = cr1.list().size();

		List<Object> arr = new ArrayList<>();

		arr.add(0, kw);
		arr.add(1, occ);

		return arr;

	}

	@Override
	public List<Otd> getOtdBySupplierAndYear(String supplierCodde, String otdYear) {
		// TODO Auto-generated method stub
		/*List<Otd> otdList = (List<Otd>) hibernateTemplate.find("from Otd where otd_suppliercode='"+supplierCodde+"' and otd_year='"+otdYear+"'");
		if(otdList.isEmpty()){
			return null;
		}else {
			return otdList;
		}*/
		
		return (List<Otd>) hibernateTemplate.find("from Otd where otd_suppliercode='"+supplierCodde+"' and otd_year='"+otdYear+"'");
	}

	@Override
	public List<Otd> getLowPer(String monthCol, String backYear) {
		// TODO Auto-generated method stub
		
		return (List<Otd>) hibernateTemplate.find("from Otd where otd_year='"+backYear+"'");
	}

	@Override
	public List<Otd> getOtdBySupplierTypeYear(String supplierCode, String supplierType, String otdYear) {
		// TODO Auto-generated method stub
		return (List<Otd>) hibernateTemplate.find("from Otd where otd_suppliercode='"+supplierCode+"' and otd_year='"+otdYear+"' and otd_suppliertype='"+supplierType+"'");
	}

}
