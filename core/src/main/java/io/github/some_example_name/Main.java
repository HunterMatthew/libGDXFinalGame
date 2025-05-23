package io.github.some_example_name;

import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.internal.org.commonmark.node.Block;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {

    private Viewport viewport;
    private SpriteBatch spriteBatch;

    //private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private String backgroundDir;

    private GameUI gameUI;

    /*      --CHANGING LOGIC--
    private BlockObject stone;
    private BlockObject iron;
    private BlockObject gold;
    private BlockObject diamond;
    private BlockObject obsidian;
     */

    private BlockObject block1, block2, block3, block4, block5;     // max 5?

    private Pickaxe pickaxe;


    private ArrayList<BlockObject> blockList;





    @Override
    public void create() {
        // Prepare your application here.

        viewport = new FitViewport(1280, 720);
        spriteBatch = new SpriteBatch();

        pickaxe = new Pickaxe();
        blockList = new ArrayList<>();

        gameUI = new GameUI(viewport, spriteBatch, pickaxe, blockList);


        backgroundDir = "minecraftDirtBackground.jpg";
        //backgroundTexture = new Texture(backgroundDir);
        backgroundSprite = new Sprite(new Texture(backgroundDir));
        backgroundSprite.setSize(1280,720);



        /*
                --CHANGING LOGIC--

        stone = new BlockObject();      // empty for tier 0 "stone"
        iron = new BlockObject(1);      // 1 for tier iron etc...
        gold = new BlockObject(2);
        diamond = new BlockObject(3);
        obsidian = new BlockObject(4);


        blockList.add(stone);
        blockList.add(iron);
        blockList.add(gold);
        blockList.add(diamond);
        blockList.add(obsidian);


        blockList.add(stone);
        blockList.add(iron);

        */

        block1 = new BlockObject(pickaxe.getMaximumBreakableTier());
        block2 = new BlockObject(pickaxe.getMaximumBreakableTier());

        blockList.add(block1);
        blockList.add(block2);





        for (BlockObject b : blockList)
        {
            System.out.println(b.toString());
        }

        /*
                --CHANGING LOGIC--

        stone.randomPos(viewport, blockList);
        iron.randomPos(viewport, blockList);
        gold.randomPos(viewport, blockList);
        diamond.randomPos(viewport, blockList);
        obsidian.randomPos(viewport, blockList);

         */

        block1.randomPos(viewport, blockList);
        block2.randomPos(viewport, blockList);



    }

    @Override
    public void resize(int width, int height) {
        // Resize your application here. The parameters represent the new window size.
        viewport.update(width, height, true);

    }

    @Override
    public void render() {
        // Draw your application here.

        try {

            ScreenUtils.clear(Color.FOREST);

            viewport.apply();
            spriteBatch.begin();

            backgroundSprite.draw(spriteBatch);

            for (BlockObject b : blockList)     // loops through every time
            {
                b.getBlockSprite().draw(spriteBatch);   // draws sprite
                clickDetector(viewport, b, gameUI, pickaxe);     // detects click on every block onscreen
            }




            spriteBatch.end();

            gameUI.render(Gdx.graphics.getDeltaTime());     // AFTER SPRITEBATCH END, crashes

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }

    public void clickDetector(Viewport viewport, BlockObject b, GameUI gameUI, Pickaxe p)
    {

        try {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());     // Vector2 holds x and y
            viewport.unproject(touchPos);    // unproject converts screen pos to world pos (400px x320px -> 2f x1.7f ex)

            Rectangle blockBounding = b.getBlockSprite().getBoundingRectangle();    // bounds of sprite

            if (Gdx.input.justTouched())        // if clicked anywhere
            {
                //System.out.println("pickaxe.getDmg() = " + p.getDmg());   debug


                // if click contains bounds of block, and the block tier is less than or equal to the pick tier + 1
                if (blockBounding.contains(touchPos) && (b.getTier() <= pickaxe.getTierInt() + 1))
                {
                    b.setHealth(b.getHealth() - p.getDmg());     // -1 health

                    if (b.getHealth() <= 0)     // if out of health
                    {

                        switch (b.getTier())    // make own method?
                        {
                            case 0:
                                gameUI.setStoneCount(gameUI.getStoneCount() + 1);
                                break;

                            case 1:
                                gameUI.setIronCount(gameUI.getIronCount() + 1);
                                break;
                            case 2:
                                gameUI.setGoldCount(gameUI.getGoldCount() + 1);
                                break;
                            case 3:
                                gameUI.setDiamondCount(gameUI.getDiamondCount() + 1);
                                break;
                            case 4:
                                gameUI.setObsidianCount(gameUI.getObsidianCount() + 1);
                                break;
                        }

                        gameUI.updateLabels(pickaxe);

                        b.resetHealth(b);       // resets health
                        randomBlockResetter(b);

                        b.randomPos(viewport, blockList);       // random pos
                    }
                } else {
                    // do nothing
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void randomBlockResetter(BlockObject b)
    {
        pickaxe.setMaximumBreakableTier();

        b.tierSetter(MathUtils.random(pickaxe.getMaximumBreakableTier()), b);
    }

    public Pickaxe getPickaxe() {
        return pickaxe;
    }

    public ArrayList<BlockObject> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<BlockObject> blockList) {
        this.blockList = blockList;
    }
}
