package kr.co.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.myproject.entity.Board;
import kr.co.myproject.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	public int boardInsert(Board board)
	{
		return boardMapper.boardInsert(board);
	}

	public List<Board> getPagedList(int start, int pageSize){
		return boardMapper.getPagedList(start, pageSize);
	}
	
	public List<Board> getList()
	{
		return boardMapper.getList();
	}
	
	public int boardUpdate(Board board)
	{
		return boardMapper.boardUpdate(board);
	}
	
	public int plusBoardViewCount(int idx)
	{
		return boardMapper.plusBoardViewCount(idx);
	}
	
	public int boardDelete(int idx)
	{
		return boardMapper.boardDelete(idx);
	}

	public Board findBoard(int idx)
	{
		return boardMapper.findBoard(idx);
	}
}
