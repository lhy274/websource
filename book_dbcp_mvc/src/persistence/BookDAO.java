package persistence;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.BookVO;
import static persistence.JDBCUtil.*;

public class BookDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BookDAO(Connection con) {
		this.con=con;
	}
	
	//book insert
	//insert into bookTBL values(1001, '이것이 자바다', '신용균', 28000);
	public int bookInsert(BookVO vo) {

		int result = 0;
		try {
			String sql = "insert into bookTBL(code,title,writer,price) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCode());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getPrice());
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);

		}
		return result;
	}//bookInsert() end
	
	
	//전체조회
	public List<BookVO> getList() {
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
			close(pstmt);
			close(rs);
		}
		return list;
		
	}//getList()
	
	
	// delete from booktbl where code=?
	public int bookDelect(int code) {
		int result = 0;
		try {
			String sql = "delete from booktbl where code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}//bookDelect
	
	public int bookUpdate(BookVO vo) {
		//update bookTBL set price=? where  code=?
		int result = 0;
		try {
			String sql = "update bookTBL set price=? where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPrice());
			pstmt.setInt(2, vo.getCode());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
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
			close(rs);
			close(pstmt);
		}
		return list;
	}//bookSearch
	
	
}
