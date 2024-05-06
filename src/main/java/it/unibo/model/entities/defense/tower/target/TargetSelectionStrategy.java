package it.unibo.model.entities.defense.tower.target;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;

import java.util.Optional;
import java.util.Set;

// Interfaccia per la selezione del bersaglio
public interface TargetSelectionStrategy {
    Optional<Set<Enemy>> selectTarget(Tower tower, Set<Enemy> enemies);
}