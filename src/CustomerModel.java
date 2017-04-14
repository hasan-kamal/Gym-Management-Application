import javax.swing.table.*;
import java.sql.*;

public class CustomerModel extends AbstractTableModel{
	
	//Schema for Customer relation:
	//	Customer(c_id, first_name, last_name, street, city, state, zip, date_birth, m_code, m_start_date)
	//  insert into Customer values(1, 'Jeff', 'Bezos', 'Some Amazon Street', 'New York City', 'New York', 910191, '01-01-1970', 1, '01-01-1970');
	//  create table Customer( c_id int, first_name varchar(50), last_name varchar(50), street varchar(50), city varchar(50), state varchar(50), zip int, date_birth varchar(50), m_code int, m_start_date varchar(50), primary key(c_id));

	int rowCount;
	String data[][];

	public CustomerModel(){
		try{
			
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
 			
 			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery( "select * from Customer"); 
			
			rowCount=0;
			while(rset.next()){
				rowCount++;
			}
			
			rset = stmt.executeQuery( "select * from Customer");
			data = new String[rowCount][10];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<10; j++)
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
  		return 10;
  	}

  	public Object getValueAt(int row, int column){
  		return data[row][column];
  	}

  	public String getColumnName(int column){
  		switch(column){
  			case 0:
  				return "c_id";
  			case 1:
  				return "first_name";
  			case 2:
  				return "last_name";
  			case 3:
  				return "street";
  			case 4:
  				return "city";
  			case 5:
  				return "state";
  			case 6:
  				return "zip";
  			case 7:
  				return "date_birth";
  			case 8:
  				return "m_code";
  			case 9:
  				return "m_start_date";
  			default:
  				return "-";
  		}
  	}

}