package model;

/**
 * Created by anirudh on 10/1/15.
 */
public class Plains extends LandUnit {
    public static final int FOOD_PRODUCED = 2;
    public static final int ENERGY_PRODUCED = 3;
    public static final int ORE_PRODUCED = 1;

    @Override
    public int getFoodProduced() {
        return FOOD_PRODUCED;
    }

    @Override
    public int getOreProduced() {
        return ENERGY_PRODUCED;
    }

    @Override
    public int getEnergyProduced() {
        return ORE_PRODUCED;
    }


}
