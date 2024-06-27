package it.unibo.model.entities.defense.tower.attack;

import java.util.Optional;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Interface for attacks.
 */
public interface AttackStrategy {

    void attack(Tower tower, Optional<Enemy> enemy);
}
