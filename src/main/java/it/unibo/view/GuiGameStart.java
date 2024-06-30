package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
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
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.GameMap;
import it.unibo.model.utilities.ScaledImage;
import it.unibo.view.defense.DefensePanel;
import it.unibo.view.defense.TowerCardFactory;
import it.unibo.view.defense.TowerCardFactoryImpl;
import it.unibo.view.enemies.EnemiesPanel;

/**
 * Loading Game Screen.
 */
public class GuiGameStart extends JFrame implements GameView {

    private static final int ICON_DEFAULT_SIZE = 20;
    private static final int ICON_PANEL_SIZE = 50;
    private static final int ICON_BUTTON_SIZE = 80;
    private transient Tower selectedTower = null;
    private JPanel mapPanel;
    private JPanel layeredPane;
    private JScrollPane scrollPane;
    private Map<JButton, String> tiles = new HashMap<>();
    private transient GameController controller = new GameControllerImpl();
    private IconsPanel iconLabelPanel;
    private transient Image icon = null;
    private boolean pause = false;
    private JLabel pauseButton = null;
    private transient EntityFactory entityFactory;
    private transient TowerCardFactory towerCardFactory;
    private static final int WIDTH_SCROLL_PANE = 300;
    private static final int HGAP_BUTTON_GUI = 5;

    // Add for enemies test
    private DefensePanel defensePanel;
    private EnemiesPanel enemiesPanel;

