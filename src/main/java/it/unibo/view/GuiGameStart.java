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
import it.unibo.model.map.GameMap;
import it.unibo.view.enemies.EnemiesPanel;

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

        towerPanel.add(createTowerCard("Tower1", "towers/img/tower1.png", 100, 540, 1));
        towerPanel.add(createTowerCard("Tower2", "towers/img/tower2.png", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower1", "towers/img/tower1.png", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "towers/img/tower2.png", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower1", "towers/img/tower1.png", 100, 540, 1));
        towerPanel.add(createTowerCard("Tower2", "towers/img/tower2.png", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower1", "towers/img/tower1.png", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "towers/img/tower2.png", 20, 100, 1));

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

    private JPanel createTowerCard(String name, String imgPath, int stat0, int stat1, int stat2) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String tooltipText = "<html><b>Name:</b> " + name + "<br><b>Stat0:</b> " + stat0 +
        "<br><b>Stat1:</b> " + stat1 + "<br><b>Stat2:</b> " + stat2 + "</html>";

        card.setToolTipText(tooltipText);
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
        JLabel imgLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(imgPath)));
        imgLabel.setPreferredSize(new Dimension(200, 150));
        imgPanel.add(imgLabel, BorderLayout.NORTH);
        card.add(imgPanel, BorderLayout.NORTH);

        // Panel for stats with left margin
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel nameLabel = new JLabel(name);
        statsPanel.add(nameLabel);

        JLabel stat1Label = new JLabel("Stat1: " + stat1);
        statsPanel.add(stat1Label);

        card.add(statsPanel, BorderLayout.CENTER);

        // Button panel with centered button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton weaponButton = new JButton("Weapons");
        weaponButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showWeaponDialog(name);
            }
        });
        buttonPanel.add(weaponButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedTowerName = name;
                selectedTowerImagePath = imgPath;
                System.out.println("Selected tower: " + name);
            }
        });
        return card;
    }

    private void showWeaponDialog(String towerName) {
        JDialog weaponDialog = new JDialog(this, "Weapons for " + towerName, true);
        weaponDialog.setSize(400, 300);

        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));

        weaponPanel.add(new JLabel("Weapon 1: Damage 10"));
        weaponPanel.add(new JLabel("Weapon 2: Damage 20"));
        weaponPanel.add(new JLabel("Weapon 3: Damage 30"));

        weaponDialog.add(weaponPanel);
        weaponDialog.setLocationRelativeTo(this);
        weaponDialog.setVisible(true);
    }
}