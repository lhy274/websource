package service;

import java.util.List;

import domain.BookVO;

public interface BookService {
	//crud
	public boolean insertBook(BookVO vo);
	public boolean updateBook(BookVO vo);
	public boolean deleteBook(int code);
	
	public List<BookVO> getRows(String criteria, String keyword);
	public List<BookVO> getList();
}
