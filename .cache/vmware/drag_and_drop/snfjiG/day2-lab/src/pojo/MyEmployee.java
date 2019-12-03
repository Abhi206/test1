package pojo;

import javax.persistence.Entity;

@Entity
public class MyEmployee 
{
	@Id
	private int empid;
	private String name;
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
