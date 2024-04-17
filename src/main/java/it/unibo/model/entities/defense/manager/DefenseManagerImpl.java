package it.unibo.model.entities.defense.manager;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DefenseManagerImpl implements DefenseManager {

    private List<Thread> towerThreads;
    private ExecutorService executorService;
    // private Set<Tower> towers = new HashSet<>();

    public DefenseManagerImpl() {
        this.towerThreads = new ArrayList<>();
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void buildTower(Tower tower) {
        Runnable towerRunnable = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                tower.attack();
                try {
                    Thread.sleep(1000); // Attendi un secondo prima di attaccare di nuovo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        executorService.submit(towerRunnable);
    }

    @Override
    public Set<Enemy> getEnemies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnemies'");
    }

    @Override
    public Set<Tower> getTowers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTowers'");
    }

    @Override
    public Enemy getNearestEnemy(Position2D position, int radius) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNearestEnemy'");
    }

    @Override
    public void activateAllTowers(List<Tower> towers) {
        stopAllTowers(); // Ferma tutti i thread delle torri
        executorService = Executors.newCachedThreadPool(); // Crea un nuovo pool di thread
        for (Tower tower : towers) {
            buildTower(tower); // Aggiungi e avvia nuovamente le torri
        }
    }

    @Override
    public void stopAllTowers() {
        executorService.shutdownNow();
    }
    
}
