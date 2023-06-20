package UI;
import Exceptions.*;
import Handlers.*;
import Users.*;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.TimerTask;

import static Handlers.DataValidation.checkData;

public class SignUp extends JFrame implements ActionListener{

    private static final String QUESTION_MARK_ICON_PATH = "src/UI/Resources/question.png";
    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";
    private static final String NAME_LIMITS_INFO = "The name must have between 6 and 20 characters";
    private static final String PASSWORD_LIMITS_INFO = "The password must have at least 8 characters, a number and a capital letter";
    private static final String ACTIVITY_INFO = "You should put physical activity aside, select an option according to your work.";
    private static final Color WALTERWHITE = Color.WHITE;

    public SignUp(Intermediary intermediary) {

        JFrame frame=new JFrame("Nutribros Sign Up");//creating instance of JFrame
        frame.getContentPane().setBackground(new Color(40, 40, 40));
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH); frame.setIconImage(logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Name label and field
        JLabel NameLabel = new JLabel("Full Name:");
        NameLabel.setForeground(WALTERWHITE);

        JTextField NameField = new JTextField();
        NameLabel.setBounds(35, 40, 80, 30);
        NameField.setBounds(115, 40, 250, 30);

        JButton NameQuestionLabel = getIconButton(QUESTION_MARK_ICON_PATH, NAME_LIMITS_INFO , 375, 40, 30, 30);
        frame.add(NameLabel); frame.add(NameField); frame.add(NameQuestionLabel);

        //Email label and field
        JLabel EmailLabel = new JLabel("Email:");
        EmailLabel.setForeground(WALTERWHITE);

        JTextField EmailField = new JTextField();
        EmailLabel.setBounds(35, 90, 80, 30);
        EmailField.setBounds(115, 90, 250, 30);
        frame.add(EmailLabel); frame.add(EmailField);

        //Password label and field
        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setForeground(WALTERWHITE);

        JPasswordField PasswordField = new JPasswordField();
        PasswordLabel.setBounds(35, 140, 80, 30);
        PasswordField.setBounds(115, 140, 250, 30);

        JButton PasswordQuestionLabel = getIconButton(QUESTION_MARK_ICON_PATH, PASSWORD_LIMITS_INFO,375, 140, 30, 30);
        frame.add(PasswordLabel); frame.add(PasswordField); frame.add(PasswordQuestionLabel);

        //Weight and height label and field
        JLabel WeightLabel = new JLabel("Weight:");
        WeightLabel.setForeground(WALTERWHITE);

        JTextField WeightField = new JTextField();
        WeightLabel.setBounds(35, 190, 80, 30);
        WeightField.setBounds(115, 190, 225, 30);

        JLabel WeightLabelKG = new JLabel("kg");
        WeightLabelKG.setForeground(Color.WHITE);
        WeightLabelKG.setBounds(345, 190, 20, 30);
        frame.add(WeightLabel); frame.add(WeightField); frame.add(WeightLabelKG);

        JLabel HeightLabel = new JLabel("Height:");
        HeightLabel.setForeground(WALTERWHITE);

        JTextField HeightField = new JTextField();
        HeightLabel.setBounds(35, 240, 80, 30);
        HeightField.setBounds(115, 240, 225, 30);

        JLabel HeightLabelCM = new JLabel("cm");
        HeightLabelCM.setForeground(WALTERWHITE);
        HeightLabelCM.setBounds(345, 240, 20, 30);
        frame.add(HeightLabel); frame.add(HeightField); frame.add(HeightLabelCM);

        //Objective combo box and label
        JLabel ObjectiveLabel = new JLabel("Objective:");
        ObjectiveLabel.setForeground(WALTERWHITE);

        String[] objectives = {"Lose weight", "Maintain weight", "Gain weight"};
        JComboBox ObjectiveCombo = new JComboBox(objectives);
        ObjectiveLabel.setBounds(35, 290, 80, 30);
        ObjectiveCombo.setBounds(115, 290, 250, 30);
        frame.add(ObjectiveLabel); frame.add(ObjectiveCombo);

        //Physical activity Combo box and label
        JLabel ActivityLabel = new JLabel("Activity:");
        ActivityLabel.setForeground(WALTERWHITE);

        String[] activities = {PhysicalActivity.NONE.toString(), PhysicalActivity.MODERATE.toString(), PhysicalActivity.ACTIVE.toString()};
        JComboBox ActivityCombo = new JComboBox(activities);
        ActivityLabel.setBounds(35, 340, 80, 30);
        ActivityCombo.setBounds(115, 340, 250, 30);

        JButton ActivityQuestionLabel = getIconButton(QUESTION_MARK_ICON_PATH, ACTIVITY_INFO,375, 340, 30, 30);
        frame.add(ActivityLabel); frame.add(ActivityCombo); frame.add(ActivityQuestionLabel);

        //age label and field
        JLabel AgeLabel = new JLabel("Age:");
        AgeLabel.setForeground(WALTERWHITE);

        JTextField AgeField = new JTextField();
        AgeLabel.setBounds(35, 390, 80, 30);
        AgeField.setBounds(115, 390, 250, 30);
        frame.add(AgeField); frame.add(AgeLabel);

        //Gender combo and label
        JLabel GenderLabel = new JLabel("Gender:");
        GenderLabel.setForeground(WALTERWHITE);

        String[] genders = {"MALE", "FEMALE"};
        JComboBox GenderCombo = new JComboBox(genders);
        GenderLabel.setBounds(35, 440, 80, 30);
        GenderCombo.setBounds(115, 440, 250, 30);
        frame.add(GenderLabel); frame.add(GenderCombo);

        JButton signButton=new JButton("Submit");//creating instance of JButton
        signButton.setBounds(135,510,150, 40);//x axis, y axis, width, height

        signButton.addActionListener(e -> {

            String name = NameField.getText();
            String email = (EmailField.getText()).toLowerCase();
            String password = (PasswordField.getText());
            String sex = (String) GenderCombo.getSelectedItem();
            String physicalActivity = (String) ActivityCombo.getSelectedItem();
            String objective = (String) ObjectiveCombo.getSelectedItem();

            try{
                DataValidation.checkData(name, email, password);
                DataValidation.checkDataDigit(HeightField, AgeField, WeightField);

                double weight = Double.parseDouble(WeightField.getText());
                int height = Integer.parseInt(HeightField.getText());
                int age = Integer.parseInt(AgeField.getText());

                DataValidation.checkUserDataBounds(age, weight, height);

                UserData userData = new UserData(age, weight, objective, height, sex, physicalActivity);
                User user = new BasicUser(name, password, email, userData);
                JSONHandler.userToFile(user);

                //EmailHandler.send(email, "Welcome to Nutribros", EmailHandler.welcomeText(name));
                //This method works but there's a common error which doesn't appear in all PCs, but we comment it as to not have conflicts between versions

                frame.dispose();
                intermediary.addUserToMap(user);
                Login login = new Login(intermediary);

            }catch (IncorrectEmailFormatException ex) {
                System.err.println("Email error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NameTooShortException ex) {
                System.err.println("Name error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (WeakPasswordException ex) {
                System.err.println("Password error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (JSONException ex) {
                System.err.println("File error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (DataOutOfBoundsException ex) {
                System.err.println("Data error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }catch (NumberFormatException ex){
                System.err.println("Data Type error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null,ex.getMessage());
            } catch (EmailInUseException ex) {
                System.err.println("Error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }

        });

        frame.add(signButton);//adding button in JFrame
        frame.setResizable(false);
        frame.setSize(450,630);
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JButton getIconButton(String iconPath, String toolTip, int x, int y, int w, int h){
        ImageIcon Icon = new ImageIcon(iconPath);
        Image scaledImage = Icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setBounds(x,y,w,h);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setToolTipText(toolTip);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        return button;
    }
}

