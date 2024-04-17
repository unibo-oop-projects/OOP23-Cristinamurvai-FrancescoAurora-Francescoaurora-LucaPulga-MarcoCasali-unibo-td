package it.unibo.model.entities;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Entity factory template.
 */
public class EntityFactoryImpl implements EntityFactory {
    /**
     * Base constructor.
     */
    public EntityFactoryImpl() { }

    @Override
    /**
     * Tower instance.
     * @param name.
     * @param position2d.
     * @return Tower instance.
     */
    public Tower createTower(final String name, final Position2D position2d) {
        // try {
        //     ObjectMapper objectMapper = new ObjectMapper();
        //     Tower tower = objectMapper.readValue(new File(name), TowerImpl.class);
        //     return tower;
        // } catch (JsonProcessingException e) {
        //     e.printStackTrace();
        //     return null;
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     return null;
        // }
        throw new UnsupportedOperationException("Unimplemented method 'createTower'");
    }
   
    @Override
    /**
     * Create enemy.
     * @param name.
     * @return Enemy instance.
    */
    public Enemy createEnemy(final String name) {
        throw new UnsupportedOperationException("Unimplemented method 'createEnemy'");
    }

    @Override
    /**
     * Create weapon.
     * @param name.
     * @return Weapon instance.
     */
    public Weapon createWeapon(final String name) {
        throw new UnsupportedOperationException("Unimplemented method 'createWeapon'");
    }

    @Override
    /**
     * Create bullet.
     * @param name.
     * @param position2d.
     * @param direction.
     * @return Bullet instance.
     */
    public Bullet createBullet(final String name, final Position2D position2d, Vector2D direction) {
        throw new UnsupportedOperationException("Unimplemented method 'createBullet'");
    }    
}
