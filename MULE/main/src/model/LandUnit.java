package model;

/**
 * Created by anirudh on 10/1/15.
 */
public abstract class LandUnit {

    public static final int UNIT_COST = 300;

    private boolean isSold;
    private String ownerName;
    private Mule mule;
    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player newOwner) {
        this.owner = newOwner;
    }

    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule newMule) {
        this.mule = newMule;
    }

    public boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(boolean newIsSold) {
        this.isSold = newIsSold;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String newOwnerName) {
        this.ownerName = newOwnerName;
    }

    public int getCost() {
        return UNIT_COST;
    }
    public abstract int getFoodProduced();
    public abstract int getOreProduced();
    public abstract int getEnergyProduced();
    public void muleProduction() {
        if (mule != null && this.getOwner().getEnergy() > 0) {
            if (this.getMule().getType() == Mule.Type.FOOD) {
                this.getOwner().setFood(this.getOwner().getFood() + this.getFoodProduced());
            }
            if (this.getMule().getType() == Mule.Type.ENERGY) {
                this.getOwner().setEnergy(this.getOwner().getEnergy() + this.getEnergyProduced());
            }
            if (this.getMule().getType() == Mule.Type.ORE) {
                this.getOwner().setOre(this.getOwner().getOre() + this.getOreProduced());
            }
            this.getOwner().setEnergy(this.getOwner().getEnergy() - 1);
        }
    }
}
