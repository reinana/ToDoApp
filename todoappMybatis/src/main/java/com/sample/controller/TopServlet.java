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
 * Servlet implementation class TopServlet
 */
@WebServlet("/top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TopServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		index.jspから追加ボタンを押した時の処理

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

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


		//サービスから戻ってきたリストをセッションに入れる
		TaskService taskService = new TaskService();
		taskService.createTask(memberId, deadLine, taskText);

		List<Task> taskList = taskService.findAll();

		session.setAttribute("taskList", taskList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		rd.forward(request, response);

		return;



	}

}
