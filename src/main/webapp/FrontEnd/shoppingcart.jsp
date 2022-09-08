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
<title>購物車</title>
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
					<li><a href="http://localhost:8080/Project/member">會員資料查詢</a></li>
					<li><a href="login.html">會員登出</a></li>
				</ul>
			</nav>
		</aside>
		<section class="menu">
			<div style="margin:20% ;">
		<%		
			String data = (String)session.getAttribute("data");
			int n1 = (int)session.getAttribute("n1");
			int n2 = (int)session.getAttribute("n2");
			int n3 = (int)session.getAttribute("n3");
			int n4 = (int)session.getAttribute("n4");
			int n5 = (int)session.getAttribute("n5");
			int n6 = (int)session.getAttribute("n6");
			int n7 = (int)session.getAttribute("n7");
			int n8 = (int)session.getAttribute("n8");
			String no = (String)session.getAttribute("NO");
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm");
			out.print("<div class='table_box'>");
			out.print("<table width='800' height='450' border='2'><tr>");
			out.print("<caption align='center' style='font-size:1cm;font-weight:bold'>訂單內容</caption>");
			out.print("<thead><tr><th colspan='2'>會員資料</th></tr></thead>");	
			out.print("<tbody><tr><td>會員帳號:</td><td>"+data+"</td></tr>");
			out.print("<tbody><tr><td>訂單號碼:</td><td>"+no+"</td></tr>");
			out.print("<tbody><tr><td>訂購時間:</td><td>"+ft.format(dNow)+"</td></tr>");
			out.print("<thead><tr><th>品項</th><th>數量</th></tr></thead>");			
			out.print("<tbody><tr><td>南瓜濃湯 Pumpkin Soup</td><td>"+n1+"</td></tr>");
			out.print("<tbody><tr><td>蕃茄濃湯 Tomato Soup</td><td>"+n2+"</td></tr>");
			out.print("<tbody><tr><td style='color:aliceblue ;'>沙朗牛排 Sirloin Steak</td><td>"+n3+"</td></tr>");
			out.print("<tbody><tr><td>肋眼牛排  Ribeye Steak</td><td>"+n4+"</td></tr>");
			out.print("<tbody><tr><td>藍莓加黑莓仿雞尾酒  Mocktail</td><td>"+n5+"</td></tr>");
			out.print("<tbody><tr><td>拿鐵咖啡  Coffee Latte</td><td>"+n6+"</td></tr>");
			out.print("<tbody><tr><td>格子鬆餅 Waffle</td><td>"+n7+"</td></tr>");
			out.print("<tfoot><tr><td>布朗尼  Brownie</td><td>"+n8+"</td></tr>");			
			out.print("</tfoot></table>");
			out.print("</div>");
		%>
		
	</div>
		</section>
	</main>
</body>
</html>