import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class appWindowScreen {
	User user;
	JFrame frame;
	JPanel panel;
	JButton generate;
	JButton logOut;
	JLabel info;
	JLabel userName;
	JLabel countryLabel;
	JLabel genderLabel;
	JTextField dim;

	public appWindowScreen(User user) {
		this.user = user;
		frameCreate();
	}

	private void frameCreate() {
		frame = new JFrame("Register new user");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new JPanel(new GridBagLayout());

		setComponentsToAppScreen(panel);

		frame.add(panel);
		frame.setVisible(true);

	}

	private void setComponentsToAppScreen(JPanel panel2) {
		TheHandler handler = new TheHandler();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 3, 3, 3);

		c.gridx = 1;
		c.gridy = 1;
		info = new JLabel("___::User info::___ ");
		panel.add(info, c);

		c.gridx = 1;
		c.gridy = 2;
		userName = new JLabel("UserName: " + user.getUsername());
		panel.add(userName, c);
		
		c.gridx = 1;
		c.gridy = 3;
		countryLabel = new JLabel("Country: " + user.getState());
		panel.add(countryLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		genderLabel = new JLabel("Gender: " + user.getGender());
		panel.add(genderLabel, c);

		c.gridx = 3;
		c.gridy = 3;
		logOut = new JButton("LogOut");
		logOut.addActionListener(handler);
		panel.add(logOut, c);

		c.gridx = 3;
		c.gridy = 1;
		dim = new JTextField();
		dim.setPreferredSize(new Dimension(30, 30));
		panel.add(dim, c);

		c.gridx = 3;
		c.gridy = 2;
		generate = new JButton("Generate");
		generate.addActionListener(handler);
		panel.add(generate, c);

	}

	private class TheHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == logOut) {
				frame.dispose();
				new Start();

			} else if (event.getSource() == generate) {
				int num = 0;
				try {
					num = Integer.parseInt(dim.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Wrong entry, please try again");
				}
				ButtonArray.generateArray(num);

			}

		}

	}

}
