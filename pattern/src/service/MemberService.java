package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {
	//DAO 와 연동 - C(insert)R(select)U(update)D(delete) 
	
	public boolean insertMember(String name);
	public boolean updateMember(String name);
	public boolean deletetMember(String name);
	
	public MemberVO getMember(String name);
	public List<MemberVO> getList();
}
