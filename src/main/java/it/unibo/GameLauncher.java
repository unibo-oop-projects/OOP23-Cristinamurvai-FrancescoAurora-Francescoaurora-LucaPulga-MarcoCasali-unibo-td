package it.unibo;

import it.unibo.model.entities.EntityFactoryImpl;

import java.io.File;

import it.unibo.model.entities.EntityFactory;
import it.unibo.utilities.Position2D;
import it.unibo.view.GUI;

public final class GameLauncher{
    private GameLauncher(){}

    public static void main(final String ... args){
        EntityFactory entity = new EntityFactoryImpl();
        File file = new File("src/main/resoures/towers/tower1.json");
        entity.createTower(file.getName(), new Position2D(0, 0));
        new GUI();
    }
}