package it.unibo.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Abstract implementation of an abstract entity but movable such as an enemy, a tower or a bullet.
 */
public abstract class AbstractMovableEntity extends AbstractEntity implements MovableEntity{
    @JsonProperty("position2d")
    protected Position2D position2d;

    @JsonProperty("direction2d")
    protected Vector2D direction2d; 

    public AbstractMovableEntity(int id, String name, String type, String imgPath, Position2D position2d, Vector2D direction2d) {
        super(id, name, type, imgPath);
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

    /**
     * Modify Entity's direction.
     * @param direction2d
     */
    public void setDirection(Vector2D direction2d) {
        this.direction2d = direction2d;
    }
}
