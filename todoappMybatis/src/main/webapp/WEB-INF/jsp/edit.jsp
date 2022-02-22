<%@page import="com.sample.dto.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%
//入っている日付を受け取る
Task taskedit = (Task)request.getAttribute("editTask");
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String deadline = dateFormat.format(taskedit.getDeadline()); // 変数で宣言しとく 


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<main class="container">
		<h2>編集画面</h2>
		<div class="task-card">
			<form method="post" action="edit">
				<input type="hidden" name="taskId" value="${editTask.taskId }" />
				<div class="item">
					<textarea name="taskText" rows="3" cols="30">${requestScope.editTask.taskText }</textarea>
				</div>
				<div class="item">
					<!-- ここで変数をEL式になおす -->
					<c:set var="test" value="<%= deadline %>" />
					<label>期日 <input name="deadline" type="date"
						value="${ test }" /></label>

				</div>
				<div class="item">
					<label>担当者名 <select name="memberId">
							<c:forEach var="member" items="${ sessionScope.memberList }">
								<option value="${ member.memberId }">${ member.name }</option>
							</c:forEach>

					</select>
					</label>
				</div>
				<div class="d-flex buttons">

					<a href="index" class="button2">キャンセル</a> <label><input
						type="submit" value="更新" class="button2"></label>
				</div>
			</form>
		</div>
	</main>
	<!-- footerを静的インクルード -->
	<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>