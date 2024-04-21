package it.unibo.model.entities;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Abstract implementation of an abstract entity but movable such as an enemy, a tower or a bullet.
 */
public abstract class AbstractMovableEntity extends AbstractEntity implements MovableEntity{

    protected Position2D position2d;
    protected Vector2D direction2d; 

    /**
     * General abstact entity's constructor.
     * @param id
     * @param name
     * @param type
     * @param position2d
     * @param direction2d
     */
    public AbstractMovableEntity(int id, String name, String type, Position2D position2d, Vector2D direction2d) {
        super(id, name, type);
        this.position2d = position2d;
        this.direction2d = direction2d;
    }

    /**
     * Entity's position.
     * @return Entity's position.
     */
    public Position2D getPosition() {
        return this.position2d;
    }

    /**
     * Entity's direction.
     * @return Entity's direction.
     */ 
    public Vector2D getDirection() {
        return this.direction2d;
    }   
}