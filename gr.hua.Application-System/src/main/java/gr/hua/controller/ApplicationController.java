package gr.hua.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.api.RequestApi;
import gr.hua.entity.Application;
import net.minidev.json.parser.ParseException;

@RestController
public class ApplicationController {
	
	@Autowired
	private RequestApi requestApi;
	
	@GetMapping("/applications/new")
	public void showApplicationForm() {
		
	}
	
	@PostMapping("/applications/new")
	public void processApplicationForm(@RequestBody Application application) {
		String applicationJson = "{\"type\":\""
								+ application.getType()+"\","
								+ "\"category\":\""
								+ application.getCategory()+"\","
								+ "\"start_date\":\""
								+ application.getStart_date()+"\","
								+ "\"last_date\":\""
								+ application.getLast_date()+"\"}";
		try {
			requestApi.postRequest("http://localhost:8080/api/applications/new", applicationJson, Application.class);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/applications/view")
	public void showApplications() {
		try {
			List<Application> applications = requestApi.getRequestMultiple("http://localhost:8080/api/applications/view", Application.class);
			for(int i=0;i<applications.size();i++) {
				System.out.println(applications.get(i).getId());
				System.out.println(applications.get(i).getCategory());
				System.out.println(applications.get(i).getDays());
				System.out.println(applications.get(i).getStart_date());
				System.out.println(applications.get(i).getType());
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
