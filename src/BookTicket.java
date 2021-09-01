

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
 * Servlet implementation class BookTicket
 */
@WebServlet("/BookTicket")
public class BookTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTicket() {
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
		String sa=null;
		try
		{
			 sa = session.getAttribute("name").toString();
		}
		catch(Exception ex)
		{
			response.sendRedirect("login.html");
		}
		response.setContentType("text/html");
		Connection con = CDB.con();
		String id  = request.getParameter("id");
		PrintWriter pw = response.getWriter();
		pw.print("<h1 style='text-align:center;color:green;'>TICKET</h1>");
		try {
			String q  = "Select * from registration where name='"+sa+"'";
			String bq = "Select * from bus where bid='"+id+"'";
			PreparedStatement pst1=con.prepareStatement(bq);
			ResultSet rs1 = pst1.executeQuery();
			PreparedStatement pst = con.prepareStatement(q);
			ResultSet rs = pst.executeQuery();
			rs.next();
			rs1.next();
			String price=rs1.getString(4);
			pw.print("<center>");
			pw.print("<form action='finalticket' method='post'>");
			pw.print("<table><h4>"
					+"<tr>"
					+"<td>Name</td>"
					+"<td>:-</td>"
					+"<td><input type='text' name='name' value='"+rs.getString(2)+"' readonly></td>"
					+"</tr>"
					
					+"<tr>"
					+"<td>Gmail</td>"
					+"<td>:-</td>"
					+"<td><input type='email' name='gmail' value='"+rs.getString(8)+"' required></td>"
					+"</tr>"
					
					+"<tr>"
					+"<td>BUS NAME:-</td>"
					+"<td>:-</td>"
					+"<td><input type='text' name='bname' value='"+rs1.getString(2)+"' readonly></td>"
					+"</tr>"

					+"<tr>"
					+"<td>TYPE</td>"
					+"<td>:-</td>"
					+"<td><input type='text' name='type' value='"+rs1.getString(3)+"' readonly></td>"
					+"</tr>"
					
					+"<tr>"+
					"<td>PRICE</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='price' value='"+rs1.getString(4)+"' readonly></td>"+
					"</tr>"
					
					+"<tr>"+
					"<td>SEAT NO</td>"+
					"<td>:-</td>"+
					"<td><select name='seatno' required><option>1</option><option>2</option><option>3</option><option>4</option></td>"+
					"</tr>"
	
					+"<tr>"+
					"<td>Departure</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='departure' value='"+rs1.getString(5)+"' readonly></td>"+
					"</tr>"
					
					+"<tr>"+
					"<td>Arrival</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='arrival' value='"+rs1.getString(6)+"' readonly></td>"+
					"</tr>"
					
					+"<tr>"+
					"<td>Departure time</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='dtime' value='"+rs1.getString(7)+"' readonly></td>"+
					"</tr>"
					
					+"<tr>"+
					"<td>Arrival time</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='atime' value='"+rs1.getString(8)+"' readonly></td>"+
					"</tr>"
					
					+"<tr>"+
					"<td>Quntity</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='qty' value='1' required></td>"+
					"</tr>"
						
					+"<tr>"+
					"<td></td>"+
					"<td></td>"+
					"<td><input type='submit' name='submit' value='BOOK'></td>"+
					"</tr>"
					);
		}catch(Exception ex)
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
