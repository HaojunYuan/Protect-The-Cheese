package view;

import common.MapGenerator;
import common.enums.Tower;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.DifficultyModel;
import model.MapModel;
import model.MonumentModel;
import model.PathModel;
import model.PositionModel;
import model.TowerSelectionMenuModel;

import java.util.ArrayList;
import java.util.List;

public class GameScreen {
    private int width;
    private int height;
    private DifficultyModel difficultyModel;
    private MonumentModel monumentModel;
    private MapModel map;
    private ComboBox<String> towerSelection;
    private TowerSelectionMenuModel towerSelectionMenu;
    private Button purchaseButton;
    private Button startCombatButton;
    private HBox headerContainer;
    private GridPane gamePane;
    private HBox towerMenu;
    private Text monumentMoney;
    private Text monumentHealth;
    private List<Circle> monsters;

    public GameScreen(int width, int height, DifficultyModel difficultyModel) {
        this.width = width;
        this.height = height;
        this.difficultyModel = difficultyModel;
        this.monumentModel = new MonumentModel(difficultyModel);
        this.towerSelection = new ComboBox();
        this.towerSelectionMenu = new TowerSelectionMenuModel();
        this.purchaseButton = new Button("purchase");
        this.startCombatButton = new Button("start combat");
        this.headerContainer = new HBox();
        gamePane = new GridPane();
        this.towerMenu = new HBox();
        towerMenu.setVisible(false);
        monsters = new ArrayList<>();
    }

    public Scene getScene() {
        VBox gameScreenBox = new VBox();
        gameScreenBox.setId("game-screen-game-screen-box-id");
        gameScreenBox.getStyleClass().add("game-screen-game-screen-box-id");
        // ****** Header Container ******
        headerContainer.prefWidthProperty().bind(gameScreenBox.widthProperty());
        headerContainer.prefHeightProperty().bind(gameScreenBox.heightProperty().multiply(0.15));
        // ****** Header Container - Info Bar ******
        VBox infoBar = new VBox();
        infoBar.setId("game-screen-info-bar-id");
        infoBar.getStyleClass().add("game-screen-info-bar");
        // add content to info bar
        monumentMoney = new Text("Your Money: " + monumentModel.getMoney());
        monumentHealth = new Text("Your Monument Health: " + monumentModel.getHealth());
        infoBar.getChildren().addAll(monumentMoney, monumentHealth);
        infoBar.prefWidthProperty().bind(headerContainer.widthProperty().multiply(0.70));
        infoBar.prefHeightProperty().bind(headerContainer.heightProperty());
        // ****** Header Container - Info Bar ******
        // ****** Header Container - start combat button ******
        startCombatButton.prefWidthProperty().bind(headerContainer.widthProperty().multiply(0.30));
        startCombatButton.prefHeightProperty().bind(headerContainer.heightProperty());
        // ****** Header Container - start combat button ******
        headerContainer.getChildren().addAll(infoBar, startCombatButton);
        // ****** Header Container ******
        // ************ Game Board Box ************
        HBox gameBoardBox = new HBox();
        gameBoardBox.setId("game-screen-game-board-box-id");
        gameBoardBox.getStyleClass().add("game-screen-game-board-box");
        gameBoardBox.prefWidthProperty().bind(gameScreenBox.widthProperty());
        gameBoardBox.prefHeightProperty().bind(gameScreenBox.heightProperty().multiply(0.85));
        // ****** Left Box ******
        VBox leftBox = new VBox();
        leftBox.setId("game-screen-left-box-id");
        leftBox.getStyleClass().add("game-screen-left-box");
        leftBox.prefWidthProperty().bind(gameBoardBox.widthProperty().multiply(0.15));
        leftBox.prefHeightProperty().bind(gameBoardBox.heightProperty());
        VBox monumentBox = new VBox();
        monumentBox.getChildren().addAll(new Text("a monument"));
        leftBox.getChildren().addAll(monumentBox);
        // ****** left Box ******
        // ****** Right Box ******
        VBox rightBox = new VBox();
        rightBox.setId("game-screen-right-box-id");
        rightBox.getStyleClass().add("game-screen-right-box");
        rightBox.prefWidthProperty().bind(gameBoardBox.widthProperty().multiply(0.85));
        rightBox.prefHeightProperty().bind(gameBoardBox.heightProperty());
        // ****** Right Box - game pane ******
        gamePane = new GridPane();
        gamePane.prefWidthProperty().bind(gameBoardBox.widthProperty());
        gamePane.prefHeightProperty().bind(gameBoardBox.heightProperty().multiply(0.70));
        map = MapGenerator.getMapById(1);
        List<PathModel> paths = map.getPaths();
        List<PathModel> towerPaths = map.getTowerPaths();
        int towerVertexId = 0;
        for (int y = 0; y < 30; y++) {
            for (int x = 0; x < 30; x++) {
                PositionModel currentPosition = new PositionModel(x, y);
                Pane vertex = new Pane();
                vertex.setMinSize(20, 20);
                vertex.setMaxSize(20, 20);
                gamePane.add(vertex, x, y);
                // generate path
                for (PathModel path : paths) {
                    if (path.getPositions().contains(currentPosition)) {
                        vertex.setStyle("-fx-background-color: green;");
                    }
                }
                // generate tower path
                for (PathModel towerPath : towerPaths) {
                    if (towerPath.getPositions().contains(currentPosition)) {
                        towerVertexId++;
                        vertex.setId("game-screen-tower-vertex-id-" + towerVertexId);
                        vertex.setStyle("-fx-background-color: grey;");
                        vertex.onMouseClickedProperty().set((event -> towerMenu.setVisible(true)));
                    }
                }
            }
        }

        // add a "circle" monster to game pane
        List<PositionModel> positions = map.getPaths().get(0).getPositions();
        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle(8.0f, Color.RED);
            circle.setVisible(false);
            circle.setId("monster-" + i);
            gamePane.add(
                    circle,
                    positions.get(positions.size() - 2).getPositionX(),
                    positions.get(positions.size() - 2).getPositionY()
            );
            monsters.add(circle);
        }
        // ****** Right Box - game pane ******

