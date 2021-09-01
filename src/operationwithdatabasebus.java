

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class operationwithdatabasebus
 */
@WebServlet("/operationwithdatabasebus")
public class operationwithdatabasebus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operationwithdatabasebus() {
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
		
		Connection con= CDB.con();
		PrintWriter pw = response.getWriter();
		String name=request.getParameter("Name");
		String contact_no=request.getParameter("contact_no");
		String car_name=request.getParameter("car_name");
		String car_number=request.getParameter("car_number");
		String mail=request.getParameter("gmail");
		String password=request.getParameter("password");
		if(request.getParameter("operationtype").equals("update"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String uq="update registration set name='"+name+"',contact_no='"+contact_no+"',car_name='"+car_name+"',car_number='"+car_number+"',gmail='"+mail+"',password='"+password+"' where ID='"+id+"'";
			try {
				Statement s = con.createStatement();
				int c=s.executeUpdate(uq);
				if(c==1)
				{
					pw.print("<script>alert('Record Updated')</script>");
					RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard");
					rd.include(request, response);
				}
				else
				{
					pw.print("<script>alert('Record not updated!')</script>");
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				pw.print(e);
			}
		}
		
		if(request.getParameter("operationtype").equals("Delete"))
		{
			int id1=Integer.parseInt(request.getParameter("id"));
			String dq="delete from registration where ID='"+id1+"'";
			try {
				Statement s1 = con.createStatement();
				int c1=s1.executeUpdate(dq);
				if(c1==1)
				{
					pw.print("<script>alert('User Deleted Succesfully')</script>");
					RequestDispatcher rd = request.getRequestDispatcher("Admin_dashbord");
					rd.include(request, response);
				}
				else
				{
					pw.print("<script>alert('User not Deleted')</script>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				pw.print(e);
			}
			
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
