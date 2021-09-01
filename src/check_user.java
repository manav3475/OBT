

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class check_user
 */
@WebServlet("/check_user")
public class check_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public check_user() {
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
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		Connection con=CDB.con();
		ResultSet rs;
		String un=request.getParameter("username");
		String p=request.getParameter("password");
		HttpSession session = request.getSession(true);
		try {
			String q="SELECT * from registration where gmail=(?) and password=MD5('"+p+"')";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setString(1,un);
			rs=pst.executeQuery();
			if(rs.next())
			{
				
				String u=rs.getString(8);
				String pa=rs.getString(9);
				String name=rs.getString(2);
			
				session.setAttribute("name", name);
				RequestDispatcher rd = request.getRequestDispatcher("customer_dashbord");
				rd.forward(request, response);	
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);
				pw.print("<center><h4 style=color:red;>USERNAME OR PASSWORD IS INVALID</h4></center>");
			}
			
			if(un.equalsIgnoreCase("admin@gmail.com") && p.equalsIgnoreCase("admin123"))
			{
		
				session.setAttribute("name", "ADMIN");
				RequestDispatcher rd = request.getRequestDispatcher("Admin_dashbord");
				rd.forward(request, response);
			}	
			
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
