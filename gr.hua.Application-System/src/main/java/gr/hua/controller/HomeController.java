package gr.hua.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.api.AuthorizationApi;
import gr.hua.api.RequestApi;
import gr.hua.entity.JwtRequest;
import gr.hua.entity.JwtToken;
import net.minidev.json.parser.ParseException;

@RestController
public class HomeController {
	
	@Autowired
	public AuthorizationApi authorizeApi;
	
	@Autowired
	public RequestApi requestApi;
	
	@GetMapping("/login")
	public String showLogin() {
		return null;
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String authorize(@RequestBody JwtRequest authenticationRequest) throws IOException, ParseException {
		JwtToken jwt = authorizeApi.getAuthorized(authenticationRequest);
		//if jwt==null
			//redirect to /login with model attribute Bad Credentials
		System.out.println();
		requestApi.setJwt(jwt);
		return null;
	}
	
}