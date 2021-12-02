package com.schindlerperformance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

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
import com.schindlerperformance.config.EmailUtility2;
import com.schindlerperformance.config.EmailUtility3;
import com.schindlerperformance.model.Role;
import com.schindlerperformance.model.Status;
import com.schindlerperformance.model.User;
import com.schindlerperformance.model.UserType;
import com.schindlerperformance.services.UserServices;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServices userServices;
	
	@SuppressWarnings("unused")
	private EmailUtility createEmailUtility;
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUserDataAndFile(@RequestBody MultipartFile file) {
		
		//String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		String rootDirectory = "D:\\testUpload\\";
		System.out.println("Root Directory "+rootDirectory);
		try {
			file.transferTo(new File(rootDirectory  + file.getOriginalFilename()));
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
	Status addUser(@RequestBody User user) {
		try {
			//System.out.println(user.toString());
			userServices.addUser(user);
			return new Status("Employee added Successfully");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status("Employee Already Exists");
		}

	}


	@RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status sendMail(@RequestBody User user) {
		try {
			
		//	EmailUtility.sendEmail("smtp.gmail.com", "587", "jainnikita995@gmail.com", "9001133677", "100" , "" ,"Mr./Ms. "+user.getFname()+", your registration has done syccesssfully", "hi.<br> Mr./Ms."+user.getFname()+" your registration has done successfully...!!!<br>login with following credentials,<br>username:"+user.getMobile_no()+"<br>and  password :"+user.getPassword()+"<br> Thanking You.");
		
			EmailUtility2.sendEmail2("smtp.outlook.com", "587", "shivam.kumar@a2mee.com", user.getFname()+", your registration has done syccesssfully", "hi.<br> Mr./Ms."+user.getFname()+" your registration has done successfully...!!!<br>login with following credentials,<br>username:"+user.getMobile_no()+"<br>and  password :"+user.getPassword()+"<br> Thanking You.");
			
			return new Status("Employee added Successfully !");
		} catch (Exception e) {
			
			System.out.println(e);
			return new Status(e.toString());
		}

	}
	
	
	
	@RequestMapping(value = "/sendPass", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status sendMailForPassChange(@RequestBody User user) {
		try {
			
		//	EmailUtility.sendEmail("smtp.gmail.com", "587", "jainnikita995@gmail.com", "9001133677", "100" , "" ,"Mr./Ms. "+user.getFname()+", your registration has done syccesssfully", "hi.<br> Mr./Ms."+user.getFname()+" your registration has done successfully...!!!<br>login with following credentials,<br>username:"+user.getMobile_no()+"<br>and  password :"+user.getPassword()+"<br> Thanking You.");
		
			EmailUtility3.sendEmail3("smtp.outlook.com", "587", user.getEmail(), user.getFname()+", your password changed syccesssfully", "hi.<br> Mr./Ms."+user.getFname()+" your your password changed successfully...!!!<br>login with following credentials,<br>username:"+user.getMobile_no()+"<br>and  password :"+user.getPassword()+"<br> Thanking You.");
			
			
			if(user.getEmail()=="no email found"){
				
				return new Status("Email Id not exist.. plz enter correct mail id");
				
			}
			
			return new Status("Password changed");
			
			
			
			
		} catch (Exception e) {
			
			System.out.println(e);
			return new Status(e.toString());
		}

	}
	
	
	
	@RequestMapping(value = "/sendsms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status sendSms(@RequestBody User user) {
		try {

			String mob = user.getMobile_no();
			
			String Message = user.getFname();
			
			URL smsApiLink = new URL("http://trans.dreamztechnolgy.org/SmsStatuswithId.aspx?mobile=8446060888&pass=Advik8&senderid=APTMEE&to="+ mob +"&msg="+Message);
							
			/*http://trans.dreamztechnolgy.org/SmsStatuswithId.aspx?mobile=9892367214&pass=IRRKI&senderid=GLORIA&to=9765908975&msg=POSPAT
*/
			/*http://trans.dreamztechnolgy.org/sendsms.aspx?mobile=9892367214&pass=your_password&senderid=mysenderid&to=myrecipient&msg=Your msg
*/			 /*http://trans.dreamztechnolgy.org/sendsms.aspx?mobile=9892367214&pass=your_password&senderid=mysenderid&to=myrecipient1,myrecipient2,myrecipient3&msg=Your msg
*/			
			System.out.println(smsApiLink);

			URLConnection smsConnecion = smsApiLink.openConnection();
			smsConnecion.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(smsConnecion.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();

			return new Status("Employee added Successfully !");
		} catch (Exception e) {

			System.out.println(e);
			return new Status(e.toString());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getUsers() {
		List<User> userList = null;
		try {
			userList = userServices.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<User> loginUser(@RequestBody User user) {
		System.out.println("Mobile Number:"+user.getMobile_no()+" Password:"+user.getPassword());
		List<User> userList = null;
		try {
		userList = userServices.loginUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}
	
	
	@RequestMapping(value = "/delete/{user_id}", method = RequestMethod.GET)
	public @ResponseBody
	Status deleteUser(@PathVariable("user_id") int user_id) {
		try {
			System.out.println("Delete User : "+user_id);
			userServices.deleteUser(user_id);
			return new Status("Employee added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(e.toString());
		}

	}
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public @ResponseBody
	List<Role> role() {
		List<Role> roleList = null;
		try {
			roleList = userServices.getRoles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
	}
	
	@RequestMapping(value = "/user_type", method = RequestMethod.GET)
	public @ResponseBody
	List<UserType> getUserType() {
		List<UserType> userTypeList = null;
		try {
			userTypeList = userServices.getUserType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userTypeList;
	}
	
}
