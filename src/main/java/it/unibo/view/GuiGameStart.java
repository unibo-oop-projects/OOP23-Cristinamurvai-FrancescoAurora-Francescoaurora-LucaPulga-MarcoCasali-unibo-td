package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.imageio.ImageIO;
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
import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.view.TowerCardFactory;
import it.unibo.model.entities.defense.tower.view.TowerCardFactoryImpl;
import it.unibo.model.map.GameMap;
import it.unibo.view.enemies.EntitiesPanel;

/**
 * Loading Game Screen.
 */
public class GuiGameStart extends JFrame implements GameView {
    private static final int ICON_DEFAULT_SIZE = 20;
    private Tower selectedTower = null;
    private JPanel contentPanel;
    private JPanel mapPanel;
    private JPanel layeredPane;
    private JScrollPane scrollPane;
    private Map<JButton, String> tiles = new HashMap<>();
    private GameController controller = new GameControllerImpl();
    private IconsPanel iconLabelPanel;
    EntityFactory entityFactory;
    TowerCardFactory towerCardFactory;
    JButton pauseButton;

    // Add for enemies test
    private EntitiesPanel enemiesPanel;

    /**
     * .
     * @param mapName
     */
    public GuiGameStart(final String mapName, final JPanel oldGui) {
        final GameMap map = controller.setGameMap(mapName);
        this.contentPanel = oldGui;
        contentPanel.setLayout(new BorderLayout()); // Main layout with BorderLayout

        // Sub-panel for the labels ‘Screw and screw image’, ‘Time wave’, ‘Available money’.
        iconLabelPanel = new IconsPanel(contentPanel.getWidth(), 50);
        contentPanel.add(iconLabelPanel, BorderLayout.NORTH);

        //addming botton paused and settings
        JPanel buttonGui = new JPanel(new GridLayout(1, 2, 5, 0));
        pauseButton = new JButton("Pause");

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
        iconLabelPanel.add(buttonGui);

        contentPanel.add(iconLabelPanel, BorderLayout.NORTH);
        this.createMap(map);


        // Adding enemies layer and map layer overlapped
        this.layeredPane = new JPanel();
        this.layeredPane.setLayout(new OverlayLayout(this.layeredPane));
        this.enemiesPanel = new EntitiesPanel(new HashSet<>(), mapPanel.getWidth() / map.getColumns(), mapPanel.getHeight() / map.getRows());
        this.enemiesPanel.setOpaque(false);
        this.layeredPane.add(this.enemiesPanel);
        this.layeredPane.add(mapPanel);
        contentPanel.add(this.layeredPane, BorderLayout.CENTER);


        entityFactory = new EntityFactoryImpl();
        towerCardFactory = new TowerCardFactoryImpl();

        try {
            JPanel towerPanel = towerCardFactory.createDefensePanel(entityFactory.loadAllTowers());
            towerPanel.setLayout(new BoxLayout(towerPanel, BoxLayout.Y_AXIS));
            scrollPane = new JScrollPane(towerPanel);
            scrollPane.setPreferredSize(new Dimension(300, 0));
            contentPanel.add(scrollPane, BorderLayout.EAST);
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
        contentPanel.revalidate();
        contentPanel.repaint();
        
    }

    @Override
    public void update(final GameState gameState) {
        iconLabelPanel.update(gameState);

        if(gameState.getLastRound() == true) {
            showGameWin(gameState.getRoundNumber());
        }

        //Updating enemy layer
        this.enemiesPanel.updateView(gameState, mapPanel.getWidth() / gameState.getGameMap().getColumns(), mapPanel.getHeight() / gameState.getGameMap().getRows());
        //System.out.println("Entities: " + (gameState.getTowers().size() - gameState.getEnemies().size()));
        // this.tiles.put(pauseButton,);
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
            cell.setBorderPainted(false);
            setScaledIcon(cell, t.getSprite(), this.mapPanel.getWidth() / map.getColumns(), this.mapPanel.getHeight() / map.getRows());
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    Tower selectedTower = towerCardFactory.getSelectedTower();
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
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("error when retrieving " + imgPath);
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
            System.exit(0);
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

        JButton resumeButton = new JButton("Resume");
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
