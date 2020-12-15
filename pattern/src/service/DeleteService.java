package service;

import persistence.MemberDAO;

public class DeleteService {
	public void deleteMember(String name) {
		System.out.println("delete service "+name);
		
		MemberDAO dao = new MemberDAO();
		dao.delete(name);
	}
}
