package gr.hua.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import gr.hua.api.RequestApi;
import gr.hua.entity.Application;
import net.minidev.json.parser.ParseException;

@Controller
public class ApplicationController {
	
	@Autowired
	private RequestApi requestApi;
	
	@GetMapping("/applications/new")
	public String showApplicationForm(Model model) {
		if(requestApi.getJwt()==null) 
			model.addAttribute("not_authorized", true);
		else
			model.addAttribute("not_authorized", false);
		model.addAttribute("app", new Application());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("lastDay", LocalDate.now().with(lastDayOfYear()));
		return "EmpForm";
	}
	
	@PostMapping("/applications/new")
	public String processApplicationForm(@ModelAttribute Application application) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String formattedStartDate = dateFormat.format(application.getStart_date());
		String formattedLastDate = dateFormat.format(application.getLast_date());
		
		String applicationJson = "{\"type\":\""
								+ application.getType()+"\","
								+ "\"category\":\""
								+ application.getCategory()+"\","
								+ "\"start_date\":\""
								+ formattedStartDate+"\","
								+ "\"last_date\":\""
								+ formattedLastDate+"\"}";
		try {
			int code = (int) requestApi.postRequest("http://localhost:8080/api/applications/new", applicationJson, null, "status");
			if(code!=200)
				return "redirect:/pplications/new-error";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/index";
	}
	
	@GetMapping("/applications/new-error")
	public String showApplicationFormWithError(Model model) {
		if(requestApi.getJwt()==null) 
			model.addAttribute("not_authorized", true);
		else
			model.addAttribute("not_authorized", false);
		model.addAttribute("error",true);
		model.addAttribute("app", new Application());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("lastDay", LocalDate.now().with(lastDayOfYear()));
		return "EmpForm";
	}
	
	@GetMapping("/applications/view")
	public String showApplications() {
		try {
			List<Object> applications = requestApi.getRequestMultiple("http://localhost:8080/api/applications/view", Application.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

}
