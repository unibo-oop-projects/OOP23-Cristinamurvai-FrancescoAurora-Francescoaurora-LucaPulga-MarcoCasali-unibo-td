package it.unibo.model.entities;

import it.unibo.utilities.Position2D;
import it.unibo.utilities.Vector2D;
import it.unibo.model.entities.Entity;

class EntityFactoryImpl implements EntityFactory {

    public EntityFactoryImpl() { }

    @Override
    public Entity createEnemy(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEnemy'");
    }

    @Override
    public Entity createWeapon(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWeapon'");
    }

    @Override
    public Entity createBullet(String name, Position2D position2d, Vector2D direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBullet'");
    }

    // RESTITUIRE IL TIPO A RUNTIME (es: TOWER) e non ENTITY
    @Override
    public Entity createTower(String name, Position2D position2d) {
        return new AbstractEntity() {
            
            // implementare in abstractEntity
            @Override
            public int getId() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getId'");
            }

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getName'");
            }

            @Override
            public String getType() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getType'");
            }

            @Override
            public Position2D getPosition() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
            }

            @Override
            public Vector2D getDirection() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getDirection'");
            }
            
            // implementare metodi specifi relativi alla torre
        };
    }
}