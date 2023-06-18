package UI;

import Exceptions.IncorrectPasswordException;
import Handlers.DataValidation;
import Handlers.Intermediary;
import Users.AdminUser;
import Users.User;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{
    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";

    public Login(Intermediary intermediary){

        JFrame frame=new JFrame("Nutribros Login");//creating instance of JFrame
        frame.getContentPane().setBackground(new Color(40, 40, 40));
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

        JButton logButton=new JButton("Submit");
        logButton.setBounds(135,180,150, 30);

        JButton goBackButton=new JButton("Go back");
        goBackButton.setBounds(135,230,150, 30);

        goBackButton.addActionListener(e -> {

            frame.dispose();
            Home home = new Home(intermediary);

        });

        logButton.addActionListener(e -> {

            String email = (EmailField.getText());
            String password = PasswordField.getText();

            try {
                if(DataValidation.checkLoginData(email,password)){
                    frame.dispose();
                    User user = DataValidation.getUserFromLogin(email,password);
                    if(user instanceof AdminUser){
                        AdminMenu adminMenu = new AdminMenu((AdminUser) user);
                        adminMenu.runAdminMenu(intermediary);
                    }else {
                        Menu menu = new Menu(user,intermediary);
                    }
                }
            } catch (IncorrectPasswordException ex) {
                System.err.println("Incorrect data: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        frame.add(goBackButton);
        frame.add(logButton);
        frame.setResizable(false);
        frame.setSize(420,360);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
