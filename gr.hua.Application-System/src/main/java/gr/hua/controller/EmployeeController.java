package gr.hua.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.api.RequestApi;
import gr.hua.entity.ChangeableInfo;
import gr.hua.entity.Contact;
import gr.hua.entity.Employee;
import net.minidev.json.parser.ParseException;

@Controller
public class EmployeeController {

	@Autowired
	private RequestApi requestApi;
	
	@GetMapping("/profile")
	public String retrieveProfile(Model model) throws IOException, ParseException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(requestApi.getJwt()==null) 
			model.addAttribute("not_authorized", true);
		else
			model.addAttribute("not_authorized", false);
		Employee profile = (Employee) requestApi.getRequest("http://themelicompany.cloudns.cl/api/employees/profile", Employee.class);
		model.addAttribute("pr", profile);
		model.addAttribute("updates", new ChangeableInfo());
		//if profile == null -> error page
		return "Profile";
	}
	
	@PostMapping("/profile")
	public String updatePhone(@ModelAttribute ChangeableInfo updates) throws IOException, ParseException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(!updates.getPhone().equals(""))
			requestApi.postRequest("http://themelicompany.cloudns.cl/api/employees/profile/update-phone", "{\"phone\":\""+updates.getPhone()+"\"}" ,null, "status");
		if(!updates.getAddress().equals(""))
			requestApi.postRequest("http://themelicompany.cloudns.cl/api/employees/profile/update-address", "{\"address\":\""+updates.getAddress()+"\"}" ,null, "status");
		if(!updates.getPassword().equals(""))
			requestApi.postRequest("http://themelicompany.cloudns.cl/api/employees/profile/update-password", "{\"password\":\""+updates.getPassword()+"\"}" ,null, "status");
		return "redirect:/profile";
	}
	
	@GetMapping("/contacts")
	public String retrieveContacts(Model model) throws IOException, ParseException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(requestApi.getJwt()==null) 
			model.addAttribute("not_authorized", true);
		else
			model.addAttribute("not_authorized", false);
		List<Object> contacts = requestApi.getRequestMultiple("http://themelicompany.cloudns.cl/api/employees/contact", Contact.class);
		model.addAttribute("contacts", contacts);
		return "Contact";
	}
}
