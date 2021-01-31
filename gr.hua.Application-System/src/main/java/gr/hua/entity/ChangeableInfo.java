package gr.hua.entity;

public class ChangeableInfo {

	private String phone;
	private String address;
	private String password;

	public ChangeableInfo(String phone, String address, String password) {
		super();
		this.phone = phone;
		this.address = address;
		this.password = password;
	}

	public ChangeableInfo() {

	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
