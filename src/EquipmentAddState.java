import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class EquipmentAddState extends AddState{

	//Equipment addPanel
	//Equipment(e_id, name, type, is_working)
	JTextField textFields[];

	public EquipmentAddState(AddCustomDialog d){
		super(d);
		textFields = new JTextField[4];
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("e_id: "));
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
				this.add(new JLabel("type: "));
				this.add(textFields[2] = new JTextField(10));
				return this;
			}
		}.make());
		this.add(new JPanel(){
			public JPanel make(){
				this.setLayout(new FlowLayout(FlowLayout.TRAILING));
				this.add(new JLabel("is_working: "));
				this.add(textFields[3] = new JTextField(10));
				return this;
			}
		}.make());
		JButton buttonAddEquipment = new JButton("Add");
		buttonAddEquipment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addClicked();
			}
		});
		this.add(buttonAddEquipment);
	}

	public void addClicked(){
		super.addClicked();

		String str = "\'"+textFields[0].getText()+"\'" + ", ";
		str += ("\'"+textFields[1].getText()+"\'" + ", ");
		str += ("\'"+textFields[2].getText()+"\'" + ", ");
		str += ("\'"+textFields[3].getText()+"\'");
		
		try{
			stmt.executeUpdate("insert into Equipment values(" + str + ")"); 
			stmt.close();
			conn.close();
			parentDialog.dispose();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}