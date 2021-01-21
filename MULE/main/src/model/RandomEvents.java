package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Justin on 10/22/15.
 */
public final class RandomEvents {
    private static Random gen = new Random();
    private static List<String> randomEvents = new ArrayList<>();
    private static int m;
    private static final int MAX_M = 100;
    private static final int MID_M = MAX_M/2;
    private static final int MIN_M = 25;
    private static final int UPPER_M = 75;
    private static final int MIN_M_ROUND_LOWER_LIMIT = 1;
    private static final int MIN_M_ROUND_UPPER_LIMIT = 3;
    private static final int MID_M_ROUND_LOWER_LIMIT = 4;
    private static final int MID_M_ROUND_UPPER_LIMIT = 6;
    private static final int UPPER_M_ROUND_LOWER_LIMIT = 8;
    private static final int UPPER_M_ROUND_UPPER_LIMIT = 11;

    private static final int BAD_EVENTS_NDX = 4;
    private static final int MULT_BY_8 = 8;
    private static final int MULT_BY_6 = 6;
    private static final int MULT_BY_4 = 4;
    private static final int MULT_BY_2 = 2;
    private static final int EXTRA_FOOD = 3;
    private static final int EXTRA_ENERGY = 2;
    private static final int EXTRA_ORE = 2;
    private static final int CASE_SOLD_COMPUTER = 2;
    private static final int CASE_SOLD_MOUSE_HIDE = 3;
    private static final int CASE_REPAIR_ROOF = 4;
    private static final int CASE_USA_THIEVES = 5;
    private static final int CASE_GYPSY_IN_LAWS = 6;

    private static RandomEvents instance = null;

    private RandomEvents() {
        randomEvents.add("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
        randomEvents.add("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
        randomEvents.add("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR  $" + MULT_BY_8*m);
        randomEvents.add("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $" + MULT_BY_2*m);
        //Bad events start at index 4
        randomEvents.add("FLYING CAT\u00ADBUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $" + MULT_BY_4*m);
        randomEvents.add("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
        randomEvents.add("YOUR SPACE GYPSY IN-LAWS MADE A MESS OF THE TOWN. IT COST YOU $" + MULT_BY_6*m + " TO CLEAN IT UP.");

    }

    public static RandomEvents getInstance() {
        if (instance == null) {
            instance = new RandomEvents();
        }
        return instance;
    }

    /**
     * Calculate round m value, and apply the random event to the player
     * @param currentRound of the game
     * @param player that the random event will affect
     * @return random event's String
     */
    public static String getEvent(int currentRound, Player player){ //TODO -- VINCENT METHOD CONTRACT
        calcM(currentRound);
        int randomNDX = gen.nextInt(randomEvents.size());

        switch (randomNDX) {
            case 0:
                player.setFood(player.getFood() + EXTRA_FOOD);
                player.setEnergy(player.getEnergy() + EXTRA_ENERGY);
                break;
            case 1:
                player.setOre(player.getOre() + EXTRA_ORE);
                break;
            case CASE_SOLD_COMPUTER:
                player.setMoney(player.getMoney() + MULT_BY_8 * m);
                break;
            case CASE_SOLD_MOUSE_HIDE:
                player.setMoney(player.getMoney() + MULT_BY_2 * m);
                break;
            case CASE_REPAIR_ROOF:
                player.setMoney(player.getMoney() - (MULT_BY_4 * m));
                break;
            case CASE_USA_THIEVES:
                player.setFood(player.getFood() / MULT_BY_2); //half your food
                break;
            case CASE_GYPSY_IN_LAWS:
                player.setMoney(player.getMoney() - (MULT_BY_6 * m));
                break;
        }
        return randomEvents.get(randomNDX);
    }

    /**
     * Calculate round m value, and apply the random event to the player.
     * Similar to getEvent, but only gets good events
     * @param currentRound of the game
     * @param player that the random event will affect
     * @return random event's String
     */
    public static String getGoodEvent(int currentRound, Player player){
        calcM(currentRound);

        int randomNDX = gen.nextInt(BAD_EVENTS_NDX);

        switch (randomNDX) {
            case 0:
                player.setFood(player.getFood() + EXTRA_FOOD);
                player.setEnergy(player.getEnergy() + EXTRA_ENERGY);
                break;
            case 1:
                player.setOre(player.getOre() + EXTRA_ORE);
                break;
            case CASE_SOLD_COMPUTER:
                player.setMoney(player.getMoney() + (MULT_BY_8 * m));
                break;
            case CASE_SOLD_MOUSE_HIDE:
                player.setMoney(player.getMoney() + (MULT_BY_2 * m));
                break;
        }
        return randomEvents.get(randomNDX);
    }

    /**
     * Calculate m variable depending on the game's current round.
     * @param currentRound
     */
    public static void calcM(int currentRound) {

        if (currentRound >= MIN_M_ROUND_LOWER_LIMIT && currentRound <= MIN_M_ROUND_UPPER_LIMIT) {
            m = MIN_M;
        } else if (currentRound >= MID_M_ROUND_LOWER_LIMIT && currentRound <= MID_M_ROUND_UPPER_LIMIT) {
            m = MID_M;
        } else if (currentRound >= UPPER_M_ROUND_LOWER_LIMIT && currentRound <= UPPER_M_ROUND_UPPER_LIMIT) {
            m = UPPER_M;
        } else if (currentRound > UPPER_M_ROUND_UPPER_LIMIT) {
            m = MAX_M;
        }
    }
}
