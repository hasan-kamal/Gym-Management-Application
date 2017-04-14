import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class CustomerAddState extends AddState{

	//Customer addPanel
	//Customer(c_id, first_name, last_name, street, city, state, zip, date_birth, m_code, m_start_date)	
	JTextField textFields[];

	public CustomerAddState(AddCustomDialog d){
		super(d);
		textFields = new JTextField[10];
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("c_id: "));
				this.add(textFields[0] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("first name: "));
				this.add(textFields[1] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("last name: "));
				this.add(textFields[2] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("street: "));
				this.add(textFields[3] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("city: "));
				this.add(textFields[4] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("state: "));
				this.add(textFields[5] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("zip: "));
				this.add(textFields[6] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("date_birth: "));
				this.add(textFields[7] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("m_code: "));
				this.add(textFields[8] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("m_start_date: "));
				this.add(textFields[9] = new JTextField(10));
				return this;
			}
		}.make());
		JButton buttonAddCustomer = new JButton("Add");
		buttonAddCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addClicked();
			}
		});
		this.add(buttonAddCustomer);
	}

	public void addClicked(){
		super.addClicked();

		String str = "\'"+textFields[0].getText()+"\'" + ", ";
		str += ("\'"+textFields[1].getText()+"\'" + ", ");
		str += ("\'"+textFields[2].getText()+"\'" + ", ");
		str += ("\'"+textFields[3].getText()+"\'" + ", ");
		str += ("\'"+textFields[4].getText()+"\'" + ", ");
		str += ("\'"+textFields[5].getText()+"\'" + ", ");
		str += ("\'"+textFields[6].getText()+"\'" + ", ");
		str += ("\'"+textFields[7].getText()+"\'" + ", ");
		str += ("\'"+textFields[8].getText()+"\'" + ", ");
		str += ("\'"+textFields[9].getText()+"\'");
		
		try{
			stmt.executeUpdate("insert into Customer values(" + str + ")"); 
			stmt.close();
			conn.close();
			parentDialog.dispose();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}