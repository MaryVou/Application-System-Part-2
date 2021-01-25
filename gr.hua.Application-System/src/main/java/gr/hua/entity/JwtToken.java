package gr.hua.entity;

import java.io.Serializable;

public class JwtToken implements Serializable{
	
	private static final long serialVersionUID = -3558001531770808808L;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
