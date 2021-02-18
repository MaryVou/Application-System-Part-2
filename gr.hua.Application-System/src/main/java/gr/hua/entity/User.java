package gr.hua.entity;

public class User {

	private String username;
	private JwtToken token;

	public User() {

	}

	public User(String username, JwtToken token) {
		super();
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public JwtToken getToken() {
		return token;
	}

	public void setToken(JwtToken token) {
		this.token = token;
	}

}
