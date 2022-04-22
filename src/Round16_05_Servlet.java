import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Round16_05_Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			req.setCharacterEncoding("utf-8");
			
			String subject = req.getParameter("subject");
			String author = req.getParameter("author");
			String contents = req.getParameter("contents");
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><body><conter><h3>");
			
			try {
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			}catch(Exception e) {}
			Connection conn= null;
			PreparedStatement pstmt = null;
			String url = "jdbc:mysql://localhost:3306/sj";
			String id = "root";
			String pw = "";
			String query = "insert into Round16_Table_01 values (null, ?,?,?)";
			try {
				conn = DriverManager.getConnection(url, id, pw);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, subject);
				pstmt.setString(2, author);
				pstmt.setString(3, contents);
				
				int res = pstmt.executeUpdate();
				
				if(res>0)
					out.println("Success Save!!");
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				out.println("SQL Process Error: " +e.getMessage());
				
			}
			out.println("</h3></center></body></html>");
			
	}
	

}
