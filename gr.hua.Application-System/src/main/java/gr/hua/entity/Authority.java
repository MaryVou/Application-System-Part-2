package gr.hua.entity;


public class Authority {

	private User user;
	private String authority;
	private String ix_auth_username;

	public Authority() {

	}

	public Authority(User user, String authority) {
		super();
		this.user = user;
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authority [user=" + user + ", authority=" + authority + "]";
	}

}
