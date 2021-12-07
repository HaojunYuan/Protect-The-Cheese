package model;

import common.enums.Levels;
import common.enums.Tower;
import javafx.scene.control.Label;

public class TowerSelectionMenuModel {
    private Label nameLabel;
    private Label descriptionLabel;
    private Label costLabel;
    private Label healthLabel;
    private Label rangeLabel;
    private Label damageLabel;
    private String name;
    private String description;
    private int cost;
    private int health;
    private int range;
    private int damage;


    public TowerSelectionMenuModel() {
        nameLabel = new Label("name: n/a");
        descriptionLabel = new Label("description: n/a");
        costLabel = new Label("cost: n/a");
        healthLabel = new Label("health: n/a");
        rangeLabel = new Label("range: n/a");
        damageLabel = new Label("damage: n/a");
    }

    public void setSelectedTowerDetails(Tower tower, Levels level) {
        int levelMultiplier = 1;

        if (level.equals(Levels.EASY)) {
            levelMultiplier = 1;
        } else if (level.equals(Levels.MEDIUM)) {
            levelMultiplier = 2;
        } else {
            levelMultiplier = 3;
        }
        name = tower.getName();
        description = tower.getDescription();
        cost = tower.getCost() * levelMultiplier;
        health = tower.getHealth();
        range = tower.getRange();
        damage = tower.getDamage();

        nameLabel.setText("name: " + name);
        descriptionLabel.setText("description: " + description);
        costLabel.setText("cost: " + cost);
        healthLabel.setText("health: " + health);
        rangeLabel.setText("range: " + range);
        damageLabel.setText("damage: " + damage);

    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }

    public Label getCostLabel() {
        return costLabel;
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public Label getRangeLabel() {
        return rangeLabel;
    }

    public Label getDamageLabel() {
        return damageLabel;
    }

    public int getCost() {
        return cost;
    }

    public int getHealth() {
        return health;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
