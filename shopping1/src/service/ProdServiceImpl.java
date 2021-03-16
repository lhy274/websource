package service;

import static persistence.JDBCUtil.*;
import java.sql.Connection;
import java.util.List;

import domain.ProductVO;
import persistence.ProdDAO;


public class ProdServiceImpl implements ProdService {

	Connection con;
	ProdDAO dao;

	public ProdServiceImpl() {
		con = getConnection();
		dao = new ProdDAO(con);
	}
	
	
	
	@Override
	public boolean insertProb(ProductVO vo) {
		int result = dao.insertProd(vo);
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
	public List<ProductVO> getProbList() {
		 List<ProductVO> list = dao.getProdList();		 
		 close(con);
		 return list;
	}
	
	
	
	

}
