package com.schindlerperformance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.plaf.ActionMapUIResource;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.schindlerperformance.config.EmailUtility4;
import com.schindlerperformance.dto.FinalReportDto;
import com.schindlerperformance.model.Fpy;
import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.model.Supplierstatus;
import com.schindlerperformance.services.FpyServices;
import com.schindlerperformance.services.OtdServices;
import com.schindlerperformance.services.PerformanceServices;
import com.schindlerperformance.services.PpmServices;
import com.schindlerperformance.services.SupplierstatusServices;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

	@Autowired
	FpyServices fpyServices;

	@Autowired
	OtdServices otdServices;

	@Autowired
	PpmServices ppmServices;
	
	@Autowired
	SupplierstatusServices supplierstatusServices;

	@Autowired
	PerformanceServices performanceServices;

	
	@RequestMapping(value = "/uploadXlsFile", method = RequestMethod.POST)
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

							Performance performance = new Performance();
							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							if (row.getCell(0) != null) {

								String supplierCode = formatter.formatCellValue(row.getCell(0));
								String supplierType = formatter.formatCellValue(row.getCell(2));
								String supplierYear = formatter.formatCellValue(row.getCell(6));
								List<Performance> performanceList = performanceServices.performanceUpdateBySuppCodeSuppTypeAndSuppYear(supplierCode, supplierType, supplierYear);

								if (performanceList.size() != 0) {
									for (Performance performanceObj : performanceList) {
										if (performanceObj.getPerformance_suppliercode().equals(supplierCode)
												&& performanceObj.getPerformance_suppliertype().equals(supplierType)
												&& performanceObj.getPerformance_year().equals(supplierYear)) {

											performanceObj.setPerformance_suppliername(
													formatter.formatCellValue(row.getCell(1)));

											if (formatter.formatCellValue(row.getCell(3)).contains("%")) {
												String otd = formatter.formatCellValue(row.getCell(3));
											//	String otd = formatter.formatCellValue(row.getCell(3)).replace("%", "");
												performanceObj.setPerformance_otd(otd);
											}

											else {

												performanceObj.setPerformance_otd(
														formatter.formatCellValue(row.getCell(3)));

											}

											performanceObj.setPerformance_ppm(formatter.formatCellValue(row.getCell(4)));

											if (formatter.formatCellValue(row.getCell(5)).contains("%")) {
												String status = formatter.formatCellValue(row.getCell(5));
											//	String status = formatter.formatCellValue(row.getCell(5)).replace("%","");
												performanceObj.setPerformance_fpy(status);
											}

											else {

												performanceObj.setPerformance_fpy(
														formatter.formatCellValue(row.getCell(5)));

											}

											performanceObj.setDeletes(1);
											performanceServices.addPerformance(performanceObj);
										} else {
											performance.setPerformance_suppliercode(formatter.formatCellValue(row.getCell(0)));
											performance.setPerformance_suppliername(formatter.formatCellValue(row.getCell(1)));
											performance.setPerformance_suppliertype(formatter.formatCellValue(row.getCell(2)));
											if (formatter.formatCellValue(row.getCell(3)).contains("%")) {

												String otd = formatter.formatCellValue(row.getCell(3)).replace("%", "");
												performanceObj.setPerformance_otd(otd);
											}

											else {

												performanceObj.setPerformance_otd(
														formatter.formatCellValue(row.getCell(3)));

											}

											performance.setPerformance_ppm(formatter.formatCellValue(row.getCell(4)));
								

											if (formatter.formatCellValue(row.getCell(5)).contains("%")) {

												String status = formatter.formatCellValue(row.getCell(5)).replace("%",
														"");
												performanceObj.setPerformance_fpy(status);
											}

											else {

												performanceObj.setPerformance_fpy(
														formatter.formatCellValue(row.getCell(5)));

											}

											performance.setPerformance_year(formatter.formatCellValue(row.getCell(6)));
													
											performance.setDeletes(1);
											performanceServices.addPerformance(performance);
										}
									}
								} else {
									performance.setPerformance_suppliercode(formatter.formatCellValue(row.getCell(0)));
									performance.setPerformance_suppliername(formatter.formatCellValue(row.getCell(1)));
									performance.setPerformance_suppliertype(formatter.formatCellValue(row.getCell(2)));

									if (formatter.formatCellValue(row.getCell(3)).contains("%")) {

										String otd = formatter.formatCellValue(row.getCell(3)).replace("%", "");
										performance.setPerformance_otd(otd);
									}

									else {

										performance.setPerformance_otd(formatter.formatCellValue(row.getCell(3)));

									}

									performance.setPerformance_ppm(formatter.formatCellValue(row.getCell(4)));

									if (formatter.formatCellValue(row.getCell(5)).contains("%")) {

										String status = formatter.formatCellValue(row.getCell(5)).replace("%", "");
										performance.setPerformance_fpy(status);
									}

									else {

										performance.setPerformance_fpy(formatter.formatCellValue(row.getCell(5)));

									}

									performance.setPerformance_year(formatter.formatCellValue(row.getCell(6)));
									performance.setDeletes(1);
									performanceServices.addPerformance(performance);
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
	public @ResponseBody Status addPerformance(@RequestBody Performance performance) {
		try {

			if (performance.getPerformance_id() == 0) {
				if (performance.getPerformance_otd().contains("%") == true) {
				} else {
					performance.setPerformance_otd(performance.getPerformance_otd());
				}

				if (performance.getPerformance_fpy() != null) {

					if (performance.getPerformance_fpy().contains("%") == true) {
					} else {
						performance.setPerformance_fpy(performance.getPerformance_fpy());
					}
				}
			} else if (performance.getPerformance_id() != 0) {
				if (performance.getPerformance_otd().contains("%") == true) {
				} else {
					performance.setPerformance_otd(performance.getPerformance_otd());
				}

				if (performance.getPerformance_fpy() != null) {
					if (performance.getPerformance_fpy().contains("%") == true) {
					} else {
						performance.setPerformance_fpy(performance.getPerformance_fpy());
					}
				}
			} else {
			}
			performanceServices.addPerformance(performance);
			System.out.println("First Name:" + performance.getPerformance_suppliername());
			return new Status("Performance added Successfully !");
		} catch (Exception e) {
			System.out.println(e);
			return new Status(e.toString());
		}
	}

	@RequestMapping(value = "/create1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addPerformance1(@RequestBody Performance performance) {
		try {
			performanceServices.addPerformance(performance);
			return new Status("Performance added Successfully !");
		} catch (Exception e) {
			return new Status(e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Performance> getPerformances() {
		List<Performance> performanceList = null;
		try {
			performanceList = performanceServices.getPerformances();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return performanceList;
	}

	@RequestMapping(value = "/delete/{performance_id}", method = RequestMethod.GET)
	public @ResponseBody Status deletePerformance(@PathVariable("performance_id") int performance_id) {
		try {
			performanceServices.deletePerformance(performance_id);
			return new Status("Performance Deleted Successfully !");
		} catch (Exception e) {
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/sendsms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status sendSms(@RequestBody Performance performance) {
		try {
			String mob = performance.getMobile_no() + "," + performance.getMobile_no2() + ","
					+ performance.getMobile_no3() + "," + performance.getMobile_no4() + ","
					+ performance.getMobile_no5() + "," + performance.getMobile_no6() + ","
					+ performance.getMobile_no7() + "," + performance.getMobile_no8() + ","
					+ performance.getMobile_no9();

			String Message = performance.getPerformance_suppliername();

			URL smsApiLink = new URL(
					"http://trans.dreamztechnolgy.org/SmsStatuswithId.aspx?mobile=8446060888&pass=Advik8&senderid=APTMEE&to="
							+ mob + "&msg=" + Message);

			System.out.println(smsApiLink);

			URLConnection smsConnecion = smsApiLink.openConnection();
			smsConnecion.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(smsConnecion.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();

			return new Status("SMS SEND SUCCESSFULLY !");
		} catch (Exception e) {
			System.out.println(e);
			return new Status(e.toString());
		}
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Status sendMail(@RequestBody Performance performance) {

		String filename = null;

		filename = performance.getPerformance_pdf().split("[/]")[2];
		System.out.println(filename);

		try {
			EmailUtility4.sendEmail4("smtp.outlook.com", "587", "jainnikita995@outlook.com", performance.getMail_id(),
					performance.getMail_id2(), performance.getMail_id3(), performance.getMail_id4(),
					performance.getMail_id5(), performance.getMail_id6(), performance.getMail_id7(),
					performance.getMail_id8(), performance.getMail_id9(), String.valueOf("Subject"), filename,
					"Hello Schindler");
			return new Status("Mail Send  Successfully !");
		} catch (Exception e) {

			System.out.println(e);
			return new Status(e.toString());
		}

	}

	/*-----------------------------------high performance by year------------------------------------*/
	@RequestMapping(value = "/highOcc", method = RequestMethod.GET)
	public @ResponseBody List<Object> getPerformances1() {

		List<Otd> otdList = otdServices.getOtds();
		List<Fpy> fpyList = fpyServices.getFpys();
		List<Ppm> ppmList = ppmServices.getPpms();

		List<Performance> otdList1 = null;
		List<Performance> ppmList1 = null;
		List<Performance> fpyList1 = null;

		int kw_high = 0;
		int occ_high = 0;
		int kw_low = 0;
		int occ_low = 0;
		

		ArrayList<Object> OneList = new ArrayList<>();
		ArrayList<Object> ZeroList = new ArrayList<>();

		List<Object> finalList = new ArrayList<>();
		List<Object> ActualList = new ArrayList<>();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		String yr = Integer.toString(year);

		String otdper = null;
		String ppmper = null;
		String fpyper = null;

		ArrayList<Object> list = new ArrayList<>();

		for (Otd o : otdList) {

			if (o.getOtd_year().equals(yr.toString())) {

				otdList1 = performanceServices.getPerformances5(o.getOtd_suppliercode(), o.getOtd_year(),
						o.getOtd_suppliertype(), o.getOtd_ytd());

				if (otdList1.size() == 0) {

					if (o.getOtd_suppliertype().equalsIgnoreCase("kw")) {
						finalList.add("kw_low");
					} else {
						finalList.add("occ_low");
					}
				}

				for (Ppm p : ppmList) {

					if (otdList1.size() != 0) {

						if (o.getOtd_suppliercode().equals(p.getPpm_suppliercode())
								&& o.getOtd_year().equals(p.getPpm_year())
								&& o.getOtd_suppliertype().equals(p.getPpm_supplierType()))

						{
							if (p.getPpm_year().equals(yr.toString())) {

								ppmList1 = performanceServices.getPerformances6(p.getPpm_suppliercode(),
										p.getPpm_year(), p.getPpm_supplierType(), p.getPpm_ytd());

								if (ppmList1.size() == 0) {

									if (p.getPpm_supplierType().equalsIgnoreCase("kw")) {
										finalList.add("kw_low");
									} else {
										finalList.add("occ_low");
									}
									
								}

								for (Fpy f : fpyList) {

									if (ppmList1.size() != 0) {

										if (o.getOtd_suppliercode().equals(f.getFpy_suppliercode())
												&& o.getOtd_year().equals(f.getFpy_year())
												&& o.getOtd_suppliertype().equals(f.getFpy_suppliertype())) {
											if (f.getFpy_year().equals(yr.toString())) {
												fpyList1 = performanceServices.getPerformances7(f.getFpy_suppliercode(),
														f.getFpy_year(), f.getFpy_suppliertype(), f.getFpy_ytd());

												if (fpyList1.size() != 0) {

													if (f.getFpy_suppliertype().equalsIgnoreCase("kw")) {

														finalList.add("kw_high");
													} else {

														finalList.add("occ_high");
													}

												}

												else {

													if (p.getPpm_supplierType().equalsIgnoreCase("kw")) {
														finalList.add("kw_low");
													} else {
														finalList.add("occ_low");
													}
												}

											}
										}

										else if (p.getPpm_supplierType().equals("kw")) {

											finalList.add("kw_high");

											break;
										}

									}

								}
							}
						}
					}

				}

			}
		}
		for (Object f : finalList) {
			
			if (f.equals("kw_high")) {
				kw_high++;
			}
			else if (f.equals("occ_high")) {
				occ_high++;
			}
			else if (f.equals("kw_low")) {
				kw_low++;
			}
			else if (f.equals("occ_low")) {
				occ_low++;
			}
		}

		ActualList.add(kw_high);
		ActualList.add(occ_high);
		ActualList.add(kw_low);
		ActualList.add(occ_low);

		return ActualList;

	}

	

	@RequestMapping(value = "/highmonth", method = RequestMethod.GET)
	public @ResponseBody List<Object> getPerformances2() {

		List<Otd> otdList = otdServices.getOtds();
		List<Fpy> fpyList = fpyServices.getFpys();
		List<Ppm> ppmList = ppmServices.getPpms();

		List<Performance> otdList1 = null;
		List<Performance> ppmList1 = null;
		List<Performance> fpyList1 = null;

		List<Otd> otdLowList = new ArrayList<>();
		
		int n = 0;
		int g = 0;

		ArrayList<Object> OneList = new ArrayList<>();
		ArrayList<Object> ZeroList = new ArrayList<>();

		List<Object> finalList = new ArrayList<>();
		List<Object> ActualList = new ArrayList<>();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) - 1;

		String yr = Integer.toString(year);
		String m = Integer.toString(month);

		String otdper = null;
		String ppmper = null;
		String fpyper = null;

		ArrayList<Object> list = new ArrayList<>();

		for (Otd o : otdList) {

			if (yr.toString().equals(o.getOtd_year())) {

				if (month == 0) {
					otdper = o.getOtd_jan();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 1) {
					otdper = o.getOtd_feb();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 2) {
					otdper = o.getOtd_mar();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 3) {
					otdper = o.getOtd_apr();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 4) {
					otdper = o.getOtd_may();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 5) {
					otdper = o.getOtd_june();

					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 6) {
					otdper = o.getOtd_july();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 7) {
					otdper = o.getOtd_aug();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 8) {
					otdper = o.getOtd_sep();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 9) {
					otdper = o.getOtd_oct();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 10) {
					otdper = o.getOtd_nov();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}
				if (month == 11) {
					otdper = o.getOtd_dec();
					if (otdper != null && otdper.length() > 1) {
						otdper = otdper.substring(0, otdper.length() - 1);
					}
				}

				otdList1 = performanceServices.getPerformances2(o.getOtd_suppliercode(), o.getOtd_year(),
						o.getOtd_suppliertype(), otdper);

				if (otdList1.size() == 0) {

					finalList.add("0");
					
					otdLowList.add(o);

				}

				for (Ppm p : ppmList) {

					if (otdList1.size() != 0) {

						if (o.getOtd_suppliercode().equals(p.getPpm_suppliercode())
								&& o.getOtd_year().equals(p.getPpm_year())
								&& o.getOtd_suppliertype().equals(p.getPpm_supplierType()))

						{
							if (yr.toString().equals(p.getPpm_year())) {
								if (month == 0) {
									ppmper = p.getPpm_jan();
								}
								if (month == 1) {
									ppmper = p.getPpm_feb();
								}
								if (month == 2) {
									ppmper = p.getPpm_mar();
								}
								if (month == 3) {
									ppmper = p.getPpm_apr();
								}
								if (month == 4) {
									ppmper = p.getPpm_may();
								}
								if (month == 5) {
									ppmper = p.getPpm_june();
								}
								if (month == 6) {
									ppmper = p.getPpm_july();
								}
								if (month == 7) {
									ppmper = p.getPpm_aug();
								}
								if (month == 8) {
									ppmper = p.getPpm_sep();
								}
								if (month == 9) {
									ppmper = p.getPpm_oct();
								}
								if (month == 10) {
									ppmper = p.getPpm_nov();
								}
								if (month == 11) {
									ppmper = p.getPpm_dec();
								}

								ppmList1 = performanceServices.getPerformances3(p.getPpm_suppliercode(),
										p.getPpm_year(), p.getPpm_supplierType(), ppmper);

								if (ppmList1.size() == 0) {

									finalList.add("0");
									
									otdLowList.add(o);
								}

								for (Fpy f : fpyList) {

									if (ppmList1.size() != 0) {

										if (o.getOtd_suppliercode().equals(f.getFpy_suppliercode())
												&& o.getOtd_year().equals(f.getFpy_year())
												&& o.getOtd_suppliertype().equals(f.getFpy_suppliertype())) {
											if (yr.toString().equals(f.getFpy_year())) {
												if (month == 0) {
													fpyper = f.getFpy_jan();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 1) {
													fpyper = f.getFpy_feb();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 2) {
													fpyper = f.getFpy_mar();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 3) {
													fpyper = f.getFpy_apr();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 4) {
													fpyper = f.getFpy_may();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 5) {
													fpyper = f.getFpy_june();

													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 6) {
													fpyper = f.getFpy_july();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 7) {
													fpyper = f.getFpy_aug();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 8) {
													fpyper = f.getFpy_sep();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 9) {
													fpyper = f.getFpy_oct();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 10) {
													fpyper = f.getFpy_nov();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}
												if (month == 11) {
													fpyper = f.getFpy_dec();
													if (fpyper != null && fpyper.length() > 1) {
														fpyper = fpyper.substring(0, fpyper.length() - 1);
													}
												}

												fpyList1 = performanceServices.getPerformances4(f.getFpy_suppliercode(),
														f.getFpy_year(), f.getFpy_suppliertype(), fpyper);

												if (fpyList1.size() != 0) {

													finalList.add("1");
												}

												else {

													finalList.add("0");
													otdLowList.add(o);
												}

											}
										}

										else if (p.getPpm_supplierType().equals("kw")) {

											finalList.add("1");

											break;
										}

									}

								}
							}
						}
					}

				}

			}
		}
		for (Object f : finalList) {

			if (f.equals("1")) {
				n++;
			}

			else {
				g++;
			}
		}

		ActualList.add(n);
		ActualList.add(g);
		
		ActualList.add(otdLowList);

		return ActualList;

	}
	
	
	//Final Report performance
	@RequestMapping(value = "/finalList", method = RequestMethod.GET)
	public @ResponseBody List<FinalReportDto> getList(@RequestParam("year") String year ){
		System.out.println("year "+year);
		List<FinalReportDto> finalReport= performanceServices.getReport(year);		
		System.out.println("List "+finalReport);
		return finalReport;
	}

}