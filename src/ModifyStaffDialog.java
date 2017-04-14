import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class ModifyStaffDialog extends JDialog{

	JPanel mainPanel;
	GymManager gymManager;
	JTextField textFields[];

	String oldData[];
	String sid;

	Connection conn;
	Statement stmt;
	
	public ModifyStaffDialog(JFrame parentFrame, GymManager g, String id){
		super(parentFrame, "Modify Staff", true);
		gymManager = g;
		sid = id;
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		loadOldData();

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		{
			textFields = new JTextField[10];
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("s_id: "));
					this.add(textFields[0] = new JTextField(oldData[0], 10));
					textFields[0].setEditable(false);
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("first name: "));
					this.add(textFields[1] = new JTextField(oldData[1], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("last name: "));
					this.add(textFields[2] = new JTextField(oldData[2], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("street: "));
					this.add(textFields[3] = new JTextField(oldData[3], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("city: "));
					this.add(textFields[4] = new JTextField(oldData[4], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("state: "));
					this.add(textFields[5] = new JTextField(oldData[5], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("zip: "));
					this.add(textFields[6] = new JTextField(oldData[6], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("designt: "));
					this.add(textFields[7] = new JTextField(oldData[7], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("date_birth: "));
					this.add(textFields[8] = new JTextField(oldData[8], 10));
					return this;
				}
			}.make());
			JButton buttonCommitStaff = new JButton("Commit");
			buttonCommitStaff.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					commitClicked();
				}
			});
			mainPanel.add(buttonCommitStaff);
			JButton buttonCancelClicked = new JButton("Cancel");
			buttonCancelClicked.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					cancelClicked();
				}
			});
			mainPanel.add(buttonCancelClicked);
		}
		
		getContentPane().add(mainPanel);
		pack();
		setVisible(true);
	}

	public void loadOldData(){
		oldData = new String[9];
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery( "select * from Staff where s_id="+sid); 
			
			oldData = new String[9];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<9; j++)
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
		try{

			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();

			//String str = "\'"+textFields[0].getText()+"\'" + ", ";
			//Staff(s_id, first_name, last_name, street, city, state, zip, designation, date_birth)
			String str = ("first_name="+"\'"+textFields[1].getText()+"\'" + ", ");
			str += ("last_name="+"\'"+textFields[2].getText()+"\'" + ", ");
			str += ("street="+"\'"+textFields[3].getText()+"\'" + ", ");
			str += ("city="+"\'"+textFields[4].getText()+"\'" + ", ");
			str += ("state="+"\'"+textFields[5].getText()+"\'" + ", ");
			str += ("zip="+"\'"+textFields[6].getText()+"\'" + ", ");
			str += ("designation="+"\'"+textFields[7].getText()+"\'" + ", ");
			str += ("date_birth="+"\'"+textFields[8].getText()+"\'");
			
			try{
				stmt.executeUpdate("update Staff set "+ str + " where s_id="+sid); 
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		this.dispose();
	}

	public void cancelClicked(){
		this.dispose();
	}

	public void dispose(){
		gymManager.applyStaffModel();
		super.dispose();
	}
}