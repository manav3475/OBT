

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
 * Servlet implementation class operationwithdatabasecus
 */
@WebServlet("/operationwithdatabasecus")
public class operationwithdatabasecus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operationwithdatabasecus() {
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
		String name=request.getParameter("name");
		String state=request.getParameter("state");
		String city=request.getParameter("city");
		String gender=request.getParameter("gender");
		String phone = request.getParameter("pnum");
		String dob=request.getParameter("dob");
		String gmail=request.getParameter("gmailid");
		String pass=request.getParameter("password");
		String cp=request.getParameter("cpassword");
		if(request.getParameter("operationtype").equals("update"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String uq="update registration set name='"+name+"',state='"+state+"',city='"+city+"',gender='"+gender+"',phone='"+phone+"',dob='"+dob+"',gmail='"+gmail+"',password=MD5('"+pass+"'),cpassword=MD5('"+cp+"') where rid='"+id+"'";
			try {
				Statement s = con.createStatement();
				int c=s.executeUpdate(uq);
				if(c==1)
				{
					pw.print("<script>alert('User Updated.....')</script>");
					RequestDispatcher rd = request.getRequestDispatcher("customer_dashbord");
					rd.include(request, response);
				}
				else
				{
					pw.print("<script>alert('Not User Updated.....')</script>");
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
