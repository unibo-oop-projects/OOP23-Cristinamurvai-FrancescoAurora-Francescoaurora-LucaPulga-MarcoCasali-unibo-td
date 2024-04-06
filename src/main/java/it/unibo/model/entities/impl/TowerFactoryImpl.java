package it.unibo.model.entities.impl;

import it.unibo.model.entities.api.Entity;
import it.unibo.model.entities.api.Tower;
import it.unibo.model.entities.api.Weapon;
import it.unibo.model.entities.factories.TowerFactory;
import it.unibo.utilities.Position2D;
import it.unibo.utilities.Serializer;
import it.unibo.utilities.Vector2D;
import java.util.Set;
import java.util.HashSet;
import java.io.*;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.OverrideMustInvoke;

public class TowerFactoryImpl implements TowerFactory, Serializable{

    private static record TowerImpl(int range, int level, int cost, String type, int id, String name, Position2D position2d, Vector2D vector2d, String filePathString) implements Tower, Serializable {
        
    }

    @Override
    public void setTargetMethod(){
        return TowerImpl();
    }

    
}
