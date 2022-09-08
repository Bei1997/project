<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*" %>
<!DOCTYPE html>
<html lang="en" style="background-image: url(./photo/15.jpg);"class="bgimg">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員查詢</title>
<style>
@import url(./css/shoppingcart.css);
</style>
</head>
<body>
	<main class="main">
		<aside class="sidebar">
			<nav class="nav">
				<ul>
					<li><a href="#">Welcome</a></li>
					<li><a href="appetizer.html">前菜</a></li>
					<li><a href="Maincourse.html">主食</a></li>
					<li><a href="dessert.html">甜點</a></li>
					<li><a href="drink.html">飲料</a></li>
					<li><a href="order.html">點餐</a></li>
					<li><a href="http://localhost:8080/Project/order_inquire">訂單查詢</a></li>
					<li class="active"><a href="shopping cart.html">會員資料查詢</a></li>
					<li><a href="login.html">會員登出</a></li>
				</ul>
			</nav>
		</aside>
		<section class="menu">
			<div style="margin:20% ;">

		<%
		
			String line = "";
			String uname = (String)session.getAttribute("uname");
			String passwd = (String)session.getAttribute("passwd");
			String name = (String)session.getAttribute("name");
			String phone_number = (String)session.getAttribute("tel");
			String address = (String)session.getAttribute("address");
			String birthday = (String)session.getAttribute("birthday");

			out.print("<div class='table_box'>");
			out.print("<table width='800' height='450' border='2'><tr>");
			out.print("<caption align='center' style='font-size:1cm;font-weight:bold'>會員資料</caption>");
	

			out.print("<thead><tr><th>欄位</th><th>內容</th></tr></thead>");			
			out.print("<tbody><tr><td>會員帳號</td><td>"+uname+"</td></tr>");
			out.print("<tbody><tr><td>會員密碼 </td><td>"+passwd+"</td></tr>");
			out.print("<tbody><tr><td>姓名</td><td>"+name+"</td></tr>");
			out.print("<tbody><tr><td>電話</td><td>"+phone_number+"</td></tr>");
			out.print("<tbody><tr><td>地址</td><td>"+address+"</td></tr>");
			out.print("<tbody><tr><td>生日</td><td>"+birthday+"</td></tr>");

			out.print("</tfoot></table>");
			out.print("</div>");
		%>


	</div>
		</section>
	</main>
</body>
</html>