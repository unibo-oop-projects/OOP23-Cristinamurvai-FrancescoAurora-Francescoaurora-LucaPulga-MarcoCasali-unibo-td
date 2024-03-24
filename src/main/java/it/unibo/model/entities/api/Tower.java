package it.unibo.model.entities.api;

/**
 * Represents the tower entity.
 */
public interface Tower{
    
    /**
     * Represents the level of the tower.
     * 
     * @return the level of the tower.
     */
    int getLevel();

    /**
     * Represents the attackable range from the tower.
     * 
     * @return the attackable range from the tower.
     */
    int getRange();

    /**
     * Represents the method used by the tower to attack the target enemy.
     */
    void setTargetMethod();

}