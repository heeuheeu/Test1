package model.domain.vo;

public class MemberVO { // ���̺� ���Ǿ� �ִ� ��ü�� Ŭ����ȭ 

	private String 	id, pwd, name, hiredate; 
	private int 	salary;
	private String	dept;
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(String id, String pwd, String name, String hiredate, int salary, String dept) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.hiredate = hiredate;
		this.salary = salary;
		this.dept = dept;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", hiredate=" + hiredate + ", salary="
				+ salary + ", dept=" + dept + "]";
	}
	
	
	
}
