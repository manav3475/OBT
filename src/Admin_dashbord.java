

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin_dashbord
 */
@WebServlet("/Admin_dashbord")
public class Admin_dashbord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_dashbord() {
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
		PrintWriter pw =response.getWriter();
	
		Connection con=CDB.con();
		pw.print("<h3 style='text-align:right;color:00CED1;'>Welcome:-ADMIN</h3>");
		pw.print("<h3><a href='Add_bus' style='float:right;color:00CED1;'>ADD NEW BUS</a></h3><br>");
		pw.print("<h3><a href='logout' style='float:right;color:00CED1;'>Log out</a></h3><br>");
		pw.print("<h3><a href='GenPDF' style='float:right;color:00CED1;'>Check Report</a></h3>");
		try {
			String q="select COUNT(*) from registration";
			String q1="select * from registration";
			String bq="select * from bus";
			Statement s2 = con.createStatement();
			ResultSet rs2=s2.executeQuery(bq);
			Statement s = con.createStatement();
			ResultSet rs =s.executeQuery(q);
			Statement s1=con.createStatement();
			ResultSet rs1=s1.executeQuery(q1);
			rs.next();
			pw.print("<div style='float:left;color:008000;border:3px solid black;padding:5px 5px'>Total<h1>"+rs.getInt(1)+"</h1></div>");
			pw.print("<br><br><br><br><br><br><br><br><br>");
			pw.print("<div style='float:left;'><form action='searchresult'><input type='text' name='fetch' placeholder='search customer' required><input type='submit' name='submit' value='search'></form></div><br><br>");
			pw.print("<h1 style='text-align:center;color:green;'>Customer Details</h1>");
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
			
			while(rs1.next())
			{
				pw.print("<tr><td>"+rs1.getInt(1)+"</td>"
						+"<td>"+rs1.getString(2)+"</td>"
						+"<td>"+rs1.getString(3)+"</td>"
						+"<td>"+rs1.getString(4)+"</td>"
						+"<td>"+rs1.getString(5)+"</td>"
						+"<td>"+rs1.getString(6)+"</td>"
						+"<td>"+rs1.getString(7)+"</td>"
						+"<td>"+rs1.getString(8)+"</td>"
						+"<td>"+rs1.getString(9)+"</td>"
						+"<td>"+rs1.getString(10)+"</td>"
						+"<td><a href='operationform?id="+rs1.getInt(1)+"'>UPDATE</a></td>"
						+"<td><a href='operationwithdatabase?operationtype=Delete&id="+rs1.getInt(1)+"'>DELETE</a></td>"
						+"</tr>"
						);
			}
			pw.print("</table>");
			pw.print("<h1 style='text-align:center;color:green;'>BUS Details</h1>");
			pw.print("<center><h4><table border='2'>"
					+"<tr><th>ID</th>"
					+"<th>NAME</th>"	
					+"<th>TYPE</th>"
					+"<th>PRICE</th>"
					+"<th>DEPARTURE</th>"
					+"<th>ARRIVAL</th>"
					+"<th>DEPARTURE TIME</th>"
					+"<th>ARRIVAL TIME</th></tr></h4></center>"
					);
			
			while(rs2.next())
			{
				pw.print("<tr><td>"+rs2.getInt(1)+"</td>"
						+"<td>"+rs2.getString(2)+"</td>"
						+"<td>"+rs2.getString(3)+"</td>"
						+"<td>"+rs2.getString(4)+"</td>"
						+"<td>"+rs2.getString(5)+"</td>"
						+"<td>"+rs2.getString(6)+"</td>"
						+"<td>"+rs2.getString(7)+"</td>"
						+"<td>"+rs2.getString(8)+"</td>"
						+"<td><a href='operationformbus?id="+rs2.getInt(1)+"'>UPDATE</a></td>"
						+"<td><a href='operationwithdatabasebus?operationtype=Delete&id="+rs2.getInt(1)+"'>DELETE</a></td>"
						+"</tr>"
						);
			}
			pw.print("</table>");
			
			
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
