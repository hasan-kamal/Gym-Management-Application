import javax.swing.*;
import java.sql.*;

public abstract class ComplexQueryPanel extends JPanel{

	String url, userid, passwd;
	Connection conn;
	Statement stmt;
	ResultSet rset;

	public ComplexQueryPanel(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			userid = Constants.userid;
			passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);	
			stmt = conn.createStatement();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}