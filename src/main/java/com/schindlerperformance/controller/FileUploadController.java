package com.schindlerperformance.controller;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.schindlerperformance.model.RestResponse;

/**
 * @author admin
 *
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

	static final Object error = "no Data";

	/**
	 * @param files
	 * @param desc
	 * @return
	 */
/*	@RequestMapping(value = "/pdfFileUpload", method = RequestMethod.POST)
	public @ResponseBody RestResponse addFileUpload(@RequestParam("file") MultipartFile[] files) {

		String mediaPath = null;
		String temp = "";
		if (files.length != 0) {

			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				try {
					byte[] bytes = file.getBytes();
					String rootPath = System.getProperty("catalina.home");

					String saveDirectory = "C:/SchindlerPerformance_pdf_files";
					File filesss = new File(saveDirectory);
					logger.info(filesss.exists());
					if (filesss.exists() == false) {
						filesss.mkdirs();
					}
					String fileName = file.getOriginalFilename();
					mediaPath = saveDirectory + "/" + fileName;
					temp = mediaPath + "," + temp;
					logger.info(temp);
					if (!"".equalsIgnoreCase(fileName)) {
						file.transferTo(new File(saveDirectory + "/" + fileName));
					}
					logger.info("success");
					logger.info(temp);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
			return new RestResponse(1, "Success", temp);
		} else {

		}
		return new RestResponse(0, "error", null);
	}*/
}
