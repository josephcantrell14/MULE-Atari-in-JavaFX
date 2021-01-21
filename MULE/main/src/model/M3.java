package model;

/**
 * Created by anirudh on 10/1/15.
 */
public class M3 extends LandUnit {

    public static final int FOOD_PRODUCED = 1;
    public static final int ENERGY_PRODUCED = 1;
    public static final int ORE_PRODUCED = 4;

    @Override
    public int getFoodProduced() {
        return FOOD_PRODUCED;
    }

    @Override
    public int getOreProduced() {
        return ORE_PRODUCED;
    }

    @Override
    public int getEnergyProduced() {
        return ENERGY_PRODUCED;
    }


}
