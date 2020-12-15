package persistence;

public class MemberDAO {
	public int insert(String name) {
		System.out.println("memberDAO insert "+name);
		return 1;
	}
	public int delete(String name) {
		System.out.println("memberDAO delete "+name);
		return 1;
	}
}
