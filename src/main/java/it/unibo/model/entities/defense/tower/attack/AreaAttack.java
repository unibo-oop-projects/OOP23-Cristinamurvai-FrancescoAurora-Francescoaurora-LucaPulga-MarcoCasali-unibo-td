package it.unibo.model.entities.defense.tower.attack;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of area attack.
 */
public class AreaAttack implements AttackStrategy {

    @Override
    public void attack(final Tower tower, final Optional<Enemy> enemy) {
        // Se viene selezionato un nemico e la frequenza di sparatoria è stata rispettata, spara un bullet
        // enemies.ifPresentOrElse(
        //     enemySet -> {
        //         for (Enemy enemy : enemySet) {
        //             System.out.println(tower.getName() + "Attacco multiplo verso " + enemy.getName());
        //             long lastShotTime = tower.getCurrentWeapon().getLastShotTime();
        //             long currentTime = System.currentTimeMillis();
        //             long timeSinceLastShot = currentTime - lastShotTime;
        //             long fireRate = tower.getCurrentWeapon().getFrequency();
        //             if (timeSinceLastShot >= fireRate) {
        //                 // Crea e avvia un thread Bullet per inseguire ed attaccare il nemico
        //                 Bullet bullet = new BulletImpl(2, "bullet", "base", "bullet/img/bullet.png", 
        //                                                 tower.getPosition(), new Vector2D(0, 0), 100, 100, enemy);
        //                 // Aggiorna il tempo dell'ultimo sparo
        //                 tower.getCurrentWeapon().setLastShotTime(currentTime);
        //             }
        //         }
        //     },
        //     () -> System.out.println(tower.getName() + "Nessun nemico nell'area")
        // );
	}
}
