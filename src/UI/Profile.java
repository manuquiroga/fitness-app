package UI;

import Exceptions.*;
import Handlers.DataValidation;
import Handlers.Intermediary;
import Users.PremiumUser;
import Users.User;
import Users.UserData;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JFrame{
    private JTextField nameField;
    private JTextField emailField;
    private JTextField weightField;
    private JTextField heightField;
    private JTextField ageField;
    private JPasswordField passwordField;
    private User user;

    public Profile(User u, Intermediary intermediary) throws HeadlessException {

        this.user = u;
        setTitle(user.getName() + " Profile");
        setBounds(0, 0, 400, 520);
        getContentPane().setBackground(new Color(40, 40, 40));
        ImageIcon logo = new ImageIcon("src/UI/Resources/weightlifter.png"); setIconImage(logo.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        weightField = new JTextField();
        heightField = new JTextField();
        ageField = new JTextField();

        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(Color.WHITE);
        lblName.setBounds(50, 90, 100, 30);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setBounds(50,130, 100, 30);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(50,170, 100, 30);

        JLabel lblWeight = new JLabel("Weight:");
        lblWeight.setForeground(Color.WHITE);
        lblWeight.setBounds(50,210, 100, 30);

        JLabel lblHeight = new JLabel("Height:");
        lblHeight.setForeground(Color.WHITE);
        lblHeight.setBounds(50,250, 100, 30);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setForeground(Color.WHITE);
        lblAge.setBounds(50,290, 100, 30);

        nameField.setText(user.getName());
        nameField.setBounds(135, 90, 200, 30);

        emailField.setText(user.getEmail());
        emailField.setBounds(135,130,200,30);

        passwordField.setText(user.getPassword());
        passwordField.setBounds(135,170,200,30);

        weightField.setText(String.valueOf((int) user.getUserData().getWeight()));
        weightField.setBounds(135,210,200,30);

        heightField.setText(String.valueOf(user.getUserData().getHeight()));
        heightField.setBounds(135,250,200,30);

        ageField.setText(String.valueOf(user.getUserData().getAge()));
        ageField.setBounds(135,290,200,30);

        JButton unlockFieldButton = new JButton("Unlock Fields");
        unlockFieldButton.setBounds(100,340,200,30);
        unlockFieldButton.setFocusable(false);
        unlockFieldButton.setToolTipText("This will allow you to modify your data");

        JButton saveDataButton = new JButton("Save data");
        saveDataButton.setBounds(100,340,200,30);
        saveDataButton.setFocusable(false);
        unlockFieldButton.setToolTipText("Save your data");

        JButton getPremiumButton = new JButton("Get Premium");
        getPremiumButton.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        getPremiumButton.setBounds(50,20,285,50);
        getPremiumButton.setFocusable(false);
        getPremiumButton.setBackground(new Color(242, 202, 90));

        JButton goBackButton = new JButton("Back to menu");
        goBackButton.setBounds(100,380,200,30);
        goBackButton.setFocusable(false);

        JButton deleteAccountButton = new JButton("DELETE ACCOUNT");
        deleteAccountButton.setBounds(100,420,200,30);
        deleteAccountButton.setFocusable(false);
        deleteAccountButton.setBackground(new Color(245, 94, 111));


        if(!(user instanceof PremiumUser)){
            add(getPremiumButton);
        }


        unlockFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setEnabled(true);
                emailField.setEnabled(true);
                passwordField.setEnabled(true);
                weightField.setEnabled(true);
                heightField.setEnabled(true);
                ageField.setEnabled(true);

                remove(unlockFieldButton);
                add(saveDataButton);
            }
        });

        saveDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldEmail = user.getEmail();
                User newUser = refactorUserFields(nameField, emailField, passwordField, weightField, heightField, ageField);
                try {
                    intermediary.updateUser(oldEmail, newUser);
                    user = newUser;
                    System.out.println(intermediary.showMapUsers());
                } catch (JSONException ex) {
                    System.err.println(ex.getMessage());
                }

                remove(saveDataButton);
                add(unlockFieldButton);

                nameField.setEnabled(false);
                emailField.setEnabled(false);
                passwordField.setEnabled(false);
                weightField.setEnabled(false);
                heightField.setEnabled(false);
                ageField.setEnabled(false);
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu(user, intermediary);
            }
        });

        getPremiumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Payment payment = new Payment(user, intermediary);
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //YES_OPTION = 0;
                //NO_OPTION = 1;
                //CANCEL_OPTION = 2;
                int option = JOptionPane.showConfirmDialog(null, "WARNING. Are you sure you want to delete your account?");

                if(option == 0){
                    try {
                        intermediary.deleteUser(user);
                    } catch (JSONException ex) {
                        System.err.println(ex.getMessage());
                    }
                    dispose();
                    Home home = new Home(intermediary);
                }
            }
        });

        add(lblName);
        add(nameField);
        add(lblEmail);
        add(emailField);
        add(lblPassword);
        add(passwordField);
        add(lblWeight);
        add(weightField);
        add(lblHeight);
        add(heightField);
        add(lblAge);
        add(ageField);
        add(unlockFieldButton);
        add(goBackButton);
        add(deleteAccountButton);

        nameField.setEnabled(false);
        emailField.setEnabled(false);
        passwordField.setEnabled(false);
        weightField.setEnabled(false);
        heightField.setEnabled(false);
        ageField.setEnabled(false);

        setVisible(true);
    }

    private User refactorUserFields(JTextField nameField,JTextField emailField,JTextField passwordField,JTextField weightField,JTextField heightField, JTextField ageField) {
        User aux = user;
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            DataValidation.checkData(name, email, password);
            aux.setName(name);
            aux.setEmail(email);
            aux.setPassword(password);

            DataValidation.checkDataDigit(weightField, heightField, ageField);

            double weight = Double.parseDouble(weightField.getText());
            int height = Integer.parseInt(heightField.getText());
            int age = Integer.parseInt(ageField.getText());

            DataValidation.checkUserDataBounds(age, weight, height);

            aux.getUserData().setAge(age);
            aux.getUserData().setWeight(weight);
            aux.getUserData().setHeight(height);

        } catch (WeakPasswordException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } catch (IncorrectEmailFormatException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } catch (NameTooShortException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } catch (EmailInUseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } catch (DataOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return aux;
    }

}
