

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class customerupdate
 */
@WebServlet("/customerupdate")
public class customerupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customerupdate() {
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
		String name = null;
		try
		{
			 name = session.getAttribute("name").toString();
		}
		catch(Exception ex)
		{
			response.sendRedirect("login.html");
		}
		Connection con = CDB.con();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print("<h2 style='text-align:center;color:green;'>RECORD UPDATE</h2>");
		try {
			String q = "Select * from registration where name='"+name+"'";
			PreparedStatement pst=con.prepareStatement(q);
			ResultSet rs1 = pst.executeQuery(q);
			pw.print("<center>");
			pw.print("<table border='2'><h4>"
					+"<tr><th>NAME</th>"	
					+"<th>STATE</th>"
					+"<th>CITY</th>"
					+"<th>GENDER</th>"
					+"<th>PHONE</th>"
					+"<th>DOB</th>"
					+"<th>GMAIL</th>"
					+"<th>PASSWORD</th>"
					+"<th>CONFIRM PASSWORD</th></tr></h4>"
					);
			while(rs1.next())
			{
				pw.print("<td>"+rs1.getString(2)+"</td>"
						+"<td>"+rs1.getString(3)+"</td>"
						+"<td>"+rs1.getString(4)+"</td>"
						+"<td>"+rs1.getString(5)+"</td>"
						+"<td>"+rs1.getString(6)+"</td>"
						+"<td>"+rs1.getString(7)+"</td>"
						+"<td>"+rs1.getString(8)+"</td>"
						+"<td>"+rs1.getString(9)+"</td>"
						+"<td>"+rs1.getString(10)+"</td>"
						+"<td><a href='operationform1?id="+rs1.getInt(1)+"'>UPDATE</a></td>"
						+"</tr><br><br>"
						);
			}
			pw.print("</table>");
			pw.print("</center>");
		}
		catch(Exception ex)
		{
			pw.print(ex);
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
