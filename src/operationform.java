

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class operationform
 */
@WebServlet("/operationform")
public class operationform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operationform() {
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
		pw.print("<center><h3>Update Customer ID="+id+"</h3></center>");
		String q ="select * from registration where rid = "+Integer.parseInt(id)+"";
		try {
			
			PreparedStatement ps = con.prepareStatement(q);
			rs =  ps.executeQuery();
			rs.next();
			pw.print("<form action='operationwithdatabase' method='post'>"
					+"<center>"
					+"<table>"
					+"<tr>"
					+		"<td>Name</td>"
					+		"<td>:-</td>"
					+		"<td><input type='text' name='name' placeholder='Enter Name' value='"+rs.getString(2)+"'></td>"
					+"</tr>");
					
					if(rs.getString(3).equals("GUJARAT"))
					{
						pw.print("<tr>"
						+		"<td>State</td>"
						+		"<td>:-</td>"
						+		"<td><select name='state'><option selected>GUJARAT</option><option>Tripura</option><option>Uttar Pradesh</option></select></td>"
						+"</tr>");
					}
					else if(rs.getString(3).equals("Tripura"))
					{
							pw.print("<tr>"
								+		"<td>State</td>"
								+		"<td>:-</td>"
								+		"<td><select name='state'><option>GUJARAT</option><option selected>Tripura</option><option>Uttar Pradesh</option></select></td>"
								+"</tr>");
					}
					else
					{
						pw.print("<tr>"
								+		"<td>State</td>"
								+		"<td>:-</td>"
								+		"<td><select name='state'><option>GUJARAT</option><option>Tripura</option><option selected>Uttar Pradesh</option></select></td>"
								+"</tr>");
					}
					
					if(rs.getString(4).equals("SURAT"))
					{
							pw.print("<tr>"
							+	"<td>City</td>"
							+	"<td>:-</td>"
							+	"<td><select name='city'><option selected>SURAT</option><option>MUMBAI</option><option>RAJKOT</option></select></td>"+
							"</tr>");

					}
					else if(rs.getString(4).equals("MUMBAI"))
					{
							pw.print("<tr>"
								+	"<td>City</td>"
								+	"<td>:-</td>"
								+	"<td><select name='city'><option>SURAT</option><option selected>MUMBAI</option><option>RAJKOT</option></select></td>"+
								"</tr>");
					}
					else
					{
							pw.print("<tr>"
								+	"<td>City</td>"
								+	"<td>:-</td>"
								+	"<td><select name='city'><option>SURAT</option><option>MUMBAI</option><option selected>RAJKOT</option></select></td>"+
								"</tr>");
					}
					
					
					if(rs.getString(5).equals("Male"))
					{
						pw.print("<tr>"+
								"<td>Gender</td>"+
								"<td>:-</td>"+
								"<td><input type='radio' name='gender' value='Male' checked>Male<input type='radio' name='gender' value='Female'>Female</td>"+
								"</tr>");
					}
					else
					{
						pw.print("<tr>"+
								"<td>Gender</td>"+
								"<td>:-</td>"+
								"<td><input type='radio' name='gender' value='Male'>Male<input type='radio' name='gender' value='Female' checked>Female</td>"+
								"</tr>");
					}
					
					pw.print("<tr>"+
							"<td>Phone No</td>"+
							"<td>:-</td>"+
							"<td><input type='text' name='pnum' size='10' maxlength='10' value='"+rs.getString(6)+"'></td>"+
							"</tr>");
					
					pw.print("<tr>"+
							"<td>Date of Birth</td>"+
							"<td>:-</td>"+
							"<td><input type='date' name='dob' value='"+rs.getString(7)+"'></td>"+
						"</tr>");	
					
					pw.print("<tr>"+
							"<td>Email ID</td>"+
							"<td>:-</td>"+
							"<td><input type='email' name='gmailid' value='"+rs.getString(8)+"'></td>"+
						"</tr>");
					
					pw.print("<tr>"+
							"<td>Password</td>"+
							"<td>:-</td>"+
							"<td><input type='text' name='password' value='"+rs.getString(9)+"'></td>"+
						"</tr>");
					
						pw.print("<tr>"+
						"<td>Confim Password</td>"+
						"<td>:-</td>"+
						"<td><input type='text' name='cpassword' value='"+rs.getString(10)+"'></td>"+
						"</tr>");
					
					pw.print("<tr>"
							+ "<input type='hidden' value='update' name='operationtype'>"
							+ "<input type='hidden' value='"+rs.getString("rid")+"' name='id'>"
							+ "<td><input type='submit' name='submit' value='UPDATE' class='btn'></td>"
							+"</tr>"
							+"</table></center></form>"
							);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
