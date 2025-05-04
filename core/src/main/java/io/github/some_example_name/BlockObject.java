package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BlockObject {

    private int health;      // how many clicks
    private int maxHealth;       // for resetting health, different for each block

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

                this.maxHealth = 3;    // 3 clicks
                this.health = maxHealth;

                this.blockDir = "Minecraft-Stone-Block.jpg";
                this.blockSprite = new Sprite(new Texture(blockDir));
                this.blockSprite.setSize(128, 128);
                break;
            case 1:     // iron
                this.setTier(1);

                this.maxHealth = 5;    // 5 clicks
                this.health = maxHealth;

                this.blockDir = "iron ore block.png";
                this.blockSprite = new Sprite(new Texture(blockDir));
                this.blockSprite.setSize(128, 128);
                break;
        }
    return block;
    }


    public void randomPos(Viewport viewport, BlockObject b)
    {
        float maxX = viewport.getWorldWidth() - 138;    // 128 for image, extra 10 for grass on top of background
        float maxY = viewport.getWorldHeight() - 128;   // doesn't need extra 10, no grass

        b.getBlockSprite().setPosition(MathUtils.random(maxX), MathUtils.random(maxY));  // sets pos

    }

    public void resetHealth(BlockObject b)
    {
        b.setHealth(b.getMaxHealth());
    }



    public String toString()
    {
        return ("Block Health : " + health +
                "\nBlock Dir : " + getBlockDir() +
                "\nBlock Teir : " + getTier() + "\n" );
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
