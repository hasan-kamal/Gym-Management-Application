import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public abstract class AddState extends JPanel{

	Statement stmt;
	Connection conn;
	AddCustomDialog parentDialog;

	public AddState(AddCustomDialog dialog){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		parentDialog = dialog;
	}

	public void addClicked(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}