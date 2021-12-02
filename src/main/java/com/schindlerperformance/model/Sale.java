package com.schindlerperformance.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sale_mst")
public class Sale {

	@Id
	@Column(name = "sale_id")
	private int sale_id;
	private String sale_no;
	private String contract;
	/*private String comm_nr;
	private String type;*/
	private String product_line;
	/*private String product_family;
	private String product_family1;*/
	private String plant;
	/*private String sloc;
	private String warehouse;
	private String pull_date;
	private String pull_month;*/
	private String sch_date;
	private String scar_date;
	private String disaptch_date;
	private String sales_offce;
	/*private String city;*/
	private String scar_days;
	private String acp;
	private String nip;
	private String site_address;
	private String office_code;
	private String mobile_no;
	private String mobile_no2;
	private String email;
	
	private String email2;
	
	
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getMobile_no2() {
		return mobile_no2;
	}
	public void setMobile_no2(String mobile_no2) {
		this.mobile_no2 = mobile_no2;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	@Transient
	private String startDate;
	
	@Transient
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Transient
	private String mails;
	
	@Transient
	private String mob;
	
	public String getMails() {
		return mails;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public void setMails(String mails) {
		this.mails = mails;
	}
	/*private String lead_time;
	private String wbs_element;
	private String passenger_capacity;
	private String warehouse2;
	private String remarks;*/
	private int deletes;

	
	public int getSale_id() {
		return sale_id;
	}
	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}
	
	public String getSale_no() {
		return sale_no;
	}
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getProduct_line() {
		return product_line;
	}
	public void setProduct_line(String product_line) {
		this.product_line = product_line;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getSch_date() {
		return sch_date;
	}
	public void setSch_date(String sch_date) {
		this.sch_date = sch_date;
	}
	public String getScar_date() {
		return scar_date;
	}
	public void setScar_date(String scar_date) {
		this.scar_date = scar_date;
	}
	public String getDisaptch_date() {
		return disaptch_date;
	}
	public void setDisaptch_date(String disaptch_date) {
		this.disaptch_date = disaptch_date;
	}
	public String getSales_offce() {
		return sales_offce;
	}
	public void setSales_offce(String sales_offce) {
		this.sales_offce = sales_offce;
	}
	public String getScar_days() {
		return scar_days;
	}
	public void setScar_days(String scar_days) {
		this.scar_days = scar_days;
	}
	public String getAcp() {
		return acp;
	}
	public void setAcp(String acp) {
		this.acp = acp;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getSite_address() {
		return site_address;
	}
	public void setSite_address(String site_address) {
		this.site_address = site_address;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public int getDeletes() {
		return deletes;
	}
	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
