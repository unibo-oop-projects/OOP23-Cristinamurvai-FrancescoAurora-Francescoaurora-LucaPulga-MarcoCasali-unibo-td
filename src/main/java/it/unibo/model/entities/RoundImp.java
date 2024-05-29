package it.unibo.model.entities;

/**
 * Implements for roud manager.
 */
public class RoundImp implements Round {
    private int numberEnemis;
    private double timeSpawn;
    private static final double DEFAULT_TIME_SPAWN = 4;
    private static final double MIN_TIME_SPAWN = 4;
    private static final int DEFAULT_NUMBER_ENEMIS = 3;
    private int rouds;
    private int[] enemisSpawn;

    /**
     * Classroom method.
     */
    public RoundImp() {
        numberEnemis = 2; //get enemis
        timeSpawn = DEFAULT_TIME_SPAWN;
        rouds = 0;
        enemisSpawn = new int[numberEnemis];
        enemisSpawn[0] = DEFAULT_NUMBER_ENEMIS;
    }

    /**
     * Increase Round and update parameters
     */
    public void increaseRoud() {
        rouds++;
        int tmp = rouds / 10;
        //posso aumentare di un valore costante determinato dal livello / qualcosa, ad esempio +2 per tipo attivo
        //se il tipo termina allora attivo un moltiplicatore che prima ha valore 1 quindi invece che aggiungerne 2 ne aggiunge 4 poi 8 ecc.
        if (rouds < numberEnemis * 10) {
            if (rouds % 10 == 0) {
                for (int i = 0; i <= tmp; i++) {
                    enemisSpawn[i] = DEFAULT_NUMBER_ENEMIS;
                }
                timeSpawn = DEFAULT_TIME_SPAWN;
            } else {
                for (int i = 0; i <= tmp; i++) {
                    enemisSpawn[i] += 2;
                }
                if (rouds % 10 == 9) {
                    timeSpawn = 3.5;
                }
            }
        } else {
            if (timeSpawn > MIN_TIME_SPAWN) {
                timeSpawn -= MIN_TIME_SPAWN;
            }
        }
    }

    /**
     * Get method for TimeSpawn.
     * @return timeSpawn
     */
    public double getTimeSpawn() {
        return timeSpawn;
    }

    /**
     * Get method for EnemisSpawn.
     * @return vector of enemisSpawn
     */
    public int[] getEnemisSpawn() {
        return enemisSpawn;
    }

    /**
     * Get actual roud.
     * @return rouds+1
     */
    public int getRoud() {
        return rouds + 1;
    }
}
