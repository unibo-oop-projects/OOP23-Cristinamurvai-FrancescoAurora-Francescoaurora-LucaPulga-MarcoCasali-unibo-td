package it.unibo.model.entities.impl;

import it.unibo.model.entities.api.EnemiesManager;
import it.unibo.model.entities.api.Enemy;
import java.util.Set;

public class EnemiesManagerImpl implements EnemiesManager{
    public EnemiesManagerImpl(){

    }
    public Set<Enemy> getCurrentEnemies(){
        return Set.of();
    }
}
