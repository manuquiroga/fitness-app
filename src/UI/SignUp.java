package UI;
import Handlers.SendEmail;
import Exceptions.IncorrectEmailFormatException;
import Exceptions.NameTooShortException;
import Exceptions.WeakPasswordException;
import Handlers.DataValidation;
import Users.UserTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener{
    public SignUp() {
        JFrame frame=new JFrame("Nutribros");//creating instance of JFrame
        frame.getContentPane().setBackground(new Color(41, 42, 54));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        signButton.addActionListener(e -> {

            String name = NameField.getText();
            String email = (EmailField.getText());
            String password = (PasswordField.getText());

            try{
                checkData(name, email, password);
                UserTest user = new UserTest(name, email, password);
                System.out.println(user);

                String subject = "Welcome to Nutribros";
                String body = SendEmail.welcomeText(name);
                SendEmail.send(email, subject, body);

                //TODO: forgot password

            } catch (IncorrectEmailFormatException ex) {
                System.err.println("Email error: " + ex.getMessage());
            } catch (NameTooShortException ex) {
                System.err.println("Name error: " + ex.getMessage());
            } catch (WeakPasswordException ex) {
                System.err.println("Password error: " + ex.getMessage());
            }

        });

        frame.add(signButton);//adding button in JFrame
        frame.setResizable(false);
        frame.setSize(420,420);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }

    private static boolean checkData(String name, String email, String password) throws NameTooShortException, IncorrectEmailFormatException, WeakPasswordException {
        boolean val = false;
        if(!DataValidation.name(name)){
            JOptionPane.showMessageDialog(null, "Name too short");
            throw new NameTooShortException("The name has to be at least "+ DataValidation.getMinCharName() + " characters long");
        }
        else if(!DataValidation.email(email)){
            JOptionPane.showMessageDialog(null, "Email bad format");
            throw new IncorrectEmailFormatException("The email format is wrong");
        }
        else if(!DataValidation.password(password)){
            JOptionPane.showMessageDialog(null, "Weak password");
            throw new WeakPasswordException("Password needs to be at least "+
                                            DataValidation.getMinCharPass() +
                                            " and contain at least 1 number and 1 uppercase"); //redactar mejor

        }
        else{
            val = true;
        }
        return val;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

