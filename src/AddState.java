import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public abstract class AddState extends JPanel{

	public AddState(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public abstract void addClicked();

}