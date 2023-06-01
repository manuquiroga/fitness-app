package UI;

import Users.UserTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    public static void main(String[] args) {
        JFrame frame=new JFrame("Nutribros");//creating instance of JFrame
        frame.getContentPane().setBackground(new Color(41, 42, 54));

        JButton signButton = new JButton("Sign Up");
        signButton.setBounds(135,110,150,50);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(135,170,150,50);

        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Login.main();
            }
        });

        frame.add(signButton);
        frame.add(loginButton);
        frame.setResizable(false);
        frame.setSize(420,420);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
