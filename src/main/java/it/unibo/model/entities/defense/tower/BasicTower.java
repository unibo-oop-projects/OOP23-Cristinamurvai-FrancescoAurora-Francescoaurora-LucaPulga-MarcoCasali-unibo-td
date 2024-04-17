package it.unibo.model.entities.defense.tower;

import java.util.Set;

import it.unibo.model.entities.AbstractEntity;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Tower impl.
 */
public class BasicTower extends AbstractEntity implements Tower {

    public BasicTower(int id, String name, String type) {
        super(id, name, type);
        //TODO Auto-generated constructor stub
    }
    private int cost;
    private int level;
    private int range;
    private Set<Weapon> weapons;
    
    @Override
    public Position2D getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }
    @Override
    public Vector2D getDirection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDirection'");
    }
    @Override
    public int getLevel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLevel'");
    }
    @Override
    public int getRange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRange'");
    }
    @Override
    public Set<Weapon> getWeapons() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeapons'");
    }
    @Override
    public int getCost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCost'");
    }
    @Override
    public void attack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }
    @Override
    public Enemy targetEnemy(Set<Enemy> enemies) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'targetEnemy'");
    }
}
