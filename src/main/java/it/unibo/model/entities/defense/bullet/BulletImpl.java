package it.unibo.model.entities.defense.bullet;

import it.unibo.model.core.GameObserver;
import it.unibo.model.core.GameState;
import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of {@link Bullet} as an {@link AbstractMovableEntity} managed with {@link GameObserver}.
 */
public class BulletImpl extends AbstractMovableEntity implements Bullet, GameObserver {

    private final int damage;
    private final double speed;
    private final Enemy targetEnemy;
    private final int MAP_MIN = 0;
    private final int MAP_MAX = 100;
    private final int BULLET_DISTANCE_TOLLERANCE = 1;

    /**
     * BulletImpl Constructor.
     *
     * @param id {@Bullet's id}.
     * @param name {@Bullet's name}.
     * @param type {@Bullet's type}.
     * @param imgPath {@Bullet's image path}.
     * @param initialPosition {@Bullet's initial position}.
     * @param direction2d {@Bullet's current direction}.
     * @param speed {@Bullet's speed}.
     * @param damage {@Bullet's damage}.
     * @param enemy {@Bullet's targetted enemy}.
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
     * Determines whether the {@link Bullet} has reached the position of the target {@link Enemy}.
     * @return {@code True} if target enemy's position reached, otherwise {@code False}.
     */
    @Override
    public boolean hasReachedTarget() {
        return this.position2d.distanceTo(targetEnemy.getPosition()) < BULLET_DISTANCE_TOLLERANCE && targetEnemy.isAlive();
    }

    /**
     * Implement {@link Bullet}'s observer.
     */
    @Override
    public void update(final GameState gameState) {
        // Move bullet towards the target enemy updating its position.
        this.position2d = this.position2d.add(this.direction2d.scale(this.speed));

        // Check if bullet has hit the target enemy.
        if (hasReachedTarget()) {
            this.targetEnemy.getDamage(this.damage);
            System.out.println("this.targetEnemy.getLP()): " + this.targetEnemy.getLP());
        }
    }

    /**
     * Represents the damage dealt to the target {@link Enemy}.
     * @return the damage dealt to the target {@link Enemy}.
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * Represents the speed {@link Bullet}.
     * @return the speed {@link Bullet}.
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Determines if {@link Bullet} is out of bound or not.
     * @return {@code True} if {@link Bullet} is out of bound, otherwise {@code False}.
     */
    @Override
    public boolean isOutOfBounds() {
        return this.position2d.x() < MAP_MIN || this.position2d.x() > MAP_MAX || this.position2d.y() < MAP_MIN || this.position2d.y() > MAP_MAX;
    }
}
