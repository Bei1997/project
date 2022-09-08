

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


@WebServlet("/order_inquire")
public class order_inquire extends HttpServlet {
	ServletConfig cfg;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cfg = config;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 驅動程式參數
				String sDriver = "com.mysql.jdbc.Driver";
				String user = "root";
				String password = "123456";
				String url = "jdbc:mysql://localhost:8888/project";
				String sql = "select * from project.order  where username=? order by datetime desc ";	
			    String line="";							

				//資料庫物件
				Connection conn = null;
				PreparedStatement stmt = null;
			    ResultSet rs = null;
			  //取得共享資料
			    ServletContext context = cfg.getServletContext();
			    String data = (String) context.getAttribute("data");
	    //取得前端資料(數值)
			  	request.setCharacterEncoding("utf-8");
			    String email=request.getParameter("email");
									  	
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
			        conn = DriverManager.getConnection(url,user,password);
			        if(conn != null)
		            {
			        	 stmt = conn.prepareStatement(sql);
			        	 stmt.setString(1, data);	 
		            }
			    }
			    catch(SQLException e)
			    {
			        out.println("與資料來源連結錯誤");
			        return;
			    }
			    
			    try
			    { 	           
			        rs = stmt.executeQuery();	
			        out.print("<table width='500' border='2'><tr>");
			        out.print("<caption align='center' style='font-size:1cm;font-weight:bold'>訂單查詢</caption>");
			        
			        out.print("<td>會員名稱</td><td>訂單號碼</td><td>南瓜濃湯</td><td>蕃茄濃湯</td><td>沙朗牛排</td><td>肋眼牛排</td><td>藍莓加黑莓仿雞尾酒</td><td>拿鐵咖啡</td><td>格子鬆餅</td><td>布朗尼</td><td>日期</td></tr>");
			        while(rs.next())
			        {	
			        	line="<tr><td>";
			        	line=line+rs.getString("username")+"</td><td>";
			        	line=line+rs.getInt("no")+"</td><td>";
			        	line=line+rs.getInt("pumpkin_soup")+"</td><td>";
			        	line=line+rs.getInt("tomato_soup")+"</td><td>";
			        	line=line+rs.getInt("sirloin_steak")+"</td><td>";
			        	line=line+rs.getInt("ribeye_steak")+"</td><td>";
			        	line=line+rs.getInt("mocktail")+"</td><td>";
			        	line=line+rs.getInt("coffee_latte")+"</td><td>";
			        	line=line+rs.getInt("waffle")+"</td><td>";
			        	line=line+rs.getInt("brownie")+"</td><td>";
			        	line=line+rs.getString("datetime")+"</td></tr>";
			        	out.print(line);
			        	
			        	 }
			        out.print("</table>");
			    }
			    catch(SQLException e){}			    			    			    			    
			    try{	        
			    	stmt.close(); 
			        conn.close(); 
			    }
			    catch( SQLException e ){}
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
