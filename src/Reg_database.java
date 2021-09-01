 

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Reg_database
 */
@WebServlet("/Reg_database")
public class Reg_database extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reg_database() {
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
		try {
		String name=request.getParameter("name");
		String state=request.getParameter("state");
		String city=request.getParameter("city");
		String gender=request.getParameter("gender");
		String pnum=request.getParameter("pnum");
		String dob=request.getParameter("dob");
		String gmail=request.getParameter("gmailid");
		String password=request.getParameter("password");
		String cpassword=request.getParameter("cpassword");
		if(!password.equals(cpassword))
		{
			request.getRequestDispatcher("/registration").include(request, response);;
			pw.print("<center><h4 style='font-family:arial;color:red;'>PASSWORD AND CONFIRM PASSWORD ARE NOT MATCH</h4></center>");
			return;
		}
			String q="insert into registration(name,state,city,gender,phone,dob,gmail,password,cpassword) values('"+name+"','"+state+"','"+city+"','"+gender+"',"
					+ "'"+pnum+"','"+dob+"','"+gmail+"',MD5('"+password+"'),MD5('"+cpassword+"'))";
			String q1="Select * from registration where gmail=(?)";
			PreparedStatement pst= con.prepareStatement(q1);
			pst.setString(1, gmail);	
			ResultSet rs = pst.executeQuery();
			java.sql.Statement st=con.createStatement();
			int i=st.executeUpdate(q);
			String g = null;
			while(rs.next())
			{
				g=rs.getString(8);
			}
			
			if(gmail.equals(g))
			{
				request.getRequestDispatcher("/registration").include(request, response);;
				pw.print("<center><h4 style='font-family:arial;color:red;'>USER IS ALREAD EXIST</h4></center>");
				return;
			}	
			
			if(i==1)
			{
				request.getRequestDispatcher("/registration").include(request, response);;
				pw.print("<center><h4 style='font-family:arial;color:008000;'>Register User successfull</h4></center>");
				pw.print("<center><a href='login.html'>LOGIN</a></center>");
			}
			else
			{
				request.getRequestDispatcher("/registration").include(request, response);;
				pw.print("<center><h4 style='font-family:arial;color:008000;'>User Not Register</h4></center>");
			}
			
		} catch (SQLException e) {
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
