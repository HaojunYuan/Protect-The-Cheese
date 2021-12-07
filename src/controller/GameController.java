package controller;

import common.Constants;
import common.enums.Levels;
import common.enums.Tower;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DifficultyModel;
import model.TowerSelectionMenuModel;
import model.PositionModel;
import model.TowerModel;
import model.PathModel;
import view.*;

import java.util.*;
import java.util.List;

public class GameController extends Application {
    private Stage primaryStage;
    private Stage secondaryStage;
    private Levels selectedDifficultyLevel;
    private String userName = null;
    private Tower selectedTower = null;
    private Map<PositionModel, TowerModel> towerMap = new HashMap<>();
    private PositionModel selectedTowerPosition;
    private PositionModel monsterPosition;
    private Node previousSelectedTowerVertex;
    private Node selectedTowerVertex;
    private Boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tower Defence");
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT
        );
        Scene welcomeScreenScene = welcomeScreen.getScene();

        Button welcomeStartGameButton = welcomeScreen.getWelcomeStartGameButton();
        welcomeStartGameButton.setOnAction(e -> goToGameConfigScreen());

        primaryStage.setScene(welcomeScreenScene);
        primaryStage.show();
    }

    public void initGameOverScreen() {
        GameOverScreen gameOverScreen = new GameOverScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT
        );
        Scene gameOverScene = gameOverScreen.getScene();

        Button gameOverButton = gameOverScreen.getGameOverButton();
        gameOverButton.setOnAction(e -> goToGameConfigScreen());
        gameOver = false;

        primaryStage.setScene(gameOverScene);
        primaryStage.show();
    }

    public void initWinScreen() {
        WinScreen winScreen = new WinScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT
        );
        Scene winScene = winScreen.getScene();

        Button gameOverButton = winScreen.getGameOverButton();
        gameOverButton.setOnAction(e -> goToGameConfigScreen());
        gameOver = false;

        primaryStage.setScene(winScene);
        primaryStage.show();
    }

    private void goToGameConfigScreen() {
        GameConfigScreen gameConfigScreen = new GameConfigScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT
        );
        Scene gameConfigScreenScene = gameConfigScreen.getScene();

        TextField userNameInputField = gameConfigScreen.getUserNameInputField();
        userNameInputField.textProperty().addListener((observable, oldValue, newValue)
                -> userName = newValue
        );

        Button playButton = gameConfigScreen.getPlayButton();
        playButton.setOnAction(e -> {
            if (selectedDifficultyLevel != null
                    && userName != null
                    && userName.trim().length() > 0) {
                DifficultyModel difficultyModel = getDifficulty();
                goToGameScreen(difficultyModel);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText(
                        "Invalid Inputs, Please select a difficulty and enter your name."
                );
                alert.showAndWait();
            }
        });

        ComboBox<Levels> difficultyLevelDropDown = gameConfigScreen.getDifficultyLevelDropDown();
        difficultyLevelDropDown.setOnAction(event ->
                selectedDifficultyLevel = difficultyLevelDropDown.getValue()
        );

        primaryStage.setScene(gameConfigScreenScene);
        primaryStage.show();
    }

    public void goToGameScreen(DifficultyModel difficultyModel) {
        GameScreen gameScreen = new GameScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                difficultyModel
        );
        Scene gameScreenScene = gameScreen.getScene();

        handleStartCombatButtonAction(gameScreen);
        handleTowerMenu(gameScreen);
        handleTowerMenuDropdownSelection(gameScreen);
        handlePurchaseButtonAction(gameScreen);

        primaryStage.setScene(gameScreenScene);
        primaryStage.show();
    }

    private void handleTowerMenu(GameScreen gameScreen) {
        HBox towerMenu = gameScreen.getTowerMenu();
        gameScreen.getGamePane().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Node node = (Node) e.getTarget();
            int y = GridPane.getRowIndex(node);
            int x = GridPane.getColumnIndex(node);
            PositionModel clickedPosition = new PositionModel(x, y);
            for (PathModel path : gameScreen.getMap().getTowerPaths()) {
                if (path.getPositions().contains(clickedPosition)) {
                    previousSelectedTowerVertex = selectedTowerVertex;
                    selectedTowerVertex = node;
                    if (previousSelectedTowerVertex == null
                            || !previousSelectedTowerVertex.equals(selectedTowerVertex)) {
                        selectedTowerVertex.setStyle("-fx-background-color: blue;");
                        if (previousSelectedTowerVertex != null
                                && towerMap.get(selectedTowerPosition) == null) {
                            previousSelectedTowerVertex.setStyle("-fx-background-color: grey;");
                        }
                    }
                    selectedTowerPosition = clickedPosition;
                } else {
                    towerMenu.setVisible(false);
                }
            }
        });
    }

    private void handleTowerMenuDropdownSelection(GameScreen gameScreen) {
        ComboBox<String> towerSelection = gameScreen.getTowerSelection();
        towerSelection.setOnAction(e -> {
            Tower tower;
            if (Tower.TOWER_TINY.getName().equals(towerSelection.getValue())) {
                tower = Tower.TOWER_TINY;
            } else if (Tower.TOWER_REGULAR.getName().equals(towerSelection.getValue())) {
                tower = Tower.TOWER_REGULAR;
            } else if (Tower.TOWER_LARGE.getName().equals(towerSelection.getValue())) {
                tower = Tower.TOWER_LARGE;
            } else if (Tower.TOWER_SUPER.getName().equals(towerSelection.getValue())) {
                tower = Tower.TOWER_SUPER;
            } else {
                tower = Tower.TOWER_MEGA;
            }
            selectedTower = tower;
            gameScreen.getTowerSelectionMenu().setSelectedTowerDetails(tower,
                    selectedDifficultyLevel);
        });
    }

    private void handlePurchaseButtonAction(GameScreen gameScreen) {
        Button purchaseButton = gameScreen.getPurchaseButton();
        TowerSelectionMenuModel towerSelectionMenu = gameScreen.getTowerSelectionMenu();
        HBox towerMenu = gameScreen.getTowerMenu();
        purchaseButton.setOnAction(e -> {
            if (selectedTower instanceof Tower
                    && gameScreen.getMonumentModel().getMoney() > towerSelectionMenu.getCost()) {
                towerMenu.setVisible(false);
                towerMap.put(selectedTowerPosition, new TowerModel(
                        towerSelectionMenu.getName(),
                        towerSelectionMenu.getDescription(),
                        towerSelectionMenu.getCost(),
                        selectedTower,
                        1,
                        selectedTowerPosition
                ));

                if (Tower.TOWER_TINY.equals(selectedTower)) {
                    selectedTowerVertex.setStyle("-fx-background-color: orange;");
                    //((Pane) selectedTowerVertex).getChildren().add(new Text("T1"));
                } else if (Tower.TOWER_REGULAR.equals(selectedTower)) {
                    selectedTowerVertex.setStyle("-fx-background-color: purple;");
                    //((Pane) selectedTowerVertex).getChildren().add(new Text("R1"));
                } else if (Tower.TOWER_LARGE.equals(selectedTower)) {
                    selectedTowerVertex.setStyle("-fx-background-color: yellow;");
                    //((Pane) selectedTowerVertex).getChildren().add(new Text("L1"));
                } else if (Tower.TOWER_SUPER.equals(selectedTower)) {
                    selectedTowerVertex.setStyle("-fx-background-color: pink;");
                    //((Pane) selectedTowerVertex).getChildren().add(new Text("S1"));
                } else {
                    selectedTowerVertex.setStyle("-fx-background-color: brown;");
                    //((Pane) selectedTowerVertex).getChildren().add(new Text("M1"));
                }

                gameScreen.getMonumentModel().decreaseMoney(towerSelectionMenu.getCost());
                gameScreen.setMonumentMoney(gameScreen.getMonumentModel().getMoney());
            }

        });
    }

    private void handleStartCombatButtonAction(GameScreen gameScreen) {
        Button startCombatButton = gameScreen.getStartCombatButton();
        List<Circle> monsters = gameScreen.getMonsters();
        List<Service<Void>> monsterServices = new LinkedList<>();

        startCombatButton.setOnAction(e -> {
            startCombatButton.setDisable(true);
            int time = 3;
            List<Paint> colorList = new ArrayList<>();
            colorList.add(Paint.valueOf("BLUE"));
            colorList.add(Paint.valueOf("YELLOW"));
            colorList.add(Paint.valueOf("RED"));
            colorList.add(Paint.valueOf("BLACK"));
            for (int i = 0; i < monsters.size(); i++) {
                if (i == 3) {
                    try {
                        Thread.sleep(1000);
                        monsterServices.add(generateMonsterService(
                                gameScreen, monsters.get(i), 5, colorList.get(i)));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    monsterServices.add(generateMonsterService(
                            gameScreen, monsters.get(i), time, colorList.get(i)));
                    time -= 0.5;
                }

            }


            Service<Void> attack = new Service<>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<>() {
                        @Override
                        protected Void call() throws InterruptedException {
                            System.out.println("monsters attack thread: "
                                    + Thread.currentThread().getId());
                            for (int i = 0; i < monsterServices.size(); i++) {
                                Service<Void> monster = monsterServices.get(i);
                                if (!monster.isRunning()
                                        && gameScreen.getMonumentModel().getHealth() > 0) {
                                    monster.start();
                                }
                                Thread.sleep(500); // wait for 0.5 sec for next monster to show up
                            }
                            return null;
                        }
                    };
                }
            };
            attack.start();
        });
    }

    private Service<Void> generateMonsterService(GameScreen gameScreen,
                                                 Circle monster, int time, Paint paint) {
        Service<Void> service = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws InterruptedException {
                        monster.setVisible(true);
                        monster.setFill(paint);
                        List<PositionModel> positions
                                = gameScreen.getMap().getPaths().get(0).getPositions();
                        PathTransition transition = new PathTransition();
                        transition.setNode(monster);
                        //transition duration
                        transition.setDuration(Duration.seconds(time));
                        transition.setCycleCount(1);
                        Polyline polyline = new Polyline();

                        double translationY = 0;
                        double translationX = 0;
                        for (int i = positions.size() - 2; i > 0; i--) {
                            int currentY = positions.get(i).getPositionY();
                            int currentX = positions.get(i).getPositionX();
                            int nextY = positions.get(i - 1).getPositionY();
                            int nextX = positions.get(i - 1).getPositionX();
                            int changeY = nextY - currentY;
                            int changeX = nextX - currentX;
                            // 20 is the size of each pane in the game pane
                            for (int t = 0; t < 20; t++) {
                                translationY += changeY;
                                translationX += changeX;
                                polyline.getPoints().addAll(translationX, translationY);
                            }
                            if (time != 5) {
                                PositionModel up = new PositionModel(currentX, currentY - 1);
                                PositionModel down = new PositionModel(currentX, currentY + 1);
                                PositionModel left = new PositionModel(currentX - 1, currentY);
                                PositionModel right = new PositionModel(currentX + 1, currentY);
                                if (towerMap.containsKey(up) || towerMap.containsKey(down)
                                        || towerMap.containsKey(left)
                                        || towerMap.containsKey(right)) {
                                    Thread.sleep(1000);
                                    monster.setVisible(false);
                                }
                            }
                        }
                        if (!monster.isVisible()) {
                            gameScreen.getMonumentModel().decreaseMoney(-200);
                            gameScreen.setMonumentMoney(gameScreen.
                                    getMonumentModel().getMoney());
                        }
                        transition.setPath(polyline);
                        transition.setOnFinished(event -> {
                            if (gameScreen.getMonumentModel().getHealth() > 200
                                    && monster.isVisible() && time != 5) {
                                System.out.println("attack!!!!");
                                monster.setVisible(false);
                                gameScreen.getMonumentModel().decreaseHealth(200);
                                gameScreen.setMonumentHealth(
                                        gameScreen.getMonumentModel().getHealth());
                            } else if (!gameOver && monster.isVisible()
                                    && gameScreen.getMonumentModel().getHealth() <= 200
                                    && time != 5) {
                                initGameOverScreen();
                                gameOver = true;
                            } else if (!gameOver && time == 5) {
                                initWinScreen();
                                gameOver = true;
                            }
                        });
                        transition.play();
                        Boolean play = true;

                        return null;
                    }
                };
            }
        };
        return service;
    }


    private DifficultyModel getDifficulty() {
        if (Levels.EASY.equals(selectedDifficultyLevel)) {
            return new DifficultyModel(1500, 1500);
        } else if (Levels.MEDIUM.equals(selectedDifficultyLevel)) {
            return new DifficultyModel(1000, 1000);
        } else {
            return new DifficultyModel(500, 500);
        }
    }

    public Node getNodeByXY(final int x, final int y, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == x && gridPane.getColumnIndex(node) == y) {
                result = node;
                break;
            }
        }

        return result;
    }
}
