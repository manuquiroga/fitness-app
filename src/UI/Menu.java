package UI;

import Users.User;
import Users.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JFrame {

    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(40, 40, 40);

    public static void main(String[] args) {
        UserData userData = new UserData(23, 50, "LOSE_WEIGHT", 160, "female", "NONE");
        User user = new User("m", "Prueba123456", "mq@gmail.com", 10, userData);

        Menu menu = new Menu(user);
    }
    public Menu(User user){
        int rows = user.getUserData().getDiet().size();

        JFrame frame=new JFrame("Nutribros");//creating instance of JFrame
        frame.getContentPane().setBackground(DEFAULT_BACKGROUND_COLOR);
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH); frame.setIconImage(logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel MenuOptions = new JPanel();
        MenuOptions.setBounds(0, 0, 215, 480);
        MenuOptions.setBackground(DEFAULT_BACKGROUND_COLOR);
        MenuOptions.setLayout(null);

        JPanel UserDiet = new JPanel();
        UserDiet.setBounds(215, 0, 960, 480);
        UserDiet.setBackground(DEFAULT_BACKGROUND_COLOR);
        UserDiet.setLayout(null);

        JPanel CreateDietPanel = new JPanel();
        CreateDietPanel.setBounds(0,0,745, 480);
        CreateDietPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
        CreateDietPanel.setLayout(null);

        JLabel Info = new JLabel("You dont have any diet created yet");
        Info.setBounds( 273, 100, 200, 30);
        Info.setForeground(Color.WHITE);

        String[] types = {"CLASSIC", "CELIAC", "VEGAN", "VEGETARIAN"};
        JComboBox DietType = new JComboBox(types);
        DietType.setBounds( 273, 150, 200, 30);
        DietType.setFocusable(false);

        Integer[] quantities = {3,4,5,6,7,8};
        JComboBox MealQuantity = new JComboBox(quantities);
        MealQuantity.setBounds( 273, 200, 200, 30);
        MealQuantity.setFocusable(false);

        JButton CreateDietButton = new JButton("Generate diet");
        CreateDietButton.setBounds( 273, 250, 200, 30);
        CreateDietButton.setFocusable(false);

        CreateDietButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDietPanel.setVisible(false);
                user.generateDiet((Integer) MealQuantity.getSelectedItem(), (String) DietType.getSelectedItem()); //fix
                System.out.println(user.getUserData().toString());
            }
        });

        CreateDietPanel.add(Info); CreateDietPanel.add(DietType); CreateDietPanel.add(MealQuantity);CreateDietPanel.add(CreateDietButton);

        JTable FoodTable = new JTable(rows, 2);
        FoodTable.setBounds(215, 0, 960, 480);
        FoodTable.setBackground(new Color(226, 226, 226));

        if(rows == 0){
            UserDiet.add(CreateDietPanel);
        }
        else{

        }



        //UserDiet.add(FoodTable);

        frame.add(MenuOptions);
        frame.add(UserDiet);
        frame.setResizable(false);
        frame.setSize(860,480);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
