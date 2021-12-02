package com.schindlerperformance.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schindlerperformance.model.Sms;
import com.schindlerperformance.model.Status;

@Controller
@RequestMapping("/sms")
public class SmsController {

	
	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Status sendSms(@RequestBody Sms sms) {
		try {

			String m = sms.getMob();
			
			
			
			String Msg = sms.getMsg();
			
			String msg = Msg.replaceAll("\\s+","%20");
			
			URL smsApiLink = new URL("http://trans.dreamztechnolgy.org/SmsStatuswithId.aspx?mobile=8446060888&pass=Advik8&senderid=APTMEE&to="+m+"&msg="+msg);
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
	
}
