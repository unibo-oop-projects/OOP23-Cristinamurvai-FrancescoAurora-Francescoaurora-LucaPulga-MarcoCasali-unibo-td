package it.unibo;

import it.unibo.model.entities.defense.tower.BasicTower;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.view.GuiStart;

/**
 * Running the game.
 */
public final class GameLauncher {
    private GameLauncher() { }

    /**
     * Game Main.
     * @param args Main topics
     */
    public static void main(final String... args) {
        // Percorso del file JSON nella cartella delle risorse
        String jsonFilePath = "src/main/resources/towers/tower1.json";
        //Tower tower = entity.createTower(jsonFilePath, new Position2D(0, 0));

        // Inizializza un ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Ottieni un InputStream per il file JSON utilizzando il ClassLoader
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            if (jsonString != null) {
                // Deserializza il JSON in un'istanza di TowerImpl
                BasicTower towejson = objectMapper.readValue(jsonString, BasicTower.class);

                System.out.println("Nome della torre: " + towejson.getName());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new GuiStart();
    }
}
