package view;

import common.Constants;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WelcomeScreen {
    private int width;
    private int height;
    private Button welcomeStartGameButton;

    public WelcomeScreen() {
        this.width = Constants.SCREEN_WIDTH;
        this.height = Constants.SCREEN_HEIGHT;
        welcomeStartGameButton = new Button("Start");
    }

    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        welcomeStartGameButton = new Button("Start");
    }

    public Scene getScene() {
        // welcome message
        Text welcomeMessageText = new Text("Welcome to Tower Defence Game!");
        welcomeMessageText.getStyleClass().add("welcome-message-text");

        // start button
        welcomeStartGameButton = new Button("Start");
        welcomeStartGameButton.getStyleClass().add("welcome-start-button");

        // Welcome Scene element: vbox
        VBox welcomeBox = new VBox();
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.getStyleClass().add("welcome-box");
        welcomeBox.getChildren().addAll(welcomeMessageText, welcomeStartGameButton);

        // create scene
        Scene scene = new Scene(welcomeBox, width, height);
        welcomeBox.requestFocus();
        scene.getStylesheets().add("file:resources/css/WelcomeScreen.css");

        return scene;
    }

    public Button getWelcomeStartGameButton() {
        return welcomeStartGameButton;
    }
}
