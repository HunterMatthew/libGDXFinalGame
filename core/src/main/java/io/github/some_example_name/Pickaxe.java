package io.github.some_example_name;

public class Pickaxe {

    private int tier;
    private int dmg;

    private int maximumBreakableTier;


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
            case 3:
                setTier(3);
                setDmg(7);
                break;
            case 4:
                setTier(4);
                setDmg(10);
                break;
            case 5:
                setTier(5);
                setDmg(20);
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
            case 3:
                tierString = "gold";
                break;
            case 4:
                tierString = "diamond";
                break;
            case 5:
                tierString = "obsidian";
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

    public int getMaximumBreakableTier() {
        return maximumBreakableTier;
    }

    public void setMaximumBreakableTier()       // custom
    {
        this.maximumBreakableTier = getTierInt() + 1;
    }

}
