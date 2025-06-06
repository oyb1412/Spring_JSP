package kr.co.myproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.myproject.entity.Board;
import kr.co.myproject.entity.User;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO springboot_project_study.user(username,password,writer,role) VALUES(#{username}, #{password}, #{writer}, #{role})")
	public int insertUser(User user);
	
	@Select("SELECT username,password,writer,role FROM springboot_project_study.user WHERE username=#{username}")
	public User findByUsername(String username);
	
	@Select("SELECT writer FROM springboot_project_study.user WHERE username=#{username}")
	public String findWriter(String username);

	@Select("SELECT COUNT(*) FROM springboot_project_study.user WHERE username = #{username}")
	public int countByUsername(String username);
		
	@Update("UPDATE springboot_project_study.user SET password = #{password} WHERE username = #{username}")
	public int updatePassword(User user);

	@Update("UPDATE springboot_project_study.user SET writer = #{writer} WHERE username =#{username}")
	public int UpdateWriter(User user);

	@Update("UPDATE springboot_project_study.user SET password = #{password} WHERE username =#{username}")
	public int UpdatePassword(User user);
}
