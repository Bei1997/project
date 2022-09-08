
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/login2")
public class login2 extends HttpServlet {

	ServletConfig cfg;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		PrintWriter out = response.getWriter();
		String sql = "select email,password from users where email=? and password=? ";
		String sql_admin = "select email,password from admin where email=? and password=?";

		try {

			Context initContext = new InitialContext();
			Context context = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) context.lookup("jdbc/mysql");

			// 取得資料庫連結和Statement物件
			Connection dbCon = ds.getConnection();
			PreparedStatement stmt = (PreparedStatement) dbCon.createStatement();
			PreparedStatement stmt2 = (PreparedStatement) dbCon.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSet rs2 = stmt.executeQuery(sql_admin);
			// 取得前端資料(數值)
			request.setCharacterEncoding("utf-8");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			// 建立資料連結和Statement物件
			try {

				if (dbCon != null) {
					stmt = dbCon.prepareStatement(sql);
					stmt2 = dbCon.prepareStatement(sql_admin);
					stmt.setString(1,email);
					stmt.setString(2,passwd);
					stmt2.setString(1,email);
					stmt2.setString(2,passwd);
				}
			} catch (SQLException e) {
				out.println("與資料來源連結錯誤");
				return;
			}
			try {
				rs2 = stmt2.executeQuery();

				if (rs2.next()) { // 使用迴圈顯示記錄資料
					((ServletContext) context).setAttribute("data", email);
					response.sendRedirect("FrontEnd/menu.html");// 更改為管理者目錄
				} else {
					try {
						rs = stmt.executeQuery();
						if (rs.next()) {
							// set共享資料
							((ServletContext) context).setAttribute("data", email);
							response.sendRedirect("FrontEnd/menu_admin.html");

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

				stmt.close(); // 關閉Statement物件
				dbCon.close(); // 關閉連結
			} catch (Exception e) {
				out.print(e);
			}
		} catch (Exception e) {
			out.print(e);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
