package it.unibo.model.entities;

import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.TowerDeserializer;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Entity factory template.
 */
public class EntityFactoryImpl implements EntityFactory {

    private static final String JSON_EXTENSION = ".json";
    private static final String TOWERS_RESOURCES = "towers/json/";

    /**
     * Base constructor.
     */
    public EntityFactoryImpl() {
    }

    /**
     * Load a generic {@link IEntity}.
     * @param <T> generic type.
     * {@link IEntity}'s @param jsonFilePath
     * {@link IEntity}'s @param position2d
     * {@link IEntity}'s @param direction
     * {@link IEntity}'s @param entityType
     * @return parsed {@link IEntity}. 
     */
    @Override
    public <T> T loadEntity(final String filePath, final Position2D position2d, final Vector2D direction2d, Class<T> entityType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = readFileFromResources(filePath);
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, entityType);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Load a specific {@link Tower}.
     * {@link Tower}'s @param jsonFilePath to know which file to read.
     * @return parsed {@link IEntity}.
     * @throws IOException signals that an I/O exception of some sort has occurred.
     */
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

    /**
     * Load all {@link Tower}s from JSON.
     * @return all the {@link Tower}s istances available.
     * @throws IOException signals that an I/O exception of some sort has occurred.
     */
    @Override
    public Set<Tower> loadAllTowers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BasicTower.class, new TowerDeserializer<>(BasicTower.class));
        objectMapper.registerModule(module);

        try (Stream<Path> paths = Files.walk(Paths.get(ClassLoader.getSystemResource(TOWERS_RESOURCES).toURI()))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(JSON_EXTENSION))
                    .map(path -> {
                        try {
                            String jsonString = new String(Files.readAllBytes(path));
                            return objectMapper.readValue(jsonString, BasicTower.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(tower -> tower != null)
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Set.of();
    }

    /**
     * Utility method to read from files.
     * File's @param filePath to load.
     * @return the file's content.
     * @throws IOException signals that an I/O exception of some sort has occurred.
     */
    private String readFileFromResources(final String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath)))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return null;
    }
}
