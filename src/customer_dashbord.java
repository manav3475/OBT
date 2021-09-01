

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

import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class customer_dashbord
 */
@WebServlet("/customer_dashbord")
public class customer_dashbord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customer_dashbord() {
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
		String name = null;
		HttpSession session = request.getSession(false);
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
		pw.print("<h3 style='text-align:right;color:00CED1;'>"+"Welcome:-"+name+"</h3>");
		pw.print("<h3><a href='logout' style='float:right;color:00CED1;'>Log out</a></h3>");
		pw.print("<h3 style='color:00CED1;'><a href='showticket'>TicketDetails</a></h3>");
		pw.print("<h3 style='text-align:left;color:00CED1;'><a href='customerupdate'>UPDATE</a></h3>");
		try {
			String b = "Select * from bus";
			String q = "Select * from registration where name='"+name+"'";
			PreparedStatement pst1= con.prepareStatement(q);
			ResultSet rs = pst1.executeQuery();
			PreparedStatement pst=con.prepareStatement(b);
			ResultSet rs1 = pst.executeQuery();
			pw.print("<h1 style='text-align:center;color:green;'>BUS DETAILS</h1>");
			pw.print("<center>");
			pw.print("<table border='2'><h4>"
					+"<tr><th>NAME</th>"	
					+"<th>TYPE</th>"
					+"<th>PRICE</th>"
					+"<th>DEPARTURE</th>"
					+"<th>ARRIVAL</th>"
					+"<th>DEPARTURE TIME</th>"
					+"<th>ARRIVAL TIME</th>"
					+"<th>TICKET</th></tr></h4>"
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
						+"<td><a href='BookTicket?id="+rs1.getInt(1)+"'>BOOK TICKET</a></td>"
						+"</tr>"
						);
			}
			pw.print("</table>");
			pw.print("</center>");
		
		}
		catch(Exception ex)
		{
			
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
