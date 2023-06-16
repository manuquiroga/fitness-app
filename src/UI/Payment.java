package UI;

import Handlers.DataValidation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment extends JPanel {
    private JTextField numberField;
    private JTextField cvField;
    private JTextField nameField;

    public Payment(){
        setBounds(0, 0, 400, 480);
        setBackground(new Color(40, 40, 40));
        setLayout(null);

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

        unlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //intermediary.addFunds(25)
                String number = numberField.getText();
                String cvv = cvField.getText();
                String name = nameField.getText();

                //DataValidation.checkCardData(number, cvv, name)

                setVisible(false);
            }
        });

        setVisible(true);
    }


}
