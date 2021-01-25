package gr.hua.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.api.RequestApi;
import gr.hua.entity.Employee;
import net.minidev.json.parser.ParseException;

//@Controller
@RestController
public class EmployeeController {

	@Autowired
	private RequestApi requestApi;
	
	@GetMapping("/profile")
	public String retrieveProfile(Model model) throws IOException, ParseException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Employee profile = (Employee) requestApi.getRequest("http://localhost:8080/api/employees/profile", Employee.class);
		//if profile == null -> error page
		System.out.println(profile.toString());
		return profile.toString();
	}
}
