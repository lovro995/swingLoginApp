import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonArray {
	
	static JFrame frame;
	static JPanel panel;
	static GridBagConstraints c=new GridBagConstraints();;

	public static void generateArray(int num) {
		newFrame();
		createButtonArray(num);
	}

	private static void createButtonArray(int num) {
		for(int i=1;i<=num;i++){
			for(int j=1;j<=num;j++){
				c.gridx=i;
				c.gridy=j;
				JButton b= new JButton();
				b.setPreferredSize(new Dimension(20, 20));
				panel.add(b,c);
				
			}
		}
	}

	private static void newFrame() {
		
		frame = new JFrame("Register new user");
		frame.setSize(650, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new JPanel(new GridBagLayout());

		frame.add(panel);
		frame.setVisible(true);

	}


}
