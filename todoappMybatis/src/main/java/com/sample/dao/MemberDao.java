package com.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sample.dto.Member;

@Mapper
public interface MemberDao {
	
//	全てのメンバーをリストで取得
	@ResultMap("memberResult")
	@Select("select * from member;")
	public List<Member> findAll();
	
//	Mapでリファクタリング全てのメンバーをリストで取得
	@MapKey("memberId")
	@ResultMap("memberResult")
	@Select("select * from member;")
	public Map<Integer, Member> findAllByMap();
	
//	ログインユーザーが正しいかチェックする
	@ResultMap("memberResult")
	@Select("select * from member where name = #{name} and password = #{password}")
	public Member findByLoginMember(Member loginMember);
	
//	@ResultMap("memberResultJoinTask")
//	@Select("select * from task join member on task.member = member.memberId where task.member = #{memberId}")
//	public Task findByPrimaryKey();
	
	
////	編集のため選択したタスクを表示する
//	@ResultMap("taskResult")
//	@Select("SELECT * FROM task where id = #{id};")
//	public Task findCurrent(int id);
//	
////	タスク完了isCompleted=trueをリストにする
//	@ResultMap("taskResult")
//	@Select("SELECT * FROM task where isCompleted = #{isCompleted}")
//	public List<Task> findAllByArchive(boolean isCompleted);
//	
////	タスクを追加する
//	@Insert("INSERT INTO task(member, deadline, text) VALUES(#{member},#{deadline},#{text});")
//	public void createTask(Task task);
//	
////	削除する
//	@Delete("delete from task where id = #{id};")
//	public void delete(@Param("id")int id);
//	
////	編集する
//	@Update("UPDATE task SET member = #{member}, text = #{text}, deadline = #{deadline}, where id = #{id};")
//	public void update(Task task);
//	
////	完了にする
//	@Update("UPDATE task SET isCompleted = #{isCompleted} where id = #{id};")
//	public void completeTask(@Param("id") int id);
}