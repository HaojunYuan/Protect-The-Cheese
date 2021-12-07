package common.enums;

public enum Tower {
    TOWER_TINY("Tiny Tower", "It is a tiny tower.", 30, 100, 1, 1, 1.1),
    TOWER_REGULAR("Regular Tower", "It is a regular tower.", 40, 200, 2, 2, 1.2),
    TOWER_LARGE("Large Tower", "It is a large tower.", 50, 300, 3, 3, 1.3),
    TOWER_SUPER("Super Tower", "It is a super tower.", 60, 400, 4, 4, 1.4),
    TOWER_MEGA("Mega Tower", "It is a mega tower.", 70, 500, 5, 5, 1.5);

    private final String name;
    private final String description;
    private final Integer cost;
    private final Integer health;
    private final Integer range;
    private final Integer damage;
    private final Double upgradeMultiplier;

    Tower(String name, String description,
          Integer cost, Integer health,
          Integer range, Integer damage,
          Double upgradeMultiplier) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.health = health;
        this.range = range;
        this.damage = damage;
        this.upgradeMultiplier = upgradeMultiplier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getRange() {
        return range;
    }

    public Integer getDamage() {
        return damage;
    }

    public Double getUpgradeMultiplier() {
        return upgradeMultiplier;
    }
}
