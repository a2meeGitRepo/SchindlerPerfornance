package com.schindlerperformance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schindlerperformance.model.Fpy;
import com.schindlerperformance.model.Otd;
import com.schindlerperformance.model.Performance;
import com.schindlerperformance.model.Ppm;
import com.schindlerperformance.model.RestResponse;
import com.schindlerperformance.services.FpyServices;
import com.schindlerperformance.services.OtdServices;
import com.schindlerperformance.services.PerformanceServices;
import com.schindlerperformance.services.PpmServices;

@Controller
@RequestMapping("/lowPerf")
public class LowPerformance {

	@Autowired
	FpyServices fpyServices;

	@Autowired
	OtdServices otdServices;

	@Autowired
	PpmServices ppmServices;

	@Autowired
	PerformanceServices performanceServices;

	static final String error = "ERROR";
	static final String serverError = "SERVER ERROR ...!";

	@RequestMapping(value = "/lowOtdFpyPpm", method = RequestMethod.POST)
	public @ResponseBody RestResponse searchFpy1(@RequestParam("criteria") String criteria,
			@RequestParam("year") String year, @RequestParam("type") String type) {
		List<Otd> otd = null;
		List<Fpy> fpy = null;
		List<Ppm> ppm = null;
		List<Otd> otdList = otdServices.getOtds();
		List<Fpy> fpyList = fpyServices.getFpys();
		List<Ppm> ppmList = ppmServices.getPpms();
		List<Performance> perfList = null;

		try {
			if (!criteria.isEmpty() && !year.isEmpty()) {

				if (criteria.equalsIgnoreCase("otd")) {
					ArrayList<Otd> arrli = new ArrayList<Otd>();
					for (Otd otdL : otdList) {
						String otdSuppCode = otdL.getOtd_suppliercode();
						String otdSuppType = otdL.getOtd_suppliertype();
						String otdSuppYear = otdL.getOtd_year();
						if (!otdSuppCode.isEmpty() && !otdSuppType.isEmpty() && !otdSuppYear.isEmpty()) {
							perfList = performanceServices.performanceUpdateBySuppCodeSuppTypeAndSuppYear(otdSuppCode,
									otdSuppType, otdSuppYear);
							if (perfList.size() != 0) {
								String otdPF = null, otdSuppCodeForD = null, otdSuppTypeForD = null;
								for (Performance per : perfList) {
									otdPF = per.getPerformance_otd();
									otdSuppCodeForD = per.getPerformance_suppliercode();
									otdSuppTypeForD = per.getPerformance_suppliertype();
								}
								if (otdPF != null) {
									if (type.equalsIgnoreCase("undefined")) {

										otd = otdServices.getLowPerForOtd(year, otdPF, otdSuppCodeForD,
												otdSuppTypeForD);
										arrli.addAll(otd);
										//logger.info(arrli.size());

									} else {
										otd = otdServices.getLowPerForOtd(year, otdPF, otdSuppCodeForD, type);
										arrli.addAll(otd);
									}
								} else {
									//logger.info("otdPF null value found");
								}
							}
						}
					}
					return new RestResponse(1, "Otd low performance", arrli);
				}

				else if (criteria.equalsIgnoreCase("fpy")) {
					ArrayList<Fpy> arrli = new ArrayList<Fpy>();
					for (Fpy fpyL : fpyList) {
						String fpySuppCode = fpyL.getFpy_suppliercode();
						String fpySuppType = fpyL.getFpy_suppliertype();
						String fpySuppYear = fpyL.getFpy_year();
						if (!fpySuppCode.isEmpty() && !fpySuppType.isEmpty() && !fpySuppYear.isEmpty()) {
							perfList = performanceServices.performanceUpdateBySuppCodeSuppTypeAndSuppYear(fpySuppCode,
									fpySuppType, fpySuppYear);
							if (perfList.size() != 0) {
								String fpyPF = null, fpySuppCodeF = null, fpySuppTypeF = null;
								for (Performance per : perfList) {
									fpyPF = per.getPerformance_fpy();
									fpySuppCodeF = per.getPerformance_suppliercode();
									fpySuppTypeF = per.getPerformance_suppliertype();
								}
								if (fpyPF != null) {
									if (type.equalsIgnoreCase("undefined")) {

										fpy = fpyServices.getLowPerForFpy(year, fpyPF, fpySuppCodeF, fpySuppTypeF);
										arrli.addAll(fpy);
									} else {
										fpy = fpyServices.getLowPerForFpy(year, fpyPF, fpySuppCodeF, type);
										arrli.addAll(fpy);
									}
								} else {
									//logger.info("null value found");
								}
							}
						}
					}
					return new RestResponse(1, "Fpy low performance", arrli);
				}

				else if (criteria.equalsIgnoreCase("ppm")) {
					ArrayList<Ppm> arrli = new ArrayList<Ppm>();
					for (Ppm ppmL : ppmList) {
						String ppmSuppCode = ppmL.getPpm_suppliercode();
						String ppmSuppType = ppmL.getPpm_supplierType();
						String ppmSuppYear = ppmL.getPpm_year();
						if (!ppmSuppCode.isEmpty() && !ppmSuppType.isEmpty() && !ppmSuppYear.isEmpty()) {
							perfList = performanceServices.performanceUpdateBySuppCodeSuppTypeAndSuppYear(ppmSuppCode,
									ppmSuppType, ppmSuppYear);
							if (perfList.size() != 0) {
								String ppmPF = null, ppmSuppCodeF = null, ppmSuppTypeF = null;
								for (Performance per : perfList) {
									ppmPF = per.getPerformance_ppm();
									ppmSuppCodeF = per.getPerformance_suppliercode();
									ppmSuppTypeF = per.getPerformance_suppliertype();
								}
								if (ppmPF != null) {
									if (type.equalsIgnoreCase("undefined")) {

										ppm = ppmServices.getLowPerForPpm(year, ppmPF, ppmSuppCodeF, ppmSuppTypeF);
										arrli.addAll(ppm);

									} else {
										ppm = ppmServices.getLowPerForPpm(year, ppmPF, ppmSuppCodeF, type);
										arrli.addAll(ppm);
									}
								} else {
									//logger.info("null value found");
								}
							}
						}
					}
					return new RestResponse(1, "PPm low performance", arrli);
				} else {
					return new RestResponse(0, "", error);
				}
			} else {
				return new RestResponse(0, "enter value first", error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new RestResponse(0, "error", serverError);
		}
	}
}
