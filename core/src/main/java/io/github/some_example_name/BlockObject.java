package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.internal.org.commonmark.node.Block;

import java.util.ArrayList;

public class BlockObject {

    private int health;      // how many clicks
    private int maxHealth;       // for resetting health, different for each block

    private String blockDir;
    private Sprite blockSprite;     // picture of requested block


    private int tier;       // sets image and health

    //public BlockObject(int tier)
    {
        tierSetter(tier, this);
    }

    public BlockObject()
    {
        tierSetter(0, this);
    }

    public BlockObject(int maximumBreakableTier)        // generates random block u can break
    {
        tierSetter(MathUtils.random(maximumBreakableTier), this);
    }


    public BlockObject tierSetter(int tier, BlockObject block)
    {
        switch(tier) {
            case 0:     // stone
                this.setTier(0);

                this.maxHealth = 5;    // 3 clicks
                this.health = maxHealth;

                this.blockDir = "blockImages/Minecraft-Stone-Block.jpg";
                this.blockSprite = new Sprite(new Texture(blockDir));
                this.blockSprite.setSize(128, 128);
                break;

            case 1:     // iron
                this.setTier(1);

                this.maxHealth = 10;    // 5 clicks
                this.health = maxHealth;

                this.blockDir = "blockImages/iron ore block.png";
                this.blockSprite = new Sprite(new Texture(blockDir));
                this.blockSprite.setSize(128, 128);
                break;

            case 2:     // gold
                this.setTier(2);

                this.maxHealth = 20;
                this.health = maxHealth;

                this.blockDir = "blockImages/Minecraft-Gold-Ore.jpg";
                this.blockSprite = new Sprite(new Texture(blockDir));
                this.blockSprite.setSize(128, 128);
                break;

            case 3:
                this.setTier(3);

                this.maxHealth = 30;
                this.health = maxHealth;

                this.blockDir = "blockImages/diamond ore.png";
                this.blockSprite = new Sprite(new Texture(blockDir));
                this.blockSprite.setSize(128, 128);
                break;

            case 4:
                this.setTier(4);

                this.maxHealth = 60;
                this.health = maxHealth;

                this.blockDir = "blockImages/Minecraft-Obsidian-Block.jpg";
                this.blockSprite = new Sprite(new Texture(blockDir));
                this.blockSprite.setSize(128, 128);
                break;
        }
    return block;
    }


    public void randomPos(Viewport viewport, ArrayList<BlockObject> blockList)
    {
        float maxX = viewport.getWorldWidth() - 138;    // 128 for image, extra 10 for grass on top of background
        float maxY = viewport.getWorldHeight() - 128;   // doesn't need extra 10, no grass

        //getBlockSprite().setPosition(MathUtils.random(maxX), MathUtils.random(maxY));  // sets pos

        while (true)
        {
            float x = MathUtils.random(maxX);
            float y = MathUtils.random(maxY);

            getBlockSprite().setPosition(x, y);

            Rectangle blockRectangle = getBlockSprite().getBoundingRectangle();
            boolean overlaps = false;        // for checking for overlapping blocks

            for (BlockObject block : blockList) {       // != this: excludes itself from list
                // .overlaps() libgdx, takes a rectangle argument
                // if it overlaps with any blocks bounding rectangle
                if (block != this && blockRectangle.overlaps(block.getBlockSprite().getBoundingRectangle())) {
                    overlaps = true;
                    break;
                }

            }
                if (!overlaps) {
                    break;
                }

        }

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
