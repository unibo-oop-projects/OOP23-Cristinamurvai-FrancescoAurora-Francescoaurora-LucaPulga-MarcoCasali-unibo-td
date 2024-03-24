package it.unibo.model.entities.api;

/**
 * Represents a defense entity.
 */
public interface DefenseEntity {
    
    /**
     * Represents the cost of the entity.
     * 
     * @return the cost of the entity.
     */
    int getCost();

    /**
     * Represents the type of the entity.
     * 
     * @return the type of the entity.
     */
    Entity getType();
}
