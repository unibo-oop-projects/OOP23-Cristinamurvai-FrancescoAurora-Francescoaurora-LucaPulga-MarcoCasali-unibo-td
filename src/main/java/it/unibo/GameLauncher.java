package it.unibo;

import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.manager.DefenseManager;
import it.unibo.model.entities.defense.manager.DefenseManagerImpl;
import it.unibo.model.entities.defense.tower.BasicTower;
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
        DefenseManager defenseManager = new DefenseManagerImpl();
        EntityFactory entityFactory = new EntityFactoryImpl();
        String jsonFilePath = "src/main/resources/towers/tower1.json";
        BasicTower tower = entityFactory.loadEntity(jsonFilePath, null, null, BasicTower.class);
        defenseManager.buildTower(tower);
        new GuiStart();
    }
}