        // ****** Right Box - tower menu ******
        towerMenu.prefWidthProperty().bind(gameBoardBox.widthProperty());
        towerMenu.prefHeightProperty().bind(gameBoardBox.heightProperty().multiply(0.30));
        towerMenu.getStyleClass().add("game-screen-tower-menu");

        towerSelection.setPromptText("Please select a tower");
        towerSelection.getItems().addAll(
                Tower.TOWER_TINY.getName(),
                Tower.TOWER_REGULAR.getName(),
                Tower.TOWER_LARGE.getName(),
                Tower.TOWER_SUPER.getName(),
                Tower.TOWER_MEGA.getName()
        );

        VBox towerDetailsPart1 = new VBox();
        towerDetailsPart1.getChildren().addAll(
                towerSelectionMenu.getNameLabel(),
                towerSelectionMenu.getDescriptionLabel(),
                towerSelectionMenu.getCostLabel()
        );
        VBox towerDetailsPart2 = new VBox();
        towerDetailsPart2.getChildren().addAll(
                towerSelectionMenu.getHealthLabel(),
                towerSelectionMenu.getRangeLabel(),
                towerSelectionMenu.getDamageLabel()
        );

        // for purchase button
        VBox towerDetailsPart3 = new VBox();
        towerDetailsPart3.getChildren().addAll(
                purchaseButton
        );

        purchaseButton.getStyleClass().add("tower-purchase-button");

        towerMenu.getChildren().addAll(towerSelection,
                towerDetailsPart1, towerDetailsPart2, towerDetailsPart3);
        // ****** Right Box - tower menu ******

        rightBox.getChildren().addAll(gamePane, towerMenu);
        // ****** Right Box ******

        gameBoardBox.getChildren().addAll(leftBox, rightBox);
        // ************ Game Board Box ************

        gameScreenBox.getChildren().addAll(headerContainer, gameBoardBox);

        Scene scene = new Scene(gameScreenBox, width, height);
        gameScreenBox.requestFocus();
        scene.getStylesheets().add("file:resources/css/GameScreen.css");
        return scene;
    }

    public DifficultyModel getDifficultyModel() {
        return difficultyModel;
    }

    public MonumentModel getMonumentModel() {
        return monumentModel;
    }

    public MapModel getMap() {
        return map;
    }

    public TowerSelectionMenuModel getTowerSelectionMenu() {
        return towerSelectionMenu;
    }

    public ComboBox<String> getTowerSelection() {
        return towerSelection;
    }

    public Button getPurchaseButton() {
        return purchaseButton;
    }

    public HBox getTowerMenu() {
        return towerMenu;
    }

    public void setMonumentMoney(int money) {
        monumentMoney.setText("Your Money: " + money);
    }

    public void setMonumentHealth(int health) {
        monumentHealth.setText("Your Monument Health: " + health);
    }

    public GridPane getGamePane() {
        return gamePane;
    }

    public List<Circle> getMonsters() {
        return monsters;
    }

    public Button getStartCombatButton() {
        return startCombatButton;
    }
}
