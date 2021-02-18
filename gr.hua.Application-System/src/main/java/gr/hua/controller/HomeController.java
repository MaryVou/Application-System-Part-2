package gr.hua.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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

	@RequestMapping(value = { "/login", "/login-error" }, method = RequestMethod.POST)
	public String authorize(@ModelAttribute JwtRequest authenticationRequest, HttpServletResponse response) throws IOException, ParseException {
		JwtToken jwt = authorizeApi.getAuthorized(authenticationRequest);
		if (jwt == null)
			return "redirect:/login-error";
		Cookie cookie = new Cookie("token", jwt.getToken());
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setHttpOnly(false);
		response.addCookie(cookie);
		return "redirect:/index";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("user", new JwtRequest());
		return "LoginPage";
	}

	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("token", null);
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setHttpOnly(false);
		response.addCookie(cookie);
		return "redirect:/login";
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/login";
	}

	@GetMapping("/index")
	public String welcomePage(Model model, @CookieValue("token") String token) {
		
		if (token == "") 
			return "redirect:/login";
		
		return "Welcomepage";
	}

}