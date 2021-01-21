package model;

/**
 * model.Player class
 */
public class Player implements Comparable<Player> {
    //Every model.Player has a name, race, color, money, ore, food, energy, crystite,
    // and a mule in their current M.U.L.E. slot.

    public enum Race {HUMAN, FLAPPER, BONZOID, UGAITE, BUZZITE, OTHERS}
    public enum Color {RED, BLUE, GREEN, YELLOW}

    // model.Player attributes declared here
    private String name;
    private Race race;
    private Color color;
    private Mule mule;
    private boolean hasMule;
    private int food;
    private int money;
    private int energy;
    private int crystite;
    private int ore;

    // Stuff for score and time keeping
    private int score;
    private int numLandUnits;
    private int turnTime;

    /**
     * Constructor for the model.Player class
     * @param playerName The name you want to give to player instance
     * @param playerRace The race you want to assign to player instance
     * @param playerColor The color you want to assign to player instance
     */
    public Player(String playerName, Race playerRace, Color playerColor) {
        this.name = playerName;
        this.race = playerRace;
        this.color = playerColor;
        this.hasMule = false;
    }

    public boolean hasMule() {
        return hasMule;
    }

    /**
     * Method to set players food
     * @param newFood
     */
    public void setFood(int newFood) {
        this.food = newFood;
    }

    /**
     * Method to get players food
     * @return
     */
    public int getFood() {
        return food;
    }

    /**
     * Method to get the players race
     * @return race
     */
    public Race getRace() {
        return race;
    }

    /**
     * Method to set the players race
     * @param newRace The race that you want to allocate to this model.Player
     */
    public void setRace(Race newRace) {
        this.race = newRace;
    }

    /**
     * Method to set the players color
     * @param newColor The color that is to be assigned to this model.Player
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Method to set the players mule
     * @param newMule The mule that is to be assinged to this model.Player
     */
    public void setMule(Mule newMule) {
        this.mule = newMule;
        this.hasMule = true;
    }

    /**
     * Method to remove the player's mule
     */
    public void removeMule() {
        this.mule = null;
        this.hasMule = false;
    }

    /**
     * Method to set the Players money
     * @param newMoney
     */
    public void setMoney(int newMoney) {
        if (money < 0) {
            this.money = 0;
        } else {
            this.money = newMoney;
        }
    }

    /**
     * Method to set the Players energy
     * @param newEnergy The amount of energy that this player is to be assigned
     */
    public void setEnergy(int newEnergy) {
        this.energy = newEnergy;
    }

    /**
     * Method to set the Players crstite
     * @param newCrystite
     */
    public void setCrystite(int newCrystite) {
        this.crystite = newCrystite;
    }

    /**
     * Method to set the players ore
     * @param newOre The amount of ore that the player is to be assigned
     */
    public void setOre(int newOre) {
        this.ore = newOre;
    }

    /**
     * Method to get the players color
     * @return The players current color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method to get the players model.Mule instance
     * @return The players current model.Mule
     */
    public Mule getMule() {
        return mule;
    }

    /**
     * Method to get the players Money
     * @return The players current money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Method to get the players current energy
     * @return The players current energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Method to get the players crystite
     * @return Players current crystite
     */
    public int getCrystite() {
        return crystite;
    }

    /**
     * Method to get the players Ore value
     * @return players current ore value
     */
    public int getOre() {
        return ore;
    }

    /**
     * Getter for player name
     * @return name Players name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for player name
     * @param newName The name that is to be assigned to the player
     */
    public void setName(String newName) {
        this.name = newName;
    }

    public void setScore() {
        final int landscore = 500;
        score = money + (landscore * numLandUnits);
    }

    public int getScore() {
        // TODO -- Add support for score from resources owner
        return score;
    }

    /**
     * Method to decide the amount of time the player gets for a turn. Time is set in the instance variable turnTime
     * based on the food available and the round number.
     * @param roundNumber Takes in the current round number
     */
    public void setTurnTime(int roundNumber) {
        // If food is 0, turnTme is 5 and return
        final int roundscale1 = 4;
        final int roundscale2 = 8;
        final int foodreq1 = 3;
        final int foodreq2 = 4;
        final int foodreq3 = 5;
        final int lowtime = 30;
        final int normaltime = 50;
        final int nofoodtime = 5;
        if (food == 0) {
            turnTime = nofoodtime;
            return;
        }
        if (roundNumber <= roundscale1) {
            turnTime = (food < foodreq1) ? lowtime : normaltime;
        } else if (roundNumber <= roundscale2) {
            turnTime = (food < foodreq2) ? lowtime : normaltime;
        } else {
            turnTime = (food < foodreq3) ? lowtime : normaltime;
        }
    }

    public int getNumLandUnits() {
        return numLandUnits;
    }

    public void setNumLandUnits(int newNumLandUnits) {
        this.numLandUnits = newNumLandUnits;
    }

    public int getTurnTime() {
        return  turnTime;
    }

    // Basic comparison based on food
    public  int compareTo(Player other) {
        return this.getScore() - other.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player player = (Player) o;
            return (this.name.equals(player.name));
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int hashNum = 31;
        int result = name.hashCode();
        result = hashNum * result + race.hashCode();
        result = hashNum * result + color.hashCode();
        result = hashNum * result + (mule != null ? mule.hashCode() : 0);
        result = hashNum * result + (hasMule ? 1 : 0);
        result = hashNum * result + food;
        result = hashNum * result + money;
        result = hashNum * result + energy;
        result = hashNum * result + crystite;
        result = hashNum * result + ore;
        result = hashNum * result + score;
        result = hashNum * result + numLandUnits;
        result = hashNum * result + turnTime;
        return result;
    }
}

