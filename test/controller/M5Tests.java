package controller;

import common.enums.Levels;
import common.enums.Tower;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * test functionality of the newly implemented features
 * 1. should have the cost of tower based on the selected difficulty
 * 2. select a tower from tower menu
 * 3. purchase and place the selected tower
 * 4. purchased tower and its state should be stored in the system
 * 5. purchased tower should show its label
 */
public class M5Tests extends ApplicationTest {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController();
        stage = primaryStage;
        gameController.start(primaryStage);
    }

    /**
     * Author: Sha Wei
     * <p>
     * Test:
     * should have the cost of tower based on the selected difficulty (easy)
     * select each tower from tower menu
     */
    @Test
    public void testGoToGameScreenWithLevelEasyAndShouldHaveTowerCostForTheLevel()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.EASY.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 1500", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1500", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-1");
        clickOn(vertex);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_TINY.getName());
        // check the cost and health of the selected tower
        verifyThat("health: 100", NodeMatchers.isVisible());
        verifyThat("cost: 30", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_TINY.getName());
        clickOn(Tower.TOWER_REGULAR.getName());
        verifyThat("health: 200", NodeMatchers.isVisible());
        verifyThat("cost: 40", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_REGULAR.getName());
        clickOn(Tower.TOWER_LARGE.getName());
        verifyThat("health: 300", NodeMatchers.isVisible());
        verifyThat("cost: 50", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_LARGE.getName());
        clickOn(Tower.TOWER_SUPER.getName());
        verifyThat("health: 400", NodeMatchers.isVisible());
        verifyThat("cost: 60", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_SUPER.getName());
        clickOn(Tower.TOWER_MEGA.getName());
        verifyThat("health: 500", NodeMatchers.isVisible());
        verifyThat("cost: 70", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }

    /**
     * Author: Haoran Zhao
     * <p>
     * Test:
     * should have the cost of tower based on the selected difficulty (medium)
     * select each tower from tower menu
     */
    @Test
    public void testGoToGameScreenWithLevelMediumAndShouldHaveTowerCostForTheLevel()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.MEDIUM.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 1000", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-2");
        clickOn(vertex);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_TINY.getName());
        // check the cost and health of the selected tower
        verifyThat("health: 100", NodeMatchers.isVisible());
        verifyThat("cost: 60", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_TINY.getName());
        clickOn(Tower.TOWER_REGULAR.getName());
        verifyThat("health: 200", NodeMatchers.isVisible());
        verifyThat("cost: 80", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_REGULAR.getName());
        clickOn(Tower.TOWER_LARGE.getName());
        verifyThat("health: 300", NodeMatchers.isVisible());
        verifyThat("cost: 100", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_LARGE.getName());
        clickOn(Tower.TOWER_SUPER.getName());
        verifyThat("health: 400", NodeMatchers.isVisible());
        verifyThat("cost: 120", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_SUPER.getName());
        clickOn(Tower.TOWER_MEGA.getName());
        verifyThat("health: 500", NodeMatchers.isVisible());
        verifyThat("cost: 140", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }

    /**
     * Author: Sha Wei
     * <p>
     * Test:
     * should have the cost of tower based on the selected difficulty (medium)
     * select each tower from tower menu
     */
    @Test
    public void testGoToGameScreenWithLevelHardAndShouldHaveTowerCostForTheLevel()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.HARD.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 500", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 500", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-3");
        clickOn(vertex);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_TINY.getName());
        // check the cost and health of the selected tower
        verifyThat("health: 100", NodeMatchers.isVisible());
        verifyThat("cost: 90", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_TINY.getName());
        clickOn(Tower.TOWER_REGULAR.getName());
        verifyThat("health: 200", NodeMatchers.isVisible());
        verifyThat("cost: 120", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_REGULAR.getName());
        clickOn(Tower.TOWER_LARGE.getName());
        verifyThat("health: 300", NodeMatchers.isVisible());
        verifyThat("cost: 150", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_LARGE.getName());
        clickOn(Tower.TOWER_SUPER.getName());
        verifyThat("health: 400", NodeMatchers.isVisible());
        verifyThat("cost: 180", NodeMatchers.isVisible());

        // next tower
        clickOn(Tower.TOWER_SUPER.getName());
        clickOn(Tower.TOWER_MEGA.getName());
        verifyThat("health: 500", NodeMatchers.isVisible());
        verifyThat("cost: 210", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }

    /**
     * Author:Yeqiao Yu
     * <p>
     * Test:
     * 1. select any mode
     * 2. purchase a Regular Tower
     * 3. reduce total money
     */
    @Test
    public void testGoToGameScreenPurchaseRegularTowerReduceMoney()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.HARD.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 500", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 500", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex1 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-5");
        clickOn(vertex1);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_REGULAR.getName());

        // purchase the selected tower
        clickOn("purchase");
        verifyThat("Your Money: 380", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 500", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }

    /**
     * Author: Yeqiao Yu
     * <p>
     * Test:
     * 1. select any mode
     * 2. purchase a Large Tower
     * 3. reduce total money
     */
    @Test
    public void testGoToGameScreenPurchaseLargeTowerReduceMoney()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.MEDIUM.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 1000", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex1 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-5");
        clickOn(vertex1);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_LARGE.getName());

        // purchase the selected tower
        clickOn("purchase");
        verifyThat("Your Money: 900", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }

    /**
     * Author:Haojun Yuan
     * <p>
     * Test:
     * 1. select any mode
     * 2. purchase a Super Tower
     * 3. reduce total money
     * 4. test start combat button
     */
    @Test
    public void testGoToGameScreenPurchaseSuperTowerReduceMoney()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.MEDIUM.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 1000", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex1 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-5");
        clickOn(vertex1);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_SUPER.getName());

        // purchase the selected tower
        clickOn("purchase");
        verifyThat("Your Money: 880", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }

    /**
     * Author: Haojun Yuan
     * <p>
     * Test:
     * 1. select any mode
     * 2. purchase a Mega Tower
     * 3. reduce total money
     * 4. test start combat button
     */
    @Test
    public void testGoToGameScreenPurchaseMegaTowerReduceMoney()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.MEDIUM.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 1000", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex1 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-5");
        clickOn(vertex1);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_MEGA.getName());

        // purchase the selected tower
        clickOn("purchase");
        verifyThat("Your Money: 860", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }


    /**
     * Author: Huajie Zhang
     * <p>
     * Test:
     * 1. select hard mode
     * 2. purchase and place the selected towers
     * 3. test start combat button
     */
    @Test
    public void testGoToGameScreenWithHardPurchasePlaceTowers()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.HARD.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 500", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 500", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex1 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-1");
        clickOn(vertex1);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_TINY.getName());

        // purchase the selected tower
        clickOn("purchase");

        // find next tower vertex
        Pane vertex2 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-2");
        clickOn(vertex2);

        // select a tower
        clickOn(Tower.TOWER_TINY.getName());
        clickOn(Tower.TOWER_REGULAR.getName());

        // purchase the selected tower
        clickOn("purchase");

        // find next tower vertex
        Pane vertex3 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-3");
        clickOn(vertex3);

        // select a tower
        clickOn(Tower.TOWER_REGULAR.getName());
        clickOn(Tower.TOWER_LARGE.getName());

        // purchase the selected tower
        clickOn("purchase");

        // find next tower vertex
        Pane vertex4 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-4");
        clickOn(vertex4);

        // select a tower
        clickOn(Tower.TOWER_LARGE.getName());
        clickOn(Tower.TOWER_SUPER.getName());

        // purchase the selected tower
        clickOn("purchase");

        // find next tower vertex
        Pane vertex5 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-5");
        clickOn(vertex5);

        // select a tower
        clickOn(Tower.TOWER_SUPER.getName());
        clickOn(Tower.TOWER_MEGA.getName());

        // purchase the selected tower
        clickOn("purchase");
        clickOn("start combat");
        Thread.sleep(5000);
    }

    /**
     * Author: Huajie Zhang
     * <p>
     * Test:
     * 1. select any mode
     * 2. purchase a Tiny Tower
     * 3. reduce total money
     * 4. test start combat button
     */
    @Test
    public void testGoToGameScreenPurchaseTinyTowerReduceMoney()
            throws InterruptedException {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.EASY.name());
        clickOn("Let's start!!");
        verifyThat("Your Money: 1500", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1500", NodeMatchers.isVisible());

        // click a tower vertex
        Pane vertex1 = (Pane) stage.getScene().lookup("#game-screen-tower-vertex-id-5");
        clickOn(vertex1);

        // select a tower
        clickOn("Please select a tower");
        clickOn(Tower.TOWER_TINY.getName());

        // purchase the selected tower
        clickOn("purchase");
        verifyThat("Your Money: 1470", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1500", NodeMatchers.isVisible());
        clickOn("start combat");
        Thread.sleep(5000);
    }
}
