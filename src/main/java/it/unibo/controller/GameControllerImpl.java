package it.unibo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import it.unibo.model.core.GameEngine;
import it.unibo.model.core.GameEngineImpl;
import it.unibo.model.core.GameState;
import it.unibo.model.map.GameMap;
import it.unibo.model.map.GameMapFactory;
import it.unibo.model.map.GameMapFactoryImpl;
import it.unibo.model.utilities.Position2D;
import it.unibo.view.GameView;

/**
 * Implementation of {@link GameController}.
 */
public class GameControllerImpl implements GameController {
    private final GameMapFactory mapFactory = new GameMapFactoryImpl();
    private final GameEngine engine = new GameEngineImpl();
    private final Set<GameView> views = new HashSet<>();

    /**
     * Constructor.
     */
    public GameControllerImpl() {
        this.engine.registerObserver(this);
    }

    @Override
    public void update(final GameState gameState) {
        this.views.forEach(v -> v.update(gameState));
    }

    @Override
    public void startGame() {
        this.engine.start();
    }

    @Override
    public void togglePause() {
        this.engine.togglePause();
    }

    @Override
    public List<String> getAvailableMaps() {
        return List.of("grass","water","lava");
    }

    @Override
    public GameMap setGameMap(final String name) {
        final GameMap map = mapFactory.fromName(name);
        this.engine.setGameMap(map);
        return map;
    }

    @Override
    public void registerView(final GameView view) {
        this.views.add(view);
    }

    @Override
    public void buildTower(final int id, final Position2D pos) {
        this.engine.buildTower(id, pos);
    }
}
