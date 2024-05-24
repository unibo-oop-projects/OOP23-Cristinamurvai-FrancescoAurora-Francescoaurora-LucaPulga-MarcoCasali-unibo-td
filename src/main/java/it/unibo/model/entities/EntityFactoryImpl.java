package it.unibo.model.entities;

import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.TowerDeserializer;
import it.unibo.model.map.tile.Tile;
import it.unibo.model.map.tile.TileFeature;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Entity factory template.
 */
public class EntityFactoryImpl implements EntityFactory {
    private static final String JSON_EXTENSION = ".json";
    private static final String ENTITY_RESOURCES = "entities/";

    /**
     * Base constructor.
     */
    public EntityFactoryImpl() { }

    @Override
    public <T> T loadEntity(final String filePath, final Position2D position2d, final Vector2D direction2d, Class<T> entityType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = readFileFromResources(filePath);
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, entityType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: togliere e utilizzare il generico sopra
    @Override
    public Tower loadTower(final String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BasicTower.class, new TowerDeserializer<>(BasicTower.class));

        objectMapper.registerModule(module);
        try {
            String jsonString = readFileFromResources(jsonFilePath);
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, BasicTower.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readFileFromResources(final String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(ClassLoader.getSystemResourceAsStream(ENTITY_RESOURCES + filePath)))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return null;
    }

}