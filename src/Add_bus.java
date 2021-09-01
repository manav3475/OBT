

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Add_bus
 */
@WebServlet("/Add_bus")
public class Add_bus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_bus() {
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
		
	
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print("<center><h2>ADD NEW BUS DETAILS<h2></center>");
		pw.print("<form action='addtodatabase' method='post'>"+
				"<center>"+
				"<table>"+
				"<tr>"+
					"<td>Name</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='name' required></td>"+
				"</tr>"+
								
				"<tr>"+
				"<td>Type</td>"+
				"<td>:-</td>"+
				"<td><select name='type'><option>SLEEPER</option><option>SITTING</option></select></td>"+
				"</tr>"+
				
				"<tr>"+
				"<td>Price</td>"+
				"<td>:-</td>"+
				"<td><input type='text' name='price' required></td>"+
				"</tr>"+

				"<tr>"+
				"<td>Departure</td>"+
				"<td>:-</td>"+
				"<td><input type='text' name='departure' required></td>"+
				"</tr>"+
				
				"<tr>"+
				"<td>Arrival</td>"+
				"<td>:-</td>"+
				"<td><input type='text' name='arrival' required></td>"+
				"</tr>"+

				"<tr>"+
				"<td>Departure time</td>"+
				"<td>:-</td>"+
				"<td><input type='text' name='dtime' required></td>"+
				"</tr>"+
				
				"<tr>"+
				"<td>Arrival time</td>"+
				"<td>:-</td>"+
				"<td><input type='text' name='atime' required></td>"+
				"</tr>"+
				
				"<tr>"+
				"<td></td>"+
				"<td></td>"+
				"<td><input type='SUBMIT' name='SUBMIT' value='SUBMIT'><input type='RESET' name='RESET' value='RESET'></td>"+
				"</tr>");

		pw.print("</table></center></table>");
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
