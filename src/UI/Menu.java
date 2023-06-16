package UI;

import Exceptions.EmailInUseException;
import Exceptions.IncorrectEmailFormatException;
import Exceptions.NameTooShortException;
import Exceptions.WeakPasswordException;
import FoodModels.Food;
import Handlers.DataValidation;
import Handlers.Intermediary;
import UI.Renderers.FontRenderer;
import Users.User;
import Users.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.util.ArrayList;

public class Menu extends JFrame {

    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";
    private static final String ALREADY_EATEN_ICON_PATH = "src/UI/Resources/aprobado.png";
    private static final String SHOW_FOOD_INFO_ICON_PATH = "src/UI/Resources/dieta.png";
    private static final String PROFILE_ICON_PATH = "src/UI/Resources/usuario.png";
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(40, 40, 40);
    private static final FontRenderer TABLE_FONT = new FontRenderer(new Font("Book Antiqua", Font.BOLD, 18));

    public static void main(String[] args) {
        UserData userData = new UserData(23, 50, "LOSE_WEIGHT", 160, "female", "NONE");
        User user = new User("m", "Prueba123456", "mq@gmail.com", 10, userData);
        //user.generateDiet(4, "vegan");
        Intermediary intermediary = new Intermediary();
        intermediary.addUserToMap(user);
        Menu menu = new Menu(user, intermediary);
        System.out.println(intermediary.showMapUsers());
    }

