package it.unibo.model.entities;

public class RoundManagerImp implements RoundManager {
    private int numberEnemis;
    private double timeSpawn;
    private static double DEFAULT_TIME_SPAWN = 4;
    private static int DEFAULT_NUMBER_ENEMIS = 3;
    private int rouds;
    private int[] enemisSpawn;

    /**
     * Classroom method
     */
    public RoundManagerImp() {
        numberEnemis = 1;//get enemis
        timeSpawn = DEFAULT_TIME_SPAWN;
        rouds=0;
        enemisSpawn = new int[numberEnemis];
        enemisSpawn[0]= DEFAULT_NUMBER_ENEMIS;
    }

    /**
     * Increase Round and update parameters
     */
    public void increaseRoud() {
        rouds++;
        //posso aumentare di un valore costante determinato dal livello / qualcosa, ad esempio +2 per tipo attivo
        //se il tipo termina allora attivo un moltiplicatore che prima ha valore 1 quindi invece che aggiungerne 2 ne aggiunge 4 poi 8 ecc.
        if (rouds%10==0) {
            int tmp = rouds / 10;
            for (int i = 0; i < tmp; i++) {
                enemisSpawn[i] = DEFAULT_NUMBER_ENEMIS;
            }
        }
    }
}
