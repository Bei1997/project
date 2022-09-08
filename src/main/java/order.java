import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/order")
public class order extends HttpServlet {
	ServletConfig cfg;


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = cfg.getServletContext();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//取得共享資料
		String data = (String) context.getAttribute("data");
		// 驅動程式參數
		String sDriver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:8888/project";
		String sql = "insert into project.order (username,pumpkin_soup,tomato_soup,sirloin_steak, ribeye_steak,mocktail, coffee_latte, waffle,brownie) values  (?,?,?,?,?,?,?,?,?)  ";
		String sql2="SELECT no FROM project.order  ORDER BY no DESC LIMIT 1";
		// 資料庫物件
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		// 取得前端資料(數值)
		request.setCharacterEncoding("utf-8");

		int n1 = Integer.parseInt(request.getParameter("number1"));
		int n2 = Integer.parseInt(request.getParameter("number2"));
		int n3 = Integer.parseInt(request.getParameter("number3"));
		int n4 = Integer.parseInt(request.getParameter("number4"));
		int n5 = Integer.parseInt(request.getParameter("number5"));
		int n6 = Integer.parseInt(request.getParameter("number6"));
		int n7 = Integer.parseInt(request.getParameter("number7"));
		int n8 = Integer.parseInt(request.getParameter("number8"));

		response.setContentType("text/html;charset=utf-8");

		try { // 載入JDBC driver

			Class.forName(sDriver);
		} catch (Exception e) {

			out.print("<h1>無法載入驅動程式</h1>");
			return;
		}
		

		try // 建立資料連結和Statement物件
		{
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, data);
				stmt.setInt(2, n1);
				stmt.setInt(3, n2);
				stmt.setInt(4, n3);
				stmt.setInt(5, n4);
				stmt.setInt(6, n5);
				stmt.setInt(7, n6);
				stmt.setInt(8, n7);
				stmt.setInt(9, n8);
				stmt.executeUpdate();
				//set共享資料
				HttpSession session=request.getSession();
				session.setAttribute("data",data);
				session.setAttribute("n1",n1);
				session.setAttribute("n2",n2);
				session.setAttribute("n3",n3);
				session.setAttribute("n4",n4);
				session.setAttribute("n5",n5);
				session.setAttribute("n6",n6);
				session.setAttribute("n7",n7);
				session.setAttribute("n8",n8);
			    		
			}
		} catch (SQLException e) {
			out.println("與資料來源連結錯誤");
			return;
		}
		//取得流水號
				try {
					conn = DriverManager.getConnection(url, user, password);
					rs = stmt.executeQuery(sql2);
					if(rs.next()) {
						String NO=rs.getString("NO");
						HttpSession session=request.getSession();
						session.setAttribute("NO",NO);
						
						}
					else {
						out.println("發生錯誤");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					 out.println("取得流水號失敗");
				}
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
		}
		 response.sendRedirect("FrontEnd/shoppingcart.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
