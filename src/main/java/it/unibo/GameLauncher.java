package it.unibo;

import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.manager.DefenseManager;
import it.unibo.model.entities.defense.manager.DefenseManagerImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.EnemiesManagerImpl;
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
        
        String jsonFilePath = "src/main/resources/towers/json/tower2.json";
        DefenseManager defenseManager = new DefenseManagerImpl();
        EntityFactory entityFactory = new EntityFactoryImpl();
        Tower tower = entityFactory.loadTower(jsonFilePath);
        defenseManager.buildTower(tower);

        EnemiesManagerImpl enemiesManagerImpl = new EnemiesManagerImpl();
        enemiesManagerImpl.parseEnemies();

        new GuiStart();
    }
}
