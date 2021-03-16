package persistence;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.ProductVO;
import static persistence.JDBCUtil.*;


public class ProdDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ProdDAO(Connection con) {
		this.con=con;
	}
	
	
	public List<ProductVO> getProdList() {
		List<ProductVO> list = new ArrayList<>();
		
		try {
			String sql = "select * from productTBL order by num";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setGoods_num(rs.getInt("num"));
				vo.setGoods_category(rs.getString("category"));
				vo.setGoods_name(rs.getString("name"));
				vo.setGoods_price(rs.getInt("price"));
				vo.setGoods_amount(rs.getInt("amount"));
				vo.setGoods_date(rs.getString("goods_date"));
				list.add(vo);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		
		return list;
	}
	
	public int insertProd(ProductVO vo) {
		int result = 0;
		
		try {
			String sql = "insert into productTBL(num,category,name,content,psize,color,amount,price) values(seq_productTBL.nextval,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getGoods_category());
			pstmt.setString(2, vo.getGoods_name());
			pstmt.setString(3, vo.getGoods_content());
			pstmt.setString(4, vo.getGoods_size());
			pstmt.setString(5, vo.getGoods_color());
			pstmt.setInt(6, vo.getGoods_amount());
			pstmt.setInt(7, vo.getGoods_price());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);

		}	
		return result;
	}
	
	
	
	
}
