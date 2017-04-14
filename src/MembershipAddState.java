import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MembershipAddState extends AddState{

	//Membership addPanel
	//MembershipPlan(m_code, name, price, duration)
	JTextField textFields[];

	public MembershipAddState(AddCustomDialog d){
		super(d);
		textFields = new JTextField[4];
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("m_code: "));
				this.add(textFields[0] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("name: "));
				this.add(textFields[1] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("price: "));
				this.add(textFields[2] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("duration: "));
				this.add(textFields[3] = new JTextField(10));
				return this;
			}
		}.make());
		JButton buttonAddMembership = new JButton("Add");
		buttonAddMembership.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addClicked();
			}
		});
		this.add(buttonAddMembership);
	}

	public void addClicked(){
		super.addClicked();

		String str = "\'"+textFields[0].getText()+"\'" + ", ";
		str += ("\'"+textFields[1].getText()+"\'" + ", ");
		str += ("\'"+textFields[2].getText()+"\'" + ", ");
		str += ("\'"+textFields[3].getText()+"\'");
		
		try{
			stmt.executeUpdate("insert into MembershipPlan values(" + str + ")"); 
			stmt.close();
			conn.close();
			parentDialog.dispose();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

}