package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()호출!");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1> Hello World!!!</h1>");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//PreparedStatement 클래스 data type으로 객체참조 변수 pstmt 선언 후 초기화
		PreparedStatement pstmt = null;
		
		//화면에 입력data를 받아서 각각 변수에 저장하고 출력
		String studentnumber = request.getParameter("StudentNumber");
		String name = request.getParameter("Name");
		String major = request.getParameter("Major");
		String grade = request.getParameter("Grade");
		String major2 = request.getParameter("Major2");
		String startdate = request.getParameter("StartDate");
		String email = request.getParameter("Email");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/dbHomeWork3";
			
			conn = DriverManager.getConnection(url, "hyunjoe3", "mei831");
			System.out.println("연결 성공!!~");
			
			//stmt = conn.createStatement();
			
			String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, studentnumber);
			pstmt.setString(2, name);
			pstmt.setString(3, major);
			pstmt.setString(4, grade);
			pstmt.setString(5, major2);
			pstmt.setString(6, startdate);
			pstmt.setString(7, email);
				
			
			out.println("<h1>" + studentnumber + " , " + name + " , " + major + " , " + 
			grade + " , " + major2 + " , " + startdate + " , " + email + "</h1>");
			
			pstmt.executeUpdate(sql);
					
		}
		
		catch( ClassNotFoundException e)
		{
			System.out.println("드라이버 로딩 실패");
		}
		
		catch( SQLException e)
		{
			System.out.println("에러" + e);
		}
		
		finally
		{
			try
			{
				if( conn != null && !conn.isClosed())
				{
					conn.close();
				}
				
				if( stmt != null && !stmt.isClosed())
				{
					stmt.close();
				}
				
				if( rs != null && !rs.isClosed())
				{
					rs.close();
				}
			}
			catch( SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		out.close();	
}
}
