import javax.swing.table.*;
import java.sql.*;

public class StaffModel extends AbstractTableModel{
	
	//Schema for Staff relation:
	//	Staff(s_id, first_name, last_name, street, city, state, zip, designation, date_birth)
	//  insert into Staff values(1, 'David', 'Bowie', 'Some English Street', 'New York City', 'New York', 910191, 'Janitor', '01-01-1970');
	//	create table Staff( s_id int, first_name varchar(50), last_name varchar(50), street varchar(50), city varchar(50), state varchar(50), zip int, designation varchar(50), date_birth varchar(50), primary key(s_id));

	int rowCount;
	String data[][];

	public StaffModel(){
		try{
			
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + "gym" + "?autoReconnect=true&useSSL=false";
			String userid = "root";
			String passwd = "hasankamal";

			Connection conn = DriverManager.getConnection(url, userid, passwd);
 			
 			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery( "select * from Staff"); 
			
			rowCount=0;
			while(rset.next()){
				rowCount++;
			}
			
			rset = stmt.executeQuery( "select * from Staff");
			data = new String[rowCount][9];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<9; j++)
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
  		return 9;
  	}

  	public Object getValueAt(int row, int column){
  		return data[row][column];
  	}

  	public String getColumnName(int column){
  		switch(column){
  			case 0:
  				return "s_id";
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
  				return "designation";
  			case 8:
  				return "date_birth";
  			default:
  				return "-";
  		}
  	}

}