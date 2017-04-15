import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;

public class Query4 extends ComplexQueryPanel{

	Query4(){
		super();
		try{
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			JLabel label = new JLabel();
			label.setText("Display number of customers in each membership plan category");
			label.setBorder(new EmptyBorder(10, 10, 10, 10));
			this.add(label);

			String query1 = "select m_code,count(*) from Customer group by m_code";
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
			String column[] = {"Membership code","Number of customers"};
			JTable jt = new JTable(data, column);
			JScrollPane scrollPane = new JScrollPane(jt);
			jt.setFillsViewportHeight(true);
			this.add(scrollPane);
		}catch(Exception e){
			System.out.println("Exception : " + e);
		}
		
	}

}