package autobotzi.user.skill.utils;

public enum Level {
    LEARNS(1),
    KNOWS(2),
    DOES(3),
    HELPS(4),
    TEACHES(5);

    private final int levels;
    Level(int level) {
        this.levels = level;
    }
    public Level getLevel() {
        return Level.values()[levels];
    }

}
