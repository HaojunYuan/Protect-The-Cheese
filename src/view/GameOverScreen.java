package view;

import common.Constants;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameOverScreen {
    private int width;
    private int height;
    private Button gameOverButton;

    public GameOverScreen() {
        this.width = Constants.SCREEN_WIDTH;
        this.height = Constants.SCREEN_HEIGHT;
        gameOverButton = new Button("New Game");
    }

    public GameOverScreen(int width, int height) {
        this.width = width;
        this.height = height;
        gameOverButton = new Button("New Game");
    }

    public Scene getScene() {
        // game over message
        Text gameOverText = new Text("You Lose!");
        gameOverText.getStyleClass().add("gameOver-message-text");

        Text monstersKilled = new Text("You defeated 0 enemies! Oh no!");
        gameOverText.getStyleClass().add("gameOver-message-text");

        Text damageDealt = new Text("You dealt 0 damage! Oh no!");
        gameOverText.getStyleClass().add("gameOver-message-text");

        Text timeSpent = new Text("You played for 10 seconds!");
        gameOverText.getStyleClass().add("gameOver-message-text");
        // start button
        gameOverButton = new Button("New Game");
        gameOverButton.getStyleClass().add("gameOver-start-button");

        // game over element: vbox
        VBox gameOverBox = new VBox();
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverBox.getStyleClass().add("gameOver-box");
        gameOverBox.getChildren().addAll(gameOverText, monstersKilled,
                damageDealt, timeSpent, gameOverButton);

        // create scene
        Scene scene = new Scene(gameOverBox, width, height);
        gameOverBox.requestFocus();
        scene.getStylesheets().add("file:resources/css/WelcomeScreen.css");
        return scene;
    }

    public Button getGameOverButton() {
        return gameOverButton;
    }
}

