package io.github.some_example_name;

public class Pickaxe {

    private int tier;
    private int dmg;


    public Pickaxe()
    {
        pickTierSetter(0);
    }

    public Pickaxe(int tier)
    {
        pickTierSetter(tier);
    }

    public void pickTierSetter(int tier)
    {
        switch(tier)
        {
            case 0:     // wood
                setTier(0);
                setDmg(1);  // how many hits it does per click
                break;
            case 1:     // stone
                setTier(1);
                setDmg(3);
                break;
            case 2:     // iron
                setTier(2);
                setDmg(5);
                break;
        }
    }


    public int getDmg() {
        return dmg;
    }

    private void setDmg(int dmg) {      // never should need to use this out of this class
        this.dmg = dmg;                // use setTier() instead
    }

    public int getTierInt() {
        return tier;
    }

    public String getTierString(int tier) {
        String tierString = "";
        switch(tier)
        {
            case 0:     // wood
                tierString = "wood";
                break;
            case 1:     // stone
                tierString = "stone";
                break;
            case 2:     // iron
                tierString = "iron";
                break;

            default:
                tierString = "MAX UPGRADES";
                break;
        }

        return tierString;

    }

    public void setTier(int tier) {
        this.tier = tier;
    }

}
