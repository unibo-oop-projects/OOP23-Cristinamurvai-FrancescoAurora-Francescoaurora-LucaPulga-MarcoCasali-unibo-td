package it.unibo.model.entities;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.entities.enemies.EnemyImpl;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

     
    public Tower loadTower(final String name, final Position2D position2d) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(name)));
            
            if (jsonString != null) {
                Tower jsonTower = objectMapper.readValue(jsonString, BasicTower.class);
                System.out.println("Nome della torre: " + jsonTower.getName());
                return jsonTower;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    @Override
    /**
     * load enemy.
     * @param name.
     * @return Enemy instance.
    */
    public Enemy loadEnemy(final String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(name)));
            
            if (jsonString != null) {
                Enemy jsonTower = objectMapper.readValue(jsonString, EnemyImpl.class);
                System.out.println("Nome della torre: " + jsonTower.getName());
                return jsonTower;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    /**
     * load weapon.
     * @param name.
     * @return Weapon instance.
     */
    public Weapon loadWeapon(final String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(name)));
            
            if (jsonString != null) {
                Weapon jsonTower = objectMapper.readValue(jsonString, WeaponImpl.class);
                System.out.println("Nome della torre: " + jsonTower.getName());
                return jsonTower;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    /**
     * load bullet.
     * @param name.
     * @param position2d.
     * @param direction.
     * @return Bullet instance.
     */
    public Bullet loadBullet(final String name, final Position2D position2d, Vector2D direction) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(name)));
            
            if (jsonString != null) {
                Bullet jsonTower = objectMapper.readValue(jsonString, BulletImpl.class);
                System.out.println("Nome della torre: " + jsonTower.getName());
                return jsonTower;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T loadEntity(final String name, final Position2D position2d, Class<T> entityType) {
        ObjectMapper objectMapper = new ObjectMapper();
    
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(name)));
    
            if (jsonString != null) {
                if (entityType.equals(Bullet.class)) {
                    return (T) objectMapper.readValue(jsonString, BulletImpl.class);
                } else if (entityType.equals(Weapon.class)) {
                    return (T) objectMapper.readValue(jsonString, WeaponImpl.class);
                } else if (entityType.equals(Tower.class)) {
                    return (T) objectMapper.readValue(jsonString, BasicTower.class);
                } else {
                    throw new IllegalArgumentException("Tipo di entità non supportato");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return null;
    }
}
