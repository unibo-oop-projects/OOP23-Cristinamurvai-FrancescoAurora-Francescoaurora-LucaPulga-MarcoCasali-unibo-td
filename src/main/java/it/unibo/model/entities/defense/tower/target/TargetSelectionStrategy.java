package it.unibo.model.entities.defense.tower.target;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;

// Interfaccia per la selezione del bersaglio
public interface TargetSelectionStrategy {

    Optional<Enemy> selectTarget(Tower tower, Set<Enemy> enemies);
}
