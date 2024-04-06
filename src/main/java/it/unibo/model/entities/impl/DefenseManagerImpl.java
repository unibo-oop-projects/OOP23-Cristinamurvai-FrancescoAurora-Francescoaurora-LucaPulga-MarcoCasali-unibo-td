package it.unibo.model.entities.impl;

import java.util.Set;
import java.util.HashSet;

import it.unibo.model.entities.api.Bullet;
import it.unibo.model.entities.api.DefenseManager;
import it.unibo.model.entities.api.EnemiesManager;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.api.Tower;
import it.unibo.model.entities.api.Weapon;
import it.unibo.utilities.Position2D;



public class DefenseManagerImpl implements DefenseManager {

    private Set<Tower> towers = new HashSet<>();
    EnemiesManager enemiesManager = new EnemiesManagerImpl();

    @Override
    public void buildTower(Tower tower) {
        this.towers.add(tower);
        
    }

    @Override
    public Set<Enemy> getEnemies() {
        return Set.copyOf(this.enemiesManager.getCurrentEnemies());
    }

    @Override
    public Set<Tower> getTowers() {
        return Set.copyOf(towers);
    }

    @Override
    public Set<Bullet> getBullets() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBullets'");
    }

    @Override
    public Set<Weapon> getWeapons() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeapons'");
    }

    @Override
    public Enemy getNearestEnemy(Position2D position, int radius) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNearestEnemy'");
    }
    
}
