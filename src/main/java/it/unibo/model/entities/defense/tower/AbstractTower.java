package it.unibo.model.entities.defense.tower;

import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import it.unibo.model.entities.defense.weapon.Weapon;
import java.util.Set;

/**
 * Abstract Tower implementation.
 */
public abstract class AbstractTower extends AbstractMovableEntity implements Tower {

    private int cost;
    private int level;
    private int range;
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
    public AbstractTower(final int id, final String name, final String type, Position2D position2d, Vector2D direction2d, int cost, int level, int range, Set<Weapon> weapons, Weapon currentWeapon) {
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
