package it.unibo.model.entities.defense.bullet;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of {@link Bullet} as an {@link AbstractMovableEntity}.
 */
public class BulletImpl extends AbstractMovableEntity implements Bullet {

    private final int damage;
    private final double speed;
    private final Enemy targetEnemy;
    private static final int MAP_MIN = 0;
    private static final int MAP_MAX = 50;
    private static final double BULLET_DISTANCE_TOLLERANCE = 0.5;

    /**
     * BulletImpl Constructor.
     *
     * @param id {@link Bullet}'s
     * @param name {@link Bullet}'s
     * @param type {@link Bullet}'s
     * @param imgPath {@link Bullet}'s
     * @param initialPosition {@link Bullet}'s
     * @param direction2d {@link Bullet}'s
     * @param speed {@link Bullet}'s
     * @param damage {@link Bullet}'s
     * @param enemy {@link Bullet}'s
     */
    public BulletImpl(
            final int id,
            final String name,
            final String type,
            final String imgPath,
            final Position2D initialPosition,
            final Vector2D direction2d,
            final double speed,
            final int damage,
            final Enemy enemy) {
        super(id, name, type, imgPath, initialPosition, direction2d);
        this.speed = speed;
        this.targetEnemy = enemy;
        this.damage = damage;
    }

    /**
     * Determines whether the {@link Bullet} has reached the position of the
     * target {@link Enemy}.
     *
     * @return {@code True} if target enemy's position reached, otherwise
     * {@code False}.
     */
    @Override
    public boolean hasReachedTarget() {
        return this.getPosition().distanceTo(targetEnemy.getPosition()) < BULLET_DISTANCE_TOLLERANCE && targetEnemy.isAlive();
    }

    /**
     * Implement {@link Bullet}'s observer.
     *
     * @param gameState current gameState.
     */
    @Override
    public void update(final GameState gameState) {
        // Move bullet towards the target enemy updating its position.
        this.setPosition(this.getPosition().add(this.getDirection().scale(this.speed)));

        // Check if bullet has hit the target enemy.
        if (hasReachedTarget()) {
            this.targetEnemy.getDamage(this.damage);
        }
    }

    /**
     * Represents the damage dealt to the target {@link Enemy}.
     *
     * @return the damage dealt to the target {@link Enemy}.
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * Represents the speed {@link Bullet}.
     *
     * @return the speed {@link Bullet}.
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Determines if {@link Bullet} is out of bound or not.
     *
     * @return {@code True} if {@link Bullet} is out of bound, otherwise
     * {@code False}.
     */
    @Override
    public boolean isOutOfBounds() {
        return this.getPosition().x() < MAP_MIN 
        || this.getPosition().x() > MAP_MAX 
        || this.getPosition().y() < MAP_MIN
        || this.getPosition().y() > MAP_MAX;
    }
}
