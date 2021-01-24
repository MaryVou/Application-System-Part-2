package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gr.hua.api.EmployeeApi;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeApi employeeApi;
	
	@GetMapping("/profile") // ADMIN , MANAGER, SUPERVISOR
	public String retrieveProfile(Model model) {
		String profile = employeeApi.test().toString();
		return profile;
	}
}
