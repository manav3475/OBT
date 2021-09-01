

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
 * Servlet implementation class GenPDF
 */
@WebServlet("/GenPDF")
public class GenPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenPDF() {
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
		String name=request.getParameter("gmail");
		pw.print("<script>PDF Genrated</script>");
		try
		{
			Document d = new Document();
			PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\Admin\\Desktop\\Bus ticket System\\Report.pdf"));
			d.open();
			Paragraph p=new Paragraph();
			p.add("REPORT");
			p.setAlignment(Element.ALIGN_CENTER);
			d.add(p);
			d.add(Chunk.NEWLINE);
			d.add(Chunk.NEWLINE);
			PdfPTable table =new PdfPTable(new float[] {20,30,30});
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell("TOTAL CUSTOMER");
			table.addCell("TOTAL BUS");
			table.addCell("TOTAL BOOKED TICKET");
			PdfPCell[] cells=table.getRow(0).getCells();
			for(int j=0;j<cells.length;j++)
			{
				cells[j].setBackgroundColor(BaseColor.MAGENTA);
			}
			Connection con=CDB.con();
			String q="select * from registration";
			String q4="select * from bus";
			String q5="Select * from bookticket";
			String q1="select COUNT(*) from registration";
			String q2="select COUNT(*) from BUS";
			String q3="select COUNT(*) from bookticket";
			Statement s4 = con.createStatement();
			ResultSet rs4=s4.executeQuery(q4);
			Statement s = con.createStatement();
			ResultSet rs=s.executeQuery(q);
			Statement s1 = con.createStatement();
			ResultSet rs1=s1.executeQuery(q1);
			Statement s2 = con.createStatement();
			ResultSet rs2=s2.executeQuery(q2);
			Statement s3 = con.createStatement();
			ResultSet rs3=s3.executeQuery(q3);
			Statement s5 = con.createStatement();
			ResultSet rs5=s5.executeQuery(q5);	

			PdfPTable table1 =new PdfPTable(new float[] {10,25,25,25,25,25,25,25,25,25});
			table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell("ID");
			table1.addCell("Customer Name");
			table1.addCell("State");
			table1.addCell("City");
			table1.addCell("Gender");
			table1.addCell("Phone Number");
			table1.addCell("Date of Birth");
			table1.addCell("Gmail");
			table1.addCell("Password");
			table1.addCell("Confirm Password");
			table1.setHeaderRows(1);
			PdfPCell[] cells1=table1.getRow(0).getCells();
			for(int j=0;j<cells1.length;j++)
			{
				cells1[j].setBackgroundColor(BaseColor.MAGENTA);
			}
			rs1.next();
			rs2.next();
			rs3.next();
			table.addCell(String.valueOf(rs1.getInt(1)));
			table.addCell(String.valueOf(rs2.getInt(1)));
			table.addCell(String.valueOf(rs3.getInt(1)));
			while(rs.next())
			{
				table1.addCell(String.valueOf(rs.getInt(1)));
				table1.addCell(rs.getString(2));
				table1.addCell(rs.getString(3));
				table1.addCell(rs.getString(4));
				table1.addCell(rs.getString(5));
				table1.addCell(rs.getString(6));
				table1.addCell(rs.getString(7));
				table1.addCell(rs.getString(8));
				table1.addCell(rs.getString(9));
				table1.addCell(rs.getString(10));
			}
			
			PdfPTable table2 =new PdfPTable(new float[] {10,25,25,25,25,25,25,25});
			table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell("ID");
			table2.addCell("Bus Name");
			table2.addCell("Type");
			table2.addCell("Price");
			table2.addCell("Departure");
			table2.addCell("Arrival");
			table2.addCell("Departure Time");
			table2.addCell("Arrival Time");
			table2.setHeaderRows(1);
			PdfPCell[] cells2=table2.getRow(0).getCells();
			for(int j=0;j<cells2.length;j++)
			{
				cells2[j].setBackgroundColor(BaseColor.MAGENTA);
			}
			while(rs4.next())
			{
				table2.addCell(String.valueOf(rs4.getInt(1)));
				table2.addCell(rs4.getString(2));
				table2.addCell(rs4.getString(3));
				table2.addCell(rs4.getString(4));
				table2.addCell(rs4.getString(5));
				table2.addCell(rs4.getString(6));
				table2.addCell(rs4.getString(7));
				table2.addCell(rs4.getString(8));
			}
			
			
			PdfPTable table3 =new PdfPTable(new float[] {10,25,25,25,25,25,25,25,25,25,25,25});
			table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell("ID");
			table3.addCell("Customer Name");
			table3.addCell("Gmail ID");
			table3.addCell("Bus Name");
			table3.addCell("TYPE");
			table3.addCell("Price");
			table3.addCell("Seat Number");
			table3.addCell("Departure");
			table3.addCell("Arrival");
			table3.addCell("Departure Time");
			table3.addCell("Arrival Time");
			table3.addCell("QUNTITY");
			table3.setHeaderRows(1);
			PdfPCell[] cells3=table3.getRow(0).getCells();
			for(int j=0;j<cells3.length;j++)
			{
				cells3[j].setBackgroundColor(BaseColor.MAGENTA);
			}
			while(rs5.next())
			{
				table3.addCell(String.valueOf(rs5.getInt(1)));
				table3.addCell(rs5.getString(2));
				table3.addCell(rs5.getString(3));
				table3.addCell(rs5.getString(4));
				table3.addCell(rs5.getString(5));
				table3.addCell(rs5.getString(6));
				table3.addCell(rs5.getString(7));
				table3.addCell(rs5.getString(8));
				table3.addCell(rs5.getString(9));
				table3.addCell(rs5.getString(10));
				table3.addCell(rs5.getString(11));
				table3.addCell(rs5.getString(12));
			}
			
		
			d.add(table);
			d.add(table1);
			d.add(table2);
			d.add(table3);
			d.close();
			con.close();
			request.getRequestDispatcher("/Admin_dashbord").include(request,response);
			pw.print("Done");
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
