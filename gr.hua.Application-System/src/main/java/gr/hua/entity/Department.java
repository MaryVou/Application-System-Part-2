package gr.hua.entity;

public class Department {

	private int id;
	private String name;
	private int supervisor;

	public Department() {

	}

	public Department(String name) {
		super();
		this.name = name;
	}

	public Department(int id, String name, int supervisor) {
		super();
		this.id = id;
		this.name = name;
		this.supervisor = supervisor;
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
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employee=" + supervisor + "]";
	}

}
