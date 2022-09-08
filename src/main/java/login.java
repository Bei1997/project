
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

@WebServlet("/login")
public class login extends HttpServlet {

	ServletConfig cfg;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		// 驅動程式參數
		String sDriver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:8888/project";
		String sql = "select email,password from users where email=? and password=? ";
		String sql_admin = "select email,password from admin where email=? and password=?";
		// 資料庫物件
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		ServletContext context = cfg.getServletContext();

		// 取得前端資料(數值)
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

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
				stmt.setString(1, email);
				stmt.setString(2, passwd);
				stmt2 = conn.prepareStatement(sql_admin);
				stmt2.setString(1, email);
				stmt2.setString(2, passwd);
			}
		} catch (SQLException e) {
			out.println("與資料來源連結錯誤");
			return;
		}

		try {
			rs2 = stmt2.executeQuery();
			if (rs2.next()) {
				// set共享資料

				context.setAttribute("data", email);
				response.sendRedirect("FrontEnd/menu_admin.html");// 更改為管理者目錄
			} else {
				try {
					rs = stmt.executeQuery();
					if (rs.next()) {
						// set共享資料

						context.setAttribute("data", email);
						response.sendRedirect("FrontEnd/menu2.html");

					} else {
						out.println("帳號密碼錯誤");
						response.sendRedirect("FrontEnd/login.html");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
					out.println("驗證失敗");
				}
			}
		} catch (SQLException e2) {

			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
