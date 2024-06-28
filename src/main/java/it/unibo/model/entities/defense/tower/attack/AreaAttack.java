package it.unibo.model.entities.defense.tower.attack;

import java.util.Optional;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Strategy pattern implmentation. Attack based on an area target, multiple
 * attack.
 */
public class AreaAttack implements AttackStrategy {

    /**
     * {@link Tower}'s attack method to attack target {@link Enemy}. Attacking
     *
     * @param tower. Target @param enemy chosen by the {@link Tower} depending
     * on the {@link TargetSelectionStrategy}.
     */
    @Override
    public void attack(final Tower tower, final Optional<Enemy> enemy) {
        throw new UnsupportedOperationException(
                "Attack based on an area target, multiple attack, will be implemented in a new release version of UNIBO-TD.");
    }
}
