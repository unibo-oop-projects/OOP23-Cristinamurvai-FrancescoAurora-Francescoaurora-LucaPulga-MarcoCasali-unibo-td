package it.unibo.model.entities.enemies;

import java.math.BigDecimal;

import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of an Enemy.
 * This class represents a basic implementation of an enemy entity in the game.
 */
public class EnemyImpl extends AbstractMovableEntity implements Enemy {

    private final Position2D pathEndPosition2d;
    private int lp;
    private final int reward;
    private final String imgPath;
    private boolean alive;
    private EnemyState enemyState;

    /**
     * Constructor for creating an EnemyImpl object.
     *
     * @param id The identifier of the enemy.
     * @param name The name of the enemy.
     * @param type The type of the enemy.
     * @param imgPath The path to the image of the enemy.
     * @param position2d The initial position of the enemy.
     * @param direction2d The initial movement direction of the enemy.
     * @param pathEndPosition2d The position where the enemy's path ends.
     * @param lp The life points of the enemy.
     * @param reward The reward points given when the enemy is defeated.
     */
    public EnemyImpl(final int id, final String name, final String type, final String imgPath, final Position2D position2d,
            final Vector2D direction2d, final Position2D pathEndPosition2d, final int lp, final int reward) {
        super(id, name, type, imgPath, position2d, direction2d);
        this.pathEndPosition2d = pathEndPosition2d;

        this.lp = lp;
        this.reward = reward;
        this.imgPath = imgPath;
        this.alive = true;
        this.enemyState = EnemyState.READY;
    }

    /**
     * Moves the enemy along its current direction.
     * Checks if the enemy has reached the path end position and updates its state.
     */
    @Override
    public void move() {
        if (this.enemyState.equals(EnemyState.MOVING)) {
            final BigDecimal x = BigDecimal.valueOf(this.getPosition().x()).add(BigDecimal.valueOf(this.getDirection().x()));
            final BigDecimal y = BigDecimal.valueOf(this.getPosition().y()).subtract(BigDecimal.valueOf(this.getDirection().y()));
            final Position2D newPosition2d = new Position2D(x.doubleValue(), y.doubleValue());
            this.setPosition(new Position2D(x.doubleValue(), y.doubleValue()));
            if (newPosition2d.xInt() == this.pathEndPosition2d.xInt() && newPosition2d.yInt() == this.pathEndPosition2d.yInt()) {
                this.enemyState = EnemyState.FINISHED;
                this.alive = false;
            }
        }
    }

    /**
     * Starts moving the enemy.
     * Changes the state of the enemy to MOVING.
     */
    @Override
    public void startMoving() {
        this.enemyState = EnemyState.MOVING;
    }

    /**
     * Checks if the enemy is alive.
     *
     * @return true if the enemy is alive, false otherwise.
     */
    @Override
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Deactivates the enemy.
     * Changes the state of the enemy to INACTIVE.
     */
    @Override
    public void deactivate() {
        this.enemyState = EnemyState.INACTIVE;
    }

    /**
     * Pauses the enemy.
     * Changes the state of the enemy to PAUSED.
     */
    @Override
    public void pause() {
        this.enemyState = EnemyState.PAUSED;
    }

    /**
     * Resumes the enemy after it has been paused.
     * Changes the state of the enemy back to MOVING.
     */
    @Override
    public void resume() {
        this.enemyState = EnemyState.MOVING;
    }

    /**
     * Returns the current state of the enemy.
     *
     * @return the current state of the enemy.
     */
    @Override
    public EnemyState getState() {
        return this.enemyState;
    }

    /**
     * Returns the current life points of the enemy.
     *
     * @return the current life points of the enemy.
     */
    @Override
    public int getCurrentLP() {
        return this.lp;
    }

    /**
     * Returns the reward points given when the enemy is defeated.
     *
     * @return the reward points given when the enemy is defeated.
     */
    @Override
    public int getReward() {
        return this.reward;
    }

    /**
     * Deals damage to the enemy.
     *
     * @param damage the amount of damage to deal.
     * @return the remaining life points of the enemy after taking the damage.
     */
    @Override
    public int getDamage(final int damage) {
        if (this.lp - damage <= 0) {
            this.lp = 0;
            this.alive = false;
            this.enemyState = EnemyState.DEAD;
        } else {
            this.lp -= damage;
        }
        return this.lp;
    }

    /**
     * Returns the path to the image of the enemy.
     *
     * @return the path to the image of the enemy.
     */
    @Override
    public final String getImagePath() {
        return this.imgPath;
    }
}
