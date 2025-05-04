package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.Viewport;


//      I absolutely hate making ui's
public class GameUI
{

    private Stage stage;        // holds ui
    private Skin skin;      // assets / images

    private Table table;

    private Label cobblestoneLabel;
    private Label ironLabel;

    private int stoneCount;
    private int ironCount;

    public GameUI(Viewport viewport, SpriteBatch spriteBatch)       // same as create()
    {
        stage = new Stage(viewport, spriteBatch);
        skin = new Skin(Gdx.files.internal("uiskin.json"));     // libGdx default skin for ui

        table = new Table();        // stupid table layout like Swing
        table.setFillParent(true);      // expands table to entire viewport / stage
        table.bottom();     // sets alignment to bottom

        cobblestoneLabel = new Label("Cobblestone : " + getStoneCount(), skin);    // text label,
        ironLabel = new Label("Iron : " + getIronCount(), skin);                   // takes in string and skin

        table.add(cobblestoneLabel).pad(10);    // extends label 10px;
        table.add(ironLabel).pad(10);

        stage.addActor(table);      // actor is a game object

        Gdx.input.setInputProcessor(stage);     // sends all inputs to the stage for handling


    }

    public void updateLabels()
    {
        cobblestoneLabel.setText("Cobblestone : " + getStoneCount());
        ironLabel.setText("Iron : " + getIronCount());
    }



    public void render(float delta)     // delta is the time since last frame
    {
        stage.act(delta);       // act is rendering for the stage, updates logic etc.
        stage.draw();

    }

    public int getStoneCount() {
        return stoneCount;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    public int getIronCount() {
        return ironCount;
    }

    public void setIronCount(int ironCount) {
        this.ironCount = ironCount;
    }

    public Stage getStage() {       // no set, feel like it would make it complicated
        return stage;
    }

}
