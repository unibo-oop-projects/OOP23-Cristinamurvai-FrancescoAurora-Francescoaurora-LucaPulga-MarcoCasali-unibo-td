package it.unibo.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import it.unibo.model.core.GameEngine;
import it.unibo.model.core.GameEngineImpl;
import it.unibo.model.core.GameState;
import it.unibo.model.map.GameMapFactory;
import it.unibo.model.map.GameMapFactoryImpl;
import it.unibo.view.GameView;

/**
 * Implementation of {@link GameController}.
 */
public class GameControllerImpl implements GameController {
    private GameState gameState = null;
    private final GameMapFactory mapFactory = new GameMapFactoryImpl();
    private final GameEngine engine = new GameEngineImpl();
    private final Set<GameView> views = new HashSet<>();

    @Override
    public void update(GameState gameState) {
        this.gameState = gameState;
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
    public Map<String, String> getAvailableMaps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableMaps'");
    }

    @Override
    public void setGameMap(String name) {
        this.engine.setGameMap(mapFactory.fromName(name));
    }

    @Override
    public void registerView(GameView view) {
        this.views.add(view);
    }
}
