package common.enums;

public enum Monster {
    MONSTER_TINY("Tiny Monster", "It is a tiny monster.", "For the horde", 100, 1, 1.1),
    MONSTER_REGULAR("Regular Monster", "It is a regular monster.", "For the horde", 200, 2, 1.2),
    MONSTER_LARGE("Large Monster", "It is a large monster.", "For the horde", 300, 3, 1.3),
    MONSTER_SUPER("Super Monster", "It is a super monster.", "For the horde", 400, 4, 1.4),
    MONSTER_MEGA("Mega Monster", "It is a mega monster.", "For the horde", 500, 5, 1.5);

    private final String name;
    private final String description;
    private final String sound;
    private final Integer health;
    private final Integer damage;
    private final Double upgradeMultiplier;

    Monster(String name, String description,
            String sound, Integer health, Integer damage, Double upgradeMultiplier) {
        this.name = name;
        this.description = description;
        this.sound = sound;
        this.health = health;
        this.damage = damage;
        this.upgradeMultiplier = upgradeMultiplier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getDamage() {
        return damage;
    }

    public Double getUpgradeMultiplier() {
        return upgradeMultiplier;
    }
}
