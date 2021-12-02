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

import com.schindlerperformance.model.Supplierstatus;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.services.SupplierstatusServices;

@Controller
@RequestMapping("/supplierstatus")
public class SupplierstatusController {

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
						//logger.info("hiiii@" + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0);
						DataFormatter formatter = new DataFormatter();
						//logger.info(i <= datatypeSheet.getLastRowNum());
						while (i <= datatypeSheet.getLastRowNum()) {

							Supplierstatus supplierstatus = new Supplierstatus();
							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							if (row.getCell(0) != null) {

								String supplierCode = formatter.formatCellValue(row.getCell(0));
								String supplierType = formatter.formatCellValue(row.getCell(2));
								String supplierYear = formatter.formatCellValue(row.getCell(6));
								List<Supplierstatus> supplerStatusList = supplierstatusServices
										.suppleirStatusUpdateBySuppCodeSuppTypeAndSuppYear(supplierCode, supplierType,
												supplierYear);

								if (supplerStatusList.size() != 0) {
									for (Supplierstatus suppObj : supplerStatusList) {
										if (suppObj.getSupplierstatus_suppliercode().equals(supplierCode)
												&& suppObj.getSupplierstatus_suppliertype().equals(supplierType)
												&& suppObj.getSupplierstatus_year().equals(supplierYear)) {

											suppObj.setSupplierstatus_suppliername(
													formatter.formatCellValue(row.getCell(1)));

											if (formatter.formatCellValue(row.getCell(3)).contains("%")) {

												String otd = formatter.formatCellValue(row.getCell(3)).replace("%", "");
												suppObj.setSupplierstatus_otd(otd);
											}

											else {

												suppObj.setSupplierstatus_otd(
														formatter.formatCellValue(row.getCell(3)));

											}

											suppObj.setSupplierstatus_ppm(formatter.formatCellValue(row.getCell(4)));

											if (formatter.formatCellValue(row.getCell(5)).contains("%")) {

												String status = formatter.formatCellValue(row.getCell(5)).replace("%",
														"");
												suppObj.setSupplierstatus_fpy(status);
											}

											else {

												suppObj.setSupplierstatus_fpy(
														formatter.formatCellValue(row.getCell(5)));

											}

											suppObj.setDeletes(1);
											supplierstatusServices.addSupplierstatus(suppObj);
										} else {
											supplierstatus.setSupplierstatus_suppliercode(
													formatter.formatCellValue(row.getCell(0)));
											supplierstatus.setSupplierstatus_suppliername(
													formatter.formatCellValue(row.getCell(1)));
											supplierstatus.setSupplierstatus_suppliertype(
													formatter.formatCellValue(row.getCell(2)));

											if (formatter.formatCellValue(row.getCell(3)).contains("%")) {

												String otd = formatter.formatCellValue(row.getCell(3)).replace("%", "");
												suppObj.setSupplierstatus_otd(otd);
											}

											else {

												suppObj.setSupplierstatus_otd(
														formatter.formatCellValue(row.getCell(3)));

											}

											supplierstatus
													.setSupplierstatus_ppm(formatter.formatCellValue(row.getCell(4)));

											if (formatter.formatCellValue(row.getCell(5)).contains("%")) {

												String status = formatter.formatCellValue(row.getCell(5)).replace("%",
														"");
												suppObj.setSupplierstatus_fpy(status);
											}

											else {

												suppObj.setSupplierstatus_fpy(
														formatter.formatCellValue(row.getCell(5)));

											}

											supplierstatus
													.setSupplierstatus_year(formatter.formatCellValue(row.getCell(6)));
											supplierstatus.setDeletes(1);
											supplierstatusServices.addSupplierstatus(supplierstatus);
										}
									}
								} else {
									supplierstatus
											.setSupplierstatus_suppliercode(formatter.formatCellValue(row.getCell(0)));
									supplierstatus
											.setSupplierstatus_suppliername(formatter.formatCellValue(row.getCell(1)));
									supplierstatus
											.setSupplierstatus_suppliertype(formatter.formatCellValue(row.getCell(2)));

									if (formatter.formatCellValue(row.getCell(3)).contains("%")) {

										String otd = formatter.formatCellValue(row.getCell(3)).replace("%", "");
										supplierstatus.setSupplierstatus_otd(otd);
									}

									else {

										supplierstatus.setSupplierstatus_otd(formatter.formatCellValue(row.getCell(3)));

									}

									supplierstatus.setSupplierstatus_ppm(formatter.formatCellValue(row.getCell(4)));

									if (formatter.formatCellValue(row.getCell(5)).contains("%")) {

										String status = formatter.formatCellValue(row.getCell(5)).replace("%", "");
										supplierstatus.setSupplierstatus_fpy(status);
									}

									else {

										supplierstatus.setSupplierstatus_fpy(formatter.formatCellValue(row.getCell(5)));

									}

									supplierstatus.setSupplierstatus_year(formatter.formatCellValue(row.getCell(6)));
									supplierstatus.setDeletes(1);
									supplierstatusServices.addSupplierstatus(supplierstatus);
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
	public @ResponseBody

			Status addSupplierstatus(@RequestBody Supplierstatus supplierstatus) {
		try {
			supplierstatusServices.addSupplierstatus(supplierstatus);
			System.out.println("First Name:" + supplierstatus.getSupplierstatus_ppm());
			return new Status("Supplierstatus added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Supplierstatus> getSupplierstatuss() {
		List<Supplierstatus> supplierstatusList = null;
		try {
			supplierstatusList = supplierstatusServices.getSupplierstatuss();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierstatusList;
	}

	@RequestMapping(value = "/delete/{supplierstatus_id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteSupplierstatus(@PathVariable("supplierstatus_id") int supplierstatus_id) {
		try {
			supplierstatusServices.deleteSupplierstatus(supplierstatus_id);
			return new Status("Supplierstatus Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/search1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Supplierstatus> searchSupplierstatus1(@RequestBody Supplierstatus supplierstatus) {
		List<Supplierstatus> supplierstatusList = null;
		try {
			supplierstatusList = supplierstatusServices.searchSupplierstatus1(supplierstatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierstatusList;
	}

}
