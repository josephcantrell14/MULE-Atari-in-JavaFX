package controllers;

import model.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements ChangeListener<Number> {


    public static enum Difficulty {BEGINNER, STANDARD, TOURNAMENT}

    public static enum Terrain {M1, M2, M3, P, R, Town}

    // The map array

    private static LandUnit[] map = {
            new Plains(), new Plains(), new M1(), new Plains(), new River(), new Plains(), new M3(), new Plains(), new Plains(),
            new Plains(), new M1(), new Plains(), new Plains(), new River(), new Plains(), new Plains(), new Plains(), new M3(),
            new M3(), new Plains(), new Plains(), new Plains(), null, new Plains(), new Plains(), new Plains(), new M1(),
            new Plains(), new M2(), new Plains(), new Plains(), new River(), new Plains(), new M2(), new Plains(), new Plains(),
            new Plains(), new Plains(), new M2(), new Plains(), new River(), new Plains(), new Plains(), new Plains(), new M2()
    };

    private Difficulty difficulty;
    //private int numPlayers;
    private static Player[] players;
    private static Store store;
    private static RandomEvents randomEvents;
    private static String eventText;
    private static final int RANDOM_CHANCE = 27;
    private static int chance;

    private static boolean landSelectionDone;
    private static int numPasses;
    private static int currentRound;
    private static int currentPlayer;
    private static int landSelectionRound;
    private static final int INITIAL_LAND = 44;
    private static int landRemaining = INITIAL_LAND;
    private static int timeRemaining;

    //To make timer stop after closing java application, and made it final with the param "true"
    private static final Timer TIMER = new Timer(true);
    private static final int TOWN_TILE = 22;
    private static final int HUMAN_INITIAL_MONEY = 600;
    private static final int FLAPPER_INITIAL_MONEY = 1600;
    private static final int OTHER_INITIAL_MONEY = 1000;
    private static final int BEGINNER_INITIAL_FOOD = 8;
    private static final int BEGINNER_INITIAL_ENERGY = 4;
    private static final int OTHER_INITIAL_FOOD = 4;
    private static final int OTHER_INITIAL_ENERGY = 2;
    private static final int ONE_SECOND = 1000;
    private static final int MAX_GAMBLE_BONUS = 250;
    private static final int ROUND_TIER_ONE = 3;
    private static final int ROUND_TIER_TWO = 7;
    private static final int ROUND_TIER_THREE = 11;
    private static final int ROUND_TIER_ONE_BONUS = 50;
    private static final int ROUND_TIER_TWO_BONUS = 100;
    private static final int ROUND_TIER_THREE_BONUS = 150;
    private static final int ROUND_TIER_FOUR_BONUS = 200;
    private static final int TIME_TIER_ONE = 37;
    private static final int TIME_TIER_TWO = 25;
    private static final int TIME_TIER_THREE = 12;
    private static final int TIME_TIER_ONE_BONUS = 200;
    private static final int TIME_TIER_TWO_BONUS = 150;
    private static final int TIME_TIER_THREE_BONUS = 100;
    private static final int TIME_TIER_FOUR_BONUS = 50;
    private static final int ONE_HUNDRED = 100;
    private static final double TRANSLUCENT = 0.5;





    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if (!landSelectionDone) {
            landSelection((int) newValue);
        } else {
            placeMULE((int) newValue);
        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Method to update the time
     */
    private static void updateTime() {
        Platform.runLater(new Runnable() {
            public void run() {
                --timeRemaining;
                GUIController.updateTime(timeRemaining);
                GUIController.updateRound(currentRound);
                if (timeRemaining == 0) {
                    ++currentPlayer;
                    for (int i = 0; i < map.length; i++) {
                        if (i != TOWN_TILE) {
                            map[i].muleProduction();
                        }
                    }
                    for (int i = 0; i < players.length; i++) {
                        updatePlayerGUI(i);
                    }
                    if (currentPlayer >= players.length) {
                        currentPlayer = 0;
                        setupRound();
                        /*if (currentRound >= 15) {
                            // TODO -- PROPER END OF GAME
                            //System.exit(0);
                        }*/
                    }
                    randomEvent();
                    for (int i = 0; i < players.length; i++) {
                        updatePlayerGUI(i);
                    }
                    timeRemaining = players[currentPlayer].getTurnTime();
                }
                //Update player indicator
                setCurrentPlayerIndicator();
            }
        });
    }

    /**
     * Private default constructor for static instance
     */
    public GameController() {
        initializePlayers();
        initGUIStats();
        store = new Store(difficulty);
        updateStorePanel();
        setCurrentPlayerIndicator();
        randomEvents = RandomEvents.getInstance();
    }

    /**
     * Method to initialize the create player instances and initialize starting resources
     * at the beginning of the game.
     */
    public final void initializePlayers() {

        int numPlayers = Controller.getNumOfPlayers();
        this.difficulty = Controller.getgDifficulty();

        players = new Player[Controller.getNumOfPlayers()];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(Controller.getpNames()[i], Controller.getpRaces()[i], Controller.getpColors()[i]);

            // Setting up players money
            if (players[i].getRace() == Player.Race.HUMAN) {
                players[i].setMoney(HUMAN_INITIAL_MONEY);
            } else if (players[i].getRace() == Player.Race.FLAPPER) {
                players[i].setMoney(FLAPPER_INITIAL_MONEY);
            } else {
                players[i].setMoney(OTHER_INITIAL_MONEY);
            }

            // Setting up players Resources
            if (difficulty == Difficulty.BEGINNER) {
                players[i].setFood(BEGINNER_INITIAL_FOOD);
                players[i].setEnergy(BEGINNER_INITIAL_ENERGY);
            } else {
                players[i].setFood(OTHER_INITIAL_FOOD);
                players[i].setEnergy(OTHER_INITIAL_ENERGY);
            }

        }
    }

    // Setup time based
    private static void setupRound() {
        //showScoreScreen();
        currentRound++;
        updateScore();
        Arrays.sort(players);
        currentPlayer = 0;

        for (int i = 0; i < players.length; ++i) {
            players[i].setTurnTime(currentRound);
            //Pass information to gui
            updatePlayerGUI(i);
        }
        // Set current player indicator
        setCurrentPlayerIndicator();
        timeRemaining = players[currentPlayer].getTurnTime();
    }

    public static void startRound() {
        setupRound();
        //timeRemaining = players[currentPlayer].getTurnTime();
        TIMER.schedule(new TimeKeeper(), ONE_SECOND);
    }

    private static void updateScore() {
        for (int i = 0; i < players.length; ++i) {
            players[i].setScore();
        }
    }

    /**
     * Method for the land selection phase of things
     *
     * @param location Which is being contented here
     */
    public void landSelection(int location) {

        boolean landPurchased = false;

        if (!map[location - 1].getIsSold()) {
            if (landSelectionRound < 2) {
                map[location - 1].setOwnerName(players[currentPlayer].getName());
                map[location - 1].setIsSold(true);
                map[location - 1].setOwner(players[currentPlayer]);
                --landRemaining;
                players[currentPlayer].setNumLandUnits(players[currentPlayer].getNumLandUnits() + 1);
                GUIController.setOwner(players[currentPlayer].getColor().name(), location, true);
                landPurchased = true;
            } else if (players[currentPlayer].getMoney() >= LandUnit.UNIT_COST) {
                map[location - 1].setOwnerName(players[currentPlayer].getName());
                map[location - 1].setIsSold(true);
                map[location - 1].setOwner(players[currentPlayer]);
                --landRemaining;
                players[currentPlayer].setNumLandUnits(players[currentPlayer].getNumLandUnits() + 1);
                GUIController.setOwner(players[currentPlayer].getColor().name(), location, true);
                players[currentPlayer].setMoney(players[currentPlayer].getMoney() - LandUnit.UNIT_COST);
                updatePlayerGUI(currentPlayer);
                landPurchased = true;
            }
            if (!landPurchased) {
                passTurn();
            } else if (++currentPlayer >= players.length) {
                currentPlayer = 0;
                numPasses = 0;
                ++landSelectionRound;
                if (landRemaining < players.length) {
                    landSelectionDone = true;
                    GUIController.enableTown();
                    startRound();
                }
            }
        }
        //Update current player indicator
        setCurrentPlayerIndicator();
    }

    public static void passTurn() {
        numPasses++;
        if (++currentPlayer >= players.length) {
            currentPlayer = 0;
            ++landSelectionRound;

            if (numPasses == players.length) {
                landSelectionDone = true;
                GUIController.enableTown();
                startRound();
            } else {
                numPasses = 0;
            }
        }
        setCurrentPlayerIndicator();
    }

    /**
     * Calculate the amount won with remaining time,
     * and the round's time bonus. Ends the current player's turn.
     */
    public void gamble() {
        Random gen = new Random();
        int timeBonus;
        int moneyEarned;
        int roundBonus;

        //Calculate roundBonus
        roundBonus = calculateRoundBonus();

        //Calculate Time Bonus
        timeBonus = calculateTimeBonus();

        //Plus 1 is added to get a random time bonus of 0 and time bonus
        int randomTimeBonus = gen.nextInt(timeBonus + 1);

        //Cap total money earned to 250
        if (roundBonus * randomTimeBonus > MAX_GAMBLE_BONUS) {
            moneyEarned = MAX_GAMBLE_BONUS;
        } else {
            moneyEarned = roundBonus * randomTimeBonus;
        }
        players[currentPlayer].setMoney((players[currentPlayer].getMoney() + moneyEarned));
        updatePlayerGUI(currentPlayer);
        // Reduce timer to zero
        timeRemaining = 1;
        //TODO -- Show how much was earned.
        setCurrentPlayerIndicator();
    }

    private int calculateRoundBonus() {
        int roundBonus;
        if (currentRound <= ROUND_TIER_ONE) {
            roundBonus = ROUND_TIER_ONE_BONUS;
        } else if (currentRound <= ROUND_TIER_TWO) {
            roundBonus = ROUND_TIER_TWO_BONUS;
        } else if (currentRound <= ROUND_TIER_THREE) {
            roundBonus = ROUND_TIER_THREE_BONUS;
        } else {
            roundBonus = ROUND_TIER_FOUR_BONUS;
        }
        return roundBonus;
    }

    private int calculateTimeBonus() {
        int timeBonus;
        if (timeRemaining >= TIME_TIER_ONE) {
            timeBonus = TIME_TIER_ONE_BONUS;
        } else if (timeRemaining >= TIME_TIER_TWO) {
            timeBonus = TIME_TIER_TWO_BONUS;
        } else if (timeRemaining >= TIME_TIER_THREE) {
            timeBonus = TIME_TIER_THREE_BONUS;
        } else {
            timeBonus = TIME_TIER_FOUR_BONUS;
        }
        return timeBonus;
    }

    /**
     * Getter to get the current player's color
     */
    public static Player.Color getCurrentPlayerColor() {
        return players[currentPlayer].getColor();
    }

    public void sellCrystite(int amountToBuy) {
        store.sellCrystite(amountToBuy, players[currentPlayer]);
    }

    public void buyFood() {
        store.buyFood(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    public void buyOre() {
        store.buyOre(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    public void buyEnergy() {
        store.buyEnergy(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    public void buyCrystite() {
        store.buyCrystite(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    public void buyFoodMule() {
        store.buyMule(players[currentPlayer], Mule.Type.FOOD);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
        changeMuleIcon();
    }

    public void buyOreMule() {
        store.buyMule(players[currentPlayer], Mule.Type.ORE);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
        changeMuleIcon();
    }

    public void buyEnergyMule() {
        store.buyMule(players[currentPlayer], Mule.Type.ENERGY);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
        changeMuleIcon();
    }

    public void sellFood() {
        store.sellFood(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    public void sellOre() {
        store.sellSmithOre(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    public void sellEnergy() {
        store.sellEnergy(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    public void sellCrystite() {
        store.sellCrystite(1, players[currentPlayer]);
        updatePlayerGUI(currentPlayer);
        updateStorePanel();
    }

    /**
     * Place current player's MULE on their own property.
     * If the player doesn't have a MULE, then nothing happens.
     * If they place a MULE on a property that is taken, the current player loses their MULE from their inventory.
     *
     * @param location , where the MULE is considered to be placed
     */
    public void placeMULE(int location) {
        //Check if the player has a MULE
        if (players[currentPlayer].hasMule()) {
            //if the location is sold and owned by the current player
            if (map[location - 1].getIsSold() && checkLandOwner(players[currentPlayer], location)) {
                //&& (map[location - 1].getOwnerName().equals(players[currentPlayer].getName()))) {
                map[location - 1].setMule(players[currentPlayer].getMule());
                GUIController.setMULE(location, TRANSLUCENT);
            }
            //Remove the MULE from the model.Player's slot
            players[currentPlayer].removeMule();
            changeMuleIcon();
        }
        //If the model.Player doesn't have a MULE, do nothing
    }

    /**
     * Check a piece of LandUnit against another Player to see ownership and returns a boolean
     * @param, location of the LandUnit
     * @param, player that will check the LandUnit
     */
    public boolean checkLandOwner(Player player, int location) {
        return map[location - 1].getOwner().equals(player);
    }

    /**
     * Shows the Player's MULE based on whether they have a MULE or not.
     * Pass the currentPlayer to the GUIcontroller
     */
    public void changeMuleIcon() {
        if (players[currentPlayer].hasMule()) {
            GUIController.setMULEOn(currentPlayer);
        } else {
            GUIController.setMULEoff(currentPlayer);
        }
    }

    /**
     * Passes the current Player's color to the gui to update
     */
    public static void setCurrentPlayerIndicator() {
        GUIController.setCurrentPlayerIndicator(getCurrentPlayerColor().name());
    }

    /**
     * Passes Player values to GUIController to populate Player gui stat boxes
     */
    public static void initGUIStats() {
        for (int i = 0; i < players.length; i++) {
            String name = players[i].getName();
            int money = (players[i].getMoney());
            int food = (players[i].getFood());
            int energy = (players[i].getEnergy());
            int ore = (players[i].getOre());
            int crystite = (players[i].getCrystite());
            String color = players[i].getColor().name();
            //Player.Color color = players[i].getColor();
            GUIController.updateGUIStats(i, name, money, food, energy, ore, crystite, color);
        }
    }

    /**
     * Passes the param Player's stats to update the gui
     * @param playerNum in which they are currently ordered
     */
    public static void updatePlayerGUI(int playerNum) {
        GUIController.updateGUIStats(playerNum, players[playerNum].getName(), players[playerNum].getMoney(),
                players[playerNum].getFood(), players[playerNum].getEnergy(), players[playerNum].getOre(),
                players[playerNum].getCrystite(), players[playerNum].getColor().name());
    }

    /**
     * Pass the Store's info to gui to update
     */
    public static void updateStorePanel() {
        String foodAmount = Integer.toString(store.getFood());
        String energyAmount = Integer.toString(store.getEnergy());
        String oreAmount = Integer.toString(store.getSmithOre());
        String crystiteAmount = Integer.toString(store.getCrystite());
        String muleAmount = Integer.toString(store.getNumMULES());
        GUIController.updateStorePanel(foodAmount, energyAmount, oreAmount, crystiteAmount, muleAmount);
    }

    /**
     * Random event calculator for each player
     */
    public static void randomEvent() {
        Random gen = new Random();
        chance = gen.nextInt(ONE_HUNDRED) + 1;
        if (chance <= RANDOM_CHANCE) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("M.U.L.E.");
            alert.setHeaderText(players[currentPlayer].getName());
            if (currentPlayer == 0) {
                eventText = randomEvents.getGoodEvent(currentRound, players[currentPlayer]);
            } else {
                eventText = randomEvents.getEvent(currentRound, players[currentPlayer]);
            }
            alert.setContentText(eventText);
            alert.showAndWait();
        }
        updatePlayerGUI(currentPlayer);


    }

    /**
     * Initial workings to show the Score Screen in between rounds
     */
    public static void showScoreScreen() {
        //Pause cancel the Score
        //GUIController.setScoreScreen(numPlayers);
        GUIController.showScoreScreen();
    }

    /**
     * Save the current game's status.
     */
    public static void saveGame() {

    }

    static class TimeKeeper extends TimerTask {

        @Override
        public void run() {
            updateTime();
            if (currentPlayer < players.length) {
                TIMER.schedule(new TimeKeeper(), ONE_SECOND);
            } else {
                setupRound();
            }
        }
    }
}