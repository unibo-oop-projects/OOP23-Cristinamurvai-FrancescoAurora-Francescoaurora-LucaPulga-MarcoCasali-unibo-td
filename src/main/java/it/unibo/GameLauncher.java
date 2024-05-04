package it.unibo;

import it.unibo.view.GuiStart;

/**
 * Running the game.
 */
public final class GameLauncher {
    private GameLauncher() { }

    /**
     * Game Main.
     * @param args Main topics
     */
    public static void main(final String... args) {
        new GuiStart();
    }
}
