package it.unibo.model.entities.defense;

import java.util.Set;

import it.unibo.model.entities.AbstractEntity;
import it.unibo.utilities.Position2D;
import it.unibo.utilities.Vector2D;

public class TowerImpl extends AbstractEntity{

    private int cost;
    private int level;
    private int range;
    private Set<Weapon> weapons;

    public TowerImpl(String name, int id, String type, Position2D position2d, Vector2D direction2d){
        super(id, name, type, position2d, direction2d);
    }

    public TowerImpl(String name, int id, Position2D position2d, Vector2D direction2d, int cost, int level, int range, Set<Weapon> weapons) {
        super(id, name, name, position2d, direction2d);
        this.cost = cost;
        this.level = level;
        this.range = range;
        this.weapons = weapons;
    } 

    public int getCost() {
        return this.cost;
    }

    public int getLevel() {
        return this.level;
    }

    public int getRange() {
        return this.range;
    }

    public Set<Weapon> getWeapons() {
        return Set.copyOf(weapons);
    }
}
