package model;

public class DifficultyModel {
    private int defaultMoney;
    private int defaultMonumentHealth;

    public DifficultyModel(int defaultMoney, int defaultMonumentHealth) {
        this.defaultMoney = defaultMoney;
        this.defaultMonumentHealth = defaultMonumentHealth;
    }

    public int getDefaultMoney() {
        return defaultMoney;
    }

    public void setDefaultMoney(int defaultMoney) {
        this.defaultMoney = defaultMoney;
    }

    public int getDefaultMonumentHealth() {
        return defaultMonumentHealth;
    }

    public void setDefaultMonumentHealth(int defaultMonumentHealth) {
        this.defaultMonumentHealth = defaultMonumentHealth;
    }


}
