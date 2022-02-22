package com.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sample.dto.Task;

@Mapper
public interface TaskDao {
	
//	全てのタスクをリストで取得
	@ResultMap("taskResult")
	@Select("select * from task;")
	public List<Task> findAll();
	
//	Mapでリファクタリング全てのタスクをリストで取得
	@MapKey("taskId")
	@ResultMap("taskResult")
	@Select("select * from task;")
	public Map<Integer, Task> findAllByMap();
	
//	編集のため選択したタスクを表示する
	@ResultMap("taskResult")
	@Select("SELECT * FROM task where id = #{id};")
	public Task findCurrent(int id);
	
//	タスク完了isCompleted=trueをリストにする
	@ResultMap("taskResult")
	@Select("SELECT * FROM task where isCompleted = 1")
	public List<Task> findAllByCompleted();
	
//	Mapでリファクタリングタスク完了isCompleted=trueをリストにする
	@MapKey("taskId")
	@ResultMap("taskResult")
	@Select("SELECT * FROM task where isCompleted = 1")
	public Map<Integer,Task> findAllByCompletedByMap();
	
//	タスクを追加する
	@Insert("INSERT INTO task(memberId, deadline, taskText) VALUES(#{memberId},#{deadline},#{taskText});")
	public void createTask(Task task);
	
//	削除する
	@Delete("delete from task where taskId = #{taskId};")
	public void delete(@Param("taskId")int taskId);
	
//	編集する
	@Update("UPDATE task SET memberId = #{memberId}, taskText = #{taskText}, deadline = #{deadline} where taskId = #{taskId};")
	public void update(Task task);
	
//	完了にする
	@Update("UPDATE task SET isCompleted = 1 where taskId = #{taskId};")
	public void completeTask(@Param("taskId") int taskId);
	

}

