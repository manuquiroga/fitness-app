package UI;

import Exceptions.IncorrectCardNumberException;
import Handlers.DataValidation;
import Handlers.Intermediary;
import Users.PremiumUser;
import Users.User;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment extends JFrame {
    private JTextField numberField;
    private JTextField cvField;
    private JTextField nameField;

    public Payment(User user, Intermediary intermediary){
        System.out.println(intermediary.showMapUsers());

        setBounds(0, 0, 400, 480);
        getContentPane().setBackground(new Color(40, 40, 40));
        setLayout(null);
        setResizable(false);

        numberField = new JTextField();
        cvField = new JTextField();
        nameField = new JTextField();

        JLabel lblNumber = new JLabel("Card Number:");
        lblNumber.setForeground(Color.WHITE);
        lblNumber.setBounds(50, 30, 100, 30);

        JLabel lblCV = new JLabel("CVV:");
        lblCV.setForeground(Color.WHITE);
        lblCV.setBounds(50,70, 100, 30);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setForeground(Color.WHITE);
        lblName.setBounds(50,110, 100, 30);

        numberField.setBounds(135, 30, 200, 30);
        cvField.setBounds(135, 70, 200, 30);
        nameField.setBounds(135, 110, 200, 30);

        JButton unlockButton = new JButton("Unlock Premium");
        unlockButton.setBounds(100,350,200,30);
        unlockButton.setFocusable(false);

        JButton goBackButton = new JButton("I am poor");
        goBackButton.setBounds(100,390,200,30);
        goBackButton.setFocusable(false);

        unlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //intermediary.addFunds(25)
                String number = numberField.getText();
                String cvv = cvField.getText();
                String name = nameField.getText();

                try{
                    DataValidation.checkCardData(number, cvv, name);
                    PremiumUser premiumUser = new PremiumUser(user.getName(), user.getPassword(), user.getEmail(), user.getId(), user.getUserData());
                    intermediary.updateUser(user.getEmail(), premiumUser);

                    JOptionPane.showMessageDialog(null, "CONGRATULATIONS, Log in again with this account to see your benefits");
                    dispose();

                    Login login = new Login(intermediary);
                }catch (IncorrectCardNumberException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                } catch (JSONException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(lblNumber);
        add(numberField);
        add(lblCV);
        add(cvField);
        add(lblName);
        add(nameField);
        add(unlockButton);
        add(goBackButton);

        setVisible(true);
    }


}
