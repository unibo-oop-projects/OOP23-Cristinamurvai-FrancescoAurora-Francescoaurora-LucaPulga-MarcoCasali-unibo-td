package it.unibo.model.entities.enemies;

import java.util.Set;

import it.unibo.model.utilities.Position2D;

public class EnemiesManagerImpl implements EnemiesManager{
    public EnemiesManagerImpl(){

    }
	@Override
	public void buildEnemy(String enemyName) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'buildEnemy'");
	}

    @Override
	public Set<Enemy> getCurrentEnemies() {
        return Set.of();
	}
    
	@Override
	public Enemy getNearestEnemy(Position2D position2d, int radius) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getNearestEnemy'");
	}
	
}
