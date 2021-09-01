

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
 * Servlet implementation class operationformbus
 */
@WebServlet("/operationformbus")
public class operationformbus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operationformbus() {
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
		Connection con = CDB.con();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String id = request.getParameter("id");
		ResultSet rs = null;
		pw.print("<center><h3>Update Bus ID="+id+"</h3></center>");
		String q ="select * from bus where bid = "+Integer.parseInt(id)+"";
		try {
			
			PreparedStatement ps = con.prepareStatement(q);
			rs =  ps.executeQuery();
			rs.next();
			pw.print("<form action='operationwithdatabasebus' method='post'>"
					+"<center>"
					+"<table>"
					+"<tr>"
					+		"<td>Name</td>"
					+		"<td>:-</td>"
					+		"<td><input type='text' name='name'  value='"+rs.getString(2)+"'></td>"
					+"</tr>");
					
					if(rs.getString(3).equals("SLEEPER"))
					{
						pw.print("<tr>"
						+		"<td>Type</td>"
						+		"<td>:-</td>"
						+		"<td><select name='type'><option selected>SLEEPER</option><option>SITTING</option></select></td>"
						+"</tr>");
					}
					else
					{
							pw.print("<tr>"
								+		"<td>Type</td>"
								+		"<td>:-</td>"
								+		"<td><select name='type'><option>SLEEPER</option><option selected>SITTING</option></select></td>"
								+"</tr>");
					}
					
					
					pw.print("<tr>"+
					"<td>price</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='price' value='"+rs.getString(4)+"'></td>"+
					"</tr>");
					
					
					pw.print("<tr>"+
				"<td>Departure</td>"+
				"<td>:-</td>"+
				"<td><input type='text' name='departure' value='"+rs.getString(5)+"'></td>"+
				"</tr>");
					
					pw.print("<tr>"+
					"<td>Arrival</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='arrival' value='"+rs.getString(6)+"'></td>"+
					"</tr>");
					
					pw.print("<tr>"+
					"<td>Departure time</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='dtime' value='"+rs.getString(7)+"'></td>"+
					"</tr>");	
					
					pw.print("<tr>"+
				"<td>Arrival time</td>"+
				"<td>:-</td>"+
				"<td><input type='text' name='atime' value='"+rs.getString(8)+"'></td>"+
				"</tr>");
					
					
					pw.print("<tr>"
							+ "<input type='hidden' value='update' name='operationtype'>"
							+ "<input type='hidden' value='"+rs.getString("bid")+"' name='id'>"
							+ "<td><input type='submit' name='submit' value='UPDATE' class='btn'></td>"
							+"</tr>"
							+"</table></center></form>");
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
