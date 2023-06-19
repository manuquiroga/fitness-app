package Users;

/**
 * This class specifies the types of objectives that exist and their minimum and maximum number of meals per day per diet.
 * it is open to new options like GAIN_WEIGHT_FAST
 */
public enum Objective {
    LOSE_WEIGHT(3,5)
    ,MAINTAIN_WEIGHT(4,5)
    ,GAIN_WEIGHT(4,7);

    private int minMeals;
    private int maxMeals;
    Objective(int minMeals, int maxMeals){
        this.minMeals = minMeals;
        this.maxMeals = maxMeals;
    }

    public int getMinMeals() {
        return minMeals;
    }

    public int getMaxMeals() {
        return maxMeals;
    }
}
