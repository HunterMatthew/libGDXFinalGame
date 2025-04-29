package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BlockObject {

    private int health;      // how many clicks

    private String blockDir;
    private Sprite blockSprite;     // picture of requested block


    private int tier;       // sets image and health

    public BlockObject(int tier)
    {
        tierSetter(tier, this);
    }

    public BlockObject()
    {
        tierSetter(0, this);
    }


    public BlockObject tierSetter(int tier, BlockObject block)
    {
        switch(tier) {
            case 0:     // stone
                this.setTier(0);
                this.health = 3;    // 3 clicks
                this.blockDir = "Minecraft-Stone-Block.jpg";
                this.blockSprite = new Sprite(new Texture(blockDir));
                break;
            case 1:     // iron
                this.setTier(1);
                this.health = 5;    // 5 clicks
                this.blockDir = "iron ore block.png";
                this.blockSprite = new Sprite(new Texture(blockDir));
                break;
        }
    return block;
    }


    public String toString()
    {
        return ("Block Health : " + health +
                "\nBlock Dir : " + getBlockDir() +
                "\nBlock Teir : " + getTier() );
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Sprite getBlockSprite() {
        return blockSprite;
    }

    public void setBlockSprite(Sprite blockSprite) {
        this.blockSprite = blockSprite;
    }

    public String getBlockDir() {
        return blockDir;
    }

    public void setBlockDir(String blockDir) {
        this.blockDir = blockDir;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
