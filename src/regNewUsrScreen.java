import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class regNewUsrScreen {

	JLabel userNameLabel;
	JLabel passLabel;
	JLabel hintLabel;
	JButton register;
	JButton back;
	JTextField userNameField;
	JTextField passwordField;
	JTextField hintField;
	List<User> listOfRegUsers;
	JFrame frame;
	JPanel panel;
	JRadioButton chooseGenderM;
	JRadioButton chooseGenderF;
	JLabel genderLabel;
	JLabel chooseCountryLabel;
	JComboBox<String> countryCombo;
	String[] country = { "Croatia", "Germany", "Austria", "Slovenia", "England" };

	public regNewUsrScreen(List<User> listOfRegUsers) {
		this.listOfRegUsers = listOfRegUsers;
		frameCreate();
	}

	private void frameCreate() {

		frame = new JFrame("Register new user");
		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new GridBagLayout());

		setComponentsToRegNewUsrScreen(panel);

		frame.add(panel);
		frame.setVisible(true);

	}

	private void setComponentsToRegNewUsrScreen(JPanel panel) {

		TheHandler handler = new TheHandler();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 3, 3);

		c.gridx = 1;
		c.gridy = 1;
		userNameLabel = new JLabel("Enter username: ");
		panel.add(userNameLabel, c);

		c.gridx = 2;
		c.gridy = 1;
		userNameField = new JTextField();
		userNameField.setPreferredSize(new Dimension(90, 26));
		panel.add(userNameField, c);

		c.gridx = 1;
		c.gridy = 2;
		passLabel = new JLabel("Enter password: ");
		panel.add(passLabel, c);

		c.gridx = 2;
		c.gridy = 2;
		passwordField = new JTextField();
		passwordField.setPreferredSize(new Dimension(90, 26));
		panel.add(passwordField, c);

		c.gridx = 1;
		c.gridy = 3;
		hintLabel = new JLabel("Enter hint: ");
		panel.add(hintLabel, c);

		c.gridx = 2;
		c.gridy = 3;
		hintField = new JTextField();
		hintField.setPreferredSize(new Dimension(90, 26));
		panel.add(hintField, c);

		c.gridx = 1;
		c.gridy = 4;
		genderLabel = new JLabel("Choose gender");
		panel.add(genderLabel, c);

		c.gridx = 2;
		c.gridy = 4;
		chooseGenderM = new JRadioButton("male");
		chooseGenderM.addActionListener(handler);
		panel.add(chooseGenderM, c);

		c.gridx = 3;
		c.gridy = 4;
		chooseGenderF = new JRadioButton("female");
		chooseGenderF.addActionListener(handler);
		panel.add(chooseGenderF, c);

		c.gridx = 1;
		c.gridy = 5;
		chooseCountryLabel = new JLabel("Choose country:");
		panel.add(chooseCountryLabel, c);

		c.gridx = 2;
		c.gridy = 5;
		countryCombo = new JComboBox<>(country);
		panel.add(countryCombo, c);

		c.gridx = 4;
		c.gridy = 2;
		register = new JButton("Register");
		register.addActionListener(handler);
		panel.add(register, c);

		c.gridx = 4;
		c.gridy = 3;
		back = new JButton("Back");
		back.addActionListener(handler);
		panel.add(back, c);

	}

	private class TheHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == register) {
				if (!checkIfUserIsAlreadyRegistrated()) {
					// nije registriran, pa ga treba registrirati
					letRegisterThatUser(userNameField.getText(), passwordField.getText(), hintField.getText(),
							(String) countryCombo.getSelectedItem(), getUserGender());
				} else {
					JOptionPane.showMessageDialog(null,
							"UserName ' " + userNameField.getText() + " ' is used. Please pick another one");
				}

			} else if (event.getSource() == back) {
				frame.dispose();
				new Start();
			} else if (event.getSource() == chooseGenderF) {
				chooseGenderM.setSelected(false);

			} else if (event.getSource() == chooseGenderM) {
				chooseGenderF.setSelected(false);
			}
		}
	}

	public boolean checkIfUserIsAlreadyRegistrated() {
		for (User usr : listOfRegUsers) {
			if (usr.getUsername().equals(userNameField.getText())) {
				return true;
			}
		}
		return false;
	}

	public String getUserGender() {
		if (chooseGenderM.isSelected())
			return "male";
		return "female";
	}

	public void letRegisterThatUser(String name, String pass, String hint, String country, String gender) {
		User user = new User(name, pass, hint, country, gender);
		Data.putNewUserToData(user);
		JOptionPane.showMessageDialog(null, "Registration successfull!");
		frame.dispose();
		new Start();

	}

}
