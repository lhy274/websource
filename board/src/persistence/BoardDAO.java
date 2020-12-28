package persistence;

import static persistence.JDBCUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.BoardVO;
import domain.SearchVO;

import static persistence.JDBCUtil.*;

public class BoardDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BoardDAO(Connection con) {
		this.con = con;
	}

	// CRUD
	
	//게시글 작성
	public int insert(BoardVO vo) {
		String sql = "insert into board(bno,name,password,title,content," + "attach,re_ref,re_lev,re_seq) "
				+ "values(board_seq.nextval,?,?,?,?,?,board_seq.currval,0,0)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getAttach());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}//insert
	
	//게시판 리스트 단독
//	public List<BoardVO> selectAll() {
//		//게시판 리스트 기존 sql bno 대로 desc
////		String sql = "select bno,title,name,regdate,readcount from board order by bno desc";
//		String sql = "select bno,title,name,regdate,readcount,re_lev from board order by re_ref desc, re_seq asc";
//		List<BoardVO> list = new ArrayList<BoardVO>();
//
//		try {
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BoardVO vo = new BoardVO();
//				vo.setBno(rs.getInt("bno"));
//				vo.setTitle(rs.getString("title"));
//				vo.setName(rs.getString("name"));
//				vo.setRegdate(rs.getDate("regdate")); //여기였네요
//				vo.setReadcount(rs.getInt("readcount"));
//				vo.setRe_lev(rs.getInt("re_lev"));
//				list.add(vo);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		return list;
//	}//selectAll

	
	//검색기능 단독
//	public List<BoardVO> searchAll(SearchVO searchVO) {
//		List<BoardVO> search = new ArrayList<BoardVO>();
//		
//		
//		String sql = "select bno,title,name,regdate,readcount,re_lev from board ";
//		sql+="where "+searchVO.getCriteria()+" like ? order by re_ref desc, re_seq asc";
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, "%"+searchVO.getKeyword()+"%");
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) { // 가져나올 필요가 없구나 그냥 검색한거야. 조건을 준거지 그 조건은 필요없어 리스트에 나올 필요가//나중에 보면 가져 나와서 검색창에 배치함
//				BoardVO vo = new BoardVO();
//				vo.setBno(rs.getInt("bno"));
//				vo.setTitle(rs.getString("title"));
//				vo.setName(rs.getString("name"));
//				vo.setRegdate(rs.getDate("regdate")); //여기였네요
//				vo.setReadcount(rs.getInt("readcount"));
//				vo.setRe_lev(rs.getInt("re_lev"));
//				search.add(vo);
//			}			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//			close(rs);
//		}
//		return search;
//	}
	
	//전체 행 수 가져오기
	// 100개의 행 => 한페이지에 10개식 => 1 ~ 10 (1 2 3 4 5 6 7 8 9 10)
	// 91개의 행 => 10페이지에 나와야함/전체행수가 나와야함
	public int totalRows(String criteria, String keyword) {
		String sql = "";
		int totalRow = 0;
		try {
			
			//if(criteria!=null) { !=null로 했더니 에러남
			if(!criteria.isEmpty()) { // 에러나서 엠티로 바꿈. 아래쪽이랑 호환 문제인듯? 호환 아래쪽도 엠티로 바꾸고 첫화면 index에서도 empty로 딸려갈 수 있게 주소를 전부 적어줌
				//검색조건에 맞는 행 수 구하기
				sql = "select count(*) from board where "+criteria+" like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
			}else {
				//전체 게시물 수 구하기
				sql = "select count(*) from board";
				pstmt = con.prepareStatement(sql);
			}	
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRow = rs.getInt(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}		
		return totalRow;
	}
	
	
	
	
	
	
	
	
	
	
	
	//search + list 구현
	public List<BoardVO> getList(SearchVO searchVO) {	
		
//		select bno,title,name,regdate,readcount,re_lev
//		from(select rownum rnum,bno,title,name,regdate,readcount,re_lev
//			from(select bno,title,name,regdate,readcount,re_lev
//				from board 
//				where bno>0 order by re_ref desc, re_seq asc)
//			where rownum<=20)
//		where rnum > 10;
		
		// 문자열 => String,
		// StringBuffer, StringBuilder => append()
		
		int start = searchVO.getPage()*searchVO.getAmount(); //1*10
		int limit = (searchVO.getPage()-1)*searchVO.getAmount(); //(1-1)*10
		
		StringBuilder builder = new StringBuilder();
		List<BoardVO> list = new ArrayList<BoardVO>();	
		try {			
			String sql = "";

			//if(searchVO.getCriteria()!=null) { //검색 - 비어있지 않다면 = 검색을 하겠다는 소리
			if(!searchVO.getCriteria().isEmpty()) { //검색 - 비어있지 않다면 = 검색을 하겠다는 소리
//				sql = "select bno,title,name,regdate,readcount,re_lev from board ";
//				sql += "where "+searchVO.getCriteria()+" like ? order by re_ref desc, re_seq asc";		
				
				builder.append("select bno,title,name,regdate,readcount,re_lev ");
				builder.append("from(select rownum rnum,bno,title,name,regdate,readcount,re_lev ");
				builder.append("from(select bno,title,name,regdate,readcount,re_lev ");
				builder.append("from board ");
				builder.append("where bno>0 and "+searchVO.getCriteria()+" like ? ");
				builder.append("order by re_ref desc, re_seq asc) ");
				builder.append("where rownum <= ?) ");
				builder.append("where rnum > ?");				
				pstmt = con.prepareStatement(builder.toString());
				pstmt.setString(1, "%"+searchVO.getKeyword()+"%");
				pstmt.setInt(2, start); //(1-1)*10,1*10
				pstmt.setInt(3, limit);
				
			}else { //일반 리스트
				//sql = "select bno,title,name,regdate,readcount,re_lev from board order by re_ref desc, re_seq asc";
				builder.append("select bno,title,name,regdate,readcount,re_lev ");
				builder.append("from(select rownum rnum,bno,title,name,regdate,readcount,re_lev ");
				builder.append("from(select bno,title,name,regdate,readcount,re_lev ");
				builder.append("from board ");
				builder.append("where bno>0 order by re_ref desc, re_seq asc) ");
				builder.append("where rownum <= ?) ");
				builder.append("where rnum > ?");	
				pstmt = con.prepareStatement(builder.toString());
				//pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, limit);
			}	
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setName(rs.getString("name"));
				vo.setRegdate(rs.getDate("regdate")); //여기였네요
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRe_lev(rs.getInt("re_lev"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}// getList
	
	
	//특정 번호 내용 조회
	public BoardVO select(int bno) {
		String sql = "select bno,name,title,content,attach,re_ref,re_lev,re_seq from board where bno=?";
		BoardVO vo = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAttach(rs.getString("attach"));	
				// reply에서 필요한 값
				vo.setRe_ref(rs.getInt("re_ref"));
				vo.setRe_lev(rs.getInt("re_lev"));
				vo.setRe_seq(rs.getInt("re_seq"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}		
		return vo;
	}
	
	//조회수 업데이트
	public int readCountUpdate(int bno) {
		String sql = "update board set readcount=readcount+1 where bno=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//게시글 삭제
	public int delete(int bno, String password) {
		String sql = "delete from board where bno=? and password=?";
		int result = 0; //리턴을 보낼라면 밖으로 빼야하는구나
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno); //이거 큰다옴표 계속 틀리네
			pstmt.setString(2, password); //번호랑 비밀번호가 다 맞아야 삭제
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		return result;
	}
	
	//게시글 수정
	public int update(BoardVO vo) {
		int result = 0;				
		String sql = "";		
		try {
			if(vo.getAttach()!=null) { //title=?,content=?,attach=?
				sql = "update board set title=?,content=?,attach=? where bno=? and password=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getAttach());
				pstmt.setInt(4, vo.getBno());
				pstmt.setString(5, vo.getPassword());				
			}else {//title=?,content=?
				sql = "update board set title=?,content=? where bno=? and password=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getBno());
				pstmt.setString(4, vo.getPassword());
			}
			result = pstmt.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		return result;
	}
	
	//댓글
	public int reply(BoardVO vo) {
		int result = 0;
		
		try {
			//원본글 정보 가져오기-그냥 넣어도 되는데 알아보기 쉽도록 뺀건가???
			int re_ref = vo.getRe_ref();
			int re_seq = vo.getRe_seq();
			int re_lev = vo.getRe_lev();
			
			//원본글에 달려 있는 기존 댓글 업데이트 -순서 때문에 기존 댓글을 업데이트 한거야.
			String sql = "update board set re_seq=re_seq+1 "
					+ "where re_ref=? and re_seq > ?";
			pstmt = con.prepareStatement(sql); // 요부분 빼먹었네!!!!!!!
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount>0) {
				commit(con);
			}
			close(pstmt);
			
			//댓글 삽입
			sql = "insert into board(bno,name,password,title,content,attach,re_ref,re_lev,re_seq) values(board_seq.nextval,?,?,?,?,null,?,?,?)";					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, re_ref);
			pstmt.setInt(6, re_lev+1);
			pstmt.setInt(7, re_seq+1);
			result = pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	
	}

	
	
	
	
	
	
	
	
	
}// BoardDAO {
