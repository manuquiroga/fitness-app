package UI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";

    public static void main(String[] args) {
        JFrame frame=new JFrame("Nutribros Login");
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH);
        frame.getContentPane().setBackground(new Color(41, 42, 54));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton signButton = new JButton("Sign Up");
        signButton.setBounds(135,110,150,50);
        signButton.setFocusable(false);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(135,170,150,50);
        loginButton.setFocusable(false);
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUp sign = new SignUp();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login log = new Login();
            }
        });

        frame.add(signButton);
        frame.add(loginButton);
        frame.setIconImage(logo.getImage());

        frame.setResizable(false);
        frame.setSize(420,420);//400 width and 500 height
        frame.setLayout(null);
        frame.setVisible(true);//making the frame visible
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
