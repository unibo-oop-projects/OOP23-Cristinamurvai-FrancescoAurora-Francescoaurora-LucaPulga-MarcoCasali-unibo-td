package it.unibo.model.entities;

import it.unibo.model.entities.defense.Tower;
import it.unibo.model.entities.defense.TowerImpl;
import it.unibo.model.entities.defense.Weapon;
import it.unibo.model.entities.defense.Bullet;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.utilities.Position2D;
import it.unibo.utilities.Vector2D;

public interface EntityFactory {

    TowerImpl createTower(String name, Position2D position2d);

    Enemy createEnemy(String name);

    Weapon createWeapon(String name);

    Bullet createBullet(String name, Position2D position2d, Vector2D direction);
}