package com.schindlerperformance.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

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

import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.Ppm2;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.model.Supplier;
import com.schindlerperformance.model.Supplierstatus;
import com.schindlerperformance.services.PerformanceServices;
import com.schindlerperformance.services.PpmServices;
import com.schindlerperformance.services.SupplierServices;
import com.schindlerperformance.services.SupplierstatusServices;

@Controller
@RequestMapping("/ppm")
public class PpmController {

	@Autowired
	PpmServices ppmServices;

	@Autowired
	SupplierServices supplierServices;

	@Autowired
	PerformanceServices performanceServices;

	@Autowired
	SupplierstatusServices supplierstatusServices;

	private static DecimalFormat df2 = new DecimalFormat(".##");

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Object saveGeneratedQrLabel(@RequestBody MultipartFile file) {
		try {
			if (!(file == null)) {
				int i = 1;
				if (file.isEmpty()) {
					//logger.info("File not found");
				} else {
					//logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						//logger.info("hiiii@" + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0);
						DataFormatter formatter = new DataFormatter();
						//logger.info(i <= datatypeSheet.getLastRowNum());
						while (i <= datatypeSheet.getLastRowNum()) {
							Ppm ppm = new Ppm();
							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							if (row.getCell(0) != null) {
								String supplieCode = formatter.formatCellValue(row.getCell(0));
								String supplierName = formatter.formatCellValue(row.getCell(1));
								String supplierType = formatter.formatCellValue(row.getCell(2));
								String supplieryear = formatter.formatCellValue(row.getCell(3));
								List<Ppm> ppmSuppNameType = ppmServices.ppmUpdateBySuppCodeAndSuppType(supplieCode,
										supplierType, supplieryear);
								List<Supplier> supplierList = supplierServices.getSuppNameSuppTypeSuppCode(supplieCode,
										supplierName, supplierType);

								List<Performance> performanceList = performanceServices
										.performanceUpdateBySuppCodeSuppTypeAndSuppYear(supplieCode, supplierType,
												supplieryear);

								List<Supplierstatus> supplerStatusList = supplierstatusServices
										.suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(supplieCode, supplierType,
												supplieryear);

								if (supplierList.size() == 0) {
									Supplier supp = new Supplier();
									supp.setSupplier_code(supplieCode);
									supp.setSupplier_name(supplierName);
									supp.setSupplier_type(supplierType);
									supp.setDeletes(1);
									supplierServices.addSupplier(supp);
									//logger.info(" new supplier insert here......");
								}

								if (performanceList.size() == 0) {
									Performance per = new Performance();
									per.setPerformance_suppliercode(supplieCode);
									per.setPerformance_suppliername(supplierName);
									per.setPerformance_suppliertype(supplierType);
									per.setPerformance_year(supplieryear);
									per.setDeletes(1);
									performanceServices.addPerformance(per);
									//logger.info(" new performace insert here...");
								}

								long ytd_ppm = 0;

								if (ppmSuppNameType.size() != 0) {
									for (Ppm ppmObj : ppmSuppNameType) {
										//logger.info(ppmObj.getPpm_suppliercode().equals(supplieCode));
										if (ppmObj.getPpm_suppliercode().equals(supplieCode)
												&& ppmObj.getPpm_supplierType().equals(supplierType)
												&& ppmObj.getPpm_year().equals(supplieryear)) {

											ppmObj.setPpm_suppliername(String.valueOf(row.getCell(1)));

											String year = formatter.formatCellValue(row.getCell(3));
											ppmObj.setPpm_year(year);

											String jan = formatter.formatCellValue(row.getCell(4));
											if (!jan.isEmpty()) {

												ppmObj.setPpm_jan(jan);
											} else {
												ppmObj.setPpm_jan(jan);
											}

											String feb = formatter.formatCellValue(row.getCell(5));
											if (!feb.isEmpty()) {

												ppmObj.setPpm_feb(feb);
											} else {
												ppmObj.setPpm_feb(feb);
											}

											String mar = formatter.formatCellValue(row.getCell(6));
											if (!mar.isEmpty()) {

												ppmObj.setPpm_mar(mar);
											} else {
												ppmObj.setPpm_mar(mar);
											}

											String apr = formatter.formatCellValue(row.getCell(7));
											if (!apr.isEmpty()) {

												ppmObj.setPpm_apr(apr);
											} else {
												ppmObj.setPpm_apr(apr);
											}

											String may = formatter.formatCellValue(row.getCell(8));
											if (!may.isEmpty()) {

												ppmObj.setPpm_may(may);
											} else {
												ppmObj.setPpm_may(may);
											}

											String june = formatter.formatCellValue(row.getCell(9));
											if (!june.isEmpty()) {

												ppmObj.setPpm_june(june);
											} else {
												ppmObj.setPpm_june(june);
											}

											String july = formatter.formatCellValue(row.getCell(10));
											if (!july.isEmpty()) {

												ppmObj.setPpm_july(july);
											} else {
												ppmObj.setPpm_july(july);
											}

											String aug = formatter.formatCellValue(row.getCell(11));
											if (!aug.isEmpty()) {

												ppmObj.setPpm_aug(aug);
											} else {
												ppmObj.setPpm_aug(aug);
											}

											String sep = formatter.formatCellValue(row.getCell(12));
											if (!sep.isEmpty()) {

												ppmObj.setPpm_sep(sep);
											} else {
												ppmObj.setPpm_sep(sep);
											}

											String oct = formatter.formatCellValue(row.getCell(13));
											if (!oct.isEmpty()) {

												ppmObj.setPpm_oct(oct);
											} else {
												ppmObj.setPpm_oct(oct);
											}

											String nov = formatter.formatCellValue(row.getCell(14));
											if (!nov.isEmpty()) {

												ppmObj.setPpm_nov(nov);
											} else {
												ppmObj.setPpm_nov(nov);
											}

											String dec = formatter.formatCellValue(row.getCell(15));
											if (!dec.isEmpty()) {

												ppmObj.setPpm_dec(dec);
											} else {
												ppmObj.setPpm_dec(dec);
											}

											String ytd = formatter.formatCellValue(row.getCell(16));
											/*long ytdNew3 = Long.parseLong(ytd);
											ytd_ppm = ytdNew3;*/
											System.out.println("YTD EXCEL SHEET====="+ytd);
											System.out.println("YTD HASH CODE ====="+ytd.hashCode());
											if (ytd.hashCode()!=0) {
												System.out.println("IF CONDITUIN TRUE");
												long ytdNew3 = Long.parseLong(ytd);
												ytd_ppm = ytdNew3;
												System.out.println("SET YTD"+ytdNew3);
												ppmObj.setPpm_ytd(ytdNew3);
											} else {
												System.out.println("ELSE CONDITUIN TRUE");
												ppmObj.setPpm_ytd(0);
											}
											

											/*ppmObj.setPpm_ytd(ytdNew3);*/
											ppmObj.setDeletes(1);
											ppmServices.addPpm(ppmObj);
											//logger.info("update");
										} else {

											String supplieCode1 = formatter.formatCellValue(row.getCell(0));
											ppm.setPpm_suppliercode(supplieCode1);

											ppm.setPpm_suppliername(String.valueOf(row.getCell(1)));

											String supplierType1 = formatter.formatCellValue(row.getCell(2));
											ppm.setPpm_supplierType(supplierType1);

											String year = formatter.formatCellValue(row.getCell(3));
											ppm.setPpm_year(year);

											String jan = formatter.formatCellValue(row.getCell(4));
											if (!jan.isEmpty()) {

												ppm.setPpm_jan(jan);
											} else {
												ppm.setPpm_jan(jan);
											}

											String feb = formatter.formatCellValue(row.getCell(5));
											if (!feb.isEmpty()) {

												ppm.setPpm_feb(feb);
											} else {
												ppm.setPpm_feb(feb);
											}

											String mar = formatter.formatCellValue(row.getCell(6));
											if (!mar.isEmpty()) {

												ppm.setPpm_mar(mar);
											} else {
												ppm.setPpm_mar(mar);
											}

											String apr = formatter.formatCellValue(row.getCell(7));
											if (!apr.isEmpty()) {

												ppm.setPpm_apr(apr);
											} else {
												ppm.setPpm_apr(apr);
											}

											String may = formatter.formatCellValue(row.getCell(8));
											if (!may.isEmpty()) {

												ppm.setPpm_may(may);
											} else {
												ppm.setPpm_may(may);
											}

											String june = formatter.formatCellValue(row.getCell(9));
											if (!june.isEmpty()) {

												ppm.setPpm_june(june);
											} else {
												ppm.setPpm_june(june);
											}

											String july = formatter.formatCellValue(row.getCell(10));
											if (!july.isEmpty()) {

												ppm.setPpm_july(july);
											} else {
												ppm.setPpm_july(july);
											}

											String aug = formatter.formatCellValue(row.getCell(11));
											if (!aug.isEmpty()) {

												ppm.setPpm_aug(aug);
											} else {
												ppm.setPpm_aug(aug);
											}

											String sep = formatter.formatCellValue(row.getCell(12));
											if (!sep.isEmpty()) {

												ppm.setPpm_sep(sep);
											} else {
												ppm.setPpm_sep(sep);
											}

											String oct = formatter.formatCellValue(row.getCell(13));
											if (!oct.isEmpty()) {

												ppm.setPpm_oct(oct);
											} else {
												ppm.setPpm_oct(oct);
											}

											String nov = formatter.formatCellValue(row.getCell(14));
											if (!nov.isEmpty()) {

												ppm.setPpm_nov(nov);
											} else {
												ppm.setPpm_nov(nov);
											}

											String dec = formatter.formatCellValue(row.getCell(15));
											if (!dec.isEmpty()) {

												ppm.setPpm_dec(dec);
											} else {
												ppm.setPpm_dec(dec);
											}

											String ytd = formatter.formatCellValue(row.getCell(16));
											/*long ytdNew2 = Long.parseLong(ytd);
											ytd_ppm = ytdNew2;
											ppm.setPpm_ytd(ytdNew2);*/
											
											if (ytd.hashCode()!=0) {
												long ytdNew2 = Long.parseLong(ytd);
												ytd_ppm = ytdNew2;
												ppm.setPpm_ytd(ytdNew2);
											} else {
												ppm.setPpm_ytd(0);
											}
											

											ppm.setDeletes(1);
											ppmServices.addPpm(ppm);
											//logger.info("update");
										}
									}
								} else {

									String supplieCode1 = formatter.formatCellValue(row.getCell(0));
									ppm.setPpm_suppliercode(supplieCode1);

									ppm.setPpm_suppliername(String.valueOf(row.getCell(1)));

									String supplierType1 = formatter.formatCellValue(row.getCell(2));
									ppm.setPpm_supplierType(supplierType1);

									String year = formatter.formatCellValue(row.getCell(3));
									ppm.setPpm_year(year);

									String jan = formatter.formatCellValue(row.getCell(4));
									if (!jan.isEmpty()) {

										ppm.setPpm_jan(jan);
									} else {
										ppm.setPpm_jan(jan);
									}

									String feb = formatter.formatCellValue(row.getCell(5));
									if (!feb.isEmpty()) {

										ppm.setPpm_feb(feb);
									} else {
										ppm.setPpm_feb(feb);
									}

									String mar = formatter.formatCellValue(row.getCell(6));
									if (!mar.isEmpty()) {

										ppm.setPpm_mar(mar);
									} else {
										ppm.setPpm_mar(mar);
									}

									String apr = formatter.formatCellValue(row.getCell(7));
									if (!apr.isEmpty()) {

										ppm.setPpm_apr(apr);
									} else {
										ppm.setPpm_apr(apr);
									}

									String may = formatter.formatCellValue(row.getCell(8));
									if (!may.isEmpty()) {

										ppm.setPpm_may(may);
									} else {
										ppm.setPpm_may(may);
									}

									String june = formatter.formatCellValue(row.getCell(9));
									if (!june.isEmpty()) {

										ppm.setPpm_june(june);
									} else {
										ppm.setPpm_june(june);
									}

									String july = formatter.formatCellValue(row.getCell(10));
									if (!july.isEmpty()) {

										ppm.setPpm_july(july);
									} else {
										ppm.setPpm_july(july);
									}

									String aug = formatter.formatCellValue(row.getCell(11));
									if (!aug.isEmpty()) {

										ppm.setPpm_aug(aug);
									} else {
										ppm.setPpm_aug(aug);
									}

									String sep = formatter.formatCellValue(row.getCell(12));
									if (!sep.isEmpty()) {

										ppm.setPpm_sep(sep);
									} else {
										ppm.setPpm_sep(sep);
									}

									String oct = formatter.formatCellValue(row.getCell(13));
									if (!oct.isEmpty()) {
										ppm.setPpm_oct(oct);
									} else {
										ppm.setPpm_oct(oct);
									}

									String nov = formatter.formatCellValue(row.getCell(14));
									if (!nov.isEmpty()) {

										ppm.setPpm_nov(nov);
									} else {
										ppm.setPpm_nov(nov);
									}

									String dec = formatter.formatCellValue(row.getCell(15));
									if (!dec.isEmpty()) {

										ppm.setPpm_dec(dec);
									} else {
										ppm.setPpm_dec(dec);
									}

									String ytd = formatter.formatCellValue(row.getCell(16));
									if (ytd.hashCode()!=0) {
										long ytdNew1 = Long.parseLong(ytd);
										ytd_ppm = ytdNew1;
										ppm.setPpm_ytd(ytdNew1);
									} else {
										ppm.setPpm_ytd(0);
									}
									
									
									/*//logger.info(ytd);
									long ytdNew1 = Long.parseLong(ytd);
									//logger.info(ytdNew1);
									ytd_ppm = ytdNew1;
									ppm.setPpm_ytd(ytdNew1);*/

									ppm.setDeletes(1);
									ppmServices.addPpm(ppm);
									//logger.info("update");
								}

								if (supplerStatusList.size() == 0) {
									Supplierstatus suppObj = new Supplierstatus();
									suppObj.setSupplierstatus_suppliercode(supplieCode);
									suppObj.setSupplierstatus_suppliername(supplierName);
									suppObj.setSupplierstatus_suppliertype(supplierType);
									suppObj.setSupplierstatus_year(supplieryear);
									String avg_ppm2 = String.valueOf(ytd_ppm);
									suppObj.setSupplierstatus_ppm(avg_ppm2);
									suppObj.setDeletes(1);
									supplierstatusServices.addSupplierstatus(suppObj);
									//logger.info(" new performace insert here...");
								} else {

									for (Supplierstatus ss : supplerStatusList) {
										if (ss.getSupplierstatus_suppliercode().equals(supplieCode)
												&& ss.getSupplierstatus_suppliertype().equals(supplierType)
												&& ss.getSupplierstatus_year().equals(supplieryear)) {
											ss.setSupplierstatus_suppliercode(supplieCode);
											ss.setSupplierstatus_suppliername(supplierName);
											ss.setSupplierstatus_suppliertype(supplierType);
											ss.setSupplierstatus_year(supplieryear);
											String avg_ppm2 = String.valueOf(ytd_ppm);
											ss.setSupplierstatus_ppm(avg_ppm2);
											ss.setDeletes(1);
											supplierstatusServices.addSupplierstatus(ss);
										}
									}
								}
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			} else {
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/create3", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addPpm(@RequestBody Ppm ppm) {
		String supplieCode = ppm.getPpm_suppliercode();
		String supplierType = ppm.getPpm_supplierType();
		String supplieryear = ppm.getPpm_year();
		List<Ppm> ppmSuppNameType = ppmServices.ppmUpdateBySuppCodeAndSuppType(supplieCode,
				supplierType, supplieryear);
		try {
			ppmServices.addPpm(ppm);
			return new Status("Ppm Update Successfully !");
			/*if(ppmSuppNameType.size()==0) {
				ppmServices.addPpm(ppm);
				System.out.println("First Name:" + ppm.getPpm_suppliername());
				return new Status("Ppm added Successfully !");
			}else if (ppmSuppNameType.size()==1 && ppm.getPpm_id()!=0){
			//	ppm.setPpm_id(ppmSuppNameType.get(0).getPpm_id());
				ppmServices.addPpm(ppm);
				System.out.println("First Name:" + ppm.getPpm_suppliername());
				return new Status("Ppm Update Successfully !");
			} {
				return new Status("Ppm AllReady Exits.. !");
			}*/
			
		} catch (Exception e) {
			// e.printStackTrace();

			System.out.println(e);
			return new Status(e.toString());
		}

	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addPpm3(@RequestBody Ppm ppm) {
		String supplieCode = ppm.getPpm_suppliercode();
		String supplierType = ppm.getPpm_supplierType();
		String supplieryear = ppm.getPpm_year();
		List<Ppm> ppmSuppNameType = ppmServices.ppmUpdateBySuppCodeAndSuppType(supplieCode,
				supplierType, supplieryear);
		try {
			int counter = 0; // for dividesion
			double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0, juneValue = 0, julyValue = 0,
					augValue = 0, sepValue = 0, octValue = 0, novValue = 0, decValue = 0;
			String zr="0";
			String nll="";
			System.out.println("JAN  VALUE =  "+ppm.getPpm_jan());
			System.out.println("ZR  VALUE =  "+zr);
			if(ppm.getPpm_jan().equals(zr)||ppm.getPpm_jan().equals(nll)) {
				
				ppm.setPpm_jan("0");	
			}else {
				
				counter++;
				janValue = Double.parseDouble(ppm.getPpm_jan());
					
			}
			if(ppm.getPpm_feb().equals(zr)||ppm.getPpm_feb().equals(nll)) {
				
				ppm.setPpm_feb("0");	
			}else {
				
				counter++;
				febValue = Double.parseDouble(ppm.getPpm_feb());
					
			}
			if(ppm.getPpm_mar().equals(zr)||ppm.getPpm_mar().equals(nll)) {
				
				ppm.setPpm_mar("0");	
			}else {
				
				counter++;
				marValue = Double.parseDouble(ppm.getPpm_mar());
					
			}
			if(ppm.getPpm_apr().equals(zr)||ppm.getPpm_apr().equals(nll)) {
				
				ppm.setPpm_apr("0");	
			}else {
				counter++;
				aprValue = Double.parseDouble(ppm.getPpm_apr());
					
			}
			if(ppm.getPpm_may().equals(zr)||ppm.getPpm_may().equals(nll)) {
				
				ppm.setPpm_may("0");	
			}else {
				counter++;
				mayValue = Double.parseDouble(ppm.getPpm_may());
					
			}
			if(ppm.getPpm_june().equals(zr)||ppm.getPpm_june().equals(nll)) {
				
				ppm.setPpm_june("0");	
			}else {
				counter++;
				juneValue = Double.parseDouble(ppm.getPpm_june());
					
			}
			if(ppm.getPpm_july().equals(zr)||ppm.getPpm_july().equals(nll)) {
				
				ppm.setPpm_july("0");	
			}else {
				counter++;
				julyValue = Double.parseDouble(ppm.getPpm_july());
					
			}
			if(ppm.getPpm_aug().equals(zr)||ppm.getPpm_aug().equals(nll)) {
				
				ppm.setPpm_aug("0");	
			}else {
				counter++;
				augValue = Double.parseDouble(ppm.getPpm_aug());
					
			}
			if(ppm.getPpm_sep().equals(zr)||ppm.getPpm_sep().equals(nll)) {
				
				ppm.setPpm_sep("0");	
			}else {
				counter++;
				sepValue = Double.parseDouble(ppm.getPpm_sep());
					
			}
			if(ppm.getPpm_oct().equals(zr)||ppm.getPpm_oct().equals(nll)) {
				
				ppm.setPpm_oct("0");	
			}else {
				counter++;
				octValue = Double.parseDouble(ppm.getPpm_oct());
					
			}
			if(ppm.getPpm_nov().equals(zr)||ppm.getPpm_nov().equals(nll)) {
				
				ppm.setPpm_nov("0");	
			}else {
				counter++;
				novValue = Double.parseDouble(ppm.getPpm_nov());
					
			}
			if(ppm.getPpm_dec().equals(zr)||ppm.getPpm_dec().equals(nll)) {
				
				ppm.setPpm_dec("0");	
			}else {
				counter++;
				decValue = Double.parseDouble(ppm.getPpm_dec());
					
			}
			double monthSum = janValue + febValue + marValue + aprValue + mayValue + juneValue + julyValue + augValue
					+ sepValue + octValue + novValue + decValue;
			//double avgResult = monthSum / counter;
			double avgResult = 0;
			if (monthSum == 0.0) {

				avgResult = 0;
			} else {

				avgResult = monthSum / counter;
			}
			System.out.println("COUTER====  "+counter);
			System.out.println("Month Sum====  "+monthSum);
			   long avgResult1 = (new Double(avgResult)).longValue();
			ppm.setPpm_ytd(avgResult1);
			
			System.out.println("SIZE======"+ppmSuppNameType.size());
			if(ppmSuppNameType.size()==0) {
				
				System.out.println("IN NEW WHERE SIZE ISS 0)");
				ppmServices.addPpm(ppm);
				System.out.println("First Name:" + ppm.getPpm_suppliername());
				return new Status("Ppm added Successfully !");
			}else{
				System.out.println("IN UPDATE WHERE SIZE NOT 0)");
			//	ppm.setPpm_id(ppmSuppNameType.get(0).getPpm_id());
				ppm.setPpm_id(ppmSuppNameType.get(0).getPpm_id());
				ppmServices.addPpm(ppm);
				System.out.println("First Name:" + ppm.getPpm_suppliername());
				return new Status("Ppm Update Successfully !");
			}/*else {
				return new Status("Ppm AllReady Exits.. !");
			}*/
		} catch (Exception e) {
			// e.printStackTrace();

			System.out.println(e);
			return new Status(e.toString());
		}

	}
	@RequestMapping(value = "/create2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody

			Status addPpm2(@RequestBody Ppm2 ppm2) {
		try {
			ppmServices.addPpm2(ppm2);
			System.out.println("First Name:" + ppm2.getPpm2_kw());
			return new Status("Ppm2 added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();

			System.out.println(e);
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Ppm> getPpms() {
		List<Ppm> ppmList = null;
		try {
			ppmList = ppmServices.getPpms();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppmList;
	}

	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public @ResponseBody List<Ppm2> getPpm2s() {
		List<Ppm2> ppm2List = null;
		try {
			ppm2List = ppmServices.getPpm2s();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppm2List;
	}

	@RequestMapping(value = "/delete/{ppm_id}", method = RequestMethod.GET)
	public @ResponseBody Status deletePpm(@PathVariable("ppm_id") int ppm_id) {
		try {
			ppmServices.deletePpm(ppm_id);
			return new Status("Ppm Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/search1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Ppm> searchPpm1(@RequestBody Ppm ppm) {
		List<Ppm> ppmList = null;
		try {
			ppmList = ppmServices.searchPpm1(ppm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppmList;
	}

	/*--------------------unique serch data---------------------*/
	@RequestMapping(value = "/uniq", method = RequestMethod.GET)
	public @ResponseBody List<Ppm> getPpms1() {
		List<Ppm> ppmList = null;
		try {
			ppmList = ppmServices.getPpms1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppmList;
	}
	/*--------------------unique serch data---------------------*/

	@RequestMapping(value = "/monthlyreport", method = RequestMethod.GET)
	public @ResponseBody List<Object> getMonthly() {
		List<Object> ppmList = null;
		try {
			ppmList = ppmServices.getMonthly();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppmList;
	}
	@RequestMapping(value = "/getmaxPpm/{supplierCode}/{supplierType}/{year}", method = RequestMethod.GET)
	public @ResponseBody float getmaxPpm(@PathVariable("supplierCode") String supplierCode,@PathVariable("supplierType") String supplierType,@PathVariable("year") String year) {
			float maxPPm = 0;
		try {
			maxPPm = ppmServices.getmaxPpm(supplierCode,supplierType,year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxPPm;
	}

}
