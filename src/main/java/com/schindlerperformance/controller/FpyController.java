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

import com.schindlerperformance.model.Fpy;
import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.model.Supplier;
import com.schindlerperformance.model.Supplierstatus;
import com.schindlerperformance.services.FpyServices;
import com.schindlerperformance.services.PerformanceServices;
import com.schindlerperformance.services.SupplierServices;
import com.schindlerperformance.services.SupplierstatusServices;


@Controller
@RequestMapping("/fpy")
public class FpyController {

	@Autowired
	FpyServices fpyServices;

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
				//	//logger.info("File not found");
				} else {
					////logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						////logger.info("hiiii@" + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0);
						DataFormatter formatter = new DataFormatter();
						////logger.info(i <= datatypeSheet.getLastRowNum());
						while (i <= datatypeSheet.getLastRowNum()) {

							Fpy fpy = new Fpy();
							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							if (row.getCell(0) != null) {

								String supplieCode = formatter.formatCellValue(row.getCell(0));
								String supplierName = formatter.formatCellValue(row.getCell(1));
								String supplierType = formatter.formatCellValue(row.getCell(2));
								String supplieryear = formatter.formatCellValue(row.getCell(3));
								List<Fpy> fpySuppNameType = fpyServices.fpyUpdateBySuppCodeAndSuppType(supplieCode,
										supplierType, supplieryear);
								List<Supplier> supplierList = supplierServices.getSuppNameSuppTypeSuppCode(supplieCode,
										supplierName, supplierType);

								List<Supplierstatus> supplerStatusList = supplierstatusServices
										.suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(supplieCode, supplierType,
												supplieryear);

								List<Performance> performanceList = performanceServices
										.performanceUpdateBySuppCodeSuppTypeAndSuppYear(supplieCode, supplierType,
												supplieryear);

								if (supplierList.size() == 0) {
									Supplier supp = new Supplier();
									supp.setSupplier_code(supplieCode);
									supp.setSupplier_name(supplierName);
									supp.setSupplier_type(supplierType);
									supp.setDeletes(1);
									supplierServices.addSupplier(supp);
									////logger.info(" new supplier insert here......");
								}

								if (performanceList.size() == 0) {
									Performance per = new Performance();
									per.setPerformance_suppliercode(supplieCode);
									per.setPerformance_suppliername(supplierName);
									per.setPerformance_suppliertype(supplierType);
									per.setPerformance_year(supplieryear);
									per.setDeletes(1);
									performanceServices.addPerformance(per);
									//.datatypeSheet.//logger.info(" new performace insert here...");
								}

								double avg_fpy = 0;

								if (fpySuppNameType.size() != 0) {
									for (Fpy fpyObj : fpySuppNameType) {
										if (fpyObj.getFpy_suppliercode().equals(supplieCode)
												&& fpyObj.getFpy_suppliertype().equals(supplierType)
												&& fpyObj.getFpy_year().equals(supplieryear)) {

											double counter = 0; // for
																// dividesion
											double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0,
													juneValue = 0, julyValue = 0, augValue = 0, sepValue = 0,
													octValue = 0, novValue = 0, decValue = 0;

											fpyObj.setFpy_suppliername(String.valueOf(row.getCell(1)));

											// String year =
											// formatter.formatCellValue(row.getCell(3));
											// fpyObj.setFpy_year(year);

											String jan = formatter.formatCellValue(row.getCell(4));
											if (!jan.isEmpty()) {
												counter++;
												fpyObj.setFpy_jan(jan);
												////logger.info(jan);
												janValue = Double.parseDouble(jan.substring(0, jan.length() - 1));
												////logger.info(janValue);
											} else {
												fpyObj.setFpy_jan(jan);
											}

											String feb = formatter.formatCellValue(row.getCell(5));
											if (!feb.isEmpty()) {
												counter++;
												fpyObj.setFpy_feb(feb);
												febValue = Double.parseDouble(feb.substring(0, feb.length() - 1));
											} else {
												fpyObj.setFpy_feb(feb);
											}

											String mar = formatter.formatCellValue(row.getCell(6));
											if (!mar.isEmpty()) {
												counter++;
												fpyObj.setFpy_mar(mar);
												marValue = Double.parseDouble(mar.substring(0, mar.length() - 1));
											} else {
												fpyObj.setFpy_mar(mar);
											}

											String apr = formatter.formatCellValue(row.getCell(7));
											if (!apr.isEmpty()) {
												counter++;
												fpyObj.setFpy_apr(apr);
												aprValue = Double.parseDouble(apr.substring(0, apr.length() - 1));
											} else {
												fpyObj.setFpy_apr(apr);
											}

											String may = formatter.formatCellValue(row.getCell(8));
											if (!may.isEmpty()) {
												counter++;
												fpyObj.setFpy_may(may);
												mayValue = Double.parseDouble(may.substring(0, may.length() - 1));

											} else {
												fpyObj.setFpy_may(may);
											}

											String june = formatter.formatCellValue(row.getCell(9));
											if (!june.isEmpty()) {
												counter++;
												fpyObj.setFpy_june(june);
												juneValue = Double.parseDouble(june.substring(0, june.length() - 1));
											} else {
												fpyObj.setFpy_june(june);
											}

											String july = formatter.formatCellValue(row.getCell(10));
											if (!july.isEmpty()) {
												counter++;
												fpyObj.setFpy_july(july);
												julyValue = Double.parseDouble(july.substring(0, july.length() - 1));
											} else {
												fpyObj.setFpy_july(july);
											}

											String aug = formatter.formatCellValue(row.getCell(11));
											if (!aug.isEmpty()) {
												counter++;
												fpyObj.setFpy_aug(aug);
												augValue = Double.parseDouble(aug.substring(0, aug.length() - 1));
											} else {
												fpyObj.setFpy_aug(aug);
											}

											String sep = formatter.formatCellValue(row.getCell(12));
											if (!sep.isEmpty()) {
												counter++;
												fpyObj.setFpy_sep(sep);
												sepValue = Double.parseDouble(sep.substring(0, sep.length() - 1));
											} else {
												fpyObj.setFpy_sep(sep);
											}

											String oct = formatter.formatCellValue(row.getCell(13));
											if (!oct.isEmpty()) {
												counter++;
												fpyObj.setFpy_oct(oct);
												octValue = Double.parseDouble(oct.substring(0, oct.length() - 1));
											} else {
												fpyObj.setFpy_oct(oct);
											}

											String nov = formatter.formatCellValue(row.getCell(14));
											if (!nov.isEmpty()) {
												counter++;
												novValue = Double.parseDouble(nov.substring(0, nov.length() - 1));
												fpyObj.setFpy_nov(nov);
											} else {
												fpyObj.setFpy_nov(nov);
											}

											String dec = formatter.formatCellValue(row.getCell(15));
											if (!dec.isEmpty()) {
												counter++;
												fpyObj.setFpy_dec(dec);
												decValue = Double.parseDouble(dec.substring(0, dec.length() - 1));
											} else {
												fpyObj.setFpy_dec(dec);
											}

											double monthSum = janValue + febValue + marValue + aprValue + mayValue
													+ juneValue + julyValue + augValue + sepValue + octValue + novValue
													+ decValue;
											/* double avgResult = monthSum / counter; */
											System.out.println("MAY FROM EXEL==="+mayValue);
											double avgResult = 0;
											if (monthSum == 0.0) {

												avgResult = 0;
											} else {

												avgResult = monthSum / counter;
											}
											
											System.out.println("SUPPLIER2  "+1157714+" : "+fpy.getFpy_suppliername() );
											System.out.println("MONTH SUM2"+monthSum );
											System.out.println("Coutnter2 "+counter );
											System.out.println("PER2 "+monthSum/counter );
											
											
											////logger.info(avgResult);
											String finalAvgResult = String.format("%.2f", avgResult);
											double finalavg = Double.parseDouble(finalAvgResult);
											avg_fpy = finalavg;
											fpyObj.setFpy_ytd(finalavg);
											fpyObj.setDeletes(1);
											fpyServices.addFpy(fpyObj);
											////logger.info("update");
										} else {

											double counter = 0; // for
																// dividesion
											double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0,
													juneValue = 0, julyValue = 0, augValue = 0, sepValue = 0,
													octValue = 0, novValue = 0, decValue = 0;
											String suppliecode1 = formatter.formatCellValue(row.getCell(0));
											fpy.setFpy_suppliercode(suppliecode1);

											fpy.setFpy_suppliername(String.valueOf(row.getCell(1)));

											String supplietype = formatter.formatCellValue(row.getCell(2));
											fpy.setFpy_suppliertype(supplietype);

											String year = formatter.formatCellValue(row.getCell(3));
											fpy.setFpy_year(year);

											String jan = formatter.formatCellValue(row.getCell(4));
											if (!jan.isEmpty()) {
												counter++;
												fpy.setFpy_jan(jan);
												janValue = Double.parseDouble(jan.substring(0, jan.length() - 1));
											} else {
												fpy.setFpy_jan(jan);
											}

											String feb = formatter.formatCellValue(row.getCell(5));
											if (!feb.isEmpty()) {
												counter++;
												fpy.setFpy_feb(feb);
												febValue = Double.parseDouble(feb.substring(0, feb.length() - 1));
											} else {
												fpy.setFpy_feb(feb);
											}

											String mar = formatter.formatCellValue(row.getCell(6));
											if (!mar.isEmpty()) {
												counter++;
												fpy.setFpy_mar(mar);
												marValue = Double.parseDouble(mar.substring(0, mar.length() - 1));
											} else {
												fpy.setFpy_mar(mar);
											}

											String apr = formatter.formatCellValue(row.getCell(7));
											if (!apr.isEmpty()) {
												counter++;
												fpy.setFpy_apr(apr);
												aprValue = Double.parseDouble(apr.substring(0, apr.length() - 1));
											} else {
												fpy.setFpy_apr(apr);
											}

											String may = formatter.formatCellValue(row.getCell(8));
											if (!may.isEmpty()) {
												counter++;
												fpy.setFpy_may(may);
												mayValue = Double.parseDouble(may.substring(0, may.length() - 1));
											} else {
												fpy.setFpy_may(may);
											}

											String june = formatter.formatCellValue(row.getCell(9));
											if (!june.isEmpty()) {
												counter++;
												fpy.setFpy_june(june);
												juneValue = Double.parseDouble(june.substring(0, june.length() - 1));
											} else {
												fpy.setFpy_june(june);
											}

											String july = formatter.formatCellValue(row.getCell(10));
											if (!july.isEmpty()) {
												counter++;
												fpy.setFpy_july(july);
												julyValue = Double.parseDouble(july.substring(0, july.length() - 1));
											} else {
												fpy.setFpy_july(july);
											}

											String aug = formatter.formatCellValue(row.getCell(11));
											if (!aug.isEmpty()) {
												counter++;
												fpy.setFpy_aug(aug);
												augValue = Double.parseDouble(aug.substring(0, aug.length() - 1));
											} else {
												fpy.setFpy_aug(aug);
											}

											String sep = formatter.formatCellValue(row.getCell(12));
											if (!sep.isEmpty()) {
												counter++;
												fpy.setFpy_sep(sep);
												sepValue = Double.parseDouble(sep.substring(0, sep.length() - 1));
											} else {
												fpy.setFpy_sep(sep);
											}

											String oct = formatter.formatCellValue(row.getCell(13));
											if (!oct.isEmpty()) {
												counter++;
												fpy.setFpy_oct(oct);
												octValue = Double.parseDouble(oct.substring(0, oct.length() - 1));
											} else {
												fpy.setFpy_oct(oct);
											}

											String nov = formatter.formatCellValue(row.getCell(14));
											if (!nov.isEmpty()) {
												counter++;
												novValue = Double.parseDouble(nov.substring(0, nov.length() - 1));
												fpy.setFpy_nov(nov);
											} else {
												fpy.setFpy_nov(nov);
											}

											String dec = formatter.formatCellValue(row.getCell(15));
											if (!dec.isEmpty()) {
												counter++;
												fpy.setFpy_dec(dec);
												decValue = Double.parseDouble(dec.substring(0, dec.length() - 1));
											} else {
												fpy.setFpy_dec(dec);
											}

											double monthSum = janValue + febValue + marValue + aprValue + mayValue
													+ juneValue + julyValue + augValue + sepValue + octValue + novValue
													+ decValue;
											// double avgResult = monthSum / counter;
											double avgResult = 0;
											if (monthSum == 0.0) {

												avgResult = 0;
											} else {

												avgResult = monthSum / counter;
											}
											
											System.out.println("SUPPLIER  "+fpy.getFpy_suppliercode()+" : "+fpy.getFpy_suppliername() );
											System.out.println("MONTH SUM"+monthSum );
											System.out.println("Coutnter "+counter );
											System.out.println("PER "+monthSum/counter );

											//logger.info(avgResult);
											String finalAvgResult = String.format("%.2f", avgResult);
											double finalavg2 = Double.parseDouble(finalAvgResult);
											avg_fpy = finalavg2;
											fpy.setFpy_ytd(finalavg2);
											fpy.setDeletes(1);
											fpyServices.addFpy(fpy);
											////logger.info("insert");
										}
									}
								} else {
									double counter = 0; // for dividesion
									double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0,
											juneValue = 0, julyValue = 0, augValue = 0, sepValue = 0, octValue = 0,
											novValue = 0, decValue = 0;
									String suppliecode1 = formatter.formatCellValue(row.getCell(0));
									fpy.setFpy_suppliercode(suppliecode1);

									fpy.setFpy_suppliername(String.valueOf(row.getCell(1)));

									String supplietype = formatter.formatCellValue(row.getCell(2));
									fpy.setFpy_suppliertype(supplietype);

									String year = formatter.formatCellValue(row.getCell(3));
									fpy.setFpy_year(year);

									String jan = formatter.formatCellValue(row.getCell(4));
									if (!jan.isEmpty()) {
										counter++;
										fpy.setFpy_jan(jan);
										//logger.info(jan);
										janValue = Double.parseDouble(jan.substring(0, jan.length() - 1));
									} else {
										fpy.setFpy_jan(jan);
									}

									String feb = formatter.formatCellValue(row.getCell(5));
									if (!feb.isEmpty()) {
										counter++;
										fpy.setFpy_feb(feb);
										febValue = Double.parseDouble(feb.substring(0, feb.length() - 1));
									} else {
										fpy.setFpy_feb(feb);
									}

									String mar = formatter.formatCellValue(row.getCell(6));
									if (!mar.isEmpty()) {
										counter++;
										fpy.setFpy_mar(mar);
										marValue = Double.parseDouble(mar.substring(0, mar.length() - 1));
									} else {
										fpy.setFpy_mar(mar);
									}

									String apr = formatter.formatCellValue(row.getCell(7));
									if (!apr.isEmpty()) {
										counter++;
										fpy.setFpy_apr(apr);
										aprValue = Double.parseDouble(apr.substring(0, apr.length() - 1));
									} else {
										fpy.setFpy_apr(apr);
									}

									String may = formatter.formatCellValue(row.getCell(8));
									if (!may.isEmpty()) {
										counter++;
										fpy.setFpy_may(may);
										mayValue = Double.parseDouble(may.substring(0, may.length() - 1));
									} else {
										fpy.setFpy_may(may);
									}

									String june = formatter.formatCellValue(row.getCell(9));
									if (!june.isEmpty()) {
										counter++;
										fpy.setFpy_june(june);
										juneValue = Double.parseDouble(june.substring(0, june.length() - 1));
									} else {
										fpy.setFpy_june(june);
									}

									String july = formatter.formatCellValue(row.getCell(10));
									if (!july.isEmpty()) {
										counter++;
										fpy.setFpy_july(july);
										julyValue = Double.parseDouble(july.substring(0, july.length() - 1));
									} else {
										fpy.setFpy_july(july);
									}

									String aug = formatter.formatCellValue(row.getCell(11));
									if (!aug.isEmpty()) {
										counter++;
										fpy.setFpy_aug(aug);
										augValue = Double.parseDouble(aug.substring(0, aug.length() - 1));
									} else {
										fpy.setFpy_aug(aug);
									}

									String sep = formatter.formatCellValue(row.getCell(12));
									if (!sep.isEmpty()) {
										counter++;
										fpy.setFpy_sep(sep);
										sepValue = Double.parseDouble(sep.substring(0, sep.length() - 1));
									} else {
										fpy.setFpy_sep(sep);
									}

									String oct = formatter.formatCellValue(row.getCell(13));
									if (!oct.isEmpty()) {
										counter++;
										fpy.setFpy_oct(oct);
										octValue = Double.parseDouble(oct.substring(0, oct.length() - 1));
									} else {
										fpy.setFpy_oct(oct);
									}

									String nov = formatter.formatCellValue(row.getCell(14));
									if (!nov.isEmpty()) {
										counter++;
										novValue = Double.parseDouble(nov.substring(0, nov.length() - 1));
										fpy.setFpy_nov(nov);
									} else {
										fpy.setFpy_nov(nov);
									}

									String dec = formatter.formatCellValue(row.getCell(15));
									if (!dec.isEmpty()) {
										counter++;
										fpy.setFpy_dec(dec);
										decValue = Double.parseDouble(dec.substring(0, dec.length() - 1));
									} else {
										fpy.setFpy_dec(dec);
									}

									double monthSum = janValue + febValue + marValue + aprValue + mayValue + juneValue
											+ julyValue + augValue + sepValue + octValue + novValue + decValue;

									double avgResult = 0;
									if (monthSum == 0.0) {

										avgResult = 0;
									} else {

										avgResult = monthSum / counter;
									}
									
									System.out.println("SUPPLIER1  "+fpy.getFpy_suppliercode()+" : "+fpy.getFpy_suppliername() );
									System.out.println("MONTH SUM1"+monthSum );
									System.out.println("Coutnter1 "+counter );
									System.out.println("PER 1"+monthSum/counter );

									//logger.info(avgResult);

									String finalAvgResult = String.format("%.2f", avgResult);
									double finalavg3 = Double.parseDouble(finalAvgResult);
									avg_fpy = finalavg3;
									fpy.setFpy_ytd(finalavg3);
									fpy.setDeletes(1);
									fpyServices.addFpy(fpy);
									//logger.info("insert");
								}

								if (supplerStatusList.size() == 0) {
									Supplierstatus suppObj = new Supplierstatus();
									suppObj.setSupplierstatus_suppliercode(supplieCode);
									suppObj.setSupplierstatus_suppliername(supplierName);
									suppObj.setSupplierstatus_suppliertype(supplierType);
									suppObj.setSupplierstatus_year(supplieryear);
									String avg_otd2 = Double.toString(avg_fpy);
									suppObj.setSupplierstatus_fpy(avg_otd2);
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
											String avg_otd2 = Double.toString(avg_fpy);
											ss.setSupplierstatus_fpy(avg_otd2);
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
	public @ResponseBody Status addFpy(@RequestBody Fpy fpy) {
		try {

			int counter = 0; // for dividesion
			double janValue = 0, febValue = 0, marValue = 0, aprValue = 0, mayValue = 0, juneValue = 0, julyValue = 0,
					augValue = 0, sepValue = 0, octValue = 0, novValue = 0, decValue = 0;

			if (fpy.getFpy_id() != 0) {
				if(fpy.getFpy_jan() == "") {
					fpy.setFpy_jan(null);
				}
				if (fpy.getFpy_jan() != null) {
					counter++;
					janValue = Double.parseDouble(fpy.getFpy_jan().substring(0, fpy.getFpy_jan().length() - 1));
				}

				if(fpy.getFpy_feb() == "") {
					fpy.setFpy_feb(null);
				}
				if (fpy.getFpy_feb() != null) {
					counter++;
					febValue = Double.parseDouble(fpy.getFpy_feb().substring(0, fpy.getFpy_feb().length() - 1));
				}

				if(fpy.getFpy_mar() == "")
				{
					fpy.setFpy_mar(null);
				}
				if (fpy.getFpy_mar() != null) {
					counter++;
					marValue = Double.parseDouble(fpy.getFpy_mar().substring(0, fpy.getFpy_mar().length() - 1));
				}

				if(fpy.getFpy_apr() == "") {
					fpy.setFpy_apr(null);
				}
				if (fpy.getFpy_apr() != null) {
					counter++;
					aprValue = Double.parseDouble(fpy.getFpy_apr().substring(0, fpy.getFpy_apr().length() - 1));
				}

				if(fpy.getFpy_may() == "")
				{
					fpy.setFpy_may(null);
				}
				if (fpy.getFpy_may() != null) {
					counter++;
					mayValue = Double.parseDouble(fpy.getFpy_may().substring(0, fpy.getFpy_may().length() - 1));
				} 
				
				if(fpy.getFpy_june() == "")
				{
					fpy.setFpy_june(null);
				}
				if (fpy.getFpy_june() != null) {
					counter++;
					juneValue = Double.parseDouble(fpy.getFpy_june().substring(0, fpy.getFpy_june().length() - 1));
				} 

				if(fpy.getFpy_july() == "") {
					fpy.setFpy_july(null);
				}
				if (fpy.getFpy_july() != null) {
					counter++;
					julyValue = Double.parseDouble(fpy.getFpy_july().substring(0, fpy.getFpy_july().length() - 1));
				} 
				
				if(fpy.getFpy_aug() == "") {
					fpy.setFpy_aug(null);
				}
				if (fpy.getFpy_aug() != null) {
					counter++;
					augValue = Double.parseDouble(fpy.getFpy_aug().substring(0, fpy.getFpy_aug().length() - 1));
				}
				if(fpy.getFpy_sep() == "") {
					fpy.setFpy_sep(null);
				}
				if (fpy.getFpy_sep() != null) {
					counter++;
					sepValue = Double.parseDouble(fpy.getFpy_sep().substring(0, fpy.getFpy_sep().length() - 1));
				}
				if(fpy.getFpy_oct() == "") {
					fpy.setFpy_oct(null);
				}
				if (fpy.getFpy_oct() != null) {
					counter++;
					octValue = Double.parseDouble(fpy.getFpy_oct().substring(0, fpy.getFpy_oct().length() - 1));
				}
				
				if(fpy.getFpy_nov() == "") {
					fpy.setFpy_nov(null);
				}
				if (fpy.getFpy_nov() != null) {
					counter++;
					novValue = Double.parseDouble(fpy.getFpy_nov().substring(0, fpy.getFpy_nov().length() - 1));
				} 
				
				if(fpy.getFpy_dec()== "")
				{
					fpy.setFpy_dec(null);
				}
				if (fpy.getFpy_dec() != null) {
					counter++;
					decValue = Double.parseDouble(fpy.getFpy_dec().substring(0, fpy.getFpy_dec().length() - 1));
				}
				
			} else {
				if(fpy.getFpy_jan() == "" )
				{
					fpy.setFpy_jan(null);
				}
				if (fpy.getFpy_jan() != null) {
					counter++;
					janValue = Double.parseDouble(fpy.getFpy_jan().substring(0, fpy.getFpy_jan().length() - 1));
				}

				if(fpy.getFpy_feb()== "") {
					fpy.setFpy_feb(null);
				}
				if (fpy.getFpy_feb() != null) {
					counter++;
					febValue = Double.parseDouble(fpy.getFpy_feb().substring(0, fpy.getFpy_feb().length() - 1));
				}
				
				if(fpy.getFpy_mar() == "") {
					fpy.setFpy_feb(null);
				}
				if (fpy.getFpy_mar() != null) {
					counter++;
					marValue = Double.parseDouble(fpy.getFpy_mar().substring(0, fpy.getFpy_mar().length() - 1));
				}
				
				if(fpy.getFpy_apr() == "") {
					fpy.setFpy_apr(null);
				}
				if (fpy.getFpy_apr() !=null) {
					counter++;
					aprValue = Double.parseDouble(fpy.getFpy_apr().substring(0, fpy.getFpy_apr().length() - 1));
				}
				
				if(fpy.getFpy_may() == "") {
					fpy.setFpy_may(null);
				}
				if (fpy.getFpy_may() != null) {
					counter++;
					mayValue = Double.parseDouble(fpy.getFpy_may().substring(0, fpy.getFpy_may().length() - 1));
				}

				if(fpy.getFpy_june() == "") {
					fpy.setFpy_june(null);
				}
				if (fpy.getFpy_june() != null) {
					counter++;
					juneValue = Double.parseDouble(fpy.getFpy_june().substring(0, fpy.getFpy_june().length() - 1));
				} 

				if(fpy.getFpy_july() == "") {
					fpy.setFpy_july(null);
				}
				if (fpy.getFpy_july() != null) {
					counter++;
					julyValue = Double.parseDouble(fpy.getFpy_july().substring(0, fpy.getFpy_july().length() - 1));
				}
				
				if(fpy.getFpy_aug() == "") {
					fpy.setFpy_aug(null);
				}
				if (fpy.getFpy_aug() != null) {
					counter++;
					augValue = Double.parseDouble(fpy.getFpy_aug().substring(0, fpy.getFpy_aug().length() - 1));
				}
				
				if(fpy.getFpy_sep() == "") {
					fpy.setFpy_sep(null);
				}
				if (fpy.getFpy_sep() != null) {
					counter++;
					sepValue = Double.parseDouble(fpy.getFpy_sep().substring(0, fpy.getFpy_sep().length() - 1));
				}
				
				if(fpy.getFpy_sep() == "") {
					fpy.setFpy_sep(null);
				}
				if (fpy.getFpy_oct() != null) {
					counter++;
					octValue = Double.parseDouble(fpy.getFpy_oct().substring(0, fpy.getFpy_oct().length() - 1));
				} 

				if(fpy.getFpy_nov() == "") {
					fpy.setFpy_nov(null);
				}
				if (fpy.getFpy_nov() != null) {
					counter++;
					novValue = Double.parseDouble(fpy.getFpy_nov().substring(0, fpy.getFpy_nov().length() - 1));
				}

				if(fpy.getFpy_dec() == "") {
					fpy.setFpy_dec(null);
				}
				if (fpy.getFpy_dec() != null) {
					counter++;
					decValue = Double.parseDouble(fpy.getFpy_dec().substring(0, fpy.getFpy_dec().length() - 1));
				}
			}

			double monthSum = janValue + febValue + marValue + aprValue + mayValue + juneValue + julyValue + augValue
					+ sepValue + octValue + novValue + decValue;
			/* double avgResult = monthSum / counter; */
			double avgResult = 0.0;
			if (monthSum == 0.0) {

				avgResult = 0.0;
			} else {

				avgResult = monthSum / counter;
			}

			String finalAvgResult = String.format("%.2f", avgResult);
			double finalavg = Double.parseDouble(finalAvgResult);
			String supplierCode = fpy.getFpy_suppliercode();
			String suplierType = fpy.getFpy_suppliertype();
			String supplierYear = fpy.getFpy_year();
			List<Fpy> FpyList = fpyServices.fpyUpdateBySuppCodeAndSuppType(supplierCode, suplierType, supplierYear);
			if (FpyList.size() == 0) {
				fpy.setFpy_ytd(finalavg);
				fpy.setDeletes(1);
				fpyServices.addFpy(fpy);
				return new Status("Fpy added Successfully !");
			} else {
				fpy.setFpy_ytd(finalavg);
				fpy.setDeletes(1);
				fpy.setFpy_id(FpyList.get(0).getFpy_id());
				fpyServices.addFpy(fpy);
				return new Status("Fpy Updated Successfully !");
			} /*else {
				return new Status("Something Went Wrong !");
			}*/
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Fpy> getFpys() {
		List<Fpy> fpyList = null;
		try {
			fpyList = fpyServices.getFpys();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fpyList;
	}

	@RequestMapping(value = "/avg", method = RequestMethod.GET)
	public @ResponseBody List<Fpy> getAvg() {
		List<Fpy> fpyList = null;
		try {
			fpyList = fpyServices.getAvg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fpyList;
	}

	@RequestMapping(value = "/avg4", method = RequestMethod.GET)
	public @ResponseBody List<Fpy> getAvg4() {
		List<Fpy> fpyList = null;
		try {
			fpyList = fpyServices.getAvg4();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fpyList;
	}

	@RequestMapping(value = "/delete/{fpy_id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteFpy(@PathVariable("fpy_id") int fpy_id) {
		try {
			fpyServices.deleteFpy(fpy_id);
			return new Status("Fpy Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/search1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Fpy> searchFpy1(@RequestBody Fpy fpy) {
		List<Fpy> fpyList = null;
		try {
			fpyList = fpyServices.searchFpy1(fpy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fpyList;
	}

	/*--------------------unique serch data---------------------*/
	@RequestMapping(value = "/uniq", method = RequestMethod.GET)
	public @ResponseBody List<Fpy> getFpys1() {
		List<Fpy> fpyList = null;
		try {
			fpyList = fpyServices.getFpys1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fpyList;
	}
	/*--------------------unique serch data---------------------*/

	@RequestMapping(value = "/monthlyreport", method = RequestMethod.GET)
	public @ResponseBody List<Object> getMonthly() {
		List<Object> fpyList = null;
		try {
			fpyList = fpyServices.getMonthly();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fpyList;
	}

}
