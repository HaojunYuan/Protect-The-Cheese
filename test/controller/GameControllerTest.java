package controller;

import common.enums.Levels;
import common.enums.Tower;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.ComboBoxMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class GameControllerTest extends ApplicationTest {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController();
        stage = primaryStage;
        gameController.start(primaryStage);
    }

    /**
     * Author: Sha Wei
     */
    @Test
    public void testStartButton() {
        clickOn("Start");
        verifyThat("Let's start!!", NodeMatchers.isVisible());
    }

    /**
     * Author: Sha Wei
     */
    @Test
    public void testThreeDifficultyLevelsExist() {
        clickOn("Start");
        clickOn("Please select difficulty");
        ComboBox<Levels> difficultyLevelDropDown = (ComboBox<Levels>) stage.getScene()
                .lookup("#game-config-screen-difficulty-level-dropdown");
        verifyThat(difficultyLevelDropDown, ComboBoxMatchers.hasItems(3));
    }

    /**
     * Author: Sha Wei
     */
    @Test
    public void testSelectDifficultyLevelEasy() {
        clickOn("Start");
        clickOn("Please select difficulty");
        clickOn(Levels.EASY.name());
        ComboBox<Levels> difficultyLevelDropDown = (ComboBox<Levels>) stage.getScene()
                .lookup("#game-config-screen-difficulty-level-dropdown");
        verifyThat(difficultyLevelDropDown, ComboBoxMatchers.hasSelectedItem(Levels.EASY));
    }

    /**
     * Author: Sha Wei
     */
    @Test
    public void testSelectDifficultyLevelMedium() {
        clickOn("Start");
        clickOn("Please select difficulty");
        clickOn(Levels.MEDIUM.name());
        ComboBox<Levels> difficultyLevelDropDown = (ComboBox<Levels>) stage.getScene()
                .lookup("#game-config-screen-difficulty-level-dropdown");
        verifyThat(difficultyLevelDropDown, ComboBoxMatchers.hasSelectedItem(Levels.MEDIUM));
    }

    /**
     * Author: Haojun Yuan
     */

    @Test
    public void testSelectDifficultyLevelHard() {
        clickOn("Start");
        clickOn("Please select difficulty");
        clickOn(Levels.HARD.name());
        ComboBox<Levels> difficultyLevelDropDown = (ComboBox<Levels>) stage.getScene()
                        .lookup("#game-config-screen-difficulty-level-dropdown");
        verifyThat(difficultyLevelDropDown, ComboBoxMatchers.hasSelectedItem(Levels.HARD));
    }

    /**
     * Author:Haojun Yuan
     */
    @Test
    public void testWarningMessageWhenOnlySelectEasy() {
        clickOn("Start");
        clickOn("Please select difficulty");
        clickOn(Levels.EASY.name());
        clickOn("Let's start!!");
        verifyThat("OK", NodeMatchers.isVisible());
    }

    /**
     * Author:Haoran Zhao
     */
    @Test
    public void testWarningMessageWhenOnlySelectMedium() {
        clickOn("Start");
        clickOn("Please select difficulty");
        clickOn(Levels.MEDIUM.name());
        clickOn("Let's start!!");
        verifyThat("OK", NodeMatchers.isVisible());
    }

    /**
     *Author: Haoran Zhao
     */
    @Test
    public void testWarningMessageWhenOnlySelectHard() {
        clickOn("Start");
        clickOn("Please select difficulty");
        clickOn(Levels.HARD.name());
        clickOn("Let's start!!");
        verifyThat("OK", NodeMatchers.isVisible());
    }

    /**
     *Author: Huajie Zhang
     */
    @Test
    public void testGoToGameScreenWithLevelMedium() {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.MEDIUM.name());
        clickOn("Let's start!!");
        verifyThat("Your Starting Money: 1000", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1000", NodeMatchers.isVisible());
    }

    /**
     * Author: Huajie Zhang
     */
    @Test
    public void testGoToGameScreenWithLevelHard() {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.HARD.name());
        clickOn("Let's start!!");
        verifyThat("Your Starting Money: 500", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 500", NodeMatchers.isVisible());
    }

    /**
     * Author: Yeqiao Yu
     */
    @Test
    public void testWarningMessageWhenOnlyUsername() {
        clickOn("Start");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Let's start!!");
        verifyThat("OK", NodeMatchers.isVisible());
    }

    /**
     *Author: Yeqiao Yu
     */
    @Test
    public void testGoToGameScreenWithLevelEasy() {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.EASY.name());
        clickOn("Let's start!!");
        verifyThat("Your Starting Money: 1500", NodeMatchers.isVisible());
        verifyThat("Your Monument Health: 1500", NodeMatchers.isVisible());
    }


    /**
     * Author:Haoran Zhao
     *
     * Test:
     * 1. select hard mode
     * 2. purchase and place the selected towers
     */
    @Test
    public void testGoToGameScreenWithHardAndPurchaseAndPlaceTowers() {
        clickOn("Start");
        clickOn("Name...");
        type(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D);
        clickOn("Please select difficulty");
        clickOn(Levels.HARD.name());
        clickOn("Let's start!!");
        verifyThat("Your Starting Money: 500", NodeMatchers.isVisible());
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
    }

}
