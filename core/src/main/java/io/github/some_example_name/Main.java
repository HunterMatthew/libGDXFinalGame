package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {

    private Viewport viewport;
    private SpriteBatch spriteBatch;

    //private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private String backgroundDir;

    private BlockObject testingBlock;


    @Override
    public void create() {
        // Prepare your application here.

        viewport = new FitViewport(1280, 720);
        spriteBatch = new SpriteBatch();

        backgroundDir = "minecraftDirtBackground.jpg";
        //backgroundTexture = new Texture(backgroundDir);
        backgroundSprite = new Sprite(new Texture(backgroundDir));
        backgroundSprite.setSize(1280,720);

        testingBlock = new BlockObject(0);       // !! change this !!
        testingBlock.getBlockSprite().setSize(128, 128);

        System.out.println(testingBlock.toString());




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

            testingBlock.getBlockSprite().draw(spriteBatch);

            spriteBatch.end();

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
}
