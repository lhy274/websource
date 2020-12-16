package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.UserVO;

import static persistence.JDBCUtil.*;

public class UserDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet re;
	
	public UserDAO(Connection con) {
		this.con = con;
	}
	
	
	// CRUD
	// insert
	public int insert(String username, String birthyear, String addr, String mobile) {

		int result = 0;
		try {
			if (con != null) {
				// 4. sql 구문 생성
				String sql = "insert into userTBL ";
				sql += "values(userTBL_seq.nextval,?,?,?,?)";
				// 5. Statement 생성 후 전송
				pstmt = con.prepareStatement(sql);
				// ?에 해당하는 값을 set 하기
				pstmt.setString(1, username);
				pstmt.setInt(2, Integer.parseInt(birthyear));
				pstmt.setString(3, addr);
				pstmt.setString(4, mobile);

				// 6. sql 구문 실행하고 결과 받기
				result = pstmt.executeUpdate();

				/*
				 * //7.결과에 따라 페이지 이동 // 여기 있으면 안됨 if(result>0) {//입력 성공
				 * response.sendRedirect("index.jsp"); }else{//입력 실패
				 * response.sendRedirect("add.jsp"); }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}// insert

	// Read
	// 전체조회
	public List<UserVO> select() {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<UserVO> list = new ArrayList<UserVO>();
		
	try {
		if(con!=null) {
			//4. sql 구문 생성
			String sql = "select * from userTBL order by no desc";
			//5. Statement 생성 후 전송 : DB연결을 통해 sql 문을 수행해 주는 클래스
			pstmt = con.prepareStatement(sql);
			//6. sql 구문 실행하고 결과 받기 => select구문 executeQuery() => ResultSet,
			// insert,update,delete  : executeUpdate() => int
			rs = pstmt.executeQuery();
			//7. 결과 출력(ResultSet : DB Select 결과를 담는 특수한 객체(테이블 구조))
			while(rs.next()){
				//UserVo 객체 생성
				UserVO user = new UserVO();
				user.setNo(rs.getInt(1)); //no(number)
				user.setUsername(rs.getString(2)); //usernamer(nchar)
				user.setBirthyear(rs.getInt(3)); // birthYear(number)
				user.setAddr(rs.getString(4)); //addr(nchar)
				user.setMobile(rs.getString(5)); //mobile(nvarchar2)
				list.add(user);
				}
			}
		}catch(Exception e) {
		e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	//개별조회
	public UserVO getUser(int no) {
		//DB 연결 후 사용자의 no에 해당하는 정보 가져오기
	
		ResultSet rs = null;
		UserVO user=null;
		try{
			if(con!=null) {
				String sql = "select * from userTBL where no=?";
				//String sql = "select no, username,addr,mobile from userTBL where no=?"; // 이렇게 순서로 써주면 써주는 순서대로 나옴
				//sql 구문 전송
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				//sql 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					user = new UserVO();
					user.setNo(rs.getInt("no"));
					user.setUsername(rs.getString("username"));
					user.setAddr(rs.getString("addr"));
					user.setMobile(rs.getString("mobile"));
					user.setBirthyear(rs.getInt("birthyear"));
					
					
				}//if
			}//if con!=null
		}catch(Exception e) {
		e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
	
	}
		return user;
	}

	
	public int deleteUser(int no) {
	
		int result = 0;
		try {//no에 해당하는 user 삭제
			
			if(con!=null) {
				//삭제가 성공하면 index.jsp로 이동
				String sql = "delete from userTBL where no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
		}
		return result;
	}
	
	
	public int updateUser(String addr, String mobile, int no) {

		int result = 0;
		
		try {
			
			if(con!=null) {
				String sql = "update userTBL set addr=?, mobile=? where no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, addr);
				pstmt.setString(2, mobile);
				pstmt.setInt(3, no);
				result  = pstmt.executeUpdate();
				
				  //if(result>0) { response.sendRedirect("index.jsp"); }
				 
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {

				close(pstmt);
		}
		return result;
	}//updateUser
	
	
}
