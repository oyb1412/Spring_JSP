package kr.co.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.myproject.entity.Notice;

@Mapper
public interface NoticeMapper {
    @Insert("INSERT INTO springboot_project_study.notice(memID, title, content, writer, indate, viewCount, commentCount) VALUES(#{memID}, #{title}, #{content}, #{writer}, #{indate} ,#{viewCount}, #{commentCount})")
	public int noticeInsert(Notice notice);
	
	@Select("SELECT idx, memID, title, content, writer, indate, viewCount, commentCount FROM springboot_project_study.notice ORDER BY idx DESC")
	public List<Notice> getList();

	@Select("SELECT idx, memID, title, content, writer, indate, viewCount, commentCount FROM springboot_project_study.notice ORDER BY idx DESC LIMIT #{start}, #{pageSize}")
	public List<Notice> getPagedList(int start, int pageSize);
	
	@Select("SELECT idx, memID, title, content, writer, indate, viewCount, commentCount FROM springboot_project_study.notice WHERE idx=#{idx}")
	public Notice findNotice(int idx);
	
	@Update("UPDATE springboot_project_study.notice SET title=#{title}, content=#{content} WHERE idx=#{idx}")
	public int noticeUpdate(Notice notice);
	
	@Update("UPDATE springboot_project_study.notice SET viewCount = viewCount + 1 WHERE idx=#{idx}")
	public int plusNoticeViewCount(int idx);

	@Update("UPDATE springboot_project_study.notice SET commentCount = commentCount + 1 WHERE idx=#{idx}")
	public int plusNoticeCommentCount(int idx);
	
	@Delete("DELETE FROM springboot_project_study.notice WHERE idx=#{idx}")
	public int noticeDelete(int idx);
}
