package UI;

import FoodModels.Food;
import Users.User;
import Users.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Menu extends JFrame {

    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(40, 40, 40);
    private static final FontRenderer TABLE_FONT = new FontRenderer(new Font("Book Antiqua", Font.BOLD, 18));
    public static void main(String[] args) {
        UserData userData = new UserData(23, 50, "GAIN_WEIGHT", 160, "female", "NONE");
        User user = new User("m", "Prueba123456", "mq@gmail.com", 10, userData);

        Menu menu = new Menu(user);
    }
    public Menu(User user){

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
        CreateDietPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
        CreateDietPanel.setLayout(null);

        JLabel Info = new JLabel("You dont have any diet created yet");
        Info.setBounds( 273, 100, 200, 30);
        Info.setForeground(Color.WHITE);

        String[] types = {"CLASSIC", "CELIAC", "VEGAN", "VEGETARIAN"};
        JComboBox DietType = new JComboBox(types);
        DietType.setBounds( 273, 150, 200, 30);
        DietType.setFocusable(false);

        Integer[] quantities = getArrayFromArrayList(user);
        
        JComboBox MealQuantity = new JComboBox(quantities);
        MealQuantity.setBounds( 273, 200, 200, 30);
        MealQuantity.setFocusable(false);

        JButton CreateDietButton = new JButton("Generate diet");
        CreateDietButton.setBounds( 273, 250, 200, 30);
        CreateDietButton.setFocusable(false);

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
                System.out.println(user.getUserData().toString());

                JPanel DietCreatedPanel = getJPanelDiet(user, user.getUserData().getDiet().size());
                UserDiet.add(DietCreatedPanel);
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
        JPanel DietCreatedPanel = new JPanel();
        DietCreatedPanel.setBounds(0,0,745, 480);
        DietCreatedPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
        DietCreatedPanel.setLayout(null);

        JTable FoodTable = new JTable(rows, 1){
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

            model.setValueAt("   " + foodType + ": " + servingSize + "g " + foodName, row, 0);
        }

        FoodTable.setDefaultRenderer(Object.class, TABLE_FONT);
        //todo: change row color if row%2==0
        FoodTable.setBounds(0, 0, 645, 480);
        FoodTable.setBackground(new Color(226, 226, 226));
        FoodTable.setAutoscrolls(false);
        FoodTable.setFocusable(false);


        int rowHeight = 440/rows;
        FoodTable.setRowHeight(rowHeight);
        DietCreatedPanel.add(FoodTable);

        return DietCreatedPanel;
    }
    private Integer[] getArrayFromArrayList(User user){
        ArrayList<Integer> quantities= new ArrayList<>();
        for (int i = user.getUserData().getObjective().getMinMeals(); i <= user.getUserData().getObjective().getMaxMeals(); i++){
            quantities.add(i);
        }
        return quantities.toArray(new Integer[0]);
    }
}
