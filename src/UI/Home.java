package UI;

import Handlers.FileHandler;
import Handlers.Intermediary;
import Handlers.JSONHandler;
import Users.User;
import Users.UserData;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Home extends JFrame implements ActionListener {

    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";

    public static void main(String[] args) {
        UserData userData = new UserData(19, 60, "MAINTAIN_WEIGHT", 172, "male", "NONE");
        User user = new User("quiman", "Prueba1234", "m@gmail.com", 1, userData);

        UserData userData2 = new UserData(19, 60, "MAINTAIN_WEIGHT", 172, "male", "NONE");
        User user2 = new User("manuel", "Prueba1234", "q@gmail.com", 2, userData2);

        UserData userData3 = new UserData(20, 63, "MAINTAIN_WEIGHT", 174, "male", "NONE");
        User user3 = new User("pepe", "Prueba1234", "p@gmail.com", 3, userData2);

//        try{
//            //JSONHandler.userToFile(user);
//            //JSONHandler.userToFile(user2);
//            JSONHandler.userToFile(user3);
//        } catch (JSONException e) {
//            System.err.println(e.getMessage());
//        }

        Intermediary intermediary = new Intermediary();
        ArrayList<User> users= JSONHandler.readUserFile();
        System.out.println(users);

        for (int i = 0; i<users.size() ; i++){
            intermediary.addUserToMap(users.get(i));
        }

        System.out.println(intermediary.showMapUsers());
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
