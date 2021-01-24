package gr.hua.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Department{

	private int id;
	private String name;
	private Employee supervisor;

	public Department() {

	}

	public Department(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSupervisor() {
		return supervisor.getId();
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employee=" + supervisor.getId() + "]";
	}

}
