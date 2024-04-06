package it.unibo.utilities;

import java.io.*;

public class Serializer implements Serializable{

    /**
     * Metodo generico per serializzare un oggetto su file.
     * @param <T>
     * @param fileName
     * @param object
     */
    public static <T extends Serializable> void serializeObject(String fileName, T object) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(object);
            System.out.println("Serialized object is saved in " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo generico per deserializzare un oggetto da file
    public static <T extends Serializable> T deserializeObject(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (T) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
