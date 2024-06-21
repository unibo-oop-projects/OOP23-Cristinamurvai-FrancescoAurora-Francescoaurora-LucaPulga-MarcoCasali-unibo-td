package it.unibo.model.entities.defense.tower.attack;

import java.util.Optional;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of single target attack.
 */
public class SingleTargetAttack implements AttackStrategy {

    @Override
    public void attack(final Tower tower, final Optional<Enemy> enemy) {
        // Se viene selezionato un nemico e la frequenza di sparatoria è stata rispettata, spara un bullet
        enemy.ifPresent(e -> {
            long lastShotTime = tower.getCurrentWeapon().getLastShotTime();
            long currentTime = System.currentTimeMillis();
            long timeSinceLastShot = currentTime - lastShotTime;
            long fireRate = tower.getCurrentWeapon().getFrequency();

            if (timeSinceLastShot >= fireRate) {
                System.out.println(tower.getName() + " attacco a " + e.getName());
                Bullet bullet = new BulletImpl(
                    124, "bullet", "base", "bullet/img/bullet.png",
                    tower.getPosition(), new Vector2D(0, 0), 20, 5, e
                );
                
                tower.getBullets().add(bullet);
                tower.getCurrentWeapon().setLastShotTime(currentTime);
            }
        });
    }
}
