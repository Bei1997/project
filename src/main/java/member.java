

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


@WebServlet("/member")
public class member extends HttpServlet {
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
				String sql = "select * from users  where email=?  ";
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
			        while(rs.next())
			        {

			        	String uname=rs.getString("email");
			        	String passwd=rs.getString("password");
			        	String name=rs.getString("user_name");
			        	String phone_number=rs.getString("phone_number");
			        	String address=rs.getString("address");
			        	String birthday=rs.getString("birthday");
			        	//set共享資料
						HttpSession session=request.getSession();
						session.setAttribute("uname",uname);
						session.setAttribute("passwd",passwd);
						session.setAttribute("name",name);
						session.setAttribute("tel",phone_number);
						session.setAttribute("address",address);
						session.setAttribute("birthday",birthday);			        	
			        }
			     
			    }
			    catch(SQLException e){}			    			    			    			    
			    try{	        
			    	stmt.close(); 
			        conn.close(); 
			    }
			    catch( SQLException e ){}
			    response.sendRedirect("FrontEnd/member.jsp"); 
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
