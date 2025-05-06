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

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {

    private Viewport viewport;
    private SpriteBatch spriteBatch;

    //private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private String backgroundDir;

    private GameUI gameUI;

    private BlockObject stone;
    private BlockObject iron;

    private Pickaxe pickaxe;

    private ArrayList<BlockObject> blockList;





    @Override
    public void create() {
        // Prepare your application here.

        viewport = new FitViewport(1280, 720);
        spriteBatch = new SpriteBatch();

        pickaxe = new Pickaxe();

        gameUI = new GameUI(viewport, spriteBatch, pickaxe);

        backgroundDir = "minecraftDirtBackground.jpg";
        //backgroundTexture = new Texture(backgroundDir);
        backgroundSprite = new Sprite(new Texture(backgroundDir));
        backgroundSprite.setSize(1280,720);

        blockList = new ArrayList<>();

        stone = new BlockObject();      // empty for tier 0 "stone"
        iron = new BlockObject(1);      // 1 for tier iron


        blockList.add(stone);
        blockList.add(iron);

        for (BlockObject b : blockList)
        {
            System.out.println(b.toString());
        }

        stone.randomPos(viewport, this.stone);
        iron.randomPos(viewport, this.iron);




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


                if (blockBounding.contains(touchPos))       // if clicked in bounds of any block
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
                        }

                        gameUI.updateLabels(pickaxe);

                        b.resetHealth(b);       // resets health

                        b.randomPos(viewport, b);       // random pos
                    }
                } else {
                    // !! EDIT THIS !!
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Pickaxe getPickaxe() {
        return pickaxe;
    }

}
