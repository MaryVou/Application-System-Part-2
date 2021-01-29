package gr.hua.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.hua.entity.Application;
import gr.hua.entity.Contact;
import gr.hua.entity.Employee;
import gr.hua.entity.JwtRequest;
import gr.hua.entity.JwtToken;
import net.minidev.json.parser.ParseException;

@Service
public class RequestApi {

	private JwtToken jwt;

	public JwtToken getJwt() {
		return jwt;
	}

	public void setJwt(JwtToken jwt) {
		this.jwt = jwt;
	}

	public Object getRequest(String GetUrl, Class entity)
			throws IOException, ParseException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		HttpURLConnection con = null;

		URL url = new URL(GetUrl);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + getJwt().getToken());
		con.setDoOutput(true);

		int code = con.getResponseCode();

		if (code == 200) {

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				if(entity!=null)
					return new ObjectMapper().readValue(response.toString(), entity);
			}
		}
		if (code == 401) {
			System.out.println("User not authorized");
		}
		return null;
	}
	
	public List<Object> getRequestMultiple(String GetUrl, Class entity)
			throws IOException, ParseException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		HttpURLConnection con = null;

		URL url = new URL(GetUrl);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + getJwt().getToken());
		con.setDoOutput(true);

		int code = con.getResponseCode();

		if (code == 200) {

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(response.toString(), mapper.getTypeFactory().constructCollectionType(List.class, entity));
			}
		}
		if (code == 401) {
			System.out.println("User not authorized");
		}
		return null;
	}

	public Object postRequest(String postUrl, String jsonInputString, Class entity, String returnValue) throws IOException, ParseException {

		HttpURLConnection con = null;

		URL url = new URL(postUrl);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + getJwt().getToken());
		con.setDoOutput(true);

		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int code = con.getResponseCode();

		if (code == 200) {

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				if(returnValue.equals("entity")) {
					if(entity!=null)
						return new ObjectMapper().readValue(response.toString(), entity);
				}if(returnValue.equals("status")) {
					return code;
				}
			}
		}
		if (code == 401) {
			System.out.println("User not authorized");
		}
		return null;
	}

}
