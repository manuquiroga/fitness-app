package Users;

/**
 * This enumeration specifies the types of objectives that exist and their minimum and maximum number of meals per day per diet.
 * it is open to new options like GAIN_WEIGHT_FAST
 */
public enum Objective {
    LOSE_WEIGHT(3,5)
    ,MAINTAIN_WEIGHT(4,5)
    ,GAIN_WEIGHT(4,7);

    private int minMeals;
    private int maxMeals;

    /**
     * Constructs an Objective object with the specified minimum meals and maximum meals.
     * @param minMeals the minimum number of meals for the objective
     * @param maxMeals the maximum number of meals for the objective
     */
    Objective(int minMeals, int maxMeals){
        this.minMeals = minMeals;
        this.maxMeals = maxMeals;
    }

    /**
     * Returns the minimum number of meals for the objective.
     * @return the minimum number of meals
     */
    public int getMinMeals() {
        return minMeals;
    }

    /**
     * Returns the maximum number of meals for the objective.
     * @return the maximum number of meals
     */
    public int getMaxMeals() {
        return maxMeals;
    }
}
