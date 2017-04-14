import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class EquipmentAddState extends AddState{

	//Equipment addPanel
	//Equipment(e_id, name, type, is_working)
	JTextField textFields[];

	public EquipmentAddState(){
		super();
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
		
	}

}