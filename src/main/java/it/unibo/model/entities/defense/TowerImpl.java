package it.unibo.model.entities.defense;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import it.unibo.utilities.Position2D;
import it.unibo.utilities.Vector2D;

public class TowerImpl implements Tower, Serializable{

    private final String filename;
    private int cost;
    private final int id; 
    private final String name;
    private final String type;
    private Position2D position2d;
    private Vector2D direction;
    private int level;
    private int range;
    private Set<Weapon> weapons;

    //public TowerImpl(){}


    // NOT USED //
    // NOT USED //
    // NOT USED //
    // NOT USED //
    

    public TowerImpl(String filename, int cost, int id, String name, String type, Position2D position2d, Vector2D direction, int level, int range, Set<Weapon> weapons) {
        this.filename = Objects.requireNonNull(filename);
        this.cost = Objects.requireNonNull(cost);
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(filename);
        this.type = Objects.requireNonNull(filename);
        this.position2d = position2d;
        this.direction = direction;
        this.level = Objects.requireNonNull(level);
        this.range = Objects.requireNonNull(range);
        this.weapons = Objects.requireNonNull(weapons);
    }   

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Position2D getPosition() {
        return this.position2d;
    }

    @Override
    public Vector2D getDirection() {
        return this.direction;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public Set<Weapon> getWeapons() {
        return Set.copyOf(weapons);
    }
}
