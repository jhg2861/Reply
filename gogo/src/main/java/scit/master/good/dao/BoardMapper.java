package scit.master.good.dao;

import java.util.List;
import java.util.Map;

import scit.master.good.vo.Board;

public interface BoardMapper {
	public int insertBoard(Board board) throws Exception;
	public int updateBoard(Board board) throws Exception;
	public int deleteBoard(int boardnum) throws Exception;
	public List<Board> selectBoard(Map<String, Object> search) throws Exception;
	public Board selectOne(int boardnum) throws Exception;
	public int updateHitCount(int boardnum) throws Exception;
	public int getBoardCount(Map<String, String> search) throws Exception;
	
}
