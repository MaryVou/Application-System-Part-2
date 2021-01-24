package gr.hua.entity;

import java.util.Set;

public class User {

	private String username;
	private String password;
	private Boolean enabled;
	private int emp_id;

	public User() {

	}

	public User(String username, String password, Boolean enabled, int emp_id) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.emp_id = emp_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

}
