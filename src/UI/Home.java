package UI;

import Handlers.Intermediary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";

    public static void main(String[] args) {
        Intermediary intermediary = new Intermediary();
        Home home = new Home(intermediary);
    }

    public Home(Intermediary intermediary) {
        JFrame frame=new JFrame("Nutribros Login");
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton signButton = new JButton("Sign Up");
        signButton.setBounds(130,110,150,40);
        signButton.setFocusable(false);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(130,170,150,40);
        loginButton.setFocusable(false);
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUp sign = new SignUp(intermediary);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login log = new Login(intermediary);
            }
        });

        frame.add(signButton);
        frame.add(loginButton);
        frame.setIconImage(logo.getImage());

        frame.setResizable(false);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);//making the frame visible
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
