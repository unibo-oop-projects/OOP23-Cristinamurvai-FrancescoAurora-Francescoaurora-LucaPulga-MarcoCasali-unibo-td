package it.unibo.model.entities;

import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.TowerDeserializer;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Entity factory template.
 */
public class EntityFactoryImpl implements EntityFactory {
    /**
     * Base constructor.
     */
    public EntityFactoryImpl() { }

    @Override
    public <T> T loadEntity(final String filePath, final Position2D position2d, final Vector2D direction2d, Class<T> entityType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath))); // TODO: UTILIZZARE INPUT STREAM COME IN TILEFACTORY_IMPL
            if (jsonString != null) {
                return (T) objectMapper.readValue(jsonString, entityType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tower loadTower(final String jsonFilePath) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BasicTower.class, new TowerDeserializer<>(BasicTower.class));

        objectMapper.registerModule(module);
        String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath))); // TODO: UTILIZZARE INPUT STREAM COME IN TILEFACTORY_IMPL
        try {
            Tower tower = objectMapper.readValue(jsonString, BasicTower.class);
            return tower;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
