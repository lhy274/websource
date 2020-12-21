package student;

public class Student {
	private int number;
	private String name;
	private int grade;
	private String addr;
	private String birthday;
	
	public Student() {
		super();
		
	}

	public Student(int number, String name, int grade, String addr, String birthday) {
		super();
		this.number = number;
		this.name = name;
		this.grade = grade;
		this.addr = addr;
		this.birthday = birthday;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", grade=" + grade + ", addr=" + addr + ", birthday="
				+ birthday + "]";
	}
	

}
