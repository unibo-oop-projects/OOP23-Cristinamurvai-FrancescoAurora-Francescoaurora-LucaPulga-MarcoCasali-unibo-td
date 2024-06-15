package it.unibo.model.entities.defense.tower.attack;

import java.util.Optional;
import java.util.Set;

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
    public void attack(final Tower tower, final Optional<Set<Enemy>> enemies) {

        // Se viene selezionato un nemico e la frequenza di sparatoria è stata rispettata, spara un bullet
        enemies.ifPresentOrElse(
            enemySet -> {
                for (Enemy enemy : enemySet) {
                    System.out.println("Attacco a " + enemy.getName());
                    long lastShotTime = tower.getCurrentWeapon().getLastShotTime();
                    long currentTime = System.currentTimeMillis();
                    long timeSinceLastShot = currentTime - lastShotTime;
                    long fireRate = tower.getCurrentWeapon().getFrequency();

                    if (timeSinceLastShot >= fireRate) {
                        // Crea e avvia un thread Bullet per inseguire ed attaccare il nemico
                        Bullet bullet = new BulletImpl(124, "bullet", "base", "",
                                                        tower.getPosition(), new Vector2D(0, 0), 100, 100, enemy);
                        Thread bulletThread = new Thread((Runnable) bullet);
                        bulletThread.start();

                        // Aggiorna il tempo dell'ultimo sparo
                        tower.getCurrentWeapon().setLastShotTime(currentTime);
                    }
                }
            },
            () -> System.out.println("Nessun nemico nell'area")
        );
    }

    // @Override
    // public void attack(final Tower tower, final Optional<Set<Enemy>> enemies) {
    //     // Se viene selezionato un nemico e la frequenza di sparatoria è stata rispettata, spara un bullet
    //     enemies.ifPresentOrElse(
    //         enemySet -> enemySet.stream()
    //             .filter(enemy -> {
    //                 long lastShotTime = tower.getCurrentWeapon().getLastShotTime();
    //                 long currentTime = System.currentTimeMillis();
    //                 long timeSinceLastShot = currentTime - lastShotTime;
    //                 long fireRate = tower.getCurrentWeapon().getFrequency();

    //                 return timeSinceLastShot >= fireRate;
    //             })
    //             .findFirst()
    //             .ifPresent(enemy -> {
    //                 System.out.println("Attacco a " + enemy.getName());
    //                 Bullet bullet = new BulletImpl(
    //                     124, "bullet", "base", "",
    //                     tower.getPosition(), new Vector2D(0, 0), 100, 100, enemy
    //                 );
    //                 new Thread((Runnable) bullet).start();
    //                 tower.getCurrentWeapon().setLastShotTime(System.currentTimeMillis());
    //             }),
    //         () -> System.out.println("Nessun nemico nell'area")
    //     );
    // }

}
