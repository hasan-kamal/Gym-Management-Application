import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class ModifyEquipmentDialog extends JDialog{

	JPanel mainPanel;
	GymManager gymManager;
	JTextField textFields[];

	String oldData[];
	String e_id;

	Connection conn;
	Statement stmt;
	
	public ModifyEquipmentDialog(JFrame parentFrame, GymManager g, String id){
		super(parentFrame, "Modify Equipment", true);
		gymManager = g;
		e_id = id;
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		loadOldData();

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		{
			textFields = new JTextField[4];
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("e_id: "));
					this.add(textFields[0] = new JTextField(oldData[0], 10));
					textFields[0].setEditable(false);
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("name: "));
					this.add(textFields[1] = new JTextField(oldData[1], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("type: "));
					this.add(textFields[2] = new JTextField(oldData[2], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("is_working: "));
					this.add(textFields[3] = new JTextField(oldData[3], 10));
					return this;
				}
			}.make());
			JButton buttonCommit = new JButton("Commit");
			buttonCommit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					commitClicked();
				}
			});
			mainPanel.add(buttonCommit);
			JButton buttonCancel = new JButton("Cancel");
			buttonCancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					cancelClicked();
				}
			});
			mainPanel.add(buttonCancel);
		}
		
		getContentPane().add(mainPanel);
		pack();
		setVisible(true);
	}

	public void loadOldData(){
		oldData = new String[9];
		try{
			//Equipment addPanel
			//Equipment(e_id, name, type, is_working)
	
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery( "select * from Equipment where e_id="+e_id); 
			
			oldData = new String[4];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<4; j++)
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

			//Equipment addPanel
			//Equipment(e_id, name, type, is_working)
			//String str = (""+"\'"+textFields[0].getText()+"\'" + ", ");
			String str = ("name="+"\'"+textFields[1].getText()+"\'" + ", ");
			str += ("type="+"\'"+textFields[2].getText()+"\'" + ", ");
			str += ("is_working="+"\'"+textFields[3].getText()+"\'");
			
			try{
				stmt.executeUpdate("update Equipment set "+ str + " where e_id="+e_id); 
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
		gymManager.applyEquipmentModel();
		super.dispose();
	}
}