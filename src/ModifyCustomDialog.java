import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ModifyCustomerDialog extends JDialog{

	JPanel mainPanel;
	GymManager gymManager;
	JTextField textFields[];

	String oldData[];
	String cid;
	
	public ModifyCustomDerialog(JFrame parentFrame, GymManager g, String id){
		super(parentFrame, "Modify Customer", true);
		gymManager = g;
		cid = id;
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		loadOldData();

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		{
			textFields = new JTextField[10];
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("c_id: "));
					this.add(textFields[0] = new JTextField(oldData[0], 10));
					textFields[0].setEditable(false);
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("first name: "));
					this.add(textFields[1] = new JTextField(oldData[1], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("last name: "));
					this.add(textFields[2] = new JTextField(oldData[2], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("street: "));
					this.add(textFields[3] = new JTextField(oldData[3], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("city: "));
					this.add(textFields[4] = new JTextField(oldData[4], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("state: "));
					this.add(textFields[5] = new JTextField(oldData[5], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("zip: "));
					this.add(textFields[6] = new JTextField(oldData[6], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("date_birth: "));
					this.add(textFields[7] = new JTextField(oldData[7], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("m_code: "));
					this.add(textFields[8] = new JTextField(oldData[8], 10));
					return this;
				}
			}.make());
			this.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("m_start_date: "));
					this.add(textFields[9] = new JTextField(oldData[9], 10));
					return this;
				}
			}.make());
			JButton buttonCommitClicked = new JButton("Commit");
			buttonCommitClicked.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					commitClicked();
				}
			});
			this.add(buttonCommitClicked);
			JButton buttonCancelClicked = new JButton("Commit");
			buttonCancelClicked.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					cancelClicked();
				}
			});
			this.add(buttonCancelClicked);
		}
		
		getContentPane().add(mainPanel);
		pack();
		setVisible(true);
	}

	public void loadOldData(){
		oldData = new String[10];
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery( "select * from Customer where c_id="+cid); 
			
			data = new String[rowCount][10];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<10; j++)
					oldData[j] = rset.getString(j+1);
				i++;
			}
			stmt.close();
			conn.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void commitClicked(){

	}

	public void cancelClicked(){

	}

	public void dispose(){
		gymManager.applyCustomerModel();
		super.dispose();
	}
}