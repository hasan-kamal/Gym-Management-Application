import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;

public class Query5 extends ComplexQueryPanel{

	Query5(){
		super();
		try{
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			JLabel label = new JLabel();
			label.setText("Display staff in increasing order of visits");
			label.setBorder(new EmptyBorder(10, 10, 10, 10));
			this.add(label);

			String query1 = "select s_id, count(*) from StaffLog where in_or_out = TRUE group by s_id order by count(*)";
			int rowCount=0;
			rset = stmt.executeQuery(query1);
			while(rset.next()){
				rowCount++;
			}
			
			rset = stmt.executeQuery(query1);
			// initialise columnSize;
			int columnSize = 2;
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
			// String column[] = {"Staff id","Number of visits"};
			// JTable jt = new JTable(data, column);
			// JScrollPane scrollPane = new JScrollPane(jt);
			// jt.setFillsViewportHeight(true);
			// this.add(scrollPane);
			String column[] = {"Staff id","Number of visits"};
			MyFilterTable jt = new MyFilterTable(data, column);
			this.add(jt);
		}catch(Exception e){
			System.out.println("Exception : " + e);
		}
		
	}

}