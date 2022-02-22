//サービスの仕事は、引数を受け取って必要なインスタンスを作成し、インターフェースを呼び出して処理を実行し、結果を返したり返さなかったり
package com.sample.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import com.sample.dao.TaskDao;
import com.sample.dto.Task;
import com.sample.util.Util;

public class TaskService {
	
//	全てのタスクをリストで取得
	public List<Task> findAll() {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		TaskDaoインターフェースからリストを取得
		List<Task> taskList = taskDao.findAll();
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
//		リストを返す
		return taskList;
	}
	
//	Mapで受け取れないかリファクタリング
	public Map<Integer, Task> findAllByMap() {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		TaskDaoインターフェースからリストを取得
		Map<Integer, Task> taskMap = taskDao.findAllByMap();
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
//		リストを返す
		return taskMap;
	}
	
//	編集のため選択したタスクを表示する
	public Task findCurrent(int id) {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		TaskDaoインターフェースからタスクを取得
		Task task = taskDao.findCurrent(id);
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
//		リストを返す
		return task;
	}
	
//	タスク完了isCompleted=trueをリストにする 引数なくてもいいかな
	public List<Task> findAllByCompleted() {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		TaskDaoインターフェースからリストを取得
		List<Task> taskList = taskDao.findAllByCompleted();
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
//		リストを返す
		return taskList;
	}
	
//	Mapで受け取るリファクタリング タスク完了isCompleted=trueをリストにする 引数なくてもいいかな
	public Map<Integer, Task> findAllByCompletedByMap() {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		TaskDaoインターフェースからリストを取得
		Map<Integer, Task> taskMap = taskDao.findAllByCompletedByMap();
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
//		リストを返す
		return taskMap;
	}
//	タスクを追加する
//	@Insert("INSERT INTO task(member, deadline, text, isCompleted) VALUES(#{memberId},#{deadline},#{taskText});")
	public void createTask(int memberId, Date deadline, String taskText) {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		Taskインスタンスを作る
		Task task = new Task();
		task.setMemberId(memberId);
		task.setDeadline(deadline);
		task.setTaskText(taskText);
		
//		TaskDaoインターフェース
		taskDao.createTask(task);
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();

	}
	
//	削除する
//	@Delete("delete from task where id = #{id};")
	public void delete(@Param("id")int id) {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		TaskDaoインターフェース呼び出す
		taskDao.delete(id);
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
	
	}
	
//	編集する
//	@Update("UPDATE task SET memberId = #{memberId} text = #{text} where taskId = #{taskId};")
	public void update(int taskId, int memberId, Date deadline, String taskText) {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		Taskインスタンスを作る
		Task task = new Task();
		task.setTaskId(taskId);
		task.setMemberId(memberId);
		task.setDeadline(deadline);
		task.setTaskText(taskText);
		System.out.println(task);
//		taskDaoインターフェース
		taskDao.update(task);
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
	}
	
//	完了にする
//	@Update("UPDATE task SET isCompleted = #{isCompleted} where taskId = #{taskId};")
	public void completeTask(int taskId) {
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
//		taskDaoインターフェース
		taskDao.completeTask(taskId);
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
	}
	
//	一括完了
//	@Update 完了のupdateを何回も呼ぶ
	public void completeAll(String[] vals) {
		
//		セッション情報を取得
		SqlSession session = Util.getSqlSession();
//		クラスの定義情報を渡す
		TaskDao taskDao = session.getMapper(TaskDao.class);
		
		for(String val: vals) {
			int taskId = Integer.parseInt(val);
//		taskDaoインターフェース
			taskDao.completeTask(taskId);			
		}
		
//		セッションをコミット
		session.commit();
//		セッションを閉じる
		session.close();
	}
}