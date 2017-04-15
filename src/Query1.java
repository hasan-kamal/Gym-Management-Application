import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;

public class Query1 extends ComplexQueryPanel{

	Query1(){
		super();
		try{
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			JLabel label = new JLabel();
			label.setText("Display membership plans with access to all equipment");
			label.setBorder(new EmptyBorder(10, 10, 10, 10));
			this.add(label);

			String query1 = "select m_code from includes where e_id in (select e_id from equipment) group by m_code having count(*) = (select count(*) from equipment)";
			int rowCount=0;
			rset = stmt.executeQuery(query1);
			while(rset.next()){
				rowCount++;
			}
			
			rset = stmt.executeQuery(query1);
			// initialise columnSize;
			int columnSize = 1;
			String data[][] = new String[rowCount][columnSize];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<columnSize; j++)
					data[i][j] = rset.getString(j+1);
				i++;
			}
			stmt.close();
			conn.close();
			for(i=0;i<rowCount;i++){
				for(int j=0;j<columnSize;j++){
					System.out.print(data[i][j] + " | ");
				}
				System.out.println();
			}
			// String column[] = {"Memberships with full access"};
			// JTable jt = new JTable(data, column);
			// JScrollPane scrollPane = new JScrollPane(jt);
			// jt.setFillsViewportHeight(true);
			// this.add(scrollPane);
			String column[] = {"Memberships with full access"};
			MyFilterTable jt = new MyFilterTable(data, column);
			this.add(jt);
		}catch(Exception e){
			System.out.println("Exception : " + e);
		}
		
	}

}