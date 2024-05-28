package it.unibo.model.entities.enemies;

import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class EnemyImpl extends AbstractMovableEntity implements Enemy, Runnable {

    private int lp;
    private int reward;
    private boolean alive;
	private EnemyState enemyState;

    public EnemyImpl(int id, String name, String type, String imgPath, Position2D position2d, Vector2D direction2d, int lp, int reward) {
        super(id, name, type, imgPath, position2d, direction2d);
        this.lp = lp;
        this.reward = reward;
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
	public int getDamage(int damage) {
        if(this.lp - damage <= 0) {
            this.lp = 0;
            this.alive = false;
        } else {
            this.lp -= damage;
        }
		return this.lp;
	}

	@Override
	public boolean isAlive() {
		return this.alive;
	}

	@Override
	public void move() {
		int x = (int)(this.position2d.x() + this.direction2d.x());
		int y = (int)(this.position2d.y() + this.direction2d.y());
		// Added only for debug purposes
		System.out.println("Enemy " + this.id + "moved from position (" + this.position2d.x() + ", " + this.position2d.y() + ") to position (" + x + ", " + y + ")");
		this.position2d = new Position2D(x, y);
		this.enemyState = EnemyState.MOVING;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
            move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}

	public void setDirection(Vector2D direction2d) {
        this.direction2d = direction2d;
    }
}
