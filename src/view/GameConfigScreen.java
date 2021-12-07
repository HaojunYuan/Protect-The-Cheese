package view;

import common.Constants;
import common.enums.Levels;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GameConfigScreen {
    private int width;
    private int height;
    private Button playButton;
    private TextField userNameInputField;
    private ComboBox<Levels> difficultyLevelDropDown;

    public GameConfigScreen() {
        this.width = Constants.SCREEN_WIDTH;
        this.height = Constants.SCREEN_HEIGHT;
    }

    public GameConfigScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getScene() {
        userNameInputField = new TextField();
        userNameInputField.setId("game-config-screen-username-input-field");
        userNameInputField.setMaxSize(250, 250);
        userNameInputField.setPromptText("Name...");

        playButton = new Button("Let's start!!");
        playButton.setId("game-config-screen-play-button");

        difficultyLevelDropDown = new ComboBox<>();
        difficultyLevelDropDown.setPromptText("Please select difficulty");
        difficultyLevelDropDown.setId("game-config-screen-difficulty-level-dropdown");
        difficultyLevelDropDown.getItems().addAll(Levels.EASY, Levels.MEDIUM, Levels.HARD);

        // Add Contents to the game config screen
        VBox userConfigBox = new VBox();
        userConfigBox.setAlignment(Pos.CENTER);
        userConfigBox.setId("game-config-screen-user-config-box");
        userConfigBox.getStyleClass().add("user-config-box");

        userConfigBox.getChildren().addAll(userNameInputField, difficultyLevelDropDown, playButton);
        Scene scene = new Scene(userConfigBox, width, height);
        userConfigBox.requestFocus();
        scene.getStylesheets().add("file:resources/css/GameConfigScreen.css");

        return scene;
    }

    public TextField getUserNameInputField() {
        return userNameInputField;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public ComboBox<Levels> getDifficultyLevelDropDown() {
        return difficultyLevelDropDown;
    }
}
