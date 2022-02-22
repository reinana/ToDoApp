<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<main id="index">

		<div class="container d-flex">
			<h1>ログイン</h1>
			<form class="login_form d-flex" action="index" method="POST">
				<label for="name">名前：</label> <input id="name" type="text"
					name="name"><br> 
				<label for="password">password：</label> <input
					id="password" type="password" name="password"><br>
				<button type="submit">ログイン</button>
			</form>
		</div>
</main>


<!-- footerを静的インクルード -->
<%@ include file="/WEB-INF/jsp/template/footer.jsp"%>