import javax.swing.table.*;
import java.sql.*;

public class EquipmentModel extends AbstractTableModel{
	
	//Schema for Equipment relation:
	//	Equipment(e_id, name, type, is_working)
	//  insert into Equipment values(1, 'HeavyTreadmill', 'treadmill', 'yes');
	//	create table Equipment( e_id int, name varchar(50), type varchar(50), is_working varchar(50), primary key(e_id));

	int rowCount;
	String data[][];

	public EquipmentModel(){
		try{
			
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + "gym" + "?autoReconnect=true&useSSL=false";
			String userid = "root";
			String passwd = "hasankamal";

			Connection conn = DriverManager.getConnection(url, userid, passwd);
 			
 			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery( "select * from Equipment"); 
			
			rowCount=0;
			while(rset.next()){
				rowCount++;
			}
			
			rset = stmt.executeQuery( "select * from Equipment");
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
  				return "e_id";
  			case 1:
  				return "name";
  			case 2:
  				return "type";
  			case 3:
  				return "is_working";
  			default:
  				return "-";
  		}
  	}

}