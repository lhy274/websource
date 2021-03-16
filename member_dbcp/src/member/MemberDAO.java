package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	// 드라이버 로드
//	static {
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	// Connection
	public Connection getConnection() {
		Connection con = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	// 로그인 => userid, password 가 일치해야 함
	// select userid, name from member1 where userid=? and password=?
	public MemberVO isLogin(String userid, String password) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		try {
			String sql = "select userid, name from member1 where userid=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setUserid(rs.getString("userid"));
				member.setName(rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return member;
	}// isLogin

	// 회원가입
	// insert into member1(userid,password,name,gender,email) values(?,?,?,?,?)
	public int register(MemberVO member) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "insert into member1(userid,password,name,gender,email) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}// joinInsert

	// 비밀번호 변경
	// update member1 set password=새로운비밀번호 where userid=userid
	public int updateMember(String userid, String password) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = "update member1 set password=? where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, userid);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}// updateMember

	// 회원탈퇴
	// delete from member where useid=? and password =?
	public int leaveMember(String userid, String password) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "delete from member1 where userid=? and password =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}// deleteMember

	// 중복 아이디
	public boolean checkId(String userid) {
		boolean result = false;
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select userid from member1 where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}//chekId(S

	
	
	
	
}
