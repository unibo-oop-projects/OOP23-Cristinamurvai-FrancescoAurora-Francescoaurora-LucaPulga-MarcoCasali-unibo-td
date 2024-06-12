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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import it.unibo.controller.GameController;
import it.unibo.controller.GameControllerImpl;
import it.unibo.model.core.GameState;
import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.view.TowerCardFactoryImpl;
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
    private JLabel labelRound = null;

    // Add for enemies test
    private EnemiesPanel enemiesPanel;

    /**
     * .
     * @param mapName
     */
    public GuiGameStart(final String mapName) {
        final GameMap map = controller.setGameMap(mapName);
        contentPanel.setLayout(new BorderLayout()); // Main layout with BorderLayout

        // Sub-panel for the labels ‘Screw and screw image’, ‘Time wave’, ‘Available money’.
        JPanel labelPanel = new JPanel(new GridLayout(1, 5)); // Layout with one row and three columns

        // add label screw
        this.labelLife = new JLabel("Vite e immagine vite");
        labelPanel.add(labelLife);

        //Add label roud
        this.labelRound = new JLabel("Roud");
        labelPanel.add(labelRound);

        // add label time
        this.labelTime = new JLabel("Tempo ondata");
        labelPanel.add(labelTime);

        // Adding label money
        this.labelMoney = new JLabel("Soldi disponibili");
        labelPanel.add(labelMoney);

        //addming botton paused and settings
        JPanel buttonGui = new JPanel(new GridLayout(1, 2, 5, 0));
        JButton pauseButton = new JButton("Pause");

        ActionListener gamePause = e -> {
            this.controller.togglePause();
            showGamePause();
        };

        pauseButton.addActionListener(gamePause);

        ActionListener gameSettings = e -> {
            this.controller.togglePause();

        };

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(gameSettings);
        buttonGui.add(pauseButton);
        buttonGui.add(settingsButton);
        labelPanel.add(buttonGui);

        contentPanel.add(labelPanel, BorderLayout.NORTH);
        this.createMap(map);


        // Adding enemies layer and map layer overlapped
        this.layeredPane = new JPanel();
        this.layeredPane.setLayout(new OverlayLayout(this.layeredPane));
        this.enemiesPanel = new EnemiesPanel(new ArrayList<>(), mapPanel.getWidth() 
        / map.getColumns(), mapPanel.getHeight() / map.getRows());
        this.enemiesPanel.setOpaque(false);
        this.layeredPane.add(this.enemiesPanel);
        this.layeredPane.add(mapPanel);
        contentPanel.add(this.layeredPane, BorderLayout.CENTER);


        JPanel towerPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        TowerCardFactoryImpl towerCardFactory = new TowerCardFactoryImpl();
        towerCardFactory.createTowerCard(new BasicTower(2, mapName, mapName, mapName, null, null, ERROR, ALLBITS, ABORT, null, null, null, null));


        JPanel towerPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout with two columns and dynamic rows

        // Add towers
        towerPanel.add(createTowerCard("Tower1", "/towers/img/tower1.jpg", 100, 540, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower1", "/towers/img/tower1.jpg", 100, 540, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));


        JScrollPane scrollPane = new JScrollPane(towerPanel);
        scrollPane.setPreferredSize(new Dimension(300, 0));
        contentPanel.add(scrollPane, BorderLayout.EAST);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
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
        if (gameState.getRoundNumber() != 0) {
            this.labelRound.setText("Round: " + gameState.getRoundNumber());
        } else {
            this.labelRound.setText("Pre-round, position the towers");
        }
        if(gameState.getLastRound() == true) {
            showGameWin(gameState.getRoundNumber());
        }

        //Updating enemy layer
        this.enemiesPanel.updateView(gameState, mapPanel.getWidth() / gameState.getGameMap().getColumns(), mapPanel.getHeight() / gameState.getGameMap().getRows());
    }

    private void resizeImages(final GameMap map) {
        this.tiles.entrySet().forEach(e ->
                setScaledIcon(e.getKey(), e.getValue(),
                 this.mapPanel.getWidth() / map.getColumns(),
                 this.mapPanel.getHeight() / map.getRows())
        );
    }

    private void createMap(final GameMap map) {
        this.mapPanel = new JPanel(new GridLayout(map.getRows(), map.getColumns()));
        map.getTiles().forEach(t -> {
            final JButton cell = new JButton();
            setScaledIcon(cell, t.getSprite(), this.mapPanel.getWidth() / map.getColumns(),
                this.mapPanel.getHeight() / map.getRows());
            cell.addMouseListener(new MouseAdapter() {
                public void mouseClicked(final MouseEvent e) {
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

    private void setScaledIcon(final JButton cell, final String imgPath, int width, int height) {
        if (width == 0) {
            width = ICON_DEFAULT_SIZE;
        }
        if (height == 0) {
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
     * https://stackoverflow.com/a/6714381 .
     * @param srcImg source Image
     * @param width
     * @param height
     */
    private ImageIcon getScaledImage(final Image srcImg, final int width, final int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }

    private JPanel createTowerCard(final String name, final String imgPath, final int stat0, final int stat1, final int stat2) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String tooltipText = "<html><b>Name:</b> " + name + "<br><b>Stat0:</b> " + stat0 
        + "<br><b>Stat1:</b> " + stat1 + "<br><b>Stat2:</b> " + stat2 + "</html>";

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
            public void actionPerformed(final ActionEvent e) {
                showWeaponDialog(name);
            }
        });
        buttonPanel.add(weaponButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                selectedTowerName = name;
                selectedTowerImagePath = imgPath;
                System.out.println("Selected tower: " + name);
            }
        });
        return card;
    }

    private void showWeaponDialog(final String towerName) {
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

    private static void showGameWin(final int round) {
        final int widthDialog = 500;
        final int heightDialog = 200;
        JDialog winDialog = new JDialog();
        winDialog.setTitle("Game Win");
        winDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        winDialog.setSize(widthDialog, heightDialog);
        winDialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        winDialog.add(panel);
        placeWinComponents(panel, winDialog, round);

        winDialog.setVisible(true);
    }

    private static void placeWinComponents(final JPanel panel, final JDialog dialog, final int round) {
        final int alignmentXLabel = 50;
        final int alignmentYLabel = 20;
        final int widthLabel = 400;
        final int heightLabel = 75;
        final int alignmentXButton = 200;
        final int alignmentYButton = 120;
        final int widthButton = 100;
        final int heightButton = 25;
        panel.setLayout(null);
        String message = "<html>Congratulations! You have completed the game.<br>"
                       + "The number of rounds completed is: " + round + ".<br>"
                       + "Press \"Exit\" to leave the game.</html>";
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setBounds(alignmentXLabel, alignmentYLabel, widthLabel, heightLabel);
        panel.add(messageLabel);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(alignmentXButton, alignmentYButton, widthButton, heightButton);
        panel.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dialog.dispose();
                System.exit(0);
            }
        });
    }

    private void showGamePause() {
        final int widthDialog = 500;
        final int heightDialog = 200;
        JDialog dialog = new JDialog();
        dialog.setTitle("Game Pause");
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setSize(widthDialog, heightDialog);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        dialog.add(panel);final int alignmentXLabel = 50;
        final int alignmentYLabel = 20;
        final int widthLabel = 300;
        final int heightLabel = 75;
        final int alignmentXButton = 200;
        final int alignmentYButton = 120;
        final int widthButton = 100;
        final int heightButton = 25;
        panel.setLayout(null);
        String message = "<html>The game is paused! Click Resume to continue</html>";
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setBounds(alignmentXLabel, alignmentYLabel, widthLabel, heightLabel);
        panel.add(messageLabel);

        JButton resumeButton = new JButton("Resunme");
        resumeButton.setBounds(alignmentXButton, alignmentYButton, widthButton, heightButton);
        ActionListener gamePause = e -> {
            this.controller.togglePause();
            dialog.dispose();
        };
        resumeButton.addActionListener(gamePause);
        panel.add(resumeButton);
        
        dialog.setVisible(true);
    }
}
