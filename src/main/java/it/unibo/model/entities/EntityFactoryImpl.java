package it.unibo.model.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.TowerDeserializer;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Entity factory template.
 */
public class EntityFactoryImpl implements EntityFactory {

    private final Logger logger = LoggerFactory.getLogger(EntityFactoryImpl.class);

    /**
     * Load a generic {@link IEntity}.
     *
     * @param <T> generic type. {@link IEntity}'s @param jsonFilePath
     * {@link IEntity}'s @param position2d {@link IEntity}'s @param direction
     * {@link IEntity}'s @param entityType
     * @return parsed {@link IEntity}.
     */
    @Override
    public <T> T loadEntity(final String filePath, 
                            final Position2D position2d, 
                            final Vector2D direction2d, 
                            final Class<T> entityType) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final String jsonString = readFileFromResources(filePath);
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, entityType);
            }
        } catch (final IOException e) {
            logger.error("An error occured while trying loading entity: " + e);
        }
        return null;
    }

    /**
     * Load a specific {@link Tower}. {@link Tower}'s @param jsonFilePath to
     * know which file to read.
     *
     * @return parsed {@link IEntity}.
     * @throws IOException signals that an I/O exception of some sort has
     * occurred.
     */
    @Override
    public Tower loadTower(final String jsonFilePath) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(BasicTower.class, new TowerDeserializer<>(BasicTower.class));

        objectMapper.registerModule(module);
        try {
            final String jsonString = readFileFromResources(jsonFilePath);
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, BasicTower.class);
            }
        } catch (IOException e) {
            logger.error("An error occured while trying loading a tower: " + e);
        }
        return null;
    }

    /**
     * Load all {@link Tower}s from JSON.
     *
     * @return all the {@link Tower}s istances available.
     * @throws IOException signals that an I/O exception of some sort has
     * occurred.
     */
    @Override
    public Set<Tower> loadAllTowers() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(BasicTower.class, new TowerDeserializer<>(BasicTower.class));
        objectMapper.registerModule(module);
        try {
            final Set<Tower> towers = new HashSet<>();
            for (final String file : getTowerFiles()) {
                towers.add(loadTower(file));
            }
            return towers;
        } catch (IOException e) {
            logger.error("An error occured while trying loading a tower: " + e);
        }
        return Set.of();
    }

    /**
     * Get all the towers' json.
     * @return A List with all towers files.
     */
    private List<String> getTowerFiles() {
        return List.of("towers/json/tower1.json", "towers/json/tower2.json", "towers/json/tower3.json");
    }

    /**
     * Utility method to read from files. File's
     *
     * @param filePath to load.
     * @return the file's content.
     * @throws IOException signals that an I/O exception of some sort has
     * occurred.
     */
    private String readFileFromResources(final String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (FileNotFoundException e) {
            logger.error("File not found: " + filePath + ": " + e.getMessage(), e);
            throw new IOException("File not found: " + filePath, e);
        } catch (IOException e) {
            logger.error("I/O error reading file: " + filePath + ": " + e.getMessage(), e);
            throw e;
        }
    }
}