    /**
     * constructor method.
     *
     * @param mapName name of the selected map
     * @param oldGui screen where to upload
     */
    public GuiGameStart(final String mapName, final JPanel oldGui) {
        controller.setGameMap(mapName);
        final GameMap map = controller.getGameMap();
        oldGui.setLayout(new BorderLayout()); // Main layout with BorderLayout

        // Sub-panel for the labels ‘Screw and screw image’, ‘Time wave’, ‘Available money’.
        iconLabelPanel = new IconsPanel(oldGui.getWidth(), ICON_PANEL_SIZE);
        oldGui.add(iconLabelPanel, BorderLayout.NORTH);

        //addming botton paused and settings
        JPanel buttonGui = new JPanel(new GridLayout(1, 2, HGAP_BUTTON_GUI, 0));

        try {
            icon = ImageIO.read(ClassLoader.getSystemResource("buttons/pause.png"));
        } catch (final IOException e) {
            System.err.println(e.getMessage());
            System.err.println("error when retrieving " + "buttons/pause.png");
        }

        pauseButton = new JLabel();
        pauseButton.setIcon(ScaledImage.getScaledImage(icon, ICON_BUTTON_SIZE, ICON_BUTTON_SIZE));

        pauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                controller.togglePause();
                Image tmp = null;
                if (pause) {
                    try {
                        tmp = ImageIO.read(ClassLoader.getSystemResource("buttons/pause.png"));
                    } catch (final IOException ex) {
                        System.err.println(ex.getMessage());
                        System.err.println("error when retrieving " + "buttons/pause.png");
                    }
                } else {
                    try {
                        tmp = ImageIO.read(ClassLoader.getSystemResource("buttons/playPause.png"));
                    } catch (final IOException ex) {
                        System.err.println(ex.getMessage());
                        System.err.println("error when retrieving " + "buttons/playPause.png");
                    }
                }
                pause = !pause;
                pauseButton.setIcon(ScaledImage.getScaledImage(tmp, ICON_BUTTON_SIZE, ICON_BUTTON_SIZE));
            }
        });

        JLabel settingsButton = new JLabel();
        try {
            icon = ImageIO.read(ClassLoader.getSystemResource("buttons/settings.png"));
        } catch (final IOException e) {
            System.err.println(e.getMessage());
            System.err.println("error when retrieving " + "buttons/settings.png");
        }
        settingsButton.setIcon(ScaledImage.getScaledImage(icon, ICON_BUTTON_SIZE, ICON_BUTTON_SIZE));
        buttonGui.add(pauseButton);
        buttonGui.add(settingsButton);
        iconLabelPanel.add(buttonGui);

        oldGui.add(iconLabelPanel, BorderLayout.NORTH);
        this.createMap(map);

        // Adding enemies layer and map layer overlapped
        this.layeredPane = new JPanel();
        this.layeredPane.setLayout(new OverlayLayout(this.layeredPane));
        this.enemiesPanel = new EnemiesPanel(mapPanel.getWidth() / map.getColumns(),
                mapPanel.getHeight() / map.getRows());
        this.enemiesPanel.setOpaque(false);
        this.layeredPane.add(this.enemiesPanel);

        this.defensePanel = new DefensePanel(new HashSet<>(), new HashSet<>(), mapPanel.getWidth() / map.getColumns(),
                mapPanel.getHeight() / map.getRows());
        this.defensePanel.setOpaque(false);
        this.layeredPane.add(this.defensePanel);

        this.layeredPane.add(mapPanel);
        oldGui.add(this.layeredPane, BorderLayout.CENTER);

        entityFactory = new EntityFactoryImpl();
        towerCardFactory = new TowerCardFactoryImpl();

        try {
            JPanel towerPanel = towerCardFactory.createDefensePanel(entityFactory.loadAllTowers());
            towerPanel.setLayout(new BoxLayout(towerPanel, BoxLayout.Y_AXIS));
            scrollPane = new JScrollPane(towerPanel);
            scrollPane.setPreferredSize(new Dimension(WIDTH_SCROLL_PANE, 0));
            oldGui.add(scrollPane, BorderLayout.EAST);
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }

        ComponentAdapter resize = new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeImages(map);
            }
        };

        //if you set this the window will not recognize that the jbutton are scaled,
        //instead putting the map when it goes to occupy all the space it is assigned to will work
        this.mapPanel.addComponentListener(resize);
        Toolkit.getDefaultToolkit().setDynamicLayout(false);

        this.controller.registerView(this);
        this.controller.startGame();

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();

    }

    @Override
    public final void update(final GameState gameState) {
        iconLabelPanel.update(gameState);
        if (gameState.isGameOver()) {
            showGameOver(gameState.getRoundNumber());
        }
        if (gameState.getLastRound()) {
            showGameWin(gameState.getRoundNumber());
        }

        //Updating enemy layer
        this.defensePanel.updateView(gameState, mapPanel.getWidth() / gameState.getGameMap().getColumns(),
                mapPanel.getHeight() / gameState.getGameMap().getRows());
        this.enemiesPanel.updateView(gameState, mapPanel.getWidth() / gameState.getGameMap().getColumns(),
                mapPanel.getHeight() / gameState.getGameMap().getRows());
    }

    /**
     * resize the map.
     *
     * @param map where to resize the map
     */
    private void resizeImages(final GameMap map) {
        this.tiles.entrySet().forEach(e
                -> setScaledIcon(e.getKey(), e.getValue(),
                        this.mapPanel.getWidth() / map.getColumns(),
                        this.mapPanel.getHeight() / map.getRows())
        );
    }

    /**
     * Create the map.
     *
     * @param map where to upload the map
     */
    private void createMap(final GameMap map) {
        this.mapPanel = new JPanel(new GridLayout(map.getRows(), map.getColumns()));
        map.getTiles().forEach(t -> {
            final JButton cell = new JButton();
            cell.setBorderPainted(false);
            setScaledIcon(cell, t.getSprite(), this.mapPanel.getWidth() / map.getColumns(),
                    this.mapPanel.getHeight() / map.getRows());
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    selectedTower = towerCardFactory.getSelectedTower();
                    if (selectedTower != null) {
                        selectedTower.setPosition(t.getPosition());
                        controller.buildTower(selectedTower);
                        System.out.println("Placed " + selectedTower.getName() + " at cell " + cell.getText());
                        selectedTower = null;
                    }
                }
            });
            this.tiles.put(cell, t.getSprite());
            this.mapPanel.add(cell);
        });
    }

    /**
     * Scale the image.
     *
     * @param cell cell to scale
     * @param imgPath image path
     * @param width image size
     * @param height image size
     */
    private void setScaledIcon(final JButton cell, final String imgPath, final int width, final int height) {
        int finalWidth = width;
        int finalHeight = height;
        if (width == 0) {
            finalWidth = ICON_DEFAULT_SIZE;
        }
        if (height == 0) {
            finalHeight = ICON_DEFAULT_SIZE;
        }
        Image icon = null;
        try {
            icon = ImageIO.read(ClassLoader.getSystemResource(imgPath));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("error when retrieving " + imgPath);
        }
        cell.setIcon(ScaledImage.getScaledImage(icon, finalWidth, finalHeight));
    }

    /**
     * shows the victory jdialog.
     *
     * @param round number of rounds played
     */
    private static void showGameWin(final int round) {
        final int widthDialog = 500;
        final int heightDialog = 200;
        JDialog winDialog = new JDialog();
        JPanel panel = new JPanel();

        winDialog.setTitle("Game Won");
        winDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        winDialog.setSize(widthDialog, heightDialog);
        winDialog.setLocationRelativeTo(null);
        winDialog.add(panel);
        placeWinComponents(panel, winDialog, round);
        winDialog.setVisible(true);
    }

    /**
     * Builds the jdialog.
     *
     * @param panel panel to print on
     * @param dialog to close jdialog
     * @param round number of rounds played
     */
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

        exitButton.addActionListener((final ActionEvent e) -> {
            dialog.dispose();
            closeGame();
        });
    }

    private static void showGameOver(final int round) {
        final int widthDialog = 500;
        final int heightDialog = 200;
        JDialog gameOverDialog = new JDialog();
        JPanel panel = new JPanel();

        gameOverDialog.setTitle("Game Over");
        gameOverDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        gameOverDialog.setSize(widthDialog, heightDialog);
        gameOverDialog.setLocationRelativeTo(null);
        gameOverDialog.add(panel);
        placeGameOverComponents(panel, gameOverDialog, round);
        gameOverDialog.setVisible(true);
    }

    /**
     * Builds the JDialog for game over.
     *
     * @param panel panel to print on
     * @param dialog JDialog to close
     * @param round number of rounds played
     */
    private static void placeGameOverComponents(final JPanel panel, final JDialog dialog, final int round) {
        final int alignmentXLabel = 50;
        final int alignmentYLabel = 20;
        final int widthLabel = 400;
        final int heightLabel = 75;
        final int alignmentXButton = 200;
        final int alignmentYButton = 120;
        final int widthButton = 100;
        final int heightButton = 25;
        panel.setLayout(null);
        String message = "<html>Game Over!<br>"
                + "The number of rounds completed is: " + round + ".<br>"
                + "Press \"Exit\" to leave the game.</html>";
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setBounds(alignmentXLabel, alignmentYLabel, widthLabel, heightLabel);
        panel.add(messageLabel);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(alignmentXButton, alignmentYButton, widthButton, heightButton);
        panel.add(exitButton);

        exitButton.addActionListener((final ActionEvent e) -> {
            dialog.dispose();
            closeGame();
        });
    }

    /**
     * Closes the game.
     */
    private static void closeGame() {
        // Effettua eventuali pulizie necessarie qui
        System.exit(0);
    }
}
