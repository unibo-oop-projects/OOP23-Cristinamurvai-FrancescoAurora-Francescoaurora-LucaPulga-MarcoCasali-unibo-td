package main.java.it.unibo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectMapGui extends JFrame{
    private JLabel[] imageLabels;
    private int focusIndex;

    public SelectMapGui(JPanel oldGui) {

        // Imposta il layout del contentPane su BorderLayout
        oldGui.setLayout(new BorderLayout());

        // Array di immagini di esempio
        ImageIcon[] images = {
            new ImageIcon("image1.jpg"),
            new ImageIcon("image2.jpg"),
            new ImageIcon("image3.jpg"),
            new ImageIcon("image4.jpg"),
            new ImageIcon("image5.jpg")
        };
        
        focusIndex = 2; // Indice dell'immagine centrale

        

         // Pannello per contenere le immagini
        JPanel imagePanel = new JPanel(new GridLayout(1, 3));
        /*
        // Inizializza i JLabel con le immagini
        for (int i = 0; i < 3; i++) {
            int index = (focusIndex - 1 + i + images.length) % images.length; // Calcola l'indice dell'immagine
            imageLabels[i] = new JLabel(images[index]);
            imagePanel.add(imageLabels[i]);
        }
        */
        
        // Aggiunge il pannello delle immagini al frame
        oldGui.add(imagePanel, BorderLayout.CENTER);

        
        // Pulsante per scorrere a sinistra
        JButton leftButton = new JButton("<");
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aggiorna il focusIndex e ridisegna le immagini
                focusIndex = (focusIndex - 1 + images.length) % images.length;
                updateImages();
            }
        });

        // Aggiunge il pulsante per scorrere a sinistra al frame
        oldGui.add(leftButton, BorderLayout.WEST);

        // Pulsante per scorrere a destra
        JButton rightButton = new JButton(">");
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aggiorna il focusIndex e ridisegna le immagini
                focusIndex = (focusIndex + 1) % images.length;
                updateImages();
            }
        });

        // Aggiunge il pulsante per scorrere a destra al frame
        oldGui.add(rightButton, BorderLayout.EAST);

        // Richiedi al contenitore di aggiornare la GUI
        oldGui.revalidate();
        oldGui.repaint();

        /*
        

        setVisible(true);*/
    }

    private void updateImages() {
        // Calcola gli indici delle immagini
        int[] indices = {
            (focusIndex - 1 + imageLabels.length) % imageLabels.length,
            focusIndex,
            (focusIndex + 1) % imageLabels.length
        };

        // Aggiorna i JLabel con le nuove immagini
        for (int i = 0; i < 3; i++) {
            imageLabels[i].setIcon(new ImageIcon("image" + (indices[i] + 1) + ".jpg"));
        }
    }
}
