package it.unibo.model.entities;

import it.unibo.utilities.Position2D;
import it.unibo.utilities.Vector2D;

public interface EntityFactory {

    Entity createTower(String name, Position2D position2d);

    Entity createEnemy(String name);

    Entity createWeapon(String name);

    Entity createBullet(String name, Position2D position2d, Vector2D direction);
}