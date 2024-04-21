package it.unibo.model.entities.enemies;

import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class EnemyImpl extends AbstractMovableEntity implements Enemy {

    private int lp;
    private int reward;
    private boolean alive;

    public EnemyImpl(int id, String name, String type, Position2D position2d, Vector2D direction2d, int lp, int reward) {
        super(id, name, type, position2d, direction2d);
        this.lp = lp;
        this.reward = reward;
        this.alive = true;
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
    
}
