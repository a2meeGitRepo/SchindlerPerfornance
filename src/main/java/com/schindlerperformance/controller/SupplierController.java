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

import com.schindlerperformance.model.Supplier;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.services.SupplierServices;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	SupplierServices supplierServices;


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

							Supplier supplier = new Supplier();
							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							if (row.getCell(0) != null) {

								String supplerCode = formatter.formatCellValue(row.getCell(0));
								String supplerType = formatter.formatCellValue(row.getCell(2));
								List<Supplier> suppList = supplierServices.getSuppilerBySuppCodeSuppType(supplerCode, supplerType);
								
								if (suppList.size() == 0) {
									supplier.setSupplier_code(formatter.formatCellValue(row.getCell(0)));
									supplier.setSupplier_name(formatter.formatCellValue(row.getCell(1)));
									supplier.setSupplier_type(formatter.formatCellValue(row.getCell(2)));
									supplier.setSupplier_contactperson(formatter.formatCellValue(row.getCell(3)));
									supplier.setSupplier_email(formatter.formatCellValue(row.getCell(4)));
									supplier.setSupplier_email2(formatter.formatCellValue(row.getCell(5)));
									supplier.setSupplier_email3(formatter.formatCellValue(row.getCell(6)));
									supplier.setSupplier_email4(formatter.formatCellValue(row.getCell(7)));
									supplier.setSupplier_email5(formatter.formatCellValue(row.getCell(8)));
									supplier.setSupplier_email6(formatter.formatCellValue(row.getCell(9)));
									supplier.setSupplier_email7(formatter.formatCellValue(row.getCell(10)));
									supplier.setSupplier_email8(formatter.formatCellValue(row.getCell(11)));
									supplier.setSupplier_email9(formatter.formatCellValue(row.getCell(12)));
									supplier.setSupplier_mobile(formatter.formatCellValue(row.getCell(13)));
									supplier.setSupplier_mobile2(formatter.formatCellValue(row.getCell(14)));
									supplier.setSupplier_mobile3(formatter.formatCellValue(row.getCell(15)));
									supplier.setSupplier_mobile4(formatter.formatCellValue(row.getCell(16)));
									supplier.setSupplier_mobile5(formatter.formatCellValue(row.getCell(17)));
									supplier.setSupplier_mobile6(formatter.formatCellValue(row.getCell(18)));
									supplier.setSupplier_mobile7(formatter.formatCellValue(row.getCell(19)));
									supplier.setSupplier_mobile8(formatter.formatCellValue(row.getCell(20)));
									supplier.setSupplier_mobile9(formatter.formatCellValue(row.getCell(21)));
									supplier.setSupplier_address(formatter.formatCellValue(row.getCell(22)));
									supplier.setSupplier_region(formatter.formatCellValue(row.getCell(23)));
									supplier.setDeletes(1);
									supplierServices.addSupplier(supplier);
									System.out.println(" NEw Successfully import supplier"+supplier.getSupplier_code());
								} else {
									/*//logger.info("Duplicate Values---------suppliercode=="+supplerCode+"supplierType=="+supplerType);*/
									for(Supplier supp : suppList){
										
										supp.setSupplier_contactperson(formatter.formatCellValue(row.getCell(3)));
										supp.setSupplier_email(formatter.formatCellValue(row.getCell(4)));
										supp.setSupplier_email2(formatter.formatCellValue(row.getCell(5)));
										supp.setSupplier_email3(formatter.formatCellValue(row.getCell(6)));
										supp.setSupplier_email4(formatter.formatCellValue(row.getCell(7)));
										supp.setSupplier_email5(formatter.formatCellValue(row.getCell(8)));
										supp.setSupplier_email6(formatter.formatCellValue(row.getCell(9)));
										supp.setSupplier_email7(formatter.formatCellValue(row.getCell(10)));
										supp.setSupplier_email8(formatter.formatCellValue(row.getCell(11)));
										supp.setSupplier_email9(formatter.formatCellValue(row.getCell(12)));
										supp.setSupplier_mobile(formatter.formatCellValue(row.getCell(13)));
										supp.setSupplier_mobile2(formatter.formatCellValue(row.getCell(14)));
										supp.setSupplier_mobile3(formatter.formatCellValue(row.getCell(15)));
										supp.setSupplier_mobile4(formatter.formatCellValue(row.getCell(16)));
										supp.setSupplier_mobile5(formatter.formatCellValue(row.getCell(17)));
										supp.setSupplier_mobile6(formatter.formatCellValue(row.getCell(18)));
										supp.setSupplier_mobile7(formatter.formatCellValue(row.getCell(19)));
										supp.setSupplier_mobile8(formatter.formatCellValue(row.getCell(20)));
										supp.setSupplier_mobile9(formatter.formatCellValue(row.getCell(21)));
										supp.setSupplier_address(formatter.formatCellValue(row.getCell(22)));
										supp.setSupplier_region(formatter.formatCellValue(row.getCell(23)));
										supp.setDeletes(1);
										supplierServices.addSupplier(supp);
										System.out.println(" Update Successfully import supplier"+supp.getSupplier_code());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody

			Status addSupplier(@RequestBody Supplier supplier) {
		try {
			supplierServices.addSupplier(supplier);
			System.out.println("First Name:" + supplier.getSupplier_name());
			return new Status("Supplier added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Supplier> getSuppliers() {
		List<Supplier> supplierList = null;
		try {
			supplierList = supplierServices.getSuppliers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierList;
	}

	/*--------------------unique serch data---------------------*/
	@RequestMapping(value = "/uniq", method = RequestMethod.GET)
	public @ResponseBody List<Supplier> getSuppliers1() {
		List<Supplier> supplierList = null;
		try {
			supplierList = supplierServices.getSuppliers1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierList;
	}
	/*--------------------unique serch data---------------------*/

	@RequestMapping(value = "/delete/{supplier_id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteSupplier(@PathVariable("supplier_id") int supplier_id) {
		try {
			supplierServices.deleteSupplier(supplier_id);
			return new Status("Supplier Deleted Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/search1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Supplier> searchSupplier1(@RequestBody Supplier supplier) {
		List<Supplier> supplierList = null;
		try {
			supplierList = supplierServices.searchSupplier1(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierList;
	}

}
