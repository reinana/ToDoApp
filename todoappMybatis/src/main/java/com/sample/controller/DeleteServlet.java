package com.sample.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample.dto.Task;
import com.sample.service.TaskService;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		未完了タスク一覧の削除ボタンを押した時の処理
		//セッションを取得
		HttpSession session = request.getSession();
		//セッションからListを取得

		int taskId = Integer.parseInt(request.getParameter("taskId"));
		// サービスからdaoへ飛んでdelete
		TaskService taskService = new TaskService();
		taskService.delete(taskId);
		
//		変更したリストをセッションに保存
		List<Task> taskList = taskService.findAll();
		session.setAttribute("taskList", taskList);
		// deleteがarchiveから来たときにはarchiveに戻る
		String from = request.getParameter("from");

		if(from != null) {
			List<Task> completedTaskList = taskService.findAllByCompleted();
			session.setAttribute("completedTaskList", completedTaskList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/archive.jsp");
			rd.forward(request, response);
			
			return;
		}
		//topに戻す
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		rd.forward(request, response);

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
