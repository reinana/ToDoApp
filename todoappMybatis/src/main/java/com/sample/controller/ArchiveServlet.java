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
 * Servlet implementation class ArchiveServlet
 */
@WebServlet("/archive")
public class ArchiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArchiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		if(request.getParameter("taskId") == null) {
			// 終了済みタスク一覧ボタンを押されたときの処理
			
			TaskService taskService = new TaskService();
			List<Task> completedTaskList = taskService.findAllByCompleted();
			
			session.setAttribute("completedTaskList",  completedTaskList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/archive.jsp");
			rd.forward(request, response);

			return;

		}
//		タスク完了ボタンを押した時の処理

		int taskId = Integer.parseInt(request.getParameter("taskId"));
		TaskService taskService = new TaskService();
		taskService.completeTask(taskId);
		List<Task> taskList = taskService.findAll();
		session.setAttribute("taskList", taskList);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//一括完了ボタンを押されたときの処理
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();

//		taskIdの配列を受け取る
		String vals[] = request.getParameterValues("checkbox");
		
		TaskService taskService = new TaskService();
		taskService.completeAll(vals);
		List<Task> taskList = taskService.findAll();
		 
		session.setAttribute("taskList", taskList);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		rd.forward(request, response);

		return;

	}

}
