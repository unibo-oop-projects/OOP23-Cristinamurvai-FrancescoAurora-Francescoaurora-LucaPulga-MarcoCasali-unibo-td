package it.unibo.view;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.OverlayLayout;
import it.unibo.controller.GameController;
import it.unibo.controller.GameControllerImpl;
import it.unibo.model.core.GameState;
import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;
import it.unibo.view.enemies.EnemiesPanel;
import it.unibo.view.defense.TowerCardView;
import it.unibo.view.defense.WeaponCardView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Loading Game Screen.
 */
public class GuiGameStart extends JFrame implements GameView {
    private static final int ICON_DEFAULT_SIZE = 20;
    private String selectedTowerName = null;
    private String selectedTowerImagePath = null;
    private JPanel contentPanel = new JPanel();
    private JPanel mapPanel;
    private JPanel layeredPane;
    private Map<JButton, String> tiles = new HashMap<>();
    private GameController controller = new GameControllerImpl();
    private JLabel labelTime = null;
    private JLabel labelLife = null;
    private JLabel labelMoney = null;

    // Add for enemies test
    private EnemiesPanel enemiesPanel;

    public GuiGameStart(final String mapName) {
        final GameMap map = controller.setGameMap(mapName);
        contentPanel.setLayout(new BorderLayout()); // Main layout with BorderLayout

        // Sub-panel for the labels ‘Screw and screw image’, ‘Time wave’, ‘Available money’.
        JPanel labelPanel = new JPanel(new GridLayout(1, 3)); // Layout with one row and three columns

        // add label screw
        this.labelLife = new JLabel("Vite e immagine vite");
        labelPanel.add(labelLife);

        // add label time
        this.labelTime = new JLabel("Tempo ondata");
        labelPanel.add(labelTime);

        // Adding label money
        this.labelMoney = new JLabel("Soldi disponibili");
        labelPanel.add(labelMoney);

        contentPanel.add(labelPanel, BorderLayout.NORTH);
        this.createMap(map);


        // Adding enemies layer and map layer overlapped
        this.layeredPane = new JPanel();
        this.layeredPane.setLayout(new OverlayLayout(this.layeredPane));
        this.enemiesPanel = new EnemiesPanel(new ArrayList<>(), mapPanel.getWidth() / map.getColumns(), mapPanel.getHeight()/ map.getRows());
        this.enemiesPanel.setOpaque(false);
        this.layeredPane.add(this.enemiesPanel);
        this.layeredPane.add(mapPanel);
        contentPanel.add(this.layeredPane, BorderLayout.CENTER);


        JPanel towerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        TowerCardView towerCardView = new TowerCardView(new BasicTower(2, mapName, mapName, mapName, null, null, ERROR, ALLBITS, ABORT, null, null, null, null));
        
        towerPanel.add(towerCardView);
        towerPanel.add(towerCardView);
        towerPanel.add(towerCardView);
        towerPanel.add(towerCardView);
        towerPanel.add(towerCardView);
        towerPanel.add(towerCardView);

        JScrollPane scrollPane = new JScrollPane(towerPanel);
        scrollPane.setPreferredSize(new Dimension(300, 0));
        contentPanel.add(scrollPane, BorderLayout.EAST);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImages(map);
            }
        });
        Toolkit.getDefaultToolkit().setDynamicLayout(false);

        this.add(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.controller.registerView(this);
        this.controller.startGame();
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public void update(final GameState gameState) {
        this.labelTime.setText(gameState.getRoundTime());
        this.labelMoney.setText("Your money: " + gameState.getMoney());
        this.labelLife.setText("Your lives: " + gameState.getLives());


        //Updating enemy layer
        this.enemiesPanel.updateView(gameState, mapPanel.getWidth() / gameState.getGameMap().getColumns(), mapPanel.getHeight() / gameState.getGameMap().getRows());
    }

    private void resizeImages(GameMap map) {
        this.tiles.entrySet().forEach( e ->
                setScaledIcon(e.getKey(), e.getValue(),
                 this.mapPanel.getWidth() / map.getColumns(),
                 this.mapPanel.getHeight() / map.getRows())
        );
    }

    private void createMap(GameMap map) {
        this.mapPanel = new JPanel(new GridLayout(map.getRows(), map.getColumns()));
        map.getTiles().forEach(t -> {
            final JButton cell = new JButton();
            setScaledIcon(cell, t.getSprite(), this.mapPanel.getWidth() / map.getColumns(),
                this.mapPanel.getHeight() / map.getRows());
            cell.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (selectedTowerName != null) {
                        setScaledIcon(cell, selectedTowerImagePath, mapPanel.getWidth() / map.getColumns(),
                            mapPanel.getHeight() / map.getRows());
                        tiles.put(cell, selectedTowerImagePath);
                        System.out.println("Placed " + selectedTowerName + " at cell " + cell.getText());
                    }
                }
            });
            this.tiles.put(cell, t.getSprite());
            this.mapPanel.add(cell);
        });
    }

    private void setScaledIcon(JButton cell, String imgPath, int width, int height) {
        if(width == 0) {
            width = ICON_DEFAULT_SIZE;
        }
        if(height == 0) {
            height = ICON_DEFAULT_SIZE;
        }
        Image icon = null;
        try {
            icon = ImageIO.read(ClassLoader.getSystemResource(imgPath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error when retrieving " + imgPath);
        }
        cell.setIcon(getScaledImage(icon, width, height));
    }

    /**
     * TODO reference
     * https://stackoverflow.com/a/6714381
     */
    private ImageIcon getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
    
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();
    
        return new ImageIcon(resizedImg);
    }
}