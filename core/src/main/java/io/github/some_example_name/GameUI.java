package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;


//      I absolutely hate making ui's
public class GameUI
{

    private Stage stage;        // holds ui
    private Skin skin;      // assets / images

    private Table table;

    private Label cobblestoneLabel;
    private Label ironLabel;

    private Label pickTierLabel;

    private int stoneCount;
    private int ironCount;



    private TextButton menuButton;
    private Table menuTable;
    private boolean isMenuDisplayed;

    //private TextButton menuExitButton;
    private TextButton pickUpgrade;
    private int pickUpgradeCost;

    public GameUI(Viewport viewport, SpriteBatch spriteBatch, Pickaxe pickaxe)       // same as create()
    {
        stage = new Stage(viewport, spriteBatch);
        skin = new Skin(Gdx.files.internal("uiskin.json"));     // libGdx default skin for ui


        table = new Table();        // stupid table layout like Swing
        table.setFillParent(true);      // expands table to entire viewport / stage
        table.top();     // sets alignment to top
        table.left();   // top-left

        menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.top();
        menuTable.left();
        menuTable.setVisible(false);


        menuButton = new TextButton("Menu", skin);
        //menuExitButton = new TextButton("X", skin);
        pickUpgrade = new TextButton(pickaxeUpgradeString(pickaxe), skin);


        //menuTable.add(menuExitButton).expandX().right().pad(10);    // expand x adds a column

        menuTable.top().left().padTop(100).padLeft(10);     // looks better, finicky
        menuTable.add(pickUpgrade);


        stage.addActor(menuTable);


        cobblestoneLabel = new Label("Cobblestone : " + getStoneCount(), skin);    // text label,
        ironLabel = new Label("Iron : " + getIronCount(), skin);                   // takes in string and skin
        pickTierLabel = new Label("Pickaxe Tier : " + pickaxe.getTierString(pickaxe.getTierInt()), skin);



        table.add(menuButton).pad(10);      // menu first
        table.add(cobblestoneLabel).pad(10);    // add them by the tiers
        table.add(ironLabel).pad(10);
        table.add(pickTierLabel).expandX().right().pad(10);



        stage.addActor(table);      // actor is a game object


        Gdx.input.setInputProcessor(stage);     // sends all inputs to the stage for handling


        menuButton.addListener(new ClickListener()  // when menuButton is clicked
        {
            @Override
            public void clicked(InputEvent event, float x, float y)     // base click handler for Scene2D
            {
                //System.out.println("CLICKED!!!");     debug
                isMenuDisplayed = !isMenuDisplayed;
                if (isMenuDisplayed)
                {
                    menuButton.setText("EXIT");
                }
                else
                {
                    menuButton.setText("MENU");
                }
                menuTable.setVisible(isMenuDisplayed);
            }

        });

        pickUpgrade.addListener(new ClickListener()  // when menuButton is clicked
        {
            @Override
            public void clicked(InputEvent event, float x, float y)     // base click handler for Scene2D
            {

                switch(pickaxe.getTierInt() + 1)
                {
                    case 1:     // stone
                        stoneCount = pickUpgrader(pickaxe, stoneCount, 3);
                        updateLabels(pickaxe);
                        break;

                    case 2:     // iron
                        ironCount = pickUpgrader(pickaxe, ironCount, 3);
                        updateLabels(pickaxe);
                        break;
                    default:
                        // add more to this
                        updateLabels(pickaxe);
                        break;
                }


                pickUpgrade.setChecked(false);


            }

        });


    }


    public int pickUpgrader(Pickaxe pickaxe, int oreCount, int upgradeCost)
    {
        if (oreCount >= upgradeCost) {

            pickaxe.pickTierSetter(pickaxe.getTierInt() + 1);
            pickUpgrade.setText(pickaxeUpgradeString(pickaxe));

            oreCount -= upgradeCost;

        }
        return oreCount;

    }


    public void updateLabels(Pickaxe pickaxe)
    {
        cobblestoneLabel.setText("Cobblestone : " + getStoneCount());
        ironLabel.setText("Iron : " + getIronCount());

        pickTierLabel.setText("PICKAXE TIER : " + pickaxe.getTierString(pickaxe.getTierInt()));
    }

    public String pickaxeUpgradeString(Pickaxe pickaxe)
    {
        return "UPGRADE : " + pickaxe.getTierString(pickaxe.getTierInt() + 1)
                    +   "\nCOST : 3x " + (pickaxe.getTierString(pickaxe.getTierInt() + 1).toUpperCase());
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
