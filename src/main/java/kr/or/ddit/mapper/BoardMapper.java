package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.Board;

public interface BoardMapper {

	void create(Board board);

	List<Board> list();

	Board read(int boardNo);

	Board modify(int boardNo);

	void update(Board board);
	
	void delete(int boardNo);

	List<Board> search(Board board);

}
