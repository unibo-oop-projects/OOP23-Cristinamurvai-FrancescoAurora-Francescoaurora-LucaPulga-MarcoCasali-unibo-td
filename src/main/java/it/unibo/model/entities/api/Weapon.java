package it.unibo.model.entities.api;

/**
 * Represents the Weapon entity.
 */
public interface Weapon extends DefenseEntity{
    
    /**
     * Represents the frequency with which the weapon can fire.
     * 
     * @return the frequency with which the weapon can fire.
     */
    int getFrequency();
}