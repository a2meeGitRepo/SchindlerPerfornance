package com.schindlerperformance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.schindlerperformance.model.Fpy;
import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.Supplierstatus;
@Transactional
public class PerformanceDaoImpl extends GenericDaoImpl<Performance, Integer> implements PerformanceDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	
//	@Autowired
	Ppm ppm=new Ppm();
//	
//	@Autowired
	Otd otd=new Otd();
//
//	@Autowired
	Fpy fpy=new Fpy();
	

	@Override
	public boolean addPerformance(Performance performance) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(performance);
		return false;
	}

	@Override
	public List<Performance> getPerformances() {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);
		criteria.add(Restrictions.eq("deletes", 1));
		List<Performance> performanceList = criteria.list();

		return performanceList;

	}

	@Override
	public boolean deletePerformance(int performance_id) {
		// TODO Auto-generated method stub
		Performance performance = new Performance();
		performance.setPerformance_id(performance_id);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("update Performance set deletes=:zero where performance_id=:performance_id");
		q.setParameter("performance_id", performance.getPerformance_id());
		q.setParameter("zero", 0);

		int status = q.executeUpdate();
		System.out.println(status);

		// session.delete(performance);
		return false;
	}

	@Override
	public List<Performance> getPerformanceByName(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);
		criteria.add(Restrictions.eq("name", name));
		List<Performance> performanceList = criteria.list();
		return performanceList;

	}

	@Override
	public Performance getPerformance(String name) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);
		criteria.add(Restrictions.eq("name", name));
		Performance performance = (Performance) criteria.uniqueResult();
		return performance;
	}

	@Override
	public List<Performance> performanceUpdateBySuppCodeSuppTypeAndSuppYear(String supplierCode, String supplerType,
			String supplerYear) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class)
				.add(Restrictions.eq("performance_suppliercode", supplierCode))
				.add(Restrictions.eq("performance_suppliertype", supplerType))
				.add(Restrictions.eq("performance_year", supplerYear));
		List<Performance> performanceList = criteria.list();
		return performanceList;
	}

	@SuppressWarnings("unused")
	@Override
	public List<Performance> getPerformances2(String otd_suppliercode, String otd_year, String otd_suppliertype,
			String otdper) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);

		double otd_per = 0;

		if (otdper == null  || otdper.equals("")) {

			otd_per = 0;
		} else {
			otd_per = Double.parseDouble(otdper);
		}

		List<Performance> performanceList = criteria.list();

		List<Performance> pList = new ArrayList<>();

		for (Performance pl : performanceList) {
			//System.out.println("PERFPRMANCE  ID"+pl.getPerformance_id());
			//System.out.println("PERFORMANCE OTD=="+ pl.getPerformance_otd());
		
		String perOTD=  pl.getPerformance_otd();
			String potd;
			if( perOTD==null) {
				//System.out.println("CHECL OTD 111111111111=="+pl.getPerformance_otd());

				potd="0";

			} else  if (perOTD.isEmpty()){
				potd="0";
			}else {
				
				int index=pl.getPerformance_otd().length() - 1;
				//System.out.println("PERFORMANCE OTD=="+pl.getPerformance_otd());
				System.out.println("PERFORMANCE  index=="+index);
				
				 potd = pl.getPerformance_otd().substring(0, index);
				// System.out.println("POTD =="+potd);
			}
			

			double p_otd = Double.parseDouble(potd);

			if (pl.getPerformance_suppliercode().equals(otd_suppliercode) && pl.getPerformance_year().equals(otd_year)
					&& pl.getPerformance_suppliertype().equals(otd_suppliertype) && p_otd < otd_per) {

				pList.add(pl);

			}

		}

		return pList;
	}

	@Override
	public List<Performance> getPerformances3(String ppm_suppliercode, String ppm_year, String ppm_supplierType,
			String ppmper) {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);

		double ppm_per = 0;
		
		
		List<Performance> performanceList = criteria.list();

		List<Performance> pList2 = new ArrayList<>();

		for (Performance pl : performanceList) {

			String pppm = pl.getPerformance_ppm();
			double p_ppm;
			if(pppm==null) {
				p_ppm=Double.parseDouble("0");  
			}else if (pppm.isEmpty()){
				p_ppm=Double.parseDouble("0"); 
			}else {
				p_ppm = Double.parseDouble(pppm);
			}
			
			
			if (ppmper.equals("")) {

				ppm_per = p_ppm + 1;
				
			} else {

				 ppm_per = Double.parseDouble(ppmper);
			}
			
			if (pl.getPerformance_suppliercode().equals(ppm_suppliercode) && pl.getPerformance_year().equals(ppm_year)
					&& pl.getPerformance_suppliertype().equals(ppm_supplierType) && p_ppm > ppm_per) {

				pList2.add(pl);

			}

		}

		return pList2;
	}

	@Override
	public List<Performance> getPerformances4(String fpy_suppliercode, String fpy_year, String fpy_suppliertype,
			String fpyper) {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);

		double fpy_per = 0;

		if (fpyper == null  || fpyper.equals("")) {

			fpy_per = 0;

		} else {
			fpy_per = Double.parseDouble(fpyper);
		}
		List<Performance> performanceList = criteria.list();

		List<Performance> pList3 = new ArrayList<>();

		for (Performance pl : performanceList) {
			String pfpy;
					if(pl.getPerformance_fpy() !=null) {
						 pfpy = pl.getPerformance_fpy().substring(0, pl.getPerformance_fpy().length() - 1);
					}else{
						 pfpy="0";
					}
			//String pfpy = pl.getPerformance_fpy().substring(0, pl.getPerformance_fpy().length() - 1);

			double p_fpy = Double.parseDouble(pfpy);

			if (pl.getPerformance_suppliercode().equals(fpy_suppliercode) && pl.getPerformance_year().equals(fpy_year)
					&& pl.getPerformance_suppliertype().equals(fpy_suppliertype) && p_fpy < fpy_per) {

				pList3.add(pl);

			}

		}

		return pList3;
	}

	/*-----------------------------------high performance by year------------------------------------*/
	@Override
	public List<Performance> getPerformances5(String otd_suppliercode, String otd_year, String otd_suppliertype,
			Double otd_ytd) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);

		List<Performance> performanceList = criteria.list();

		List<Performance> pList = new ArrayList<>();

		for (Performance pl : performanceList) {
			
				String potd ;
				String otdPER=pl.getPerformance_otd();
				if(otdPER==null) {
					potd="0";
			}else if(otdPER.equals("")) {
				potd="0";
			}else {
				 potd = pl.getPerformance_otd().substring(0, pl.getPerformance_otd().length() - 1);
			}
				

			double p_otd = Double.parseDouble(potd);

			if (pl.getPerformance_suppliercode().equals(otd_suppliercode) && pl.getPerformance_year().equals(otd_year)
					&& pl.getPerformance_suppliertype().equals(otd_suppliertype) && p_otd < otd_ytd) {

				pList.add(pl);

			}

		}

		return pList;
	}

	@Override
	public List<Performance> getPerformances6(String ppm_suppliercode, String ppm_year, String ppm_supplierType,
			long ppm_ytd) {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);

		List<Performance> performanceList = criteria.list();

		List<Performance> pList2 = new ArrayList<>();

		for (Performance pl : performanceList) {
			String pppm ;
			System.out.println("PPM VALUE ============="+pl.getPerformance_ppm());
			if( pl.getPerformance_ppm()==null) {
				 pppm = "0";
				 
			}else if (pl.getPerformance_ppm().isEmpty()){
				 pppm = "0";
			}else {
				pppm = pl.getPerformance_ppm();
			}

			//String pppm = pl.getPerformance_ppm();
			System.out.println("PPM VALUE +++++++++++++++++ "+pppm);
			if(pppm.isEmpty()) {
				pppm = "0";
			}
			int p_ppm = Integer.parseInt(pppm);

			if (pl.getPerformance_suppliercode().equals(ppm_suppliercode) && pl.getPerformance_year().equals(ppm_year)
					&& pl.getPerformance_suppliertype().equals(ppm_supplierType) && p_ppm > ppm_ytd) {

				pList2.add(pl);

			}

		}

		return pList2;
	}

	@Override
	public List<Performance> getPerformances7(String fpy_suppliercode, String fpy_year, String fpy_suppliertype,
			Double fpy_ytd) {
		// TODO Auto-generated method stub
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Performance.class);

		List<Performance> performanceList = criteria.list();

		List<Performance> pList3 = new ArrayList<>();

		for (Performance pl : performanceList) {
			String pfpy;
			if(pl.getPerformance_fpy()==null) {
				pfpy="0";
			}else if(pl.getPerformance_fpy().isEmpty()) {
				pfpy="0";
			}else {
				pfpy = pl.getPerformance_fpy().substring(0, pl.getPerformance_fpy().length() - 1);
			}
			
		//	pfpy = pl.getPerformance_fpy().substring(0, pl.getPerformance_fpy().length() - 1);

			double p_fpy = Double.parseDouble(pfpy);

			if (pl.getPerformance_suppliercode().equals(fpy_suppliercode) && pl.getPerformance_year().equals(fpy_year)
					&& pl.getPerformance_suppliertype().equals(fpy_suppliertype) && p_fpy < fpy_ytd) {

				pList3.add(pl);

			}

		}

		return pList3;
	}
	
	@Override
	public List<Object[]> getReport(String year) {
		
		Ppm ppm;
		Otd otd;
		Fpy fpy;
		//query=SELECT ppm.ppm_suppliercode,ppm.ppm_suppliername,ppm.ppm_jan,ppm.ppm_feb,ppm.ppm_mar,ppm.ppm_apr,ppm.ppm_may,ppm.ppm_june,ppm.ppm_july,ppm.ppm_aug,ppm.ppm_sep,ppm.ppm_oct,ppm.ppm_nov,ppm.ppm_dec,ppm.ppm_ytd,otd.otd_jan,otd.otd_feb,otd.otd_mar,otd.otd_apr,otd.otd_may,otd.otd_june,otd.otd_july,otd.otd_aug,otd.otd_sep,otd.otd_oct,otd.otd_nov,otd.otd_dec,otd.otd_ytd,fpy.fpy_jan,fpy.fpy_feb,fpy.fpy_mar,fpy.fpy_apr,fpy.fpy_may,fpy.fpy_june,fpy.fpy_july,fpy.fpy_aug,fpy.fpy_sep,fpy.fpy_oct,fpy.fpy_nov,fpy.fpy_dec,fpy.fpy_ytd from Ppm ppm inner join Otd otd on otd.otd_suppliercode=ppm.ppm_suppliercode inner join Fpy fpy on fpy.fpy_suppliercode=ppm.ppm_suppliercode where ppm.ppm_year = "+year+"and otd.otd_year = "+year+"and fpy.fpy_year = "+year+""
		System.out.println("year "+year);
		String query="\r\n" + 
				"SELECT ppm.ppm_suppliercode, ppm.ppm_suppliername, ppm.ppm_supplierType,\r\n" + 
				"ppm.ppm_jan, ppm.ppm_feb,ppm.ppm_mar,ppm.ppm_apr,ppm.ppm_may,ppm.ppm_june,ppm.ppm_july,ppm.ppm_aug,ppm.ppm_sep,ppm.ppm_oct,ppm.ppm_nov,ppm.ppm_dec,ppm.ppm_ytd,\r\n" + 
				"otd.otd_jan,otd.otd_feb,otd.otd_mar,otd.otd_apr,otd.otd_may,otd.otd_june,otd.otd_july,otd.otd_aug,otd.otd_sep,otd.otd_oct,otd.otd_nov,otd.otd_dec,otd.otd_ytd,\r\n" + 
				"fpy.fpy_jan,fpy.fpy_feb,fpy.fpy_mar,fpy.fpy_apr,fpy.fpy_may,fpy.fpy_june,fpy.fpy_july,fpy.fpy_aug,fpy.fpy_sep,fpy.fpy_oct,fpy.fpy_nov,fpy.fpy_dec,fpy.fpy_ytd\r\n" + 
				"from ppm_mst ppm \r\n" + 
				"inner join otd_mst otd on ppm.ppm_suppliercode = otd.otd_suppliercode\r\n" + 
				"inner join fpy_mst fpy on fpy.fpy_suppliercode = ppm.ppm_suppliercode\r\n" + 
				"where ppm.ppm_year ="+year+" and otd.otd_year ="+year+" and fpy.fpy_year ="+year;
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(query);
		List<Object[]> finalReport=q.list();
		
		return finalReport;
	}
	
	

}
