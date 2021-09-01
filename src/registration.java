

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
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
		Connection con=CDB.con();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print("<html>"+
				"<head>"+
				"<title>User Registration</title>"+
				"<style>"+
					"h1{font-family:arial;}"+
					"td{padding:10px 10px;}"+
					"body{font-family:arial;}"+
					"table{border:5px dotted black;padding:25px;}"+
				"</style>"+
				"</head>"+
				"<body>"+
				"<form action='Reg_database' method='post'>"+
				"<center>"+
				"<h1>User Registration</h1>"+
				"<table>"+
				"<tr>"+
					"<td>Name</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='name' required></td>"+
				"</tr>"+	
				
				"<tr>"+
					"<td>State</td>"+
					"<td>:-</td>"+
					"<td><select name='state' required><option>GUJARAT</option><option>Tripura</option><option>Uttar Pradesh</option></select></td>"+
				"</tr>"+
				
				"<tr>"+
					"<td>City</td>"+
					"<td>:-</td>"+
					"<td><select name='city' required><option>SURAT</option><option>MUMBAI</option><option>RAJKOT</option></select></td>"+
				"</tr>"+

				"<tr>"+
					"<td>Gender</td>"+
					"<td>:-</td>"+
					"<td><input type='radio' name='gender' value='Male'>Male<input type='radio' name='gender' value='Female'>Female</td>"+
				"</tr>"+	
				
				"<tr>"+
					"<td>Phone No</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='pnum' size='10' maxlength='10' required></td>"+
				"</tr>"+	
					
				"<tr>"+
					"<td>Date of Birth</td>"+
					"<td>:-</td>"+
					"<td><input type='date' name='dob' required></td>"+
				"</tr>"+	

			
				"<tr>"+
					"<td>Email ID</td>"+
					"<td>:-</td>"+
					"<td><input type='email' name='gmailid' required></td>"+
				"</tr>"+
					
				"<tr>"+
					"<td>Password</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='password' required></td>"+
				"</tr>"+	

				"<tr>"+
					"<td>Confim Password</td>"+
					"<td>:-</td>"+
					"<td><input type='text' name='cpassword' required></td>"+
				"</tr>"+	
				
				
				"<tr>"+
					"<td></td>"+
					"<td></td>"+
					"<td><input type='SUBMIT' name='SUBMIT' value='SUBMIT'><input type='RESET' name='RESET' value='RESET'></td>"+
				"</tr>"+	

				"</table>"+
				"</center>"+
				"</form>"+
				"</body>"+
				"</html>"
				);
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
