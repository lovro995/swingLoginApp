import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Start {

	JButton registerNewUser;
	JButton hintButton;
	JButton loginButton;
	JPasswordField userPassword;
	JLabel userNameLabel;
	JLabel passLabel;
	JTextField userNameField;
	JFrame frame;
	JPanel panel;
	List<User> listOfRegUsers;

	public Start() {
		loginScreen();
	}

	private void loginScreen() {

		usersToList();

		frame = new JFrame("Welcome");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel(new GridBagLayout());

		setComponentsAndTheirFunction(panel);

		frame.add(panel);
		frame.setVisible(true);

	}

	private void usersToList() {
		listOfRegUsers = new LinkedList<>();
	//	System.out.println("1");
		listOfRegUsers = Data.putAllRegUsersToList();
		//System.out.println("2");
	}

	private void setComponentsAndTheirFunction(JPanel p) {

		TheHandler handler = new TheHandler();

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 3, 3);

		c.gridx = 1;
		c.gridy = 1;
		userNameLabel = new JLabel("Enter username");
		p.add(userNameLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		passLabel = new JLabel("Enter password");
		p.add(passLabel, c);

		c.gridx = 2;
		c.gridy = 1;
		userNameField = new JTextField();
		userNameField.setPreferredSize(new Dimension(90, 26));
		p.add(userNameField, c);

		c.gridx = 2;
		c.gridy = 2;
		userPassword = new JPasswordField();
		userPassword.setPreferredSize(new Dimension(90, 26));
		p.add(userPassword, c);

		c.gridx = 2;
		c.gridy = 3;
		loginButton = new JButton("Login");
		loginButton.addActionListener(handler);
		p.add(loginButton, c);

		c.gridx = 3;
		c.gridy = 3;
		hintButton = new JButton("Hint");
		hintButton.addActionListener(handler);
		p.add(hintButton, c);

		c.gridx = 4;
		c.gridy = 1;
		registerNewUser = new JButton("Register");
		registerNewUser.addActionListener(handler);
		p.add(registerNewUser, c);

	}

	private class TheHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == loginButton) {

				String userName = userNameField.getText();
				String pass = getPass(); /////////////////////////////7

				if (checkUsernameExists(userName)) {

					if (checkIfPassIsCorrect(userName, pass)) {
						User user=getUser(userName);
						frame.dispose();
						new appWindowScreen(user);

					} else {
						JOptionPane.showMessageDialog(null,
								"Username " + userName + " is registrated user, but entered pass is wrong!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Unknown UserName!");
				}

			} else if (event.getSource() == hintButton) {

				String userName = userNameField.getText();

				if (checkUsernameExists(userName)) {
					showHint(userName);
				} else {
					JOptionPane.showMessageDialog(null, "Unknown UserName!");
				}

			} else if (event.getSource() == registerNewUser) {
				frame.dispose();
				regNewUsr();
			}
		}

	}

	public boolean checkUsernameExists(String userName) {
		for (User usr : listOfRegUsers) {
			if (usr.getUsername().equals(userName)) {
				return true;
			}
		}

		return false;

	}

	public String getPass() {
		char[] password=userPassword.getPassword();
		String pass=new String(password);
		return pass;
	}

	public User getUser(String name) {
		for(User usr: listOfRegUsers){
			if(usr.getUsername().equals(name)){
				return usr;
			}
		}
		return null;
	}

	public void showHint(String userName) {
		for (User usr : listOfRegUsers) {
			if (usr.getUsername().equals(userName)) {
				JOptionPane.showMessageDialog(null, userName + "'s hint is: " + usr.getHint());
				break;
			}
		}

	}

	public boolean checkIfPassIsCorrect(String userName, String pass) {
		for (User usr : listOfRegUsers) {
			if (usr.getUsername().equals(userName) && usr.getPassword().equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public void regNewUsr() {
		new regNewUsrScreen(listOfRegUsers);
	}

}
