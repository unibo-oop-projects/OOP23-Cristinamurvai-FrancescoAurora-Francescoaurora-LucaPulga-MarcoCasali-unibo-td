package it.unibo.model.entities.defense.tower.target;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;


// Implementazione di selezione del bersaglio basata sulla distanza
class DistanceBasedTargetSelection implements TargetSelectionStrategy {
    @Override
    public Optional<Set<Enemy>> selectTarget(Tower tower, Set<Enemy> enemies) {
        Set<Enemy> targets = new HashSet<>();
        for (Enemy enemy : enemies) {
            double distance = calculateDistance(tower.getPosition(), enemy.getPosition());
            if (distance <= tower.getRange()) {
                targets.add(enemy);
            }
        }
        if (!targets.isEmpty()) {
            return Optional.of(targets);
        } else {
            return Optional.empty();
        }
    }

    private double calculateDistance(Position2D position1, Position2D position2) {
        int deltaX = position2.x() - position1.x();
        int deltaY = position2.y() - position1.y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
