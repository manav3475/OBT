

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
 * Servlet implementation class addtodatabase
 */
@WebServlet("/addtodatabase")
public class addtodatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addtodatabase() {
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
		PrintWriter pw = response.getWriter();
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		String price=request.getParameter("price");
		String dep=request.getParameter("departure");
		String arr=request.getParameter("arrival");
		String dt=request.getParameter("dtime");
		String at=request.getParameter("atime");
		try {
			String q="insert into bus(name,type,price,departure,arrival,dtime,atime) values('"+name+"','"+type+"','"+price+"','"+dep+"','"+arr+"','"+dt+"','"+at+"')";
			Statement s = con.createStatement();
			int c = s.executeUpdate(q);
			if(c==1)
			{
				pw.print("<script>alert('Bus ADDED.....')</script>");
				RequestDispatcher rd = request.getRequestDispatcher("Admin_dashbord");
				rd.include(request, response);
			}
			else
			{
				pw.print("<script>alert('Bus Not ADDED.....')</script>");
			}
		}catch(Exception e){
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
