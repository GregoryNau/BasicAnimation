package cs3500.animator;

import java.util.Random;

public enum RunConfigs {
    BIGBANG("src/cs3500/animator/view/starter_files/big-bang-big-crunch.txt", 26),
    BUILDINGS("src/cs3500/animator/view/starter_files/buildings.txt", 26),
    HANOI("src/cs3500/animator/view/starter_files/hanoi.txt", 52);

    private static final Random random = new Random();

    public final String filePath;
    public final int fps;

    public static RunConfigs getRandom() {
        return values()[random.nextInt(values().length)];
    }

    private RunConfigs(String filePath, int fps) {
        this.filePath = filePath;
        this.fps = fps;
    }

    public String[] getRunArgs() {
        String[] args = new String[6];
        args[0] = "-in";
        args[1] = this.filePath;
        args[2] = "-view";
        args[3] = "edit";
        args[4] = "-speed";
        args[5] = Integer.toString(this.fps);
        return args;
    }
}
