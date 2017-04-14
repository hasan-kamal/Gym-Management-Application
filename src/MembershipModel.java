import javax.swing.table.*;
import java.sql.*;

public class MembershipModel extends AbstractTableModel{
	
	//Schema for Membership relation:
	//	MembershipPlan(m_code, name, price, duration)
	//  insert into MembershipPlan values(1, 'Gold-membership', 50000, '1 year');
	//	create table MembershipPlan( m_code int, name varchar(50), price int, duration varchar(50));

	int rowCount;
	String data[][];

	public MembershipModel(){
		try{
			
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
 			
 			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery( "select * from MembershipPlan"); 
			
			rowCount=0;
			while(rset.next()){
				rowCount++;
			}
			
			rset = stmt.executeQuery( "select * from MembershipPlan");
			data = new String[rowCount][4];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<4; j++)
					data[i][j] = rset.getString(j+1);
				i++;
			}
			stmt.close();
			conn.close();

		}catch(Exception sqle){
			System.out.println("Exception: " + sqle);
		}
	}

	public int getRowCount(){
		return rowCount;
	}

  	public int getColumnCount(){
  		return 4;
  	}

  	public Object getValueAt(int row, int column){
  		return data[row][column];
  	}

  	public String getColumnName(int column){
  		switch(column){
  			case 0:
  				return "m_code";
  			case 1:
  				return "name";
  			case 2:
  				return "price";
  			case 3:
  				return "duration";
  			default:
  				return "-";
  		}
  	}

}