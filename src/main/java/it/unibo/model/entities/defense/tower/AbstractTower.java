package it.unibo.model.entities.defense.tower;

import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import it.unibo.model.entities.defense.weapon.Weapon;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Abstract Tower implementation.
 */
public abstract class AbstractTower extends AbstractMovableEntity implements Tower {
    @JsonProperty("cost")
    private int cost;
    
    @JsonProperty("level")
    private int level;
    
    @JsonProperty("range")
    private int range;

    @JsonProperty("weapons")
    private Set<Weapon> weapons;

    private Weapon currentWeapon;

    /**
     * Abstract tower constructor.
     * @param id
     * @param name
     * @param type
     * @param position2d
     * @param direction2d
     * @param cost
     * @param level
     * @param range
     * @param weapons
     */
    @JsonCreator
    public AbstractTower(@JsonProperty("id") int id, 
                        @JsonProperty("name") String name, 
                        @JsonProperty("type") String type, 
                        @JsonProperty("position2d") Position2D position2d, 
                        @JsonProperty("direction2d") Vector2D direction2d, 
                        @JsonProperty("cost") int cost, 
                        @JsonProperty("level") int level, 
                        @JsonProperty("range") int range, 
                        @JsonProperty("weapons") Set<Weapon> weapons, 
                        @JsonProperty("currentWeapon") Weapon currentWeapon)  {
        super(id, name, type, position2d, direction2d);
        this.cost = cost;
        this.level = level;
        this.range = range;
        this.weapons = weapons;
        this.currentWeapon = currentWeapon;
    }

    /**
     * Get tower's position.
     */
    @Override
    public Position2D getPosition() {
        return this.position2d;
    }

    /**
     * Get tower's direction.
     */
    @Override
    public Vector2D getDirection() {
        return this.direction2d;
    }
    
    /**
     * Get tower's level.
     */
    @Override
    public int getLevel() {
        return this.level;
    }
    
    /**
     * Get tower's position.
     */
    @Override
    public int getRange() {
        return this.range;
    }
    
    /**
     * Get tower's weapons.
     */
    @Override
    public Set<Weapon> getWeapons() {
        return Set.copyOf(weapons);
    }

    /**
     * Get tower's weapons.
     */
    @Override
    public Weapon getCurrentWeapon() {
        return this.currentWeapon;
    }

    /**
     * Get tower's cost.
     */
    @Override
    public int getCost() {
        return this.cost;
    }
}
