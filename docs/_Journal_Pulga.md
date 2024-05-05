## PRIMA DEL 15 Aprile 2024
### SCOPE: 
Sviluppo design parte di defense (torri, armi, proiettili)
### AZIONI: Design architettura software
### TASKS:  _SCADENZA_ 29 aprile 2024
- Luca --> Model, Control defense

---------

## 18/04/2024
Ridefinizione del Defense Model, ridefinizione organizzazione cartella.
Trovato il filepath da passare come parametro per trovare i json delle torri da caricare e successivamente trasformare in java objects.
Errore nella conversione da json a java object leggendo "tower1.json"

---------

## 20/04/2024
New defense model implementation with interfaces, abstract classes and concrete classes partly re-designed.
Prima implementazione della fisica del gioco.

---------

## 04/05/2024

EntityFactoryImpl e dunque EntityFactory:
	gestisce il loading della torre, ma non l'inizializzazione del thread della torre;
	metodo generico utile per tutti i tipi di entità;
DefenseManagerImpl:
	dopo il loading della torre da parte dell'EntityFactoryImpl, gestisce l'inizializzazione del thread della torre;
	
Main:
	test per lettura file json, creazione obj voluto e gestione dei thread con il proprio manager;
	
ALTRO:
	Aggiunta di @JsonProperty("id") nei parametri dei costruttori, altrimenti la libreria jackson non capisce cosa deve parsare;

---------

TODO: IMPLEMENTARE STRATEGY PER ATTACK E TARGET 
// Interfaccia per gli attacchi
interface AttackStrategy {
    void attack(List<Enemy> enemies);
}

// Implementazione di attacco a un singolo bersaglio
class SingleTargetAttack implements AttackStrategy {
    @Override
    public void attack(List<Enemy> enemies) {
        Enemy target = selectTarget(enemies);
        System.out.println("Attacco a " + target.getName());
    }

    private Enemy selectTarget(List<Enemy> enemies) {
        return enemies.get(new Random().nextInt(enemies.size()));
    }
}

// Implementazione di attacco ad area
class AreaAttack implements AttackStrategy {
    @Override
    public void attack(List<Enemy> enemies) {
        System.out.print("Attacco ad area: ");
        for (Enemy enemy : enemies) {
            System.out.print(enemy.getName() + " ");
        }
        System.out.println();
    }
}

// Interfaccia per la selezione del bersaglio
interface TargetSelectionStrategy {
    Enemy selectTarget(List<Enemy> enemies);
}

// Implementazione di selezione del bersaglio casuale
class RandomTargetSelection implements TargetSelectionStrategy {
    @Override
    public Enemy selectTarget(List<Enemy> enemies) {
        return enemies.get(new Random().nextInt(enemies.size()));
    }
}

// Implementazione di selezione del bersaglio basata sulla distanza
class DistanceBasedTargetSelection implements TargetSelectionStrategy {
    @Override
    public Enemy selectTarget(List<Enemy> enemies) {
        // Esempio: scegli il bersaglio più vicino
        Enemy nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Enemy enemy : enemies) {
            if (enemy.getDistanceFromTower() < minDistance) {
                minDistance = enemy.getDistanceFromTower();
                nearest = enemy;
            }
        }
        return nearest;
    }
}

// Classe della torre
class Tower {
    private AttackStrategy attackStrategy;
    private TargetSelectionStrategy targetSelectionStrategy;

    public Tower(AttackStrategy attackStrategy, TargetSelectionStrategy targetSelectionStrategy) {
        this.attackStrategy = attackStrategy;
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    public void attack(List<Enemy> enemies) {
        Enemy target = targetSelectionStrategy.selectTarget(enemies);
        attackStrategy.attack(enemies);
    }