    public Menu(User user, Intermediary intermediary){

        JFrame frame=new JFrame("Nutribros");//creating instance of JFrame
        frame.getContentPane().setBackground(DEFAULT_BACKGROUND_COLOR);
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH); frame.setIconImage(logo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel MenuOptions = new JPanel();
        MenuOptions.setBounds(0, 0, 215, 480);
        MenuOptions.setBackground(DEFAULT_BACKGROUND_COLOR);
        MenuOptions.setLayout(null);

        JPanel UserDiet = new JPanel();
        UserDiet.setBounds(215, 0, 645, 480);
        UserDiet.setBackground(DEFAULT_BACKGROUND_COLOR);
        UserDiet.setLayout(null);

        JPanel CreateDietPanel = new JPanel();
        CreateDietPanel.setBounds(0,0,645, 480);
        CreateDietPanel.setBackground(new Color(64, 63, 63));
        CreateDietPanel.setLayout(null);

        JLabel Info = new JLabel("You dont have any diet created yet");
        Info.setBounds( 222, 100, 200, 30);
        Info.setForeground(Color.WHITE);

        String[] types = {"CLASSIC", "CELIAC", "VEGAN", "VEGETARIAN"};
        JComboBox DietType = new JComboBox(types);
        DietType.setBounds( 222, 150, 200, 30);
        DietType.setFocusable(false);

        Integer[] quantities = getArrayFromArrayList(user);
        JComboBox MealQuantity = new JComboBox(quantities);
        MealQuantity.setBounds( 222, 200, 200, 30);
        MealQuantity.setFocusable(false);

        JButton CreateDietButton = new JButton("Generate diet");
        CreateDietButton.setBounds( 222, 250, 200, 30);
        CreateDietButton.setFocusable(false);


        ImageIcon Icon = new ImageIcon(PROFILE_ICON_PATH);
        Image scaledImage = Icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton profileButton = new JButton(scaledIcon);
        profileButton.setBounds(60,20,100,100);
        profileButton.setFocusable(false);
        profileButton.setBackground(new Color(242, 202, 90));
        MenuOptions.add(profileButton);

        CreateDietPanel.add(Info); CreateDietPanel.add(DietType); CreateDietPanel.add(MealQuantity);CreateDietPanel.add(CreateDietButton);


        if(user.getUserData().getDiet().size() == 0){
            UserDiet.add(CreateDietPanel);
        }
        else{
            JPanel DietCreatedPanel = getJPanelDiet(user, user.getUserData().getDiet().size());
            UserDiet.add(DietCreatedPanel);
        }

        //ActionListeners
        CreateDietButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CreateDietPanel.setVisible(false);
                user.generateDiet((Integer) MealQuantity.getSelectedItem(), (String) DietType.getSelectedItem()); //fix

                JPanel DietCreatedPanel = getJPanelDiet(user, user.getUserData().getDiet().size());
                UserDiet.add(DietCreatedPanel);
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Profile profile = new Profile(user, intermediary);
            }
        });

        frame.add(MenuOptions);
        frame.add(UserDiet);
        frame.setResizable(false);
        frame.setSize(860,480);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JPanel getJPanelDiet(User user, int rows){
        int rowHeight = 440/rows;

        JPanel DietCreatedPanel = new JPanel();
        DietCreatedPanel.setBounds(0,0,745, 480);
        DietCreatedPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
        DietCreatedPanel.setLayout(null);

        JTable FoodTable = new JTable(rows, 1){
            @Serial
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = (DefaultTableModel) FoodTable.getModel();
        ArrayList<Food> userDiet = user.getUserData().getDiet();


        for(int row = 0; row < rows; row++){


            String foodType = userDiet.get(row).getFoodType().toString();
            int servingSize = (int) userDiet.get(row).getServingSize_g();
            String foodName = userDiet.get(row).getName();
            Food food = userDiet.get(row);

            int buttonY = 0;

            switch (rows){
                case 3: buttonY = (row)*(rowHeight)+ 35; break;
                case 4: buttonY = (row)*(rowHeight)+ 25; break;
                case 5: buttonY = (row)*(rowHeight)+ 15; break;
                case 6: buttonY = (row)*(rowHeight)+ 5; break;
                case 7: buttonY = (row)*(rowHeight); break;
            }

            JButton ShowFoodInfo = getButton(SHOW_FOOD_INFO_ICON_PATH, "Show food info", rows);
            JButton AlreadyEaten = getButton(ALREADY_EATEN_ICON_PATH, "Click if eaten", rows);

            ShowFoodInfo.addActionListener(e -> {
                FoodInfo fi = new FoodInfo(food);
            });

            AlreadyEaten.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "This feature will be added in version 2.0");
            });

            ShowFoodInfo.setBounds(470, buttonY, 60, 60);
            AlreadyEaten.setBounds(540, buttonY, 60, 60);

            FoodTable.add(ShowFoodInfo); FoodTable.add(AlreadyEaten);

            model.setValueAt("   " + foodType + ": " + servingSize + "g " + foodName, row, 0);
        }

        FoodTable.setDefaultRenderer(Object.class, TABLE_FONT);
        FoodTable.setBounds(0, 0, 645, 440);
        FoodTable.setForeground(Color.WHITE);
        FoodTable.setRowSelectionAllowed(false);
        FoodTable.setColumnSelectionAllowed(false);
        FoodTable.setGridColor(DEFAULT_BACKGROUND_COLOR);
        FoodTable.setBackground(new Color(64, 63, 63));
        FoodTable.setAutoscrolls(false);
        FoodTable.setFocusable(false);


        FoodTable.setRowHeight(rowHeight);
        DietCreatedPanel.add(FoodTable);

        return DietCreatedPanel;
    }

    private JButton getButton(String iconPath, String toolTip, int rows){
        ImageIcon Icon = new ImageIcon(iconPath);
        int w=0, h=0;

        switch (rows){
            case 3: case 4: w = 60; h=60; break;
            case 5: w = 50; h=50; break;
            case 6: case 7: w = 40; h=40; break;
        }

        Image scaledImage = Icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setToolTipText(toolTip);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        return button;
    }
    private Integer[] getArrayFromArrayList(User user){
        ArrayList<Integer> quantities= new ArrayList<>();
        for (int i = user.getUserData().getObjective().getMinMeals(); i <= user.getUserData().getObjective().getMaxMeals(); i++){
            quantities.add(i);
        }
        return quantities.toArray(new Integer[0]);
    }

}
