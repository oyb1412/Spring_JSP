package kr.co.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.myproject.entity.Board;

@Mapper
public interface BoardMapper {
	
	@Insert("INSERT INTO springboot_project_study.board(memID, title, content, writer, indate, viewCount, commentCount) VALUES(#{memID}, #{title}, #{content}, #{writer}, #{indate} ,#{viewCount}, #{commentCount})")
	public int boardInsert(Board board);
	
	@Select("SELECT idx, memID, title, content, writer, indate, viewCount, commentCount FROM springboot_project_study.board ORDER BY idx DESC")
	public List<Board> getList();

	@Select("SELECT idx, memID, title, content, writer, indate, viewCount, commentCount FROM springboot_project_study.board ORDER BY idx DESC LIMIT #{start}, #{pageSize}")
	public List<Board> getPagedList(int start, int pageSize);
	
	@Select("SELECT idx, memID, title, content, writer, indate, viewCount, commentCount FROM springboot_project_study.board WHERE idx=#{idx}")
	public Board findBoard(int idx);
	
	@Update("UPDATE springboot_project_study.board SET title=#{title}, content=#{content} WHERE idx=#{idx}")
	public int boardUpdate(Board board);
	
	@Update("UPDATE springboot_project_study.board SET viewCount = viewCount + 1 WHERE idx=#{idx}")
	public int plusBoardViewCount(int idx);

	@Update("UPDATE springboot_project_study.board SET commentCount = commentCount + 1 WHERE idx=#{idx}")
	public int plusBoardCommentCount(int idx);
	
	@Delete("DELETE FROM springboot_project_study.board WHERE idx=#{idx}")
	public int boardDelete(int idx);

}
