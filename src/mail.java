

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mysql.cj.protocol.Resultset;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet implementation class mail
 */
@WebServlet("/mail")
public class mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	final String senderEmailID="17bmiit032@gmail.com";
	final String senderpasword="Man011299";
	final String emailSMTServer="smtp.gmail.com";
	final String emailServerPort="465";
	String gmail;
	static String emailsubject= "";
	static String emailbody= "";
	public class SMTPAuthenticator extends javax.mail.Authenticator
	{
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(senderEmailID,senderpasword);
			}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=CDB.con();
		PrintWriter pw = response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		this.emailsubject="BUS TICKET";
		this.emailbody="";
		Properties p = new Properties();
		p.put("mail.smtp.user",senderEmailID);
		p.put("mail.smtp.host",emailSMTServer);
		p.put("mail.smtp.port",emailServerPort);
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.socketFactory.port",emailServerPort);
		p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback","false");
		SecurityManager s = System.getSecurityManager();
		String q ="Select * from bookticket where bid='"+id+"'";
		
		try {
			PreparedStatement pst=con.prepareStatement(q);
			ResultSet rs = pst.executeQuery();
	
			Authenticator a = new SMTPAuthenticator();
			Session s1 = Session.getInstance(p,a);
			Message m=new MimeMessage(s1);
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
			  
		    /*String filename = "ticket.pdf";  
		    DataSource source = (DataSource) new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler((javax.activation.DataSource) source));  
		    messageBodyPart2.setFileName(filename); */
			
			String id1=null;
			String name=null;
			String bname=null;
			String btype=null;
			String price=null;
			String seat=null;
			String d=null;
			String arr=null;
			String dt=null;
			String at=null;
			String qty=null;
			while(rs.next())
			{
				id1=rs.getString(1);
				name=rs.getString(2);
				gmail=rs.getString(3);
				bname=rs.getString(4);
				btype=rs.getString(5);
				price=rs.getString(6);
				seat=rs.getString(7);
				d=rs.getString(8);
				arr=rs.getString(9);
				dt=rs.getString(10);
				at=rs.getString(11);
				qty=rs.getString(12);
			}
			m.setText("Ticket ID:-"+id1+"    "+"Name:-"+name+"    "+"BUS NAME:-"+bname+"    "+"BUS TYPE:-"+btype+"    "+"PRICE:-"+price+"    "+"Seat Number:-"+seat+"    "+"Departure Place:-"+d+"    "+"Arrival Place:-"+arr+"    "+"Departure Time:-"+dt+"    "+"Arrival Time:-"+at+"    "+"Ticket Quntity:-"+qty);
			m.setSubject(emailsubject);
			m.setFrom(new InternetAddress(senderEmailID));
			m.addRecipient(Message.RecipientType.TO,new InternetAddress(gmail));
			Transport.send(m);
			System.out.print("MAIL SEND");
			RequestDispatcher rd =request.getRequestDispatcher("/showticket");
			rd.include(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
