package service;

import java.sql.Connection;
import java.util.List;

import domain.UserVO;
import persistence.UserDAO;

import static persistence.JDBCUtil.*;

public class UserServiceImpl implements UserService {
	
	Connection con;
	UserDAO dao;

	public UserServiceImpl() {
		con = getConnection();
		dao = new UserDAO(con);
	}
	

	
	@Override
	public boolean insertUser(String username, String birthyear, String addr, String mobile) {
		//UserDAO 객체 생성 - DB작업 시키기
		int result = dao.insert(username, birthyear, addr, mobile);
		
		//DB작업 결과를 action에게 보내기 - commit, rollback
		boolean insertFlag = false;
		if(result>0) {
			insertFlag = true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return insertFlag;
	}

	@Override
	public boolean updateUser(String addr, String mobile, int no) {
		// 업데이트 플레그 잘 됐나 못 됐나.
		boolean updateFlag = false;
		int result = dao.updateUser(addr, mobile, no);
		
		if(result>0) {
			commit(con);
			updateFlag=true;
		}else {
			rollback(con);
		}
		close(con);
		return updateFlag;
	}

	@Override
	public boolean deleteUser(int no) {
		// 리퀘스트.갯파라미터는 - action에서 하고
		// dao에 값 넣어서 보내서 결과 받아오는건 service에서 하는구나
		//service에서는 커밋,롤백까지만 하고 - pro 제일 마지막에 위치 이동 시키는건 다시 action그리고controller에서 함
		boolean deleteFlag = false;
		int result = dao.deleteUser(no);
		
		if(result>0) {
			commit(con);
			deleteFlag=true;
		}else {
			rollback(con);
		}
		close(con);
		return deleteFlag;
	}

	@Override
	public UserVO getUser(int no) {
		UserVO user = dao.getUser(no);
		close(con);
		return user;
	}

	@Override
	public List<UserVO> getUserList() {
		List<UserVO> list = dao.select();
		close(con);
		return list;
	}
	
	
	

}
