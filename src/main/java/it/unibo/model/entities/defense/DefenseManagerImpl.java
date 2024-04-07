// package it.unibo.model.entities.defense;

// import java.util.Set;

// import java.util.HashSet;

// import it.unibo.model.entities.api.EnemiesManager;
// import it.unibo.model.entities.api.Enemy;
// import it.unibo.model.entities.api.Entity;
// import it.unibo.model.entities.impl.EnemiesManagerImpl;
// import it.unibo.utilities.Position2D;


// public class DefenseManagerImpl implements DefenseManager {

//     private Set<Tower> towers = new HashSet<>();
//     EnemiesManager enemiesManager = new EnemiesManagerImpl();
//     TowerFactory towerFactory = new TowerFactory();

//     public DefenseManagerImpl() {
//         this.towers = new HashSet<>();
//     }

//     @Override
//     public void buildTower(String filename) {
//         Entity t = towerFactory.createEntity(filename);
//         this.towers.add((TowerImpl) t);
//     }

//     @Override
//     public Set<Enemy> getEnemies() {
//         return Set.copyOf(this.enemiesManager.getCurrentEnemies());
//     }

//     @Override
//     public Set<Tower> getTowers() {
//         return Set.copyOf(this.towers);
//     }

//     @Override
//     public Enemy getNearestEnemy(Position2D position, int radius) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getNearestEnemy'");
//     }

//     public void removeTower(Tower tower) {
//         this.towers.remove((TowerImpl) tower);
//     }

//     public void upgradeTowers() {
//         towers.forEach(tower -> {System.out.println("Tower upgraded");
//         });
//     }
// }
