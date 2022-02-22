<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メイン画面</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<header class="page-header d-flex wrapper">
		<h1>ToDo APP</h1>
		<nav>
			<ul class="main-nav d-flex">
				<li><a href="archive" class="button2">終了済みタスク一覧</a></li>
			</ul>
		</nav>
	</header>
	<main class="container">

		<div class="task-card">
			<form method="post" action="top">
				<div class="item">
					<textarea name="taskText" rows="3" cols="30" required></textarea>
				</div>
				<div class="item">
					<label>期日 <input name="deadline" type="date" required></label>
				</div>
				<div class="item">
					<label>担当者名 <select name="memberId">
							<c:forEach var="member" items="${ sessionScope.memberList }">
								<option value="${ member.memberId }">${ member.name }</option>
							</c:forEach>

					</select>
					</label>
				</div>
				<div class="d-flex justify-content-end add">
					<label><input type="submit" value="追加" class="button2"></label>
				</div>
			</form>
		</div>

		<h2>[未完了タスク一覧]</h2>

		<form name="checkbox" method="post" action="archive" class="not-done-form d-flex">

			<!-- 繰り返し -->
			<c:forEach items="${sessionScope.taskList}" var="task">
				<c:if test="${ task.isCompleted == false }">
					<div class="not-done d-flex">
						<div class="task-element">
							<label><input type="checkbox" name="checkbox" value="${ task.taskId }" ></label>
							<div>
								<p>
									<c:out value="${ task.taskText }"></c:out>
								</p>
								<p>
									期日:
									<fmt:formatDate value="${ task.deadline }" pattern="yyyy/MM/dd" />
								</p>
								<p>
									担当者名:
									<c:forEach var="m" items="${ sessionScope.memberList }">
										<c:if test="${task.memberId == m.memberId }">
											<c:out value="${ m.name }"></c:out>
										</c:if>
									</c:forEach>
								</p>
							</div>
							<div class="d-flex buttons">
								<a href="delete?taskId=${ task.taskId }" class="button2">削除</a> <a
									href="edit?taskId=${ task.taskId }" class="button2">編集</a> <a
									href="archive?taskId=${ task.taskId }" class="button2">タスク完了</a>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<!-- 繰り返し終了 -->
			<!-- Mapでリファクタリング -->
			<c:forEach items="${sessionScope.taskMap}" var="task">
				<c:if test="${ task.value.isCompleted == false }">
					<div class="not-done d-flex">
						<div class="task-element">
							<label><input type="checkbox" name="checkbox" value="${ task.key }" ></label>
							<div>
								<p>
									<c:out value="${ task.value.taskText }"></c:out>
								</p>
								<p>
									期日:
									<fmt:formatDate value="${ task.value.deadline }" pattern="yyyy/MM/dd" />
								</p>
								<p>
									担当者名:
									<c:set var="m_id" value="${ task.value.memberId }"/>
									<c:out value="${ sessionScope.memberMap[m_id].name }"/>

								</p>
							</div>
							<div class="d-flex buttons">
								<a href="delete?taskId=${ task.key }" class="button2">削除</a> <a
									href="edit?taskId=${ task.key }" class="button2">編集</a> <a
									href="archive?taskId=${ task.key }" class="button2">タスク完了</a>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<div class="d-flex justify-content-end wrapper">
				<input type="submit" value="一括完了" class="button2">
			</div>
		</form>

	</main>
<!-- footerを静的インクルード -->
<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>