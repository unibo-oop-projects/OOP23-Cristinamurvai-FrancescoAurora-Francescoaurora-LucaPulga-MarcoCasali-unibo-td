package it.unibo.model.entities.defense.tower;

import java.util.Set;
import java.util.Optional;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class BasicTower extends AbstractTower {

    public BasicTower(int id, String name, String type, Position2D position2d, Vector2D direction2d, int cost,
            int level, int range, Set<Weapon> weapons, Weapon currentWeapon) {
        super(id, name, type, position2d, direction2d, cost, level, range, weapons, currentWeapon);
    }

    @Override
    public void attack(Set<Enemy> enemies) {
        // Seleziona l'enemy da targettare all'interno del raggio d'azione
        Optional<Enemy> optionalEnemy = targetEnemy(enemies);

        // Se viene selezionato un nemico e la frequenza di sparatoria è stata rispettata, spara un bullet
        optionalEnemy.ifPresent(enemy -> {
            long lastShotTime = this.getCurrentWeapon().getLastShotTime();
            long currentTime = System.currentTimeMillis();
            long timeSinceLastShot = currentTime - lastShotTime;
            long fireRate = this.getCurrentWeapon().getFrequency();

            if (timeSinceLastShot >= fireRate) {
                // Crea e avvia un thread Bullet per inseguire il nemico
                Bullet bullet = new BulletImpl(124, "bullet", "base", this.position2d , new Vector2D(0, 0), 100, 100, enemy);
                Thread bulletThread = new Thread((Runnable)bullet);
                bulletThread.start();

                // Aggiorna il tempo dell'ultimo sparo
                this.getCurrentWeapon().setLastShotTime(currentTime);
            }
        });
    }

    @Override
    public Optional<Enemy> targetEnemy(Set<Enemy> enemies) {
        Enemy targetEnemy = null;
        double minDistance = Double.MAX_VALUE;
        for (Enemy enemy : enemies) {
            double distance = calculateDistance(this.getPosition(), enemy.getPosition());
            if (distance <= this.getRange()) {
                targetEnemy = enemy;
                break;
            }
        }
        // Enemy nearestEnemy = null;
        // double minDistance = Double.MAX_VALUE;
        // for (Enemy enemy : enemies) {
        //     double distance = calculateDistance(this.getPosition(), enemy.getPosition());
        //     if (distance < minDistance) {
        //         nearestEnemy = enemy;
        //         minDistance = distance;
        //     }
        // }
        return Optional.ofNullable(targetEnemy);
    }

    private double calculateDistance(Position2D position1, Position2D position2) {
        int deltaX = position2.x() - position1.x();
        int deltaY = position2.y() - position1.y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
