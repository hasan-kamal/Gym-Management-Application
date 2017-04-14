import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class StaffAddState extends AddState{

	//Staff addPanel
	//Staff(s_id, first_name, last_name, street, city, state, zip, designation, date_birth)
	JTextField textFields[];

	public StaffAddState(AddCustomDialog d){
		super(d);
		textFields = new JTextField[10];
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("s_id: "));
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
				this.add(new JLabel("designt: "));
				this.add(textFields[7] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("date_birth: "));
				this.add(textFields[8] = new JTextField(10));
				return this;
			}
		}.make());
		JButton buttonAddStaff = new JButton("Add");
		buttonAddStaff.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addClicked();
			}
		});
		this.add(buttonAddStaff);
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
		str += ("\'"+textFields[8].getText()+"\'");
		
		try{
			stmt.executeUpdate("insert into Staff values(" + str + ")"); 
			stmt.close();
			conn.close();
			parentDialog.dispose();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}