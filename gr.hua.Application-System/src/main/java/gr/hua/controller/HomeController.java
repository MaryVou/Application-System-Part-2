package gr.hua.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.api.AuthorizationApi;
import gr.hua.api.RequestApi;
import gr.hua.entity.JwtRequest;
import gr.hua.entity.JwtToken;
import net.minidev.json.parser.ParseException;

@Controller
public class HomeController {
	
	@Autowired
	public AuthorizationApi authorizeApi;
	
	@Autowired
	public RequestApi requestApi;
	
	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("user", new JwtRequest());
		return "LoginPage";
	}
	
	@RequestMapping(value={"/login","/login-error"} ,method=RequestMethod.POST)
	public String authorize(@ModelAttribute JwtRequest authenticationRequest) throws IOException, ParseException {
		JwtToken jwt = authorizeApi.getAuthorized(authenticationRequest);
		if(jwt==null)
			return "redirect:/login-error";
		requestApi.setJwt(jwt);
		return "redirect:/index";
	}
	
	@GetMapping("/login-error")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    model.addAttribute("user", new JwtRequest());
	    return "LoginPage";
	  }
	
	@GetMapping("/logout")
	public String logout(){
		requestApi.setJwt(null);
		return "redirect:/login";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/login";
	}
	
	@GetMapping("/index")
	public String welcomePage(Model model) {
		if(requestApi.getJwt()==null) 
			model.addAttribute("not_authorized", true);
		else
			model.addAttribute("not_authorized", false);
		return "Welcomepage";
	}
	
}