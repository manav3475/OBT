import java.sql.*;

public class CDB {
public static Connection con() 
{
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/obt","root","root");
		return con;
	}
	catch(Exception e)
	{
		
	}
	return null;
}
}
