package gr.hua.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

	private int id;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String address;
	private Date birth_date;
	private Date hire_date;
	private Date works_since;
	private Department department;
	private User user;

	public Employee() {

	}

	public Employee(String fname, String lname, String email, String phone, String address, Date birth_date,
			Date hire_date, Date works_since) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birth_date = birth_date;
		this.hire_date = hire_date;
		this.works_since = works_since;
	}

	public Employee(int id, String fname, String lname, String email, String phone, String address, Date birth_date,
			Date hire_date, Date works_since, Department department, User user) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birth_date = birth_date;
		this.hire_date = hire_date;
		this.works_since = works_since;
		this.department = department;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public Date getWorks_since() {
		return works_since;
	}

	public void setWorks_since(Date works_since) {
		this.works_since = works_since;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", birth_date=" + birth_date + ", hire_date=" + hire_date + ", works_since="
				+ works_since + ", department=" + department + ", user=" + user + "]";
	}

}
