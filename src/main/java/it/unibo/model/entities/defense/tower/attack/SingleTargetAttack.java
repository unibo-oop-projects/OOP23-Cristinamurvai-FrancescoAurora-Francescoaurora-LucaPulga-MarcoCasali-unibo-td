package it.unibo.model.entities.defense.tower.attack;

import java.util.Optional;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of single target attack.
 */
public class SingleTargetAttack implements AttackStrategy {

    @Override
    public void attack(final Tower tower, final Optional<Enemy> enemy) {
        enemy.ifPresent(e -> {
            long lastShotTime = tower.getCurrentWeapon().getLastShotTime();
            long currentTime = System.currentTimeMillis();
            long timeSinceLastShot = currentTime - lastShotTime;
            long fireRateInMilliseconds = (long) (5000 / tower.getCurrentWeapon().getFrequency());

            if (timeSinceLastShot >= fireRateInMilliseconds) {
                System.out.println(tower.getName() + " attacco a " + e.getName());
                Vector2D direction = calculateDirection(tower.getPosition(), e.getPosition());
                Bullet bullet = new BulletImpl(1, "bullet", "base", "bullet/img/bullet.png", tower.getPosition(), direction, 0.25, 1, e);

                tower.getBullets().add(bullet);
                tower.getCurrentWeapon().setLastShotTime(currentTime);
            }
        });
    }

    private Vector2D calculateDirection(Position2D towerPos, Position2D enemyPos) {
        double dx = enemyPos.x() - towerPos.x();
        double dy = enemyPos.y() - towerPos.y();
        return new Vector2D(dx, dy).normalize();
    }
}
