package gr.hua.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		if(requestApi.getJwt()==null) {
			model.addAttribute("not_authorized", true);
			return "redirect:/login";
		}
		else {
			model.addAttribute("not_authorized", false);
		}
		model.addAttribute("app", new Application());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("lastDay", LocalDate.now().with(lastDayOfYear()));
		return "EmpForm";
	}
	
	@RequestMapping(value={"/applications/new","/applications/new-error"} ,method=RequestMethod.POST)
	public String processApplicationForm(@ModelAttribute Application application) {
		if(requestApi.getJwt()!=null) {
			
			String applicationJson = null;
		
			applicationJson = "{\"type\":\""
									+ application.getType()+"\","
									+ "\"category\":\""
									+ application.getCategory()+"\","
									+ "\"start_date\":\""
									+ application.getStart_date()+"\","
									+ "\"last_date\":\""
									+ application.getLast_date()+"\"}";
			
			try {
				String code = (String) requestApi.postRequest("http://themelicompany.cloudns.cl/api/applications/new", applicationJson, null, "status");
				if(!code.equals("200"))
					return "redirect:/applications/new-error";
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "redirect:/index";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/applications/new-error")
	public String showApplicationFormWithError(Model model) {
		if(requestApi.getJwt()==null) {
			model.addAttribute("not_authorized", true);
			return "redirect:/login";
		}
		else {
			model.addAttribute("not_authorized", false);
		}
		model.addAttribute("error",true);
		model.addAttribute("app", new Application());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("lastDay", LocalDate.now().with(lastDayOfYear()));
		return "EmpForm";
	}
	
	@GetMapping("/applications/view")
	public String showApplications(Model model) {
		if(requestApi.getJwt()==null) {
			model.addAttribute("not_authorized", true);
			return "redirect:/login";
		}
		else 
			model.addAttribute("not_authorized", false);
		try {
			List<Object> applications = requestApi.getRequestMultiple("http://themelicompany.cloudns.cl/api/applications/view", Application.class);
			model.addAttribute("allApplications", applications);
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
		return "ViewApp";
	}

}
