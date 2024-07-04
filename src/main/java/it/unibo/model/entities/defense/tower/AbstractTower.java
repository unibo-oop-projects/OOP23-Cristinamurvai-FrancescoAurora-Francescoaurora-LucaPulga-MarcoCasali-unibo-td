package it.unibo.model.entities.defense.tower;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Abstract Tower class extending {@link AbstractMovableEntity} implementing
 * {@link Tower}.
 */
public abstract class AbstractTower extends AbstractMovableEntity implements Tower {

    @JsonProperty("cost")
    private final int cost;

    @JsonProperty("level")
    private final int level;

    @JsonProperty("range")
    private final int range;

    @JsonProperty("weapons")
    private final Set<WeaponImpl> weapons;

    @JsonProperty("currentWeapon")
    private final Weapon currentWeapon;

    @JsonProperty("attackStrategy")
    private AttackStrategy attackStrategy;

    @JsonProperty("targetSelectionStrategy")
    private TargetSelectionStrategy targetSelectionStrategy;

    private final Set<Bullet> bullets;

    /**
     * Constructor.
     *
     * @param id {@link Tower}'s
     * @param name {@link Tower}'s
     * @param type {@link Tower}'s
     * @param imgPath {@link Tower}'s
     * @param position2d {@link Tower}'s
     * @param direction2d {@link Tower}'s
     * @param cost {@link Tower}'s
     * @param level {@link Tower}'s
     * @param range {@link Tower}'s
     * @param weapons {@link Tower}'s
     * @param currentWeapon {@link Tower}'s
     * @param attackStrategy {@link Tower}'s
     * @param targetSelectionStrategy {@link Tower}'s
     */
    @JsonCreator
    public AbstractTower(@JsonProperty("id") final int id,
            @JsonProperty("name") final String name,
            @JsonProperty("type") final String type,
            @JsonProperty("imgPath") final String imgPath,
            @JsonProperty("position2d") final Position2D position2d,
            @JsonProperty("direction2d") final Vector2D direction2d,
            @JsonProperty("cost") final int cost,
            @JsonProperty("level") final int level,
            @JsonProperty("range") final int range,
            @JsonProperty("weapons") final Set<WeaponImpl> weapons,
            @JsonProperty("currentWeapon") final Weapon currentWeapon,
            @JsonProperty("attackStrategy") final AttackStrategy attackStrategy,
            @JsonProperty("targetSelectionStrategy") final TargetSelectionStrategy targetSelectionStrategy) {
        super(id, name, type, imgPath, position2d, direction2d);
        this.cost = cost;
        this.level = level;
        this.range = range;
        this.weapons = new HashSet<>(weapons);
        this.currentWeapon = currentWeapon != null ? currentWeapon : null;
        this.attackStrategy = attackStrategy;
        this.targetSelectionStrategy = targetSelectionStrategy;
        this.bullets = new HashSet<>();
    }

    /**
     * Get {@link Tower}'s level.
     *
     * @return {@link Tower}'s level.
     */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
     * Represents the attackable range from the {@link Tower}.
     *
     * @return the attackable range from the {@link Tower}.
     */
    @Override
    public double getRange() {
        return this.range;
    }

    /**
     * Get {@link Tower}'s associated weapons.
     *
     * @return {@link Tower}'s associated weapons.
     */
    @Override
    public Set<Weapon> getWeapons() {
        return Set.copyOf(weapons);
    }

    /**
     * Get {@link Tower}'s current {@link Weapon}.
     *
     * @return {@link Tower}'s current {@link Weapon}.
     */
    @Override
    public Weapon getCurrentWeapon() {
        return this.currentWeapon == null ? null : this.currentWeapon;
    }

    /**
     * Get {@link Tower}'s cost.
     *
     * @return {@link Tower}'s cost.
     */
    @Override
    public int getCost() {
        return this.cost;
    }

    /**
     * Get {@link Tower}'s Target Selection Strategy.
     *
     * @return {@link Tower}'s Target Selection Strategy.
     */
    @Override
    public TargetSelectionStrategy getTargetSelectionStrategy() {
        return this.targetSelectionStrategy;
    }

    /**
     * Get {@link Tower}'s AttackStrategy.
     *
     * @return {@link Tower}'s AttackStrategy.
     */
    @Override
    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
    }

    /**
     * Set {@link Tower}'s setTargetSelectionStrategy. {@link Tower}'s @param
     * targetSelectionStrategy type of target strategy.
     */
    @Override
    public void setTargetSelectionStrategy(final TargetSelectionStrategy targetSelectionStrategy) {
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    /**
     * Set {@link Tower}'s AttackStrategy. {@link Tower}'s @param
     * targetSelectionStrategy type of attack strategy.
     */
    @Override
    public void setAttackStrategy(final AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    /**
     * Get all active {@link Bullet}s fired from a {@link Tower}.
     *
     * @return active {@link Bullet}s fired from a {@link Tower}.
     */
    @Override
    public Set<Bullet> getBullets() {
        return Collections.unmodifiableSet(new HashSet<>(this.bullets));
    }

    /**
     * Add {@link Bullet}s {@link Tower}.
     *
     * @param bullet to add.
     */
    @Override
    public void addBullet(final Bullet bullet) {
        this.bullets.add(bullet);
    }

    /**
     * Clear all the {@link Tower}'s {@link Bullet}s fired.
     */
    @Override
    public void clearBullets() {
        this.bullets.clear();
    }

    /**
     * Update all the {@link Tower}'s {@link Bullet}s position.
     */
    @Override
    public void updateBullets() {
        this.bullets.forEach(b -> b.update(null));
        bullets.removeIf(bullet -> bullet.hasReachedTarget() || bullet.isOutOfBounds());
    }

    /**
     * {@link Tower}'s target method to identify the target {@link Enemy}.
     *
     * Target @param enemies available on the map.
     */
    @Override
    public abstract Optional<Enemy> target(Set<Enemy> enemies);

    /**
     * {@link Tower}'s attack method to attack {@link Enemy}.
     *
     * @param enemies available on the map.
     */
    @Override
    public abstract void attack(Set<Enemy> enemies);
}
