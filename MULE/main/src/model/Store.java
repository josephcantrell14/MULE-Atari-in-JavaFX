package model;

import controllers.GameController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 10/7/15.
 * Create a store class to hold resources -Food, Energy, Ore, and mules
 */
public class Store {
    private int food;
    private int energy;
    private int crystite;
    private int smithOre;
    private List<Mule> mules = new ArrayList<>();

    private static final int BEGINNER_INITIAL_FOOD = 16;
    private static final int BEGINNER_INITIAL_ENERGY = 16;
    private static final int BEGINNER_INITIAL_MULE_COUNT = 25;
    private static final int OTHER_INITIAL_FOOD = 8;
    private static final int OTHER_INITIAL_ENERGY = 8;
    private static final int OTHER_INITIAL_MULE_COUNT = 14;
    private static final int FOOD_PRICE = 30;
    private static final int ENERGY_PRICE = 25;
    private static final int ORE_PRICE = 50;
    private static final int CRYSTITE_PRICE = 100;
    private static final int BASE_MULE_PRICE = 100;
    private static final int FOOD_MULE_PRICE = 125;
    private static final int ORE_MULE_PRICE = 175;
    private static final int ENERGY_MULE_PRICE = 150;
    private static final int CRYSTITE_MULE_PRICE = 200;

    /**
     * Default model.Store constructor depending on the game Difficulty
     */
    public Store(GameController.Difficulty difficulty) {
        //If the Difficulty is Beginner
        if (difficulty == GameController.Difficulty.BEGINNER) {
            //16 Food and Energy, and 25 mules
            food = BEGINNER_INITIAL_FOOD;
            energy = BEGINNER_INITIAL_ENERGY;
            smithOre = 0;
            crystite = 0;
            //Add mules to the store's arraylist
            for (int i = 0; i < BEGINNER_INITIAL_MULE_COUNT; i++) {
                mules.add(new Mule());
            }
        } else {
            //8 Food and Energy, and 14 mules
            food = OTHER_INITIAL_FOOD;
            energy = OTHER_INITIAL_ENERGY;
            smithOre = 0;
            crystite = 0;
            //Add mules to the store's arraylist
            for (int i = 0; i < OTHER_INITIAL_MULE_COUNT; i++) {
                mules.add(new Mule());
            }
        }
    }
    /**
     * Method to get amount of Food
     */
    public int getFood(){ return food;}
    /**
     * Method to get amount of energy
     */
    public int getEnergy() { return energy;}
    /**
     * Method to get amount of crystite
     */
    public int getCrystite() {return crystite;}
    /**
     * Method to get amount of smithOre
     */
    public int getSmithOre() {return smithOre;}
    /**
     * Method to get amount of mules
     */
    public int getNumMULES() { return mules.size();}
    //TODO --JOHN METHOD CONTRACT: ONE OF THESE BUY/SELL METHODS
    /**
     * Method to buy food/ model.Player sells to the model.Store
     */
    public void sellFood(int amountToBuy, Player player) {
        // Check and see if model.Player has enough
        if (player.getFood() >= amountToBuy) {
            food += amountToBuy;
            int foodPrice = FOOD_PRICE;
            int totalCost = foodPrice * amountToBuy;
            //Add food to model.Player and pay them for their resource
            player.setFood(player.getFood() - amountToBuy);
            player.setMoney(player.getMoney() + totalCost);
        }
    }
    /**
     * Method to buy energy/ model.Player sells to the model.Store
     */
    public void sellEnergy(int amountToBuy, Player player) {
        if (player.getEnergy() >= amountToBuy) {
            energy += amountToBuy;
            int ePrice = ENERGY_PRICE;
            int totalCost = ePrice * amountToBuy;
            //Add food to model.Player and pay them for their resource
            player.setEnergy(player.getEnergy() - amountToBuy);
            player.setMoney(player.getMoney() + totalCost);
        }
    }
    /**
     * Method to buy smithore/ model.Player sells to the model.Store
     */
    public void sellSmithOre(int amountToBuy, Player player) {
        if (player.getOre() >= amountToBuy) {
            smithOre += amountToBuy;
            int orePrice = ORE_PRICE;
            int totalCost = orePrice * amountToBuy;
            //Add food to model.Player and pay them for their resource
            player.setOre(player.getOre() - amountToBuy);
            player.setMoney(player.getMoney() + totalCost);
        }
    }
    /**
     * Method to buy food/ model.Player sells to the model.Store
     */
    public void sellCrystite(int amountToBuy, Player player) {
        if (player.getCrystite() >= amountToBuy) {
            crystite += amountToBuy;
            int crystitePrice = CRYSTITE_PRICE;
            int totalCost = crystitePrice * amountToBuy;
            //Add food to model.Player and pay them for their resource
            player.setCrystite(player.getCrystite() - amountToBuy);
            player.setMoney(player.getMoney() + totalCost);
        }
    }

    /**
     * Method for a player to buy food from the store
     * @param amountToBuy
     * @param player
     */
    public void buyFood(int amountToBuy, Player player) {
        if (food >= amountToBuy && player.getMoney() >= FOOD_PRICE) {
            food -= amountToBuy;
            player.setMoney(player.getMoney() - FOOD_PRICE * amountToBuy);
            player.setFood(player.getFood() + amountToBuy);
        }
    }

    /**
     * Method for a player to buy ore from the store
     * @param amountToBuy
     * @param player
     */
    public void buyOre(int amountToBuy, Player player) {
        if (smithOre >= amountToBuy && player.getMoney() >= ORE_PRICE) {
            smithOre -= amountToBuy;
            player.setMoney(player.getMoney() - ORE_PRICE * amountToBuy);
            player.setOre(player.getOre() + amountToBuy);
        }
    }

    /**
     * Method for a player to buy energy from the store
     * @param amountToBuy
     * @param player
     */
    public void buyEnergy(int amountToBuy, Player player) {
        if (energy >= amountToBuy && player.getMoney() >= ENERGY_PRICE) {
            energy -= amountToBuy;
            player.setMoney(player.getMoney() - ENERGY_PRICE * amountToBuy);
            player.setEnergy(player.getEnergy() + amountToBuy);
        }
    }

    /**
     * Method for a player to buy crystite from the store
     * @param amountToBuy
     * @param player
     */
    public void buyCrystite(int amountToBuy, Player player) {
        if (crystite >= amountToBuy && player.getMoney() >= CRYSTITE_PRICE) {
            crystite -= amountToBuy;
            player.setMoney(player.getMoney() - CRYSTITE_PRICE * amountToBuy);
            player.setCrystite(player.getCrystite() + amountToBuy);
        }
    }

    public void buyMule(Player player, Mule.Type type) {
        int cost = BASE_MULE_PRICE;
        switch (type){
            case FOOD:
                cost = FOOD_MULE_PRICE;
                break;
            case ORE:
                cost = ORE_MULE_PRICE;
                break;
            case ENERGY:
                cost = ENERGY_MULE_PRICE;
                break;
            case CRYSTITE:
                cost = CRYSTITE_MULE_PRICE;
                break;
        }
        if (!mules.isEmpty() && player.getMoney() >= cost && !player.hasMule()) {
            Mule m = mules.remove(0);
            m.setType(type);
            m.setOwnerName(player.getName());
            m.setOwner(player);
            player.setMoney(player.getMoney() - cost);
            player.setMule(m);
        }
    }
}

