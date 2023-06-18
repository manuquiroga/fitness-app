package UI;

import FoodModels.Food;
import UI.Renderers.FontRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class FoodInfo extends JFrame {
    private static final String LOGO_ICON_PATH = "src/UI/Resources/weightlifter.png";
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(40, 40, 40);
    private static final Font TABLE_FONT = new Font("Book Antiqua", Font.BOLD, 18);
    private static final Color WALTERWHITE = Color.WHITE;

    public FoodInfo(Food food){
        JFrame frame=new JFrame("Food Info");//creating instance of JFrame
        frame.getContentPane().setBackground(DEFAULT_BACKGROUND_COLOR);
        ImageIcon logo = new ImageIcon(LOGO_ICON_PATH); frame.setIconImage(logo.getImage());

        JLabel NameLabel = new JLabel("Name: " + food.getName());
        NameLabel.setBounds(50, 30, 300, 30);
        NameLabel.setForeground(WALTERWHITE);
        NameLabel.setFont(TABLE_FONT);
        frame.add(NameLabel);

        JLabel ServingLabel = new JLabel("Serving size: " + food.getServingSize_g()+ "g");
        ServingLabel.setBounds(50, 60, 300, 30);
        ServingLabel.setForeground(WALTERWHITE);
        ServingLabel.setFont(TABLE_FONT);
        frame.add(ServingLabel);

        JLabel CaloriesLabel = new JLabel("Calories: " + food.getCalories());
        CaloriesLabel.setBounds(50, 90, 300, 30);
        CaloriesLabel.setForeground(WALTERWHITE);
        CaloriesLabel.setFont(TABLE_FONT);
        frame.add(CaloriesLabel);

        JLabel ProteinsLabel = new JLabel("Proteins: " + food.getproteins_g()+ "g");
        ProteinsLabel.setBounds(50, 120, 300, 30);
        ProteinsLabel.setForeground(WALTERWHITE);
        ProteinsLabel.setFont(TABLE_FONT);
        frame.add(ProteinsLabel);

        JLabel FatsLabel = new JLabel("Fats: " + food.getfats_g()+ "g");
        FatsLabel.setBounds(50, 150, 300, 30);
        FatsLabel.setForeground(WALTERWHITE);
        FatsLabel.setFont(TABLE_FONT);
        frame.add(FatsLabel);

        JLabel CarbohydratesLabel = new JLabel("Carbohydrates: " + food.getCarbohydrates_g() + "g");
        CarbohydratesLabel.setBounds(50, 180, 300, 30);
        CarbohydratesLabel.setForeground(WALTERWHITE);
        CarbohydratesLabel.setFont(TABLE_FONT);
        frame.add(CarbohydratesLabel);

        JLabel IngredientsLabel = new JLabel("Ingredients: ");
        IngredientsLabel.setBounds(50, 210, 300, 30);
        IngredientsLabel.setForeground(WALTERWHITE);
        IngredientsLabel.setFont(TABLE_FONT);
        frame.add(IngredientsLabel);

        DefaultListModel model = new DefaultListModel();
        Iterator<String> it= food.getIngredients().iterator();

        for (int i = 0; i<food.getIngredients().size();i++){
            if(it.hasNext()){
                model.add(i,it.next());
            }
        }

        JList IngredientsList = new JList(model);
        IngredientsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        IngredientsList.setLayoutOrientation(JList.VERTICAL);
        IngredientsList.setVisibleRowCount(-1);
        IngredientsList.setBounds(50, 250, 290, 90);
        IngredientsList.setBackground(new Color(226,226,226));
        frame.add(IngredientsList);


        frame.setResizable(false);
        frame.setSize(400,480);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
