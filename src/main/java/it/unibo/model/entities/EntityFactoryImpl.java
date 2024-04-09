package it.unibo.model.entities;

import it.unibo.utilities.*;
import it.unibo.model.entities.defense.*;
import it.unibo.model.entities.enemies.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EntityFactoryImpl implements EntityFactory {

    public EntityFactoryImpl() { }

    @Override
    public TowerImpl createTower(String name, Position2D position2d) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TowerImpl tower = objectMapper.readValue(name, TowerImpl.class);
            return tower;
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // o gestisci l'eccezione in modo appropriato
            return null; // o lancia un'eccezione o restituisci un valore di default
        }
    }

    @Override
    public Enemy createEnemy(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEnemy'");
    }

    @Override
    public Weapon createWeapon(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWeapon'");
    }

    @Override
    public Bullet createBullet(String name, Position2D position2d, Vector2D direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBullet'");
    }

    
}