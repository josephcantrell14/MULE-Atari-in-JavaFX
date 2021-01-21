package model;

/**
 * Created by anirudh on 9/22/15.
 */
public class Mule {
    public static enum Type {FOOD, ENERGY, ORE, CRYSTITE}

    private Type type;
    private String ownerName;
    private Player owner;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String newOwnerName) {
        this.ownerName = newOwnerName;
    }

    public void setType(Mule.Type newType) {
        this.type = newType;
    }

    public Mule.Type getType() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player newOwner) {
        this.owner = newOwner;
    }


}
