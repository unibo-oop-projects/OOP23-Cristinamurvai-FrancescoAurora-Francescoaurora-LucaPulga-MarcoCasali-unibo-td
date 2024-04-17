package it.unibo;

import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.utilities.Position2D;
import it.unibo.view.GUI;

public final class GameLauncher{
    private GameLauncher(){}

    public static void main(final String ... args){
        EntityFactory entity = new EntityFactoryImpl();
        String file = "src/main/resources/towers/tower1.json";
        Tower tower = entity.createTower(file, new Position2D(0, 0));
        new GUI();
    }
}
