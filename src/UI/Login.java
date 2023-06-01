package UI;
import Users.User;
import Users.UserTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener{
    public static void main(String[] args) {
        JFrame frame=new JFrame("Nutribros");//creating instance of JFrame
        frame.getContentPane().setBackground(new Color(41, 42, 54));

        JLabel NameLabel = new JLabel("Full Name:");
        NameLabel.setForeground(Color.WHITE);

        JTextField NameField = new JTextField();
        NameLabel.setBounds(35, 40, 80, 30);
        NameField.setBounds(115, 40, 250, 30);
        frame.add(NameLabel); frame.add(NameField);

        JLabel EmailLabel = new JLabel("Email:");
        EmailLabel.setForeground(Color.WHITE);

        JTextField EmailField = new JTextField();
        EmailLabel.setForeground(Color.WHITE);

        EmailLabel.setBounds(35, 90, 80, 30);
        EmailField.setBounds(115, 90, 250, 30);
        frame.add(EmailLabel); frame.add(EmailField);

        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setForeground(Color.WHITE);

        JPasswordField PasswordField = new JPasswordField();
        PasswordLabel.setBounds(35, 140, 80, 30);
        PasswordField.setBounds(115, 140, 250, 30);
        frame.add(PasswordLabel); frame.add(PasswordField);

        //TODO:
        JLabel DateLabel = new JLabel("Birth Date:");



        JButton signButton=new JButton("Submit");//creating instance of JButton
        signButton.setBounds(135,300,150, 40);//x axis, y axis, width, height
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserTest user = new UserTest();
                user.setName(NameField.getText());
                user.setEmail(EmailField.getText());
                user.setPassword(PasswordField.getText());
                System.out.println(user.toString());
            }
        });

        frame.add(signButton);//adding button in JFrame
        frame.setResizable(false);
        frame.setSize(420,420);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

