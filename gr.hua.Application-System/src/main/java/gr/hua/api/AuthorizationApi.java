package gr.hua.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.hua.entity.JwtRequest;
import gr.hua.entity.JwtToken;
import net.minidev.json.parser.ParseException;

@Service
public class AuthorizationApi {

	public JwtToken getAuthorized(JwtRequest authenticationRequest) throws IOException, ParseException {

		JwtToken jwt = null;
		
		HttpURLConnection con = null;

		URL url = new URL("http://localhost:8080/authenticate");
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);

		String jsonInputString = "{\"username\": \"" + authenticationRequest.getUsername() + "\",\"password\":\"" + authenticationRequest.getPassword() + "\"}";

		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int code = con.getResponseCode();
		
		if(code==200) {
		
			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				
				jwt = new ObjectMapper().readValue(response.toString(), JwtToken.class);
				System.out.println(jwt.getToken());
			}
		}if(code==401) {
			System.out.println("User not authorized");
		}
		return jwt;
	}
}
