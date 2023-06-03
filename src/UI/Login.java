package UI;

import Exceptions.IncorrectEmailFormatException;
import Exceptions.NameTooShortException;
import Exceptions.WeakPasswordException;
import Handlers.DataValidation;
import Handlers.SendEmail;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Login extends JFrame{
    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";

    public Login(){
        JFrame frame=new JFrame("Nutribros");//creating instance of JFrame
        frame.getContentPane().setBackground(new Color(41, 42, 54));
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH); frame.setIconImage(logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel EmailLabel = new JLabel("Email:");
        EmailLabel.setForeground(Color.WHITE);

        JTextField EmailField = new JTextField();
        EmailLabel.setBounds(35, 40, 80, 30);
        EmailField.setBounds(115, 40, 250, 30);
        frame.add(EmailLabel); frame.add(EmailField);

        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setForeground(Color.WHITE);

        JPasswordField PasswordField = new JPasswordField();
        PasswordLabel.setBounds(35, 90, 80, 30);
        PasswordField.setBounds(115, 90, 250, 30);
        frame.add(PasswordLabel); frame.add(PasswordField);

        //TODO: (ponele) forgot password

        JButton logButton=new JButton("Submit");
        logButton.setBounds(135,180,150, 40);

        logButton.addActionListener(e -> {
            frame.dispose();
            String email = (EmailField.getText());
            String password = Arrays.toString((PasswordField.getPassword()));

            //TODO: verify correct email/password
            Menu menu = new Menu();
        });

        frame.add(logButton);
        frame.setResizable(false);
        frame.setSize(420,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
