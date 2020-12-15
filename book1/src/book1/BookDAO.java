package book1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	//connection, CRUD
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "javaDB";
			String password = "12345";
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			
		}
		return con;
	}//getConnection() end
	
	//book insert
	//insert into bookTBL values(1001, '이것이 자바다', '신용균', 28000);
	public int bookInsert(int code, String title, String writer, int price) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "insert into bookTBL(code,title,writer,price) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, title);
			pstmt.setString(3, writer);
			pstmt.setInt(4, price);
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}//bookInsert() end
	
	
	//전체조회
	public List<BookVO> getList() {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVO > list = new ArrayList<>();
		try {
			String sql = "select * from bookTBL order by code desc";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setCode(rs.getInt("code"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
		
	}//getList()
	
	
	// delete from booktbl where code=?
	public int bookDelect(int code) {
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "delete from booktbl where code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}//bookDelect
	
	public int bookUpdate(int code, int price) {
		//update bookTBL set price=? where  code=?
		Connection con=getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "update bookTBL set price=? where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setInt(2, code);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
		
	}//bookUpdate
	
	public List<BookVO> bookSearch(String criteria,String keyword) {
		//코드가 2001 번인 거 검색하기
		//select * from bookTBL where code=?
		
		//두개를 아우르는 이거 하나!
		//String sql="select * from bookTBL where" + criteria + " =?";
		
		//작가가 홍길동 검색하기
		//select * from bookTBL where writer='홍길동'
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List<BookVO> list = new ArrayList<BookVO>();
		try {
			//String sql="select * from bookTBL where" + criteria + " =?";
			
			if(criteria.equals("code")) {
				sql = "select * from bookTBL where code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(keyword));
			}else {
				sql = "select * from bookTBL where writer=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//봉지하나 만들어
				BookVO vo = new BookVO();
				vo.setCode(rs.getInt(1));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setPrice(rs.getInt("price"));
				//박스에 담아
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				con.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}//bookSearch
	
	
}
