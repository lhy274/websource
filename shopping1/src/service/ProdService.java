package service;

import java.util.List;

import domain.ProductVO;

public interface ProdService {
	public boolean insertProb(ProductVO vo);
	public List<ProductVO> getProbList();
	
	
	
}
