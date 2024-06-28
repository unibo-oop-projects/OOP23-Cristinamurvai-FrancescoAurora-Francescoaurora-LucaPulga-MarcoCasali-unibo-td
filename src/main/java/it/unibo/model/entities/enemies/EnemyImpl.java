package it.unibo.model.entities.enemies;

import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * .
 */
public class EnemyImpl extends AbstractMovableEntity implements Enemy {

    private Position2D pathEndPosition2d;
    private int lp;
    private int reward;
    private String imgPath;
    private boolean alive;
    private EnemyState enemyState;

    /**
     * Constructor.
     *
     * @param id
     * @param name
     * @param type
     * @param imgPath
     * @param position2d
     * @param direction2d
     * @param pathEndPosition2d
     * @param lp
     * @param reward
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

    @Override
    public EnemyState getState() {
        return this.enemyState;
    }

    @Override
    public int getLP() {
        return this.lp;
    }

    @Override
    public int getReward() {
        return this.reward;
    }

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

    @Override
    public String getImagePath() {
        return this.imgPath;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    public void startMoving() {
        this.enemyState = EnemyState.MOVING;
    }

    @Override
    public void deactivate() {
        this.enemyState = EnemyState.INACTIVE;
    }

    public void pause() {
        this.enemyState = EnemyState.PAUSED;
    }

    public void resume() {
        this.enemyState = EnemyState.MOVING;
    }

    @Override
    public void move() {
        if (this.enemyState.equals(EnemyState.MOVING)) {
            final double x = (this.getPosition().x() + this.getDirection().x());
            final double y = (this.getPosition().y() - this.getDirection().y());
            final Position2D newPosition2d = new Position2D(x, y);
            // System.out.println("Enemy " + this.id + "moved from position (" + this.position2d.x() + ", " + this.position2d.y() 
            // 							+ ") to position (" + x + ", " + y + ")");
            this.setPosition(new Position2D(x, y));
            //System.out.println("gli sommo: " + x + " " + y);
            //System.out.println("diventa pos: (" + this.position2d.x() + ", " + this.position2d.y() + ")");
            if (newPosition2d.xInt() == this.pathEndPosition2d.xInt() && newPosition2d.yInt() == this.pathEndPosition2d.yInt()) {
                this.enemyState = EnemyState.FINISHED;
                this.alive = false;
            }
        }
    }

    public void setState(EnemyState newState) {
        this.enemyState = newState;
    }
}
