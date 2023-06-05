package UI;
import Exceptions.DataOutOfBoundsException;
import Handlers.FileHandler;
import Handlers.JSONHandler;
import Handlers.SendEmail;
import Exceptions.IncorrectEmailFormatException;
import Exceptions.NameTooShortException;
import Exceptions.WeakPasswordException;
import Handlers.DataValidation;
import Users.PhysicalActivity;
import Users.User;
import Users.UserData;
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
    private static final String ACTIVITY_INFO = "You should put physical activity aside.\n NONE is if you work from home or if you are a student.\n MODERATE is for example a job where you lift boxes.\n ACTIVE is for example an active job as a cyclist postman";
    private static final Color WALTERWHITE = Color.WHITE;

    public SignUp() {

        JFrame frame=new JFrame("Nutribros");//creating instance of JFrame
        frame.getContentPane().setBackground(new Color(41, 42, 54));
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH); frame.setIconImage(logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Name label and field
        JLabel NameLabel = new JLabel("Full Name:");
        NameLabel.setForeground(WALTERWHITE);

        JTextField NameField = new JTextField();
        NameLabel.setBounds(35, 40, 80, 30);
        NameField.setBounds(115, 40, 250, 30);

        JLabel NameQuestionLabel = CreateIconLabel(QUESTION_MARK_ICON_PATH, NAME_LIMITS_INFO , 370, 40, 30, 30);
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

        JLabel PasswordQuestionLabel = CreateIconLabel(QUESTION_MARK_ICON_PATH, PASSWORD_LIMITS_INFO,370, 140, 30, 30);
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

        //Desired Weight label and field
        JLabel DesiredWeightLabel = new JLabel("Desired Weight:");
        DesiredWeightLabel.setForeground(WALTERWHITE);

        JTextField DesiredWeightField = new JTextField();
        DesiredWeightLabel.setBounds(35, 290, 80, 30);
        DesiredWeightField.setBounds(115, 290, 225, 30);

        JLabel DesiredWeightLabelKG = new JLabel("kg");
        DesiredWeightLabelKG.setForeground(WALTERWHITE);
        DesiredWeightLabelKG.setBounds(345, 290, 20, 30);
        frame.add(DesiredWeightLabel); frame.add(DesiredWeightField); frame.add(DesiredWeightLabelKG);

        //Physical activity Combo box and label
        JLabel ActivityLabel = new JLabel("Activity:");
        ActivityLabel.setForeground(WALTERWHITE);

        String[] activities = {PhysicalActivity.NONE.toString(), PhysicalActivity.MODERATE.toString(), PhysicalActivity.ACTIVE.toString()};
        JComboBox ActivityCombo = new JComboBox(activities);
        ActivityLabel.setBounds(35, 340, 80, 30);
        ActivityCombo.setBounds(115, 340, 250, 30);

        JLabel ActivityQuestionLabel = CreateIconLabel(QUESTION_MARK_ICON_PATH, ACTIVITY_INFO,370, 340, 30, 30);
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
        //TODO: (ponele) link to gender choice


        JButton signButton=new JButton("Submit");//creating instance of JButton
        signButton.setBounds(135,510,150, 40);//x axis, y axis, width, height

        signButton.addActionListener(e -> {

            String name = NameField.getText();
            String email = (EmailField.getText());
            String password = (PasswordField.getText());
            String sex = (String) GenderCombo.getSelectedItem();
            String physicalActivity = (String) ActivityCombo.getSelectedItem();

            double weight = Double.parseDouble(WeightField.getText());
            double desiredWeight = Double.parseDouble(DesiredWeightField.getText());
            int height = Integer.parseInt(HeightField.getText());
            int age = Integer.parseInt(AgeField.getText());
            int id=0;

            try{
                if(FileHandler.existsFile("user")) {
                    id = JSONHandler.countItemsInUserJSON() + 1;
                }
            }
            catch (FileNotFoundException ex ) {
                System.err.println(ex.getMessage());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

            try{
                DataValidation.checkData(name, email, password);
                DataValidation.checkUserDataBounds(age, weight, height);
                DataValidation.checkDataDouble(WeightField,DesiredWeightField);
                DataValidation.checkDataInteger(HeightField,AgeField);
                UserData userData = new UserData(age, weight, desiredWeight, height, sex, physicalActivity);
                User user = new User(name, password, email, id, userData);
                JSONHandler.userToFile(user);

                System.out.println(user);

                String subject = "Welcome to Nutribros";
                String body = SendEmail.welcomeText(name);
                //SendEmail.send(email, subject, body);



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
            }

        });

        frame.add(signButton);//adding button in JFrame
        frame.setResizable(false);
        frame.setSize(420,630);
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JLabel CreateIconLabel(String iconPath, String labelText, int x, int y, int w, int h){
        ImageIcon Icon = new ImageIcon(iconPath);
        Image scaledImage = Icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel IconLabel = new JLabel(scaledIcon);
        IconLabel.setBounds(x, y, w, h);

        IconLabel.addMouseListener(new MouseAdapter() {
            private JDialog dialog;

            @Override
            public void mouseEntered(MouseEvent e) {
                dialog = new JDialog();
                dialog.setUndecorated(true);
                dialog.setLayout(new BorderLayout());
                dialog.add(new JLabel(labelText), BorderLayout.CENTER);
                dialog.pack();
                dialog.setLocationRelativeTo(IconLabel);
                dialog.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (dialog != null) {
                    dialog.dispose();
                    dialog = null;
                }
            }
        });

        return IconLabel;
    }
}

