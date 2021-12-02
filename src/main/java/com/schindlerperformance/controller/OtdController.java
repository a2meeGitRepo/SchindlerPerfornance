package com.schindlerperformance.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.model.Supplier;
import com.schindlerperformance.model.Supplierstatus;
import com.schindlerperformance.services.OtdServices;
import com.schindlerperformance.services.PerformanceServices;
import com.schindlerperformance.services.SupplierServices;
import com.schindlerperformance.services.SupplierstatusServices;

@Controller
@RequestMapping("/otd")
public class OtdController {

	@Autowired
	OtdServices otdServices;

	@Autowired
	SupplierServices supplierServices;

	@Autowired
	PerformanceServices performanceServices;

	@Autowired
	SupplierstatusServices supplierstatusServices;


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
						System.out.println("FIME HAS BEEN GET ");
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0);
						DataFormatter formatter = new DataFormatter();
						//logger.info(i <= datatypeSheet.getLastRowNum());
						while (i <= datatypeSheet.getLastRowNum()) {

							Otd otd = new Otd();
							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							if (row.getCell(0) != null) {

								String supplieCode = formatter.formatCellValue(row.getCell(0));
								String supplierName = formatter.formatCellValue(row.getCell(1));
								String supplierType = formatter.formatCellValue(row.getCell(2));
								String supplieryear = formatter.formatCellValue(row.getCell(3));
								List<Otd> otdSuppNameType = otdServices.otdUpdateBySuppCodeAndSuppType(supplieCode,
										supplierType, supplieryear);
								List<Supplier> supplierList = supplierServices.getSuppNameSuppTypeSuppCode(supplieCode,
										supplierName, supplierType);
								List<Performance> performanceList = performanceServices
										.performanceUpdateBySuppCodeSuppTypeAndSuppYear(supplieCode, supplierType,
												supplieryear);
								List<Supplierstatus> supplerStatusList = supplierstatusServices
										.suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(supplieCode, supplierType,
												supplieryear);

								/*if (supplierList.size() == 0) {
									Supplier supp = new Supplier();
									supp.setSupplier_code(supplieCode);
									supp.setSupplier_name(supplierName);
									supp.setSupplier_type(supplierType);
									supp.setDeletes(1);
									supplierServices.addSupplier(supp);
									//logger.info(" new supplier insert here......");
								}*/

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

								double avg_otd = 0;
								
								if (otdSuppNameType.size() != 0) {
									for (Otd otdObj : otdSuppNameType) {
										if (otdObj.getOtd_suppliercode().equals(supplieCode)
												&& otdObj.getOtd_suppliertype().equals(supplierType)
												&& otdObj.getOtd_year().equals(supplieryear)) {

											int counter = 0; // for dividesion
											double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0,
													juneValue = 0, julyValue = 0, augValue = 0, sepValue = 0,
													octValue = 0, novValue = 0, decValue = 0;

											otdObj.setOtd_suppliername(String.valueOf(row.getCell(1)));

											// String year =
											// formatter.formatCellValue(row.getCell(3));
											// otdObj.setOtd_year(year);

											String jan = formatter.formatCellValue(row.getCell(4));
											if (!jan.isEmpty()) {
												counter++;
												otdObj.setOtd_jan(jan);
												//logger.info(jan);
												janValue = Double.parseDouble(jan.substring(0, jan.length() - 1));
												//logger.info(janValue);
											} else {
												otdObj.setOtd_jan(jan);
											}

											String feb = formatter.formatCellValue(row.getCell(5));
											if (!feb.isEmpty()) {
												counter++;
												otdObj.setOtd_feb(feb);
												febValue = Double.parseDouble(feb.substring(0, feb.length() - 1));
											} else {
												otdObj.setOtd_feb(feb);
											}

											String mar = formatter.formatCellValue(row.getCell(6));
											if (!mar.isEmpty()) {
												counter++;
												otdObj.setOtd_mar(mar);
												marValue = Double.parseDouble(mar.substring(0, mar.length() - 1));
											} else {
												otdObj.setOtd_mar(mar);
											}

											String apr = formatter.formatCellValue(row.getCell(7));
											System.out.println("apr :: "+apr);
											if (!apr.isEmpty()) {
												counter++;
												otdObj.setOtd_apr(apr);
												aprValue = Double.parseDouble(apr.substring(0, apr.length() - 1));
											} else {
												otdObj.setOtd_apr(apr);
											}

											String may = formatter.formatCellValue(row.getCell(8));
											if (!may.isEmpty()) {
												counter++;
												otdObj.setOtd_may(may);
												mayValue = Double.parseDouble(may.substring(0, may.length() - 1));
											} else {
												otdObj.setOtd_may(may);
											}

											String june = formatter.formatCellValue(row.getCell(9));
											if (!june.isEmpty()) {
												counter++;
												otdObj.setOtd_june(june);
												juneValue = Double.parseDouble(june.substring(0, june.length() - 1));
											} else {
												otdObj.setOtd_june(june);
											}

											String july = formatter.formatCellValue(row.getCell(10));
											if (!july.isEmpty()) {
												counter++;
												otdObj.setOtd_july(july);
												julyValue = Double.parseDouble(july.substring(0, july.length() - 1));
											} else {
												otdObj.setOtd_july(july);
											}

											String aug = formatter.formatCellValue(row.getCell(11));
											if (!aug.isEmpty()) {
												counter++;
												otdObj.setOtd_aug(aug);
												augValue = Double.parseDouble(aug.substring(0, aug.length() - 1));
											} else {
												otdObj.setOtd_aug(aug);
											}

											String sep = formatter.formatCellValue(row.getCell(12));
											if (!sep.isEmpty()) {
												counter++;
												otdObj.setOtd_sep(sep);
												sepValue = Double.parseDouble(sep.substring(0, sep.length() - 1));
											} else {
												otdObj.setOtd_sep(sep);
											}

											String oct = formatter.formatCellValue(row.getCell(13));
											if (!oct.isEmpty()) {
												counter++;
												otdObj.setOtd_oct(oct);
												octValue = Double.parseDouble(oct.substring(0, oct.length() - 1));
											} else {
												otdObj.setOtd_oct(oct);
											}

											String nov = formatter.formatCellValue(row.getCell(14));
											if (!nov.isEmpty()) {
												counter++;
												novValue = Double.parseDouble(nov.substring(0, nov.length() - 1));
												otdObj.setOtd_nov(nov);
											} else {
												otdObj.setOtd_nov(nov);
											}

											String dec = formatter.formatCellValue(row.getCell(15));
											if (!dec.isEmpty()) {
												counter++;
												otdObj.setOtd_dec(dec);
												decValue = Double.parseDouble(dec.substring(0, dec.length() - 1));
											} else {
												otdObj.setOtd_dec(dec);
											}

											double monthSum = janValue + febValue + marValue + aprValue + mayValue
													+ juneValue + julyValue + augValue + sepValue + octValue + novValue
													+ decValue;
											//double avgResult = monthSum / counter;
											double avgResult = 0;
											if (monthSum == 0.0) {

												avgResult = 0;
											} else {

												avgResult = monthSum / counter;
											}
											//logger.info(avgResult);
											String finalAvgResult = String.format("%.2f", avgResult);
											double avgResultNew1 = Double.parseDouble(finalAvgResult);
											avg_otd=avgResultNew1;

											otdObj.setOtd_ytd(avgResultNew1);
											otdObj.setDeletes(1);
											otdServices.addOtd(otdObj);
											System.out.println("OTD IS UPDATE ====================================111111111111111111111111111"+otd.getOtd_suppliername());
										} else {

											int counter = 0; // for dividesion
											double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0,
													juneValue = 0, julyValue = 0, augValue = 0, sepValue = 0,
													octValue = 0, novValue = 0, decValue = 0;
											String suppliecode1 = formatter.formatCellValue(row.getCell(0));
											otd.setOtd_suppliercode(suppliecode1);

											otd.setOtd_suppliername(String.valueOf(row.getCell(1)));

											String supplietype = formatter.formatCellValue(row.getCell(2));
											otd.setOtd_suppliertype(supplietype);

											String year = formatter.formatCellValue(row.getCell(3));
											otd.setOtd_year(year);

											String jan = formatter.formatCellValue(row.getCell(4));
											if (!jan.isEmpty()) {
												counter++;
												otd.setOtd_jan(jan);
												janValue = Double.parseDouble(jan.substring(0, jan.length() - 1));
											} else {
												otd.setOtd_jan(jan);
											}

											String feb = formatter.formatCellValue(row.getCell(5));
											if (!feb.isEmpty()) {
												counter++;
												otd.setOtd_feb(feb);
												febValue = Double.parseDouble(feb.substring(0, feb.length() - 1));
											} else {
												otd.setOtd_feb(feb);
											}

											String mar = formatter.formatCellValue(row.getCell(6));
											if (!mar.isEmpty()) {
												counter++;
												otd.setOtd_mar(mar);
												marValue = Double.parseDouble(mar.substring(0, mar.length() - 1));
											} else {
												otd.setOtd_mar(mar);
											}

											String apr = formatter.formatCellValue(row.getCell(7));
											if (!apr.isEmpty()) {
												counter++;
												otd.setOtd_apr(apr);
												aprValue = Double.parseDouble(apr.substring(0, apr.length() - 1));
											} else {
												otd.setOtd_apr(apr);
											}

											String may = formatter.formatCellValue(row.getCell(8));
											if (!may.isEmpty()) {
												counter++;
												otd.setOtd_may(may);
												mayValue = Double.parseDouble(may.substring(0, may.length() - 1));
											} else {
												otd.setOtd_may(may);
											}

											String june = formatter.formatCellValue(row.getCell(9));
											if (!june.isEmpty()) {
												counter++;
												otd.setOtd_june(june);
												juneValue = Double.parseDouble(june.substring(0, june.length() - 1));
											} else {
												otd.setOtd_june(june);
											}

											String july = formatter.formatCellValue(row.getCell(10));
											if (!july.isEmpty()) {
												counter++;
												otd.setOtd_july(july);
												julyValue = Double.parseDouble(july.substring(0, july.length() - 1));
											} else {
												otd.setOtd_july(july);
											}

											String aug = formatter.formatCellValue(row.getCell(11));
											if (!aug.isEmpty()) {
												counter++;
												otd.setOtd_aug(aug);
												augValue = Double.parseDouble(aug.substring(0, aug.length() - 1));
											} else {
												otd.setOtd_aug(aug);
											}

											String sep = formatter.formatCellValue(row.getCell(12));
											if (!sep.isEmpty()) {
												counter++;
												otd.setOtd_sep(sep);
												sepValue = Double.parseDouble(sep.substring(0, sep.length() - 1));
											} else {
												otd.setOtd_sep(sep);
											}

											String oct = formatter.formatCellValue(row.getCell(13));
											if (!oct.isEmpty()) {
												counter++;
												otd.setOtd_oct(oct);
												octValue = Double.parseDouble(oct.substring(0, oct.length() - 1));
											} else {
												otd.setOtd_oct(oct);
											}

											String nov = formatter.formatCellValue(row.getCell(14));
											if (!nov.isEmpty()) {
												counter++;
												novValue = Double.parseDouble(nov.substring(0, nov.length() - 1));
												otd.setOtd_nov(nov);
											} else {
												otd.setOtd_nov(nov);
											}

											String dec = formatter.formatCellValue(row.getCell(15));
											if (!dec.isEmpty()) {
												counter++;
												otd.setOtd_dec(dec);
												decValue = Double.parseDouble(dec.substring(0, dec.length() - 1));
											} else {
												otd.setOtd_dec(dec);
											}

											double monthSum = janValue + febValue + marValue + aprValue + mayValue
													+ juneValue + julyValue + augValue + sepValue + octValue + novValue
													+ decValue;
											//double avgResult = monthSum / counter;
											double avgResult = 0;
											if (monthSum == 0.0) {

												avgResult = 0;
											} else {

												avgResult = monthSum / counter;
											}
											//logger.info(avgResult);
											String finalAvgResult = String.format("%.2f", avgResult);
											double avgResultNew = Double.parseDouble(finalAvgResult);
											avg_otd = avgResultNew;
											otd.setOtd_ytd(avgResultNew);
											otd.setDeletes(1);
											otdServices.addOtd(otd);
											System.out.println("OTD IS UPDATE ====================================222222222222222222222222"+otd.getOtd_suppliername());
										}
									}
								} else {
									int counter = 0; // for dividesion
									double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0,
											juneValue = 0, julyValue = 0, augValue = 0, sepValue = 0, octValue = 0,
											novValue = 0, decValue = 0;
									String suppliecode1 = formatter.formatCellValue(row.getCell(0));
									otd.setOtd_suppliercode(suppliecode1);

									otd.setOtd_suppliername(String.valueOf(row.getCell(1)));

									String supplietype = formatter.formatCellValue(row.getCell(2));
									otd.setOtd_suppliertype(supplietype);

									String year = formatter.formatCellValue(row.getCell(3));
									otd.setOtd_year(year);

									String jan = formatter.formatCellValue(row.getCell(4));
									if (!jan.isEmpty()) {
										counter++;
										otd.setOtd_jan(jan);
										//logger.info(jan);
										janValue = Double.parseDouble(jan.substring(0, jan.length() - 1));
									} else {
										otd.setOtd_jan(jan);
									}

									String feb = formatter.formatCellValue(row.getCell(5));
									if (!feb.isEmpty()) {
										counter++;
										otd.setOtd_feb(feb);
										febValue = Double.parseDouble(feb.substring(0, feb.length() - 1));
									} else {
										otd.setOtd_feb(feb);
									}

									String mar = formatter.formatCellValue(row.getCell(6));
									if (!mar.isEmpty()) {
										counter++;
										otd.setOtd_mar(mar);
										marValue = Double.parseDouble(mar.substring(0, mar.length() - 1));
									} else {
										otd.setOtd_mar(mar);
									}

									String apr = formatter.formatCellValue(row.getCell(7));
									if (!apr.isEmpty()) {
										counter++;
										otd.setOtd_apr(apr);
										aprValue = Double.parseDouble(apr.substring(0, apr.length() - 1));
									} else {
										otd.setOtd_apr(apr);
									}

									String may = formatter.formatCellValue(row.getCell(8));
									if (!may.isEmpty()) {
										counter++;
										otd.setOtd_may(may);
										mayValue = Double.parseDouble(may.substring(0, may.length() - 1));
									} else {
										otd.setOtd_may(may);
									}

									String june = formatter.formatCellValue(row.getCell(9));
									if (!june.isEmpty()) {
										counter++;
										otd.setOtd_june(june);
										juneValue = Double.parseDouble(june.substring(0, june.length() - 1));
									} else {
										otd.setOtd_june(june);
									}

									String july = formatter.formatCellValue(row.getCell(10));
									if (!july.isEmpty()) {
										counter++;
										otd.setOtd_july(july);
										julyValue = Double.parseDouble(july.substring(0, july.length() - 1));
									} else {
										otd.setOtd_july(july);
									}

									String aug = formatter.formatCellValue(row.getCell(11));
									if (!aug.isEmpty()) {
										counter++;
										otd.setOtd_aug(aug);
										augValue = Double.parseDouble(aug.substring(0, aug.length() - 1));
									} else {
										otd.setOtd_aug(aug);
									}

									String sep = formatter.formatCellValue(row.getCell(12));
									if (!sep.isEmpty()) {
										counter++;
										otd.setOtd_sep(sep);
										sepValue = Double.parseDouble(sep.substring(0, sep.length() - 1));
									} else {
										otd.setOtd_sep(sep);
									}

									String oct = formatter.formatCellValue(row.getCell(13));
									if (!oct.isEmpty()) {
										counter++;
										otd.setOtd_oct(oct);
										octValue = Double.parseDouble(oct.substring(0, oct.length() - 1));
									} else {
										otd.setOtd_oct(oct);
									}

									String nov = formatter.formatCellValue(row.getCell(14));
									if (!nov.isEmpty()) {
										counter++;
										novValue = Double.parseDouble(nov.substring(0, nov.length() - 1));
										otd.setOtd_nov(nov);
									} else {
										otd.setOtd_nov(nov);
									}

									String dec = formatter.formatCellValue(row.getCell(15));
									if (!dec.isEmpty()) {
										counter++;
										otd.setOtd_dec(dec);
										decValue = Double.parseDouble(dec.substring(0, dec.length() - 1));
									} else {
										otd.setOtd_dec(dec);
									}

									double monthSum = janValue + febValue + marValue + aprValue + mayValue + juneValue
											+ julyValue + augValue + sepValue + octValue + novValue + decValue;
									//double avgResult = monthSum / counter;
									double avgResult = 0;
									if (monthSum == 0.0) {

										avgResult = 0;
									} else {

										avgResult = monthSum / counter;
									}
									//logger.info(avgResult);
									String finalAvgResult = String.format("%.2f", avgResult);
									double avgResultNew2 = Double.parseDouble(finalAvgResult);
									avg_otd = avgResultNew2;
									otd.setOtd_ytd(avgResultNew2);
									otd.setDeletes(1);
									otdServices.addOtd(otd);
									System.out.println("NEW OTD IS ADDED =========FOR "+otd.getOtd_suppliername());
								}

								if (supplerStatusList.size() == 0) {
									Supplierstatus suppObj = new Supplierstatus();
									suppObj.setSupplierstatus_suppliercode(supplieCode);
									suppObj.setSupplierstatus_suppliername(supplierName);
									suppObj.setSupplierstatus_suppliertype(supplierType);
									suppObj.setSupplierstatus_year(supplieryear);
									String avg_otd2 =  Double.toString(avg_otd);
									suppObj.setSupplierstatus_otd(avg_otd2);
									suppObj.setDeletes(1);
									supplierstatusServices.addSupplierstatus(suppObj);
									//logger.info(" new performace insert here...");
								} else {

									for (Supplierstatus ss : supplerStatusList) {
										if (ss.getSupplierstatus_suppliercode().equals(supplieCode)
												&& ss.getSupplierstatus_suppliertype().equals(supplierType)
												&& ss.getSupplierstatus_year().equals(supplieryear)) 
										{
											
											ss.setSupplierstatus_suppliercode(supplieCode);
											ss.setSupplierstatus_suppliername(supplierName);
											ss.setSupplierstatus_suppliertype(supplierType);
											ss.setSupplierstatus_year(supplieryear);
											String avg_otd2 = Double.toString(avg_otd);
											ss.setSupplierstatus_otd(avg_otd2);
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

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addOtd(@RequestBody Otd otd) {
		try {
				int counter = 0; // for dividesion
			double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0, juneValue = 0, julyValue = 0,
					augValue = 0, sepValue = 0, octValue = 0, novValue = 0, decValue = 0;

			if(otd.getOtd_id()!=0) {
				
				System.out.println("AAAAAA");
				if(otd.getOtd_jan()=="")
				{
					System.out.println("jan Is Null");
					otd.setOtd_jan(null);
				}
				if (otd.getOtd_jan()!=null) {
					counter++;
					janValue = Double.parseDouble(otd.getOtd_jan().substring(0, otd.getOtd_jan().length() - 1));
					System.out.println("jan Value"+janValue);
				} 
	
				if(otd.getOtd_feb()==""){
					System.out.println("Feb Is Null");
					otd.setOtd_feb(null);
				}
				if (otd.getOtd_feb()!=null) {
					counter++;
					febValue = Double.parseDouble(otd.getOtd_feb().substring(0, otd.getOtd_feb().length() - 1));
					System.out.println("Feb Value"+febValue);
				}
				
				if(otd.getOtd_mar()=="") {
					System.out.println("MAr Is Null");
					otd.setOtd_mar(null);
				}
				if (otd.getOtd_mar()!=null) {
					counter++;
					marValue = Double.parseDouble(otd.getOtd_mar().substring(0, otd.getOtd_mar().length() - 1));
					System.out.println("MAr Value"+marValue);
					
				}
				
				if(otd.getOtd_apr()=="") {
					System.out.println("APR Is Null");
					otd.setOtd_apr(null);
				}
				if (otd.getOtd_apr()!=null) {
					counter++;
					aprValue = Double.parseDouble(otd.getOtd_apr().substring(0, otd.getOtd_apr().length() - 1));
					System.out.println("ARl Value"+aprValue);
					
				}
				
				if(otd.getOtd_may()=="") {
					System.out.println("MAY Is Null");
				otd.setOtd_may(null);	
				}
				if (otd.getOtd_may()!=null) {
					counter++;
					mayValue = Double.parseDouble(otd.getOtd_may().substring(0, otd.getOtd_may().length() - 1));
					System.out.println("MAy Value"+mayValue);
				}
				
				if(otd.getOtd_june()=="") {
					System.out.println("Jun Is Null");
					otd.setOtd_june(null);
				}
				if (otd.getOtd_june()!=null) {
					counter++;
					juneValue = Double.parseDouble(otd.getOtd_june().substring(0, otd.getOtd_june().length() - 1));
					System.out.println("Jun Value"+juneValue);
				} 
				
				if(otd.getOtd_july()=="") {
					System.out.println("July Is Null");
					otd.setOtd_july(null);
				}
				if (otd.getOtd_july()!=null) {
					counter++;
					julyValue = Double.parseDouble(otd.getOtd_july().substring(0, otd.getOtd_july().length() - 1));
					System.out.println("July Value"+julyValue);

				}
				
				if(otd.getOtd_aug()=="") {
					System.out.println("AUG Is Null");
					otd.setOtd_aug(null);
				}
				if (otd.getOtd_aug()!=null) {
					counter++;
					augValue = Double.parseDouble(otd.getOtd_aug().substring(0, otd.getOtd_aug().length() - 1));
					System.out.println("Aug Value"+augValue);
				}
				
				if(otd.getOtd_sep()=="") {
					System.out.println("Sep Is Null");
				otd.setOtd_sep(null);	
				}
				if (otd.getOtd_sep()!=null) {
					counter++;
					sepValue = Double.parseDouble(otd.getOtd_sep().substring(0, otd.getOtd_sep().length() - 1));
					System.out.println("SEP Value"+sepValue);
					
				}
		
				if(otd.getOtd_oct()=="") {
					System.out.println("OTC Is Null");
					otd.setOtd_oct(null);
				}
				if (otd.getOtd_oct()!=null) {
					counter++;
					octValue = Double.parseDouble(otd.getOtd_oct().substring(0, otd.getOtd_oct().length() - 1));
					System.out.println("OTC Value"+octValue);
				}
				
				if(otd.getOtd_dec()=="")
				{
					System.out.println("DEC Is Null");
					otd.setOtd_dec(null);
				}
				if (otd.getOtd_dec()!=null) {
					counter++;
					decValue = Double.parseDouble(otd.getOtd_dec().substring(0, otd.getOtd_dec().length() - 1));
					System.out.println("DEC Value"+decValue);
				} 
				
				if(otd.getOtd_nov()=="") {
					System.out.println("NOv Is Null");
					otd.setOtd_nov(null);
				}
				if (otd.getOtd_nov()!="") {
					counter++;
					novValue = Double.parseDouble(otd.getOtd_nov().substring(0, otd.getOtd_nov().length() - 1));
					System.out.println("NOV Value"+novValue);
				}
				
				
				
			}else {
				
				System.out.println("BBBBBB");
				if(otd.getOtd_jan()=="") {
					otd.setOtd_jan(null);
				}
				if (otd.getOtd_jan()!=null) {
					counter++;
					janValue = Double.parseDouble(otd.getOtd_jan().substring(0, otd.getOtd_jan().length() - 1));
				} 
				
				if(otd.getOtd_feb()=="") {
					otd.setOtd_feb(null);
				}
				if (otd.getOtd_feb()!=null) {
					counter++;
					febValue = Double.parseDouble(otd.getOtd_feb().substring(0, otd.getOtd_feb().length() - 1));
				}
				
				if(otd.getOtd_mar()=="") {
					otd.setOtd_mar(null);
				}
				if (otd.getOtd_mar()!=null) {
					counter++;
					marValue = Double.parseDouble(otd.getOtd_mar().substring(0, otd.getOtd_mar().length() - 1));
				}
				
				if(otd.getOtd_apr()=="") {
					otd.setOtd_apr(null);
				}
				if (otd.getOtd_apr()!=null) {
					counter++;
					aprValue = Double.parseDouble(otd.getOtd_apr().substring(0, otd.getOtd_apr().length() - 1));
				}
				
				if(otd.getOtd_may()=="") {
					otd.setOtd_may(null);
				}
				if (otd.getOtd_may()!=null) {
					counter++;
					mayValue = Double.parseDouble(otd.getOtd_may().substring(0, otd.getOtd_may().length() - 1));
				}
				
				if(otd.getOtd_june()=="") {
					otd.setOtd_june(null);
				}
				if (otd.getOtd_june()!=null) {
					counter++;
					juneValue = Double.parseDouble(otd.getOtd_june().substring(0, otd.getOtd_june().length() - 1));
				} 
				
				if(otd.getOtd_july()=="") {
					otd.setOtd_july(null);
				}
				if (otd.getOtd_july()!=null) {
					counter++;
					julyValue = Double.parseDouble(otd.getOtd_july().substring(0, otd.getOtd_july().length() - 1));
				}
				
				if(otd.getOtd_aug()=="") {
					otd.setOtd_aug(null);
				}
				if (otd.getOtd_aug()!=null) {
					counter++;
					augValue = Double.parseDouble(otd.getOtd_aug().substring(0, otd.getOtd_aug().length() - 1));
				}
				
				if(otd.getOtd_sep()=="") {
					otd.setOtd_sep(null);
				}
				if (otd.getOtd_sep()!=null) {
					counter++;
					sepValue = Double.parseDouble(otd.getOtd_sep().substring(0, otd.getOtd_sep().length() - 1));
				}
		
				if(otd.getOtd_oct()=="") {
					otd.setOtd_oct(null);
				}
				if (otd.getOtd_oct()!=null) {
					counter++;
					octValue = Double.parseDouble(otd.getOtd_oct().substring(0, otd.getOtd_oct().length() - 1));
				}
				
				if(otd.getOtd_nov()=="") {
					otd.setOtd_nov(null);
				}
				if (otd.getOtd_nov()!=null) {
					counter++;
					novValue = Double.parseDouble(otd.getOtd_nov().substring(0, otd.getOtd_nov().length() - 1));
				}
				
				if(otd.getOtd_dec()=="") {
					otd.setOtd_dec(null);
				}
				if (otd.getOtd_dec()!=null) {
					counter++;
					decValue = Double.parseDouble(otd.getOtd_dec().substring(0, otd.getOtd_dec().length() - 1));
				} 
			}
			System.out.println("1111");
			double monthSum = janValue + febValue + marValue + aprValue + mayValue + juneValue + julyValue + augValue
					+ sepValue + octValue + novValue + decValue;
			//double avgResult = monthSum / counter;
			double avgResult = 0;
			if (monthSum == 0.0) {

				avgResult = 0;
			} else {

				avgResult = monthSum / counter;
			}
			
			String supplierCode=otd.getOtd_suppliercode();
			String otdYear =otd.getOtd_year();
			String supplierType = otd.getOtd_suppliertype();
			System.out.println("Year "+otdYear);
			List<Otd> fndotd = otdServices.getOtdBySupplierTypeYear(supplierCode,supplierType,otdYear);
			System.out.println("Size"+fndotd.size());
			if(fndotd.size()==0){
				System.out.println("If Null");
				String finalAvgResult = String.format("%.2f", avgResult);
				double avgResultNew = Double.parseDouble(finalAvgResult);
				otd.setOtd_ytd(avgResultNew);
				otd.setDeletes(1);
				
				otdServices.addOtd(otd);
				System.out.println("First Name:" + otd.getOtd_suppliername());
				return new Status("Otd added Successfully !");				
			}
			else {
				System.out.println("UPDATE ");
				String finalAvgResult = String.format("%.2f", avgResult);
				double avgResultNew = Double.parseDouble(finalAvgResult);
				otd.setOtd_ytd(avgResultNew);
				otd.setDeletes(1);
				otd.setOtd_id(fndotd.get(0).getOtd_id());
				otdServices.addOtd(otd);
				System.out.println("First Name:" + otd.getOtd_suppliername());
				return new Status("Otd added Successfully !");				
			} /*{
				System.out.println("already Exit ");
				otdServices.addOtd(otd);
				System.out.println("b2222");
				return new Status("Already Exits");
			}*/
			
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status editOtd(@RequestBody Otd otd) {
		try {
				int counter = 0; // for dividesion
			double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0, juneValue = 0, julyValue = 0,
					augValue = 0, sepValue = 0, octValue = 0, novValue = 0, decValue = 0;

			
				
			
				
				if (otd.getOtd_jan()!=null) {
					counter++;
					janValue = Double.parseDouble(otd.getOtd_jan().substring(0, otd.getOtd_jan().length() - 1));
					System.out.println("jan Value"+janValue);
				} 
	
				
				if (otd.getOtd_feb()!=null) {
					counter++;
					febValue = Double.parseDouble(otd.getOtd_feb().substring(0, otd.getOtd_feb().length() - 1));
					System.out.println("Feb Value"+febValue);
				}
				
				
				if (otd.getOtd_mar()!=null) {
					counter++;
					marValue = Double.parseDouble(otd.getOtd_mar().substring(0, otd.getOtd_mar().length() - 1));
					System.out.println("MAr Value"+marValue);
					
				}
				
				
				if (otd.getOtd_apr()!=null) {
					counter++;
					aprValue = Double.parseDouble(otd.getOtd_apr().substring(0, otd.getOtd_apr().length() - 1));
					System.out.println("ARl Value"+aprValue);
					
				}
				
				
				if (otd.getOtd_may()!=null) {
					counter++;
					mayValue = Double.parseDouble(otd.getOtd_may().substring(0, otd.getOtd_may().length() - 1));
					System.out.println("MAy Value"+mayValue);
				}
				
				
				if (otd.getOtd_june()!=null) {
					counter++;
					juneValue = Double.parseDouble(otd.getOtd_june().substring(0, otd.getOtd_june().length() - 1));
					System.out.println("Jun Value"+juneValue);
				} 
				
				
				if (otd.getOtd_july()!=null) {
					counter++;
					julyValue = Double.parseDouble(otd.getOtd_july().substring(0, otd.getOtd_july().length() - 1));
					System.out.println("July Value"+julyValue);

				}
				
				
				if (otd.getOtd_aug()!=null) {
					counter++;
					augValue = Double.parseDouble(otd.getOtd_aug().substring(0, otd.getOtd_aug().length() - 1));
					System.out.println("Aug Value"+augValue);
				}
				
				
				if (otd.getOtd_sep()!=null) {
					counter++;
					sepValue = Double.parseDouble(otd.getOtd_sep().substring(0, otd.getOtd_sep().length() - 1));
					System.out.println("SEP Value"+sepValue);
					
				}
		
				
				if (otd.getOtd_oct()!=null) {
					counter++;
					octValue = Double.parseDouble(otd.getOtd_oct().substring(0, otd.getOtd_oct().length() - 1));
					System.out.println("OTC Value"+octValue);
				}
				
				
				if (otd.getOtd_dec()!=null) {
					counter++;
					decValue = Double.parseDouble(otd.getOtd_dec().substring(0, otd.getOtd_dec().length() - 1));
					System.out.println("DEC Value"+decValue);
				} 
				
				
				if (otd.getOtd_nov()!=null) {
					counter++;
					novValue = Double.parseDouble(otd.getOtd_nov().substring(0, otd.getOtd_nov().length() - 1));
					System.out.println("NOV Value"+novValue);
				}
				
				
		
			System.out.println("1111");
			double monthSum = janValue + febValue + marValue + aprValue + mayValue + juneValue + julyValue + augValue
					+ sepValue + octValue + novValue + decValue;
			//double avgResult = monthSum / counter;
			double avgResult = 0;
			if (monthSum == 0.0) {

				avgResult = 0;
			} else {

				avgResult = monthSum / counter;
			}
			
			String supplierCode=otd.getOtd_suppliercode();
			String otdYear =otd.getOtd_year();
			String supplierType = otd.getOtd_suppliertype();
			System.out.println("Year "+otdYear);
			List<Otd> fndotd = otdServices.getOtdBySupplierTypeYear(supplierCode,supplierType,otdYear);
			System.out.println("Size"+fndotd.size());
			if(fndotd.size()==0){
				System.out.println("If Null");
				String finalAvgResult = String.format("%.2f", avgResult);
				double avgResultNew = Double.parseDouble(finalAvgResult);
				otd.setOtd_ytd(avgResultNew);
				otd.setDeletes(1);
				
				otdServices.addOtd(otd);
				System.out.println("First Name:" + otd.getOtd_suppliername());
				return new Status("Otd added Successfully !");				
			}
			else{
				System.out.println("If Null");
				String finalAvgResult = String.format("%.2f", avgResult);
				double avgResultNew = Double.parseDouble(finalAvgResult);
				otd.setOtd_ytd(avgResultNew);
				otd.setDeletes(1);
				
				otdServices.addOtd(otd);
				System.out.println("First Name:" + otd.getOtd_suppliername());
				return new Status("Otd added Successfully !");				
			} 
			
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Otd> getOtds() {
		List<Otd> otdList = null;
		try {
			otdList = otdServices.getOtds();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otdList;
	}
	
	@RequestMapping(value = "/lowper/{monthCol}/{backYear}", method = RequestMethod.GET)
	public @ResponseBody List<Otd> getLowPer(@PathVariable("monthCol") String monthCol,@PathVariable("backYear") String backYear) {
		List<Otd> otdList = null;
		try {
			otdList = otdServices.getLowPer(monthCol,backYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otdList;
	}

	@RequestMapping(value = "/avg", method = RequestMethod.GET)
	public @ResponseBody List<Otd> getAvg() {
		List<Otd> otdList = null;
		try {
			otdList = otdServices.getAvg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otdList;
	}

	@RequestMapping(value = "/monthlyreport", method = RequestMethod.GET)
	public @ResponseBody List<Object> getMonthly() {
		List<Object> otdList = null;
		try {
			otdList = otdServices.getMonthly();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otdList;
	}

	@RequestMapping(value = "/avg3", method = RequestMethod.GET)
	public @ResponseBody List<Otd> getAvg3() {
		List<Otd> otdList = null;
		try {
			otdList = otdServices.getAvg3();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otdList;
	}

	@RequestMapping(value = "/delete/{otd_id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteOtd(@PathVariable("otd_id") int otd_id) {
		try {
			otdServices.deleteOtd(otd_id);
			return new Status("Otd Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/search1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Otd> searchOtd1(@RequestBody Otd otd) {
		List<Otd> otdList = null;
		try {
			otdList = otdServices.searchOtd1(otd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otdList;
	}

	/*--------------------unique serch data---------------------*/
	@RequestMapping(value = "/uniq", method = RequestMethod.GET)
	public @ResponseBody List<Otd> getOtds1() {
		List<Otd> otdList = null;
		try {
			otdList = otdServices.getOtds1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otdList;
	}
	/*--------------------unique serch data---------------------*/

}
