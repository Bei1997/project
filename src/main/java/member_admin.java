

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member_admin")
public class member_admin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 驅動程式參數
				String sDriver = "com.mysql.jdbc.Driver";
				String user = "root";
				String password = "123456";
				String url = "jdbc:mysql://localhost:8888/project";
				String sql = "select * from project.admin";
				String line="";	
				//資料庫物件
				Connection conn = null;
				Statement stmt = null;
			    ResultSet rs = null;
									  	
			  	response.setContentType("text/html;charset=utf-8");
			    PrintWriter out=response.getWriter();	       
			       
			    try{   //載入JDBC driver 
			            
			    	Class.forName(sDriver);
			    }
			    catch(Exception e){
			       
			    	out.print("<h1>無法載入驅動程式</h1>");
			        return;
			    }
			    
			    //建立資料庫連結和Statement物件
			    
			    try {
					conn=DriverManager.getConnection(url,user,password);
					stmt=conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					out.print("<h1>與資料來源連結有誤</h1>");
			        return;
				}
			    
			    
			    try
			    { 	           
			        rs = stmt.executeQuery(sql);
			        out.print("<table width='500' border='2'><tr>");
			        out.print("<caption align='center' style='font-size:1cm;font-weight:bold'>員工基本資料</caption>");
			        
			        out.print("<td>管理者編號</td><td>帳號</td><td>密碼</td><td>管理者名稱</td><td>管理者職位</td></tr>");
			        System.out.println("122355");
			        while(rs.next())
			        {
			        	line="<tr><td>";
			        	line=line+rs.getInt("admin_id")+"</td><td>";
			        	line=line+rs.getString("email")+"</td><td>";
			        	line=line+rs.getString("password")+"</td><td>";
			        	line=line+rs.getString("admin_name")+"</td><td>";
			        	line=line+rs.getString("admin_position")+"</td></tr>";
			        	
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
