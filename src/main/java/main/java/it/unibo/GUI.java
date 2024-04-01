package main.java.it.unibo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JButton startButton;

    public GUI (){

        // Imposta il titolo del frame
        super("Proggetto oop - Sostituire con nome gioco");

        // Imposta il comportamento di chiusura del frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea un pannello vuoto
        JPanel contentPane = new JPanel();

        // Imposta il layout del pannello vuoto con GridBagLayout
        contentPane.setLayout(new GridBagLayout());

        // Crea un GridBagConstraints per configurare il posizionamento del bottone
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Posizione della colonna
        gbc.gridy = 0; // Posizione della riga
        gbc.insets = new Insets(10, 10, 10, 10); // Margine esterno
        
        ActionListener startGameListen = e -> {
            //rimuovere bottone successivamente sarà tutto passatto all'oggetto che dovrà caricare le mappe
            contentPane.remove(startButton);
            // Richiedi al contenitore di aggiornare la GUI
            contentPane.revalidate();
            contentPane.repaint();
            
            new SelectMapGui();
        };
        
        // Creo il bottone start gioco
        startButton = new JButton("Start");
 
        // Aggiungo un ascoltatore per il bottone
        startButton.addActionListener(startGameListen);

        // Imposto dimensioni personalizzate per il bottone
        startButton.setPreferredSize(new Dimension(100, 50));

        // Imposta l'allineamento del bottone al centro
        gbc.anchor = GridBagConstraints.CENTER;

        // Aggiungo il bottone al pannello con le specifiche di GridBagConstraints
        contentPane.add(startButton, gbc);

        // Imposta il layout del frame
        this.getContentPane().setLayout(new BorderLayout());

        // Adatta il frame alle dimensioni dello schermo
        this.setExtendedState(MAXIMIZED_BOTH);

        // Aggiungi il pannello vuoto al frame
        this.getContentPane().add(contentPane, BorderLayout.CENTER);

        // Rendi visibile il frame
        this.setVisible(true);
    }
}
