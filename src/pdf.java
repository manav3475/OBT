

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class pdf
 */
@WebServlet("/pdf")
public class pdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pdf() {
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
		int id=Integer.parseInt(request.getParameter("id"));
		try
		{
			Document d = new Document();
			PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\Admin\\Desktop\\Bus ticket System\\ticket.pdf"));
			d.open();
			Paragraph p=new Paragraph();
			p.add("TICKET");
			p.setAlignment(Element.ALIGN_CENTER);
			d.add(p);
			d.add(Chunk.NEWLINE);
			d.add(Chunk.NEWLINE);
			PdfPTable table =new PdfPTable(new float[] {40,70,70,70,70,70,70,70,70,70,70,70});
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell("ID");
			table.addCell("Customer Name");
			table.addCell("Gmail");
			table.addCell("Bus Name");
			table.addCell("Type");
			table.addCell("Price");
			table.addCell("Seat Number");
			table.addCell("departure");
			table.addCell("arrival");
			table.addCell("Departure Time");
			table.addCell("Arrival Time");
			table.addCell("QUNTITY");
			table.setHeaderRows(1);
			PdfPCell[] cells=table.getRow(0).getCells();
			for(int j=0;j<cells.length;j++)
			{
				cells[j].setBackgroundColor(BaseColor.MAGENTA);
			}
			String q="select * from bookticket where bid='"+id+"'";
			Statement s = con.createStatement();
			ResultSet rs=s.executeQuery(q);
			while(rs.next())
			{
				table.addCell(String.valueOf(rs.getInt(1)));
				table.addCell(rs.getString(2));
				table.addCell(rs.getString(3));
				table.addCell(rs.getString(4));
				table.addCell(rs.getString(5));
				table.addCell(rs.getString(6));
				table.addCell(rs.getString(7));
				table.addCell(rs.getString(8));
				table.addCell(rs.getString(9));
				table.addCell(rs.getString(10));
				table.addCell(rs.getString(11));
				table.addCell(rs.getString(12));
			}
			d.add(table);
			d.close();
			con.close();
			request.getRequestDispatcher("/showticket").include(request,response);
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
