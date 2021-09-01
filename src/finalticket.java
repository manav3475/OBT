

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

/**
 * Servlet implementation class finalticket
 */
@WebServlet("/finalticket")
public class finalticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public finalticket() {
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
		String namec = null;
		HttpSession session = request.getSession(false);
		try
		{
			 namec = session.getAttribute("name").toString();
		}
		catch(Exception ex)
		{
			response.sendRedirect("login.html");
		}
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Connection con = CDB.con();
		String name = request.getParameter("name");
		String gmail = request.getParameter("gmail");
		String bname = request.getParameter("bname");
		String type = request.getParameter("type");
		String price = request.getParameter("price");
		String seat = request.getParameter("seatno");
		String d = request.getParameter("departure");
		String a = request.getParameter("arrival");
		String dt = request.getParameter("dtime");
		String at = request.getParameter("atime");
		String qty = request.getParameter("qty");
		try {
			String q = "insert into bookticket(name,gmail,bname,type,price,seat,departure,arrival,dtime,atime,qty) values('"+name+"','"+gmail+"','"+bname+"','"+type+"','"+price+"','"+seat+"','"+d+"','"+a+"','"+dt+"','"+at+"','"+qty+"')";
			Statement s = con.createStatement();
			int c = s.executeUpdate(q);
			if(c==1)
			{
				pw.print("<script>alert('Ticket Booked....')</script>");
				RequestDispatcher rd = request.getRequestDispatcher("customer_dashbord");
				rd.include(request, response);
			}
			else
			{
				pw.print("<script>alert('Ticket Not Booked....')</script>");
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
