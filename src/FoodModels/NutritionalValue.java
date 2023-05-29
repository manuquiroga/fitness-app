package FoodModels;

public class NutritionalValue {
    private double fatTotalG;
    private double fatSaturatedG;
    private double proteinG;
    private double sodiumMg;
    private double potassiumMg;
    private double cholesterolMg
    private double carbohydratesG;
    private double fiberG;
    private double sugarG;

    //Builders:
    public NutritionalValue(double fatTotalG, double fatSaturatedG, double proteinG, double sodiumMg, double potassiumMg, double cholesterolMg, double carbohydratesG, double fiberG, double sugarG) {
        this.fatTotalG = fatTotalG;
        this.fatSaturatedG = fatSaturatedG;
        this.proteinG = proteinG;
        this.sodiumMg = sodiumMg;
        this.potassiumMg = potassiumMg;
        this.cholesterolMg = cholesterolMg;
        this.carbohydratesG = carbohydratesG;
        this.fiberG = fiberG;
        this.sugarG = sugarG;
    }

    public NutritionalValue() {
    }

    //Getters:
    public double getFatTotalG() {
        return fatTotalG;
    }

    public double getFatSaturatedG() {
        return fatSaturatedG;
    }

    public double getProteinG() {
        return proteinG;
    }

    public double getSodiumMg() {
        return sodiumMg;
    }

    public double getPotassiumMg() {
        return potassiumMg;
    }

    public double getCholesterolMg() {
        return cholesterolMg;
    }

    public double getCarbohydratesG() {
        return carbohydratesG;
    }

    public double getFiberG() {
        return fiberG;
    }

    public double getSugarG() {
        return sugarG;
    }
}
