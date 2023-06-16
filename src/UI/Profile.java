package UI;

import Users.User;

import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame{
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Profile(User user) throws HeadlessException {

        setTitle("User Profile");
        setBounds(0, 0, 215, 480);
        getContentPane().setBackground(new Color(40, 40, 40));
        ImageIcon logo = new ImageIcon("src/UI/Resources/weightlifter.png"); setIconImage(logo.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        nameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);

        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        passwordField.setText(user.getPassword());


        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(Color.WHITE);
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);

        add(lblName);
        add(nameField);
        add(lblEmail);
        add(emailField);
        add(lblPassword);
        add(passwordField);

        nameField.setEnabled(false);
        emailField.setEnabled(false);
        passwordField.setEnabled(false);

        setVisible(true);
    }
}
