package com.sample.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//index.jspから編集ボタンを押した時の処理
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		List<Task> taskList = (List<Task>) session.getAttribute("taskList");

		int taskId = Integer.parseInt(request.getParameter("taskId"));
	
		Task editTask = null;

		for(Task task:taskList) {
			if(task.getTaskId()==taskId) {
				editTask = task;
				break;
			}
		}

//		編集したいタスクをリクエストスコープに保存
		request.setAttribute("editTask", editTask);
		//session.setAttribute("edit",editTask);
		// edit.jspへ飛ぶ
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/edit.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//edit.jspから更新ボタンを押したときの処理
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		int taskId = Integer.parseInt(request.getParameter("taskId"));

		String strDeadline = (String) request.getParameter("deadline");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date deadLine = null;

		try {
			deadLine = dateFormat.parse(strDeadline);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String taskText = request.getParameter("taskText");
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		
				
//		サービスから戻ってきたリストをセッションに入れる
		TaskService taskService = new TaskService();
		taskService.update(taskId, memberId, deadLine, taskText);
		
		List<Task> taskList = taskService.findAll();
		
		session.setAttribute("taskList", taskList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		rd.forward(request, response);

		return;

	}

}
