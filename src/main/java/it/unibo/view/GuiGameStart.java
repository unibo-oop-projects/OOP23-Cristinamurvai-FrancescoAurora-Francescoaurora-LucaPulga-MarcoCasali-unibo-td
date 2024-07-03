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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final transient Logger logger = LoggerFactory.getLogger(GuiGameStart.class);
    private static final long serialVersionUID = 1L;
    private static final int ICON_DEFAULT_SIZE = 20;
    private static final int ICON_PANEL_SIZE = 50;
    private static final int ICON_BUTTON_SIZE = 80;
    private transient Tower selectedTower;
    private JPanel mapPanel;
    private final Map<JButton, String> tiles = new HashMap<>();
    private final transient GameController controller = new GameControllerImpl();
    private IconsPanel iconLabelPanel;
    private boolean pause;
    private JLabel pauseButton;
    private transient TowerCardFactory towerCardFactory;
    private static final int WIDTH_SCROLL_PANE = 300;
    private static final int HGAP_BUTTON_GUI = 5;
    private final GuiStart guiStartOld;
    private DefensePanel defensePanel;
    private EnemiesPanel enemiesPanel;

    /**
     * Constructor of the GuiGameStart class.
     *
     * @param mapName name of the selected map
     * @param oldGui panel to update
     * @param guiStart to get out of the game
     */
    public GuiGameStart(final String mapName, final JPanel oldGui, final GuiStart guiStart) {
        this.guiStartOld = guiStart != null ? guiStart : null;
        controller.setGameMap(mapName);
        initialize(oldGui);
    }

    /**
     * Explicit initialization method. Should be called after the object has
     * been fully constructed.
     *
     * @param oldGui panel to update
     */
    private void initialize(final JPanel oldGui) {
        final GameMap map = controller.getGameMap();

        // Initialize the main panel
        oldGui.setLayout(new BorderLayout());

        // Initialize the icon panel
        iconLabelPanel = new IconsPanel(oldGui.getWidth(), ICON_PANEL_SIZE);
        oldGui.add(iconLabelPanel, BorderLayout.NORTH);

        // Initialize the pause and settings buttons panel
        initializePauseAndSettingsButtons(oldGui);

        // Create the map and add overlapping panels
        createAndAddLayeredPanels(map, oldGui);

        // Create and add the tower panel
        createAndAddTowerPanel(oldGui);

        // Add a listener for map resizing
        addResizeListener(map);

        // Register the view in the controller and start the game
        registerViewAndStartGame();

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();
    }

    /**
     * Initializes the pause and settings buttons.
     *
     * @param oldGui panel to update
     */
    private void initializePauseAndSettingsButtons(final JPanel oldGui) {
        final JPanel buttonGui = new JPanel(new GridLayout(1, 2, HGAP_BUTTON_GUI, 0));
        Image icon = null;
        try {
            icon = ImageIO.read(ClassLoader.getSystemResource("buttons/pause.png"));
        } catch (final IOException e) {
            logger.error("Error when retrieving buttons/pause.png", e);
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
                        logger.error("Error when retrieving buttons/pause.png {}", e);
                    }
                } else {
                    try {
                        tmp = ImageIO.read(ClassLoader.getSystemResource("buttons/playPause.png"));
                    } catch (final IOException ex) {
                        logger.error("Error when retrieving buttons/playPause.png {}", e);
                    }
                }
                pause = !pause;
                pauseButton.setIcon(ScaledImage.getScaledImage(tmp, ICON_BUTTON_SIZE, ICON_BUTTON_SIZE));
            }
        });

        final JLabel settingsButton = new JLabel();
        try {
            icon = ImageIO.read(ClassLoader.getSystemResource("buttons/settings.png"));
        } catch (final IOException e) {
            logger.error("Error when retrieving buttons/settings.png", e);
        }
        settingsButton.setIcon(ScaledImage.getScaledImage(icon, ICON_BUTTON_SIZE, ICON_BUTTON_SIZE));
        buttonGui.add(pauseButton);
        buttonGui.add(settingsButton);
        iconLabelPanel.add(buttonGui);

        oldGui.add(iconLabelPanel, BorderLayout.NORTH);
    }

    /**
     * Creates the map and adds overlapping panels.
     *
     * @param map map to display
     * @param oldGui panel to update
     */
    private void createAndAddLayeredPanels(final GameMap map, final JPanel oldGui) {
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
                        logger.error("Placed " + selectedTower.getName() + " at cell " + cell.getText() + " {}", e);
                        selectedTower = null;
                    }
                }
            });
            this.tiles.put(cell, t.getSprite());
            this.mapPanel.add(cell);
        });

        // Add overlapping panels
        final JPanel layeredPane = new JPanel();
        layeredPane.setLayout(new OverlayLayout(layeredPane));
        this.enemiesPanel = new EnemiesPanel(mapPanel.getWidth() / map.getColumns(),
                mapPanel.getHeight() / map.getRows());
        this.enemiesPanel.setOpaque(false);
        layeredPane.add(this.enemiesPanel);

        this.defensePanel = new DefensePanel(new HashSet<>(), new HashSet<>(), mapPanel.getWidth() / map.getColumns(),
                mapPanel.getHeight() / map.getRows());
        this.defensePanel.setOpaque(false);
        layeredPane.add(this.defensePanel);

        layeredPane.add(mapPanel);
        oldGui.add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * Creates and adds the tower panel.
     *
     * @param oldGui panel to update
     */
    private void createAndAddTowerPanel(final JPanel oldGui) {
        final EntityFactory entityFactory = new EntityFactoryImpl();
        towerCardFactory = new TowerCardFactoryImpl();

        try {
            final JPanel towerPanel = towerCardFactory.createDefensePanel(entityFactory.loadAllTowers());
            towerPanel.setLayout(new BoxLayout(towerPanel, BoxLayout.Y_AXIS));
            final JScrollPane scrollPane = new JScrollPane(towerPanel);
            scrollPane.setPreferredSize(new Dimension(WIDTH_SCROLL_PANE, 0));
            oldGui.add(scrollPane, BorderLayout.EAST);
        } catch (IOException e1) {
            logger.error("Error", e1);
        }
    }

    /**
     * Adds a listener for resizing the map panel.
     *
     * @param map map to resize
     */
    private void addResizeListener(final GameMap map) {
        final ComponentAdapter resize = new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeImages(map);
            }
        };

        this.mapPanel.addComponentListener(resize);
        Toolkit.getDefaultToolkit().setDynamicLayout(false);
    }

    /**
     * Registers the view in the controller and starts the game.
     */
    private void registerViewAndStartGame() {
        controller.registerView(this);
        controller.startGame();
    }

    @Override
    public final void update(final GameState gameState) {
        iconLabelPanel.update(gameState);
        if (gameState.isGameOver()) {
            showGameOver(gameState.getRoundNumber());
        }
        if (gameState.isLastRound()) {
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
    /*
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
                        logger.error("Placed " + selectedTower.getName() + " at cell " + cell.getText() + " {}", e);
                        selectedTower = null;
                    }
                }
            });
            this.tiles.put(cell, t.getSprite());
            this.mapPanel.add(cell);
        });
    }*/
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
            logger.error("error when retrieving " + imgPath, e);
        }
        cell.setIcon(ScaledImage.getScaledImage(icon, finalWidth, finalHeight));
    }

    /**
     * shows the victory jdialog.
     *
     * @param round number of rounds played
     */
    private void showGameWin(final int round) {
        final int widthDialog = 500;
        final int heightDialog = 200;
        final JDialog winDialog = new JDialog();
        final JPanel panel = new JPanel();

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
    private void placeWinComponents(final JPanel panel, final JDialog dialog, final int round) {
        final int alignmentXLabel = 50;
        final int alignmentYLabel = 20;
        final int widthLabel = 400;
        final int heightLabel = 75;
        final int alignmentXButton = 200;
        final int alignmentYButton = 120;
        final int widthButton = 100;
        final int heightButton = 25;
        panel.setLayout(null);
        final String message = "<html>Congratulations! You have completed the game.<br>"
                + "The number of rounds completed is: " + round + ".<br>"
                + "Press \"Exit\" to leave the game.</html>";
        final JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setBounds(alignmentXLabel, alignmentYLabel, widthLabel, heightLabel);
        panel.add(messageLabel);

        final JButton exitButton = new JButton("Exit");
        exitButton.setBounds(alignmentXButton, alignmentYButton, widthButton, heightButton);
        panel.add(exitButton);

        exitButton.addActionListener((final ActionEvent e) -> {
            dialog.dispose();
            closeGame();
        });
    }

    private void showGameOver(final int round) {
        final int widthDialog = 500;
        final int heightDialog = 200;
        final JDialog gameOverDialog = new JDialog();
        final JPanel panel = new JPanel();

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
    private void placeGameOverComponents(final JPanel panel, final JDialog dialog, final int round) {
        final int alignmentXLabel = 50;
        final int alignmentYLabel = 20;
        final int widthLabel = 400;
        final int heightLabel = 75;
        final int alignmentXButton = 200;
        final int alignmentYButton = 120;
        final int widthButton = 100;
        final int heightButton = 25;
        panel.setLayout(null);
        final String message = "<html>Game Over!<br>"
                + "The number of rounds completed is: " + round + ".<br>"
                + "Press \"Exit\" to leave the game.</html>";
        final JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setBounds(alignmentXLabel, alignmentYLabel, widthLabel, heightLabel);
        panel.add(messageLabel);

        final JButton exitButton = new JButton("Exit");
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
    private void closeGame() {
        guiStartOld.closeGame();
    }
}
