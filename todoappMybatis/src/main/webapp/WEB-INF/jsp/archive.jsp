<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>完了済みタスク一覧</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<header class="page-header d-flex wrapper">
		<h2>完了済みタスク一覧</h2>
		<nav>
			<ul class="main-nav d-flex">
				<li><a href="index" class="button2">メイン画面に戻る</a></li>
			</ul>
		</nav>
	</header>
	<main class="container">

			<!-- 繰り返し -->
			<c:forEach items="${sessionScope.completedTaskList}" var="task">
					<div class="not-done d-flex">
						<div class="task-element">
							<div>
								<p><c:out value="${ task.taskText }"></c:out></p>
								<p>期日 <fmt:formatDate value="${ task.deadline }" pattern="yyyy/MM/dd" /></p>
								<p>担当者名 
									<c:forEach var="m" items="${ sessionScope.memberList }">
										<c:if test="${task.memberId == m.memberId }">
											<c:out value="${ m.name }"></c:out>
										</c:if>
									</c:forEach>
								</p>
							</div>
							<div class="d-flex buttons">
								<a href="delete?taskId=${ task.taskId }&from=archive" class="button2">削除</a>
							</div>
						</div>
					</div>
				
			</c:forEach>
			<!-- 繰り返し終了 -->
		<div class="d-flex justify-content-end wrapper">
			<a href="index" class="button2">メイン画面に戻る</a>
		</div>

	</main>
<!-- footerを静的インクルード -->
<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>