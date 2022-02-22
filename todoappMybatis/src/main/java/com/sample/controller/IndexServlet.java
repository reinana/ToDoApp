package com.sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample.dto.Member;
import com.sample.dto.Task;
import com.sample.service.MemberService;
import com.sample.service.TaskService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		List<Task> list = (List<Task>) session.getAttribute("list");

		if(request.getParameter("taskId") == null) {
//			edit.jspからキャンセルボタンを押した時の処理
//			archive.jspからメイン画面に戻るを押した時の処理
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
			rd.forward(request, response);

			return;

		}

//		int id = Integer.parseInt(request.getParameter("id"));
//
//		for(Task task:list) {
//			if(task.getTaskId()==id) {
//				task.setIsCompleted(true);
//				break;
//			}
//		}
//
//		session.setAttribute("list", list);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//ログインフォームから来た処理
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		//dbに接続して存在するか確認 memberservice -> memberdao
		MemberService memberService = new MemberService();
		Member loginMember = memberService.findByLoginMember(name, password);

		if(loginMember == null) {
			// ログイン情報がdbに無かったらログインページに戻る
			response.sendRedirect("/todoappMybatis/index.jsp");
		}
		else {



			// 正しいログインメンバーだったときは以下の処理

			HttpSession session = request.getSession();
			//dbからタスク一覧を取得
			TaskService taskService = new TaskService();
			List<Task> taskList = taskService.findAll();
			
			//dbからMapで受け取ってみる
			Map<Integer, Task> taskMap = taskService.findAllByMap();
			if(taskMap == null) taskMap = new HashMap<Integer, Task>();

			//sessionのなかにタスクが存在しない場合は新しく作る
			if(taskList == null) {
				taskList = new ArrayList<>();
			}

			//dbから担当者一覧を取得 サービス->memberDao
			List<Member> memberList = memberService.findAll();
			//		ｄｂにメンバーがいなかったらリスト作る
			if(memberList == null) {
				memberList = new ArrayList<Member>();
			}
			
			//Mapで受け取ってみるdbから担当者一覧を取得 サービス->memberDao
			Map<Integer, Member> memberMap = memberService.findAllByMap();
			//		ｄｂにメンバーがいなかったらリスト作る
			if(memberMap == null) {
				memberMap = new HashMap<Integer, Member>();
			}
			
			
			System.out.println("Map");
			System.out.println(taskMap);

			//セッションにタスクのListを格納する
			session.setAttribute("taskList", taskList);
			session.setAttribute("memberList", memberList);
			
			session.setAttribute("taskMap", taskMap);
			session.setAttribute("memberMap", memberMap);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
			rd.forward(request, response);

			return;
		}
	}

}
