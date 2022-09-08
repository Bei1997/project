

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regist_admin")
public class regist_admin extends HttpServlet {
          
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		response.getWriter().append("Served at: ").append(request.getContextPath());
				// 驅動程式參數
				String sDriver = "com.mysql.jdbc.Driver";
				String user = "root";
				String passwd = "123456";
				String url = "jdbc:mysql://localhost:8888/project";
				String sql = "insert into admin (email,password,admin_name,admin_position) values  (?,?,?,?)  ";
						
				//資料庫物件
						Connection conn = null;
						PreparedStatement stmt = null;
					    ResultSet rs = null;
			    //取得前端資料(數值)
					  	request.setCharacterEncoding("utf-8");
					    String admin_name=request.getParameter("admin_name");
						String email=request.getParameter("email");
						String password=request.getParameter("password");
						String admin_position=request.getParameter("admin_position");

						
											  	
					  	response.setContentType("text/html;charset=utf-8");
					    PrintWriter out=response.getWriter();	       
					       
					    try{   //載入JDBC driver 
					            
					    	Class.forName(sDriver);
					    }
					    catch(Exception e){
					       
					    	out.print("<h1>無法載入驅動程式</h1>");
					        return;
					    }
					       
					    try   //建立資料連結和Statement物件
					    {
					        conn = DriverManager.getConnection(url,user,passwd);
					        if(conn != null)
				            {
					        	 stmt = conn.prepareStatement(sql);					        	 
					        	 stmt.setString(1, email);	
					        	 stmt.setString(2, password);
					        	 stmt.setString(3, admin_name);
					        	 stmt.setString(4, admin_position);	

					        	 stmt.executeUpdate();
					        	 response.sendRedirect("FrontEnd/login.html");
				            }
					    }
					    catch(SQLException e)
					    {
					        out.println("與資料來源連結錯誤");
					        return;
					    }				    
					    					    					    					    
					    
					    try{	        
					    	stmt.close(); 
					        conn.close(); 
					    }
					    catch( SQLException e ){}			
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
