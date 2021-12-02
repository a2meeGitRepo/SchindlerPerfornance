package com.schindlerperformance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/*import javax.servlet.http.HttpServletResponse;*/

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.schindlerperformance.config.EmailUtility;
import com.schindlerperformance.model.Sale;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.services.EmployeeServices;
import com.schindlerperformance.services.SaleServices;

@Controller
@RequestMapping("/sale")

public class SaleController {
	@Autowired
	SaleServices saleServices;

	

	@Autowired
	EmployeeServices employeeServices;

	


	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUserDataAndFile(@RequestBody MultipartFile file) {

		try {
			int i = 1;
			if (file.isEmpty()) {
				System.out.println("File not found");
			} else {
				System.out.println(file.getOriginalFilename());
				try {

					File dir = new File(System.getProperty("catalina.base"), "uploads");
 					File uplaodedFile = new File(dir + file.getOriginalFilename());
					file.transferTo(uplaodedFile);
					FileInputStream excelFile = new FileInputStream(uplaodedFile);
					Workbook workbook = new XSSFWorkbook(excelFile);
					Sheet datatypeSheet = workbook.getSheetAt(0);
					DataFormatter formatter = new DataFormatter();
					
					
					System.out.println(datatypeSheet.getLastRowNum());

					while (i <= datatypeSheet.getLastRowNum()) {
 
						Sale sale = new Sale();
						XSSFRow row = (XSSFRow) datatypeSheet.getRow(i++);
						System.out.println(row.getCell(1));

						if (row.getCell(1) != null) {
							
							
							
							
							String docc = String.valueOf(row.getCell(0));
							String docc1 = null;
						
								  
							docc = String.valueOf(row.getCell(0)).replaceAll("\\.+", "");
								  
								  int len1 = docc.length();
								  
								  System.out.println(docc.charAt(len1-1));
								  
								  if(docc.charAt(len1-1)=='0'){
									  
									  docc = docc.substring(0, docc.length() - 1);
									
								  }
								
								  else if(docc.contains("E")){
									  
									  docc1 = docc.substring(0, docc.length() - 2);
									 
								  }
								  
								  else{
									  
									  docc1 = String.valueOf(row.getCell(0));
								  }
								  
							
							
							
							List<Sale> saleList = saleServices.getSaleByName(docc1);
							System.out.println("Asset Available:" + saleList.size());
							
							if (saleList.size() == 0) {
								
							
							
							String mob111 = null;
							
							String mob222 = null;
								
								
								//String doc = String.valueOf(row.getCell(0));
								
							
								String doc = String.valueOf(row.getCell(0));
								
					  
								  System.out.println(doc.contains(doc));
								  
								  if(doc.contains(doc)==true && doc.contains(".")==false){
									  
									  sale.setSale_no(String.valueOf(row.getCell(0)));
								  }
								  
								  else{
									  
									  doc = String.valueOf(row.getCell(0)).replaceAll("\\.+", "");
									  
									  int len = doc.length();
									  
									  System.out.println(doc.charAt(len-1));
									  
									  if(doc.charAt(len-1)=='0'){
										  
										  doc = doc.substring(0, doc.length() - 1);
										  sale.setSale_no(doc);
										  
									  }
									
									  else if(doc.contains("E")){
										  
										  String doc1 = doc.substring(0, doc.length() - 2);
										  sale.setSale_no(doc1);
									  }
									  
									  else{
										  
										  sale.setSale_no(doc);
										  
									  }
									  
									
									  
								  }
								
								 sale.setDisaptch_date(String.valueOf(row.getCell(1)));
					              
					              sale.setContract(String.valueOf(row.getCell(2)));
					              


					              String Oc = String.valueOf(row.getCell(3)).replaceAll("\\.0+", "");
					              

					              sale.setOffice_code(Oc);
					              
					              sale.setAcp(String.valueOf(row.getCell(4)));
					              
					              String plant = String.valueOf(row.getCell(5)).replaceAll("\\.0+", "");
					              
					              sale.setPlant(plant);
					              
					              sale.setNip(String.valueOf(row.getCell(6)));
					              
					              String day = String.valueOf(row.getCell(7)).replaceAll("\\.0+", "");
					              
					              sale.setScar_days(day);
					              
					              sale.setSales_offce(String.valueOf(row.getCell(8)));
					              
					              sale.setProduct_line(String.valueOf(row.getCell(9)));
					              
					              sale.setSch_date(String.valueOf(row.getCell(10)));
					              
					              sale.setScar_date(String.valueOf(row.getCell(11)));
					              
					              sale.setSite_address(String.valueOf(row.getCell(12)));
					              
					              sale.setEmail(String.valueOf(row.getCell(13)));
					              
					              sale.setEmail2(String.valueOf(row.getCell(14)));
					              

					              String mob1 = String.valueOf(row.getCell(15)).replaceAll("\\.+", "");
					              
					              String mob11 = mob1.substring(0, mob1.length() - 2);
					              
					              if (mob11.length() == 9)
					              {
					                mob111 = mob11.concat("0");

					              }
					              else if (mob11.length() == 8)
					              {
					                mob111 = mob11.concat("00");


					              }
					              else if (mob11.length() == 7)
					              {
					                mob111 = mob11.concat("000");


					              }
					              else if (mob11.length() == 6)
					              {
					                mob111 = mob11.concat("0000");


					              }
					              else if (mob11.length() == 5)
					              {
					                mob111 = mob11.concat("00000");


					              }
					              else if (mob11.length() == 4)
					              {
					                mob111 = mob11.concat("000000");


					              }
					              else
					              {

					            	  mob111  = mob11;
					              //  mob111 = mob1.substring(0, mob1.length() - 2);
					              }
					              


					              sale.setMobile_no(mob111);
					              


					              String mob2 = String.valueOf(row.getCell(16)).replaceAll("\\.+", "");
					              
					              String mob22 = mob2.substring(0, mob2.length() - 2);
					              


					              if (mob22.length() == 9)
					              {
					                mob222 = mob22.concat("0");

					              }
					              else if (mob22.length() == 8)
					              {
					                mob222 = mob22.concat("00");


					              }
					              else if (mob22.length() == 7)
					              {
					                mob222 = mob22.concat("000");


					              }
					              else if (mob22.length() == 6)
					              {
					                mob222 = mob22.concat("0000");


					              }
					              else if (mob22.length() == 5)
					              {
					                mob222 = mob22.concat("00000");


					              }
					              else if (mob22.length() == 4)
					              {
					                mob222 = mob22.concat("000000");


					              }
					              else
					              {
					            	  
					            	  mob222 = mob22;

					              //  mob222 = mob2.substring(0, mob1.length() - 2);
					              }
					              


					              sale.setMobile_no2(mob222);
					             
					              sale.setDeletes(1);
					              
							
								saleServices.addSale(sale);

								System.out.println("added successfully");
								
							}
							
							} else {
								System.out.println("sale id already registered");
							}

						

					}

				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

		@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Sale> getSale() {
		List<Sale> saleList = null;
		try {
			saleList = saleServices.getSale();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}

		
		@RequestMapping(value = "/list1", method = RequestMethod.GET)
	public @ResponseBody List<Sale> getSale1() {
		List<Sale> saleList = null;
		try {
			saleList = saleServices.getSale1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}

	
		/*@RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public synchronized  @ResponseBody
		Status sendMail(@RequestBody Sale sale) {
			try {
			EmailUtility.sendEmail("smtp.outlook.com", "587", "jainnikita995@outlook.com" , sale.getEmail()  , String.valueOf("Order Confirmation Report : Job No"+""+sale.getSale_no()+":"+sale.getContract()), String.valueOf(sale.getSale_no()), "Dear Recipients,<br><br>Please find attached the Order Confirmation for subject mentioned site.<br>Ensure Site Readiness before Ex.OCC std material dispatch date.<br>Kindly provide RVP approval in case of Early shipment request.<br> <br>Do Not Reply : This is system generated auto email ,<br> <br>"+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+"For any clarification,Feel Free to Contact<br> <br>"+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" Tejashree Keluskar ( Logistics Co-Ordinator )<br>"+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+"Email : Tejashree.keluskar@Schindler.com<br>"+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+"Phone +9102135622329 | Mobile +918407939200<br><br><br>Regards,<br>Logistics Team<br>Schindler India Pvt Ltd");
				return new Status("Mail Send  Successfully !");
			} catch (Exception e) {
				
				System.out.println(e);
				return new Status(e.toString());
			}

		}	*/
		
		
		@RequestMapping(value = "/sendsms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public synchronized @ResponseBody Status sendSms(@RequestBody Sale sale) {
			try {

				String mob = sale.getMobile_no();
				
				//String Message = sale.getSales_offce();
				
				String mobs = sale.getMobile_no2();
				
				String m = mob+","+mobs;
				
				String project = sale.getContract();
				
				String prj = project.replaceAll("\\s+","%20");
				
				URL smsApiLink = new URL("http://trans.dreamztechnolgy.org/SmsStatuswithId.aspx?mobile=8446060888&pass=Advik8&senderid=APTMEE&to="+m+"&msg=SIPL%20LOG%20Order%20Confirmation%20for%20SO%20"+sale.getSale_no()+"%20"+prj+"%20sent%20to%20you%20via%20email%20OC%20STD%20date%20"+sale.getSch_date()+"%20and%20Non%20STD%20Date%20"+sale.getScar_date());
				System.out.println(smsApiLink);

				URLConnection smsConnecion = smsApiLink.openConnection();
				smsConnecion.connect();
				BufferedReader in = new BufferedReader(new InputStreamReader(smsConnecion.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null)
					System.out.println(inputLine);
				in.close();

				return new Status("SMS Send Successfully !");
			} catch (Exception e) {

				System.out.println(e);
				return new Status(e.toString());
			}

		}

		
		
		@RequestMapping(value = "/delete/{sale_id}", method = RequestMethod.GET)
		public @ResponseBody Status deleteSale(@PathVariable("sale_id") int sale_id) {
			try {
				saleServices.deleteSale(sale_id);
				return new Status("Sale Deleted Successfully !");
			} catch (Exception e) {
				// e.printStackTrace();
				return new Status(e.toString());
			}

		}
		
		@RequestMapping(value = "/permdelete/{sale_id}", method = RequestMethod.GET)
		public @ResponseBody Status deleteSale1(@PathVariable("sale_id") int sale_id) {
			try {
				saleServices.deleteSale1(sale_id);
				return new Status("Sale Deleted Successfully !");
			} catch (Exception e) {
				// e.printStackTrace();
				return new Status(e.toString());
			}

		}
		
		@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Sale> searchSale(@RequestBody Sale sale) {
			List<Sale> saleList = null;
			try {
				saleList = saleServices.searchSale(sale);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return saleList;
		}
		
		
		@RequestMapping(value = "/search1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Sale> searchSale1(@RequestBody Sale sale) {
			List<Sale> saleList = null;
			try {
				saleList = saleServices.searchSale1(sale);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return saleList;
		}
	
	/*@RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status verifySale(@RequestBody Sale sale) {
		System.out.println("Sr ERP No:" + sale.getSr_no_erp());

		try {
			saleServices.verifySale(sale);
			return new Status("verified successfully");

		} catch (Exception e) {
			return new Status(e.toString());
			// e.printStackTrace();
		}

	}
*/
	
	
		
	/*@RequestMapping(value = "/delete/{sale_id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteSale(@PathVariable("sale_id") int sale_id) {
		try {
			saleServices.deleteSale(sale_id);
			return new Status("Sale Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}
	
	@RequestMapping(value = "/transfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addTransfer(@RequestBody Transfer transfer) {
		try {
			saleServices.addTransfer(transfer);
			// System.out.println("First Name:"+sale.getIin());
			return new Status("Sale trasnfered Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}
*/
	
}
