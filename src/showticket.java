

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
 * Servlet implementation class showticket
 */
@WebServlet("/showticket")
public class showticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showticket() {
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
		String namec = null;
		HttpSession session = request.getSession(false);
		try
		{
			 namec = session.getAttribute("name").toString();
		}
		catch(Exception ex)
		{
			response.sendRedirect("login.html");
		}
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Connection con = CDB.con();
		try {
		String t = "Select * from bookticket where name='"+namec+"'";
		PreparedStatement p  = con.prepareStatement(t);
		ResultSet rs = p.executeQuery();
		pw.print("<h1 style='text-align:center;color:green;'>Ticket Details</h1>");
		pw.print("<center>");
		pw.print("<table border='2'><h4>"
				+"<tr><th>NAME</th>"	
				+"<th>Gmail</th>"
				+"<th>BUS NAME</th>"
				+"<th>TYPE</th>"
				+"<th>PRICE</th>"
				+"<th>SEAT</th>"
				+"<th>DEPARTURE</th>"
				+"<th>ARRIVAL</th>"
				+"<th>DEPARTURE TIME</th>"
				+"<th>ARRIVAL TIME</th>"
				+"<th>QUNTITY</th></tr></h4>"
				);
		while(rs.next())
		{
			pw.print("<td>"+rs.getString(2)+"</td>"
					+"<td>"+rs.getString(3)+"</td>"
					+"<td>"+rs.getString(4)+"</td>"
					+"<td>"+rs.getString(5)+"</td>"
					+"<td>"+rs.getString(6)+"</td>"
					+"<td>"+rs.getString(7)+"</td>"
					+"<td>"+rs.getString(8)+"</td>"
					+"<td>"+rs.getString(9)+"</td>"
					+"<td>"+rs.getString(10)+"</td>"
					+"<td>"+rs.getString(11)+"</td>"
					+"<td>"+rs.getString(12)+"</td>"
					+"<td><a href='cancelticket?id="+rs.getInt(1)+"'>CANCLE</a></td>"
					+"<td><a href='mail?id="+rs.getInt(1)+"'>MAIL</a></td>"
					+"<td><a href='pdf?id="+rs.getInt(1)+"'>PDF</a></td>"
					+"</tr>"
					);
		}
		pw.print("</table>");
		pw.print("</center>");
		}
		catch(Exception e)
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
