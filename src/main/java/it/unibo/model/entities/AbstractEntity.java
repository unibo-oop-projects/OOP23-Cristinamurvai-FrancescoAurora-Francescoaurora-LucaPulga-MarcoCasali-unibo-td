package it.unibo.model.entities;

import it.unibo.utilities.Position2D;
import it.unibo.utilities.Vector2D;
import java.util.Objects;

public abstract class AbstractEntity implements Entity{
    
    protected final int id;
    protected final String name;
    protected final String type;
    protected final Position2D position2d;
    protected final Vector2D direction2d;

    protected AbstractEntity(int id, String name, String type, Position2D position2d, Vector2D direction2d){
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
        this.position2d = position2d;
        this.direction2d = direction2d;
    }
    
    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public Position2D getPosition(){
        return this.position2d;
    }

    public Vector2D getDirection(){
        return this.direction2d;
    }
}
