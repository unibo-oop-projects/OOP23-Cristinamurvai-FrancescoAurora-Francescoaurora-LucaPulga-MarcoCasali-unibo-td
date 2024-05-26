package it.unibo;

import it.unibo.view.GuiStart;
import java.io.IOException;

/**
 * Running the game.
 */
public final class GameLauncher {
    private GameLauncher() { }

    /**
     * Game Main.
     * @param args Main topics
     */
    public static void main(final String... args) throws IOException {

        /* String jsonFilePath = "src/main/resources/towers/json/tower2.json";
        DefenseManager defenseManager = new DefenseManagerImpl();
        EntityFactory entityFactory = new EntityFactoryImpl();
        Tower tower = entityFactory.loadTower(jsonFilePath);
        defenseManager.buildTower(tower);

        EnemiesManagerImpl enemiesManagerImpl = new EnemiesManagerImpl();
        enemiesManagerImpl.parseEnemies(); */

        new GuiStart();
        //new MapViewTest();
    }
}
