package model;

public class MonumentModel {
    private int health;
    private int money;

    public MonumentModel(DifficultyModel difficulty) {
        health = difficulty.getDefaultMonumentHealth();
        money = difficulty.getDefaultMoney();
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }

    public void increaseMoney(int money) {
        this.money += money;
    }

    public void decreaseMoney(int money) {
        this.money -= money;
    }

    public void decreaseHealth(int health) {
        this.health -= health;
    }

}
