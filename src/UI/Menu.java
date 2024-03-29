package UI;

import Exceptions.EmailInUseException;
import Exceptions.IncorrectEmailFormatException;
import Exceptions.NameTooShortException;
import Exceptions.WeakPasswordException;
import FoodModels.Food;
import Handlers.DataValidation;
import Handlers.Intermediary;
import Handlers.RecipeApiHandler;
import UI.Renderers.FontRenderer;
import Users.AdminUser;
import Users.PremiumUser;
import Users.User;
import Users.UserData;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serial;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Menu extends JFrame {

    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";
    private static final String OPEN_RECIPE_ICON_PATH = "src/UI/Resources/libro.png";
    private static final String SHOW_FOOD_INFO_ICON_PATH = "src/UI/Resources/dieta.png";
    private static final String PROFILE_ICON_PATH = "src/UI/Resources/usuario.png";
    private static final String NEW_DIET_ICON_PATH = "src/UI/Resources/nueva-dieta.png";
    private static final String LOGOUT_ICON_PATH = "src/UI/Resources/cerrar-sesion.png";
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(40, 40, 40);
    private static final int PREMIUM_USER_MAX_DIET = 10;
    private static final FontRenderer TABLE_FONT = new FontRenderer(new Font("Book Antiqua", Font.BOLD, 18));
    private JPanel DietCreatedPanel;

    public Menu(User user, Intermediary intermediary){

        DietCreatedPanel = new JPanel();

        JFrame frame=new JFrame("Nutribros");
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


        ImageIcon IconProfile = new ImageIcon(PROFILE_ICON_PATH);
        Image scaledImageProfile = IconProfile.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIconProfile = new ImageIcon(scaledImageProfile);

        JButton profileButton = new JButton(scaledIconProfile);
        profileButton.setBounds(60,35,100,100);
        profileButton.setFocusable(false);
        profileButton.setBackground(new Color(242, 202, 90));
        profileButton.setToolTipText("View profile");
        MenuOptions.add(profileButton);

        ImageIcon IconNewDiet = new ImageIcon(NEW_DIET_ICON_PATH);
        Image scaledImageNewDiet = IconNewDiet.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIconNewDiet = new ImageIcon(scaledImageNewDiet);

        JButton newDietButton = new JButton(scaledIconNewDiet);
        newDietButton.setBounds(60,165,100,100);
        newDietButton.setFocusable(false);
        newDietButton.setBackground(new Color(242, 202, 90));
        MenuOptions.add(newDietButton);

        if(!(user instanceof PremiumUser)){
            newDietButton.setToolTipText("Generate new diet is only allowed for premium users");
            newDietButton.setEnabled(false);
            newDietButton.setBackground(new Color(60,60,60));
        } else{
            newDietButton.setToolTipText("Generate new diet");
            if(((PremiumUser) user).getNumberOfDietsGenerated() >= PREMIUM_USER_MAX_DIET){
                newDietButton.setToolTipText("The maximum of diets per week is "+PREMIUM_USER_MAX_DIET);
                newDietButton.setEnabled(false);
                newDietButton.setBackground(new Color(60,60,60));
            }
            if(!user.hasDiet()){
                newDietButton.setToolTipText("You dont have a diet created yet");
                newDietButton.setEnabled(false);
                newDietButton.setBackground(new Color(60,60,60));
            }
        }

        ImageIcon IconLogOut = new ImageIcon(LOGOUT_ICON_PATH);
        Image scaledImageLogOut = IconLogOut.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIconLogOut = new ImageIcon(scaledImageLogOut);

        JButton logOutButton = new JButton(scaledIconLogOut);
        logOutButton.setBounds(60,295,100,100);
        logOutButton.setFocusable(false);
        logOutButton.setBackground(new Color(242, 202, 90));
        logOutButton.setToolTipText("Log Out");
        MenuOptions.add(logOutButton);

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
                if(user instanceof PremiumUser){
                    if(((PremiumUser) user).getNumberOfDietsGenerated() < 10){
                        newDietButton.setBackground(new Color(242, 202, 90));
                        newDietButton.setEnabled(true);
                    }
                    else{
                        newDietButton.setToolTipText("The maximum of diets per week is 10");
                    }
                }

                CreateDietPanel.setVisible(false);
                user.generateDiet((Integer) MealQuantity.getSelectedItem(), (String) DietType.getSelectedItem());
                System.out.println(user);

                try {
                    intermediary.addUserToMap(user);
                    intermediary.updateUser(user.getEmail(), user);
                    DietCreatedPanel = getJPanelDiet(user, user.getUserData().getDiet().size());
                    UserDiet.add(DietCreatedPanel);

                } catch (JSONException ex) {
                    JOptionPane.showMessageDialog(null, "Wooops... try again");
                }
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Profile profile = new Profile(user, intermediary);
            }
        });

        logOutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login login = new Login(intermediary);
            }
        });

        newDietButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                user.resetDiet();
                ((PremiumUser) user).addMaxDiet();

                try {
                    intermediary.updateUser(user.getEmail(), user);
                } catch (JSONException ex) {
                    System.err.println(ex.getMessage());
                }

                frame.dispose();
                Menu menu = new Menu(user,intermediary);
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
            JButton AlreadyEaten = getButton(OPEN_RECIPE_ICON_PATH, "Open recipe", rows);

            ShowFoodInfo.addActionListener(e -> {
                FoodInfo fi = new FoodInfo(food);
            });

            AlreadyEaten.addActionListener(e -> {
                String response = RecipeApiHandler.searchRecipe(foodName);
                String recipeURL = RecipeApiHandler.getRecipeUrl(response);
                try {
                    RecipeApiHandler.openBrowser(recipeURL);
                } catch (URISyntaxException ex) {
                    System.err.println("URL Syntax error"+ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Sorry, there was an error while opening the recipe");
                } catch (IOException ex) {
                    System.err.println("Open browser error: "+ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Sorry, there was an error while opening the recipe");
                }
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
