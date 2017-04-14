import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Query3 extends ComplexQueryPanel{

	JTextField f;
	JButton button;

	Query3(){
		super();

		this.add(new JLabel("Enter cid of customer"));
		f = new JTextField(10);
		this.add(f);

		button = new JButton("Submit");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				submitClicked();
			}
		});
		this.add(button);
	}

	public void submitClicked(){
		try{
			String query1 = "select s_id, count(*) from StaffLog where in_or_out = TRUE group by s_id order by count(*)";
			int rowCount=0;

			rset = stmt.executeQuery("select count(*) from CustomerLog where in_or_out = TRUE and c_id = " + f.getText() + " group by c_id having c_id = " + f.getText());
			String compVal;
			if(rset.next())
				compVal = rset.getString(1);
			else
				compVal = "0";
			System.out.println("dfdf: "+compVal);
			query1 = "select Customer.c_id, first_name from CustomerLog, Customer where Customer.c_id = CustomerLog.c_id and in_or_out = TRUE group by CustomerLog.c_id having count(*) > " + compVal;
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
			String column[] = {"Customer id","Name"};
			JTable jt = new JTable(data, column);
			JScrollPane scrollPane = new JScrollPane(jt);
			jt.setFillsViewportHeight(true);
			this.add(scrollPane);
			this.revalidate();
			this.repaint();
		}catch(Exception e){
			System.out.println("Exception : " + e);
		}
	}

}