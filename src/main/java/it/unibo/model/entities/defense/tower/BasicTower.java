package it.unibo.model.entities.defense.tower;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class BasicTower extends AbstractTower {

    @JsonCreator
    public BasicTower(@JsonProperty("id") int id, 
                      @JsonProperty("name") String name, 
                      @JsonProperty("type") String type, 
                      @JsonProperty("position2d") Position2D position2d, 
                      @JsonProperty("direction2d") Vector2D direction2d, 
                      @JsonProperty("cost") int cost, 
                      @JsonProperty("level") int level, 
                      @JsonProperty("range") int range, 
                      @JsonProperty("weapons") Set<Weapon> weapons, 
                      @JsonProperty("currentWeapon") Weapon currentWeapon) {
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
