

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class searchresult
 */
@WebServlet("/searchresult")
public class searchresult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchresult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		try
		{
			String sa = session.getAttribute("name").toString();
		}
		catch(Exception ex)
		{
			response.sendRedirect("login.html");
		}
		
		Connection con= CDB.con();
		PrintWriter pw = response.getWriter();
		String name=request.getParameter("fetch");
		try {
			String q = "select * from registration where name='"+name+"'";
			PreparedStatement p = con.prepareStatement(q);
			ResultSet rs = p.executeQuery(q);
			pw.print("<table border='2'><center><h4>"
						+"<tr><th>ID</th>"
						+"<th>NAME</th>"	
						+"<th>STATE</th>"
						+"<th>CITY</th>"
						+"<th>GENDER</th>"
						+"<th>PHONE</th>"
						+"<th>DOB</th>"
						+"<th>GMAIL</th>"
						+"<th>PASSWORD</th>"
						+"<th>CONFIRM PASSWORD</th></tr></h4></center>"
						);
				
				while(rs.next())
				{
					pw.print("<tr><td>"+rs.getInt(1)+"</td>"
							+"<td>"+rs.getString(2)+"</td>"
							+"<td>"+rs.getString(3)+"</td>"
							+"<td>"+rs.getString(4)+"</td>"
							+"<td>"+rs.getString(5)+"</td>"
							+"<td>"+rs.getString(6)+"</td>"
							+"<td>"+rs.getString(7)+"</td>"
							+"<td>"+rs.getString(8)+"</td>"
							+"<td>"+rs.getString(9)+"</td>"
							+"<td>"+rs.getString(10)+"</td>"
							+"<td><a href='operationform?id="+rs.getInt(1)+"'>UPDATE</a></td>"
							+"<td><a href='operationwithdatabase?operationtype=Delete&id="+rs.getInt(1)+"'>DELETE</a></td>"
							+"</tr><br><br>"
							);
				}
				pw.print("</table>");
				
			
		}catch(Exception e)
		{
			pw.print(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
