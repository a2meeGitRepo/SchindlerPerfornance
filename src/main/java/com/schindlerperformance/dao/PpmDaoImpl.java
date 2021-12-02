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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.Ppm2;

public class PpmDaoImpl extends GenericDaoImpl<Ppm, Integer> implements PpmDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	Session session;

	static final Logger logger = Logger.getLogger(PpmDaoImpl.class);

	@Override
	public synchronized boolean addPpm(Ppm ppm) {
		
		// TODO Auto-generated method stub
		
		System.out.println("IN DAO YTD"+ppm.getPpm_ytd());
		hibernateTemplate.saveOrUpdate(ppm);
		return true;
	}

	@Override
	public synchronized List<Ppm> getPpms() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Ppm> ppmList = criteria.list();

		return ppmList;

	}

	@Override
	public synchronized List<Ppm2> getPpm2s() {
		// TODO Auto-generated method stub
		//Criteria criteria = session.createCriteria(Ppm2.class);
		DetachedCriteria maxId = DetachedCriteria.forClass(Ppm2.class).setProjection(Projections.max("ppm2_id"));
		List ppm2List = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm2.class).add(Property.forName("ppm2_id").eq(maxId)).list();

		//List<Ppm2> ppm2List = criteria.list();

		return ppm2List;

	}

	@Override
	public boolean deletePpm(int ppm_id) {
		// TODO Auto-generated method stub
		Ppm ppm = new Ppm();
		ppm.setPpm_id(ppm_id);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("delete from Ppm where ppm_id=:ppm_id");
		q.setParameter("ppm_id", ppm.getPpm_id());

		int status = q.executeUpdate();
		System.out.println(status);

		// session.delete(ppm);
		return false;
	}

	@Override
	public List<Ppm> getPpmByName(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		criteria.add(Restrictions.eq("name", name));
		List<Ppm> ppmList = criteria.list();
		return ppmList;

	}

	@Override
	public Ppm getPpm(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		criteria.add(Restrictions.eq("name", name));
		Ppm ppm = (Ppm) criteria.uniqueResult();
		return ppm;
	}

	@Override
	public synchronized List<Ppm> ppmUpdateBySuppCodeAndSuppType(String supplierCode, String suplierType,
			String supplierYear) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class).add(Restrictions.eq("ppm_suppliercode", supplierCode))
				.add(Restrictions.eq("ppm_supplierType", suplierType)).add(Restrictions.eq("ppm_year", supplierYear));
		List ppm = criteria.list();
		return ppm;
	}

	@Override
	public List<Ppm> searchPpm1(Ppm ppm) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);

		if (ppm.getPpm_suppliername() != null) {
			criteria.add(Restrictions.eq("ppm_suppliername", ppm.getPpm_suppliername()));
		}
		if (ppm.getPpm_supplierType() != null) {
			criteria.add(Restrictions.eq("ppm_supplierType", ppm.getPpm_supplierType()));
		}
		if (ppm.getPpm_ytd() != 0) {
			criteria.add(Restrictions.eq("ppm_ytd", ppm.getPpm_ytd()));
		}
		if (ppm.getPpm_year() != null) {
			criteria.add(Restrictions.eq("ppm_year", ppm.getPpm_year()));
		}

		List<Ppm> ppmList = criteria.list();
		return ppmList;

	}

	/*--------------------unique serch data---------------------*/
	@Override
	public List<Ppm> getPpms1() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		criteria.add(Restrictions.eq("deletes", 1));

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("ppm_year"));

		criteria.setProjection(Projections.distinct(projList));

		List<Ppm> ppmList = criteria.list();

		return ppmList;

	}
	/*--------------------unique serch data---------------------*/

	@Override
	public List<Ppm> getHighPerForPpm(String year, String ppmFP, String ppmSuppCodeF, String ppmSuppType) {
		
		long conver = Long.parseLong(ppmFP);
		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		cr.add(Restrictions.le("ppm_ytd", conver)).add(Restrictions.eq("ppm_suppliercode", ppmSuppCodeF))
				.add(Restrictions.eq("ppm_year", year)).add(Restrictions.eq("ppm_supplierType", ppmSuppType));
		List<Ppm> list = cr.list();
		return list;
	}
	/*
	 * @Override public List<Ppm> getLowPerForPpm(String year, String ppmFP) {
	 * session = sessionFactory.openSession(); tx = session.beginTransaction();
	 * int conver = Integer.parseInt(ppmFP); logger.info(ppmFP); Query q =
	 * session.createQuery(
	 * "FROM Ppm WHERE ppm_year=:ppm_year AND ppm_ytd > :conver");
	 * q.setInteger("conver", conver); q.setParameter("ppm_year", year);
	 * List<Ppm> list = q.list(); return list; }
	 */

	@Override
	public List<Ppm> getLowPerForPpm(String year, String ppmFP, String ppmSuppCodeF, String ppmSuppType) {
		long conver = Long.parseLong(ppmFP);
		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		cr.add(Restrictions.gt("ppm_ytd", conver)).add(Restrictions.eq("ppm_suppliercode", ppmSuppCodeF))
				.add(Restrictions.eq("ppm_year", year)).add(Restrictions.eq("ppm_supplierType", ppmSuppType));
		List<Ppm> list = cr.list();
		return list;
	}

	@Override
	public List<Object> getMonthly() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);

		String yr = Integer.toString(year);
		String m = Integer.toString(month);

		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		cr.add(Restrictions.eq("ppm_year", yr));

		if (month == 0) {

			cr.add(Restrictions.isNotNull("ppm_jan"));
		}

		if (month == 1) {

			cr.add(Restrictions.isNotNull("ppm_feb"));
		}

		if (month == 2) {

			cr.add(Restrictions.isNotNull("ppm_mar"));
		}

		if (month == 3) {

			cr.add(Restrictions.isNotNull("ppm_apr"));
		}

		if (month == 4) {

			cr.add(Restrictions.isNotNull("ppm_may"));
		}

		if (month == 5) {

			cr.add(Restrictions.isNotNull("ppm_june"));
		}

		if (month == 6) {

			cr.add(Restrictions.isNotNull("ppm_july"));
		}

		if (month == 7) {

			cr.add(Restrictions.isNotNull("ppm_aug"));
		}

		if (month == 8) {

			cr.add(Restrictions.isNotNull("ppm_sep"));
		}

		if (month == 9) {

			cr.add(Restrictions.isNotNull("ppm_oct"));
		}

		if (month == 10) {

			cr.add(Restrictions.isNotNull("ppm_nov"));
		}

		if (month == 11) {

			cr.add(Restrictions.isNotNull("ppm_dec"));
		}

		cr.add(Restrictions.eq("ppm_supplierType", "kw").ignoreCase());

		Criteria cr1 = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		cr1.add(Restrictions.eq("ppm_year", yr));

		if (month == 0) {

			cr1.add(Restrictions.isNotNull("ppm_jan"));
		}

		if (month == 1) {

			cr1.add(Restrictions.isNotNull("ppm_feb"));
		}

		if (month == 2) {

			cr1.add(Restrictions.isNotNull("ppm_mar"));
		}

		if (month == 3) {
			cr1.add(Restrictions.isNotNull("ppm_apr"));
		}

		if (month == 4) {

			cr1.add(Restrictions.isNotNull("ppm_may"));
		}

		if (month == 5) {

			cr1.add(Restrictions.isNotNull("ppm_june"));
		}

		if (month == 6) {

			cr1.add(Restrictions.isNotNull("ppm_july"));
		}

		if (month == 7) {

			cr1.add(Restrictions.isNotNull("ppm_aug"));
		}

		if (month == 8) {

			cr1.add(Restrictions.isNotNull("ppm_sep"));
		}

		if (month == 9) {

			cr1.add(Restrictions.isNotNull("ppm_oct"));
		}

		if (month == 10) {

			cr1.add(Restrictions.isNotNull("ppm_nov"));
		}

		if (month == 11) {

			cr1.add(Restrictions.isNotNull("ppm_dec"));
		}

		cr1.add(Restrictions.eq("ppm_supplierType", "occ").ignoreCase());

		int kw = cr.list().size();
		int occ = cr1.list().size();

		List<Object> arr = new ArrayList<>();

		arr.add(0, kw);
		arr.add(1, occ);

		return arr;

	}

	@Override
	public synchronized boolean addPpm2(Ppm2 ppm2) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(ppm2);
		return false;
	}

	@Override
	public float getmaxPpm(String supplierCode, String supplierType, String year) {
		// TODO Auto-generated method stub
		Criteria cr = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Ppm.class);
		cr.add(Restrictions.eq("ppm_suppliercode", supplierCode))
				.add(Restrictions.eq("ppm_year", year)).add(Restrictions.eq("ppm_supplierType", supplierType));
		List<Ppm> list = cr.list();
		Ppm ppm = list.get(0);
		
		String jan= ppm.getPpm_jan();
		String feb =  ppm.getPpm_feb();
		String mar = ppm.getPpm_mar();
		String apr = ppm.getPpm_apr() ;
		String may= ppm.getPpm_may();
		String june= ppm.getPpm_june();
		String july = ppm.getPpm_july();
		String aug=ppm.getPpm_aug();
		String sep = ppm.getPpm_sep();
		String oct = ppm.getPpm_oct();
		String nov = ppm.getPpm_nov();
		String dec =  ppm.getPpm_dec();
		
		float jan1= 0;
		float feb1 =  0;
		float mar1 = 0;
		float apr1 = 0 ;
		float may1= 0;
		float june1= 0;
		float july1 =0;
		float aug1=0;
		float sep1 = 0;
		float oct1 = 0;
		float nov1 = 0;
		float dec1 =  0;
		System.out.println("SEP ++++++++++++++++++++++++    "+ sep);
		if(jan.equals("")) {
			jan1=0;
		}else{
			jan1=Float.parseFloat(jan);
		}
		
		if(feb.equals("")) {
			feb1=0;
		}else{
			feb1=Float.parseFloat(feb);
		}
		if(mar.equals("")) {
			mar1=0;
		}else{
			mar1=Float.parseFloat(mar);
		}
		if(apr.equals("")) {
			apr1=0;
		}else{
			apr1=Float.parseFloat(apr);
		}
		if(may.equals("")) {
			may1=0;
		}else{
			may1=Float.parseFloat(may);
		}
		
		if(june.equals("")) {
			june1=0;
		}else{
			june1=Float.parseFloat(june);
		}
		
		if(july.equals("")) {
			july1=0;
		}else{
			july1=Float.parseFloat(july);
		}
		
		if(aug.equals("")) {
			aug1=0;
		}else{
			aug1=Float.parseFloat(aug);
		}
		if(sep.equals("")) {
			sep1=0;
		}else{
			sep1=Float.parseFloat(sep);
		}
		if(oct.equals("")) {
			oct1=0;
		}else{
			oct1=Float.parseFloat(oct);
		}
		if(nov.equals("")) {
			nov1=0;
		}else{
			nov1=Float.parseFloat(nov);
		}
		if(dec.equals("")) {
			dec1=0;
		}else{
			dec1=Float.parseFloat(dec);
		}
		float[] a = new float[] { jan1, feb1, mar1, apr1, may1, june1,july1,aug1,sep1,oct1,nov1,dec1};
		float max = a[0];
		for(int i = 1; i < a.length;i++)
		{
			if(a[i] > max)
			{
				max = a[i];
			}
		}
		return max;
		
	}

}
