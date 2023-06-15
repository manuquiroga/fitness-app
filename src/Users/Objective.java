package Users;

public enum Objective {
    //open to new options like LOSE_WEIGHT_FAST
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
