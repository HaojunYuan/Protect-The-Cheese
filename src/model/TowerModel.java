package model;

import common.enums.Tower;

public class TowerModel {
    private String name;
    private String description;
    private Integer cost;
    private Tower towerType;
    private Integer level;
    private PositionModel position;

    public TowerModel(String name, String description,
                      Integer cost, Tower towerType,
                      Integer level, PositionModel position) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.towerType = towerType;
        this.level = level;
        this.position = position;
    }
}
