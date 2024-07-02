package it.unibo.model.entities.defense.manager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;
import it.unibo.model.player.Player;

/**
 * Represents the defense manager implementation to manage all the defense
 * entities such as {@link Tower} and {@link Bullet}.
 */
public class DefenseManagerImpl implements DefenseManager {

    private final Set<Tower> towers = new HashSet<>();
    private final EntityFactory towerFactory = new EntityFactoryImpl();
    private Optional<GameMap> map = Optional.empty();
    private final Logger logger = LoggerFactory.getLogger(BulletImpl.class);

    /**
     * Selected @param tower to be built, chosen by the {@link Player}.
     */
    @Override
    public void buildTower(final Tower tower) {
        try {
            final Tower newTower = towerFactory.loadTower("towers/json/tower" + tower.getId() + ".json");
            newTower.setPosition(tower.getPosition());
            map.ifPresent(gameMap -> gameMap.buildTower(newTower));
            towers.add(newTower);
        } catch (IOException e) {
            logger.error("An error occured while trying building a tower from DefenseManager: " + e);
        }
    }

    /**
     * Implement {@link Tower}'s observer.
     */
    @Override
    public void update(final GameState gameState) {
        if (!gameState.isPaused()) {
            towers.forEach(tower -> {
                tower.attack(gameState.getEnemies());
                tower.getBullets().forEach(b -> b.update(gameState));
            });
        }
    }

    /**
     * Represents the towers.
     *
     * @return a set of all the active towers.
     */
    @Override
    public Set<Tower> getTowers() {
        return Set.copyOf(towers);
    }

    /**
     * Represents the number of towers.
     *
     * @return The number of all the active towers.
     */
    @Override
    public int getNumberOfTowers() {
        return this.towers.size();
    }

    /**
     * Set the actual map.
     *
     * @param gameMap actual map.
     */
    @Override
    public void setMap(final GameMap gameMap) {
        this.map = Optional.of(gameMap);
    }

    /**
     * Represents the Bullets fired by a specific {@link Tower}.
     *
     * @return a set of all the active bullets fired by a {@link Tower}.
     */
    @Override
    public Set<Bullet> getBullets() {
        return towers.stream()
                .flatMap(tower -> tower.getBullets().stream())
                .collect(Collectors.toSet());
    }
}
