package it.unibo.view.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.entities.enemies.EnemyImpl;
import it.unibo.model.entities.enemies.EnemyState;

/**
 * A JPanel subclass responsible for rendering enemies on the game board.
 */
public class EnemiesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final transient Logger logger = LoggerFactory.getLogger(EnemyImpl.class);
    private transient Set<Enemy> enemies;
    private int xCellSize;
    private int yCellSize;

    /**
     * Constructs an EnemiesPanel.
     *
     * @param xCellSize The width of each cell in the game grid.
     * @param yCellSize The height of each cell in the game grid.
     */
    public EnemiesPanel(final int xCellSize, final int yCellSize) {
        this.enemies = new HashSet<>();
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
    }

    /**
     * Custom paint method to draw the enemies on the panel.
     *
     * @param g The graphics context used for drawing.
     */
    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);

        for (final Enemy enemy : this.enemies) {
            if (enemy.getState().equals(EnemyState.MOVING) || enemy.getState().equals(EnemyState.PAUSED)) {
                try {
                    final BufferedImage enemyImage = ImageIO.read(ClassLoader.getSystemResource(enemy.getImagePath()));
                    g.drawImage(enemyImage, (int) (enemy.getPosition().x() * this.xCellSize),
                            (int) (enemy.getPosition().y() * this.yCellSize), this);
                } catch (IOException e) {
                    logger.error("An error occured while painting ENEMIES components: " + e);
                }
            }

        }
    }

    /**
     * Updates the view with the latest game state and cell sizes.
     *
     * @param gameState The current game state containing enemy information.
     * @param xCellSize The width of each cell in the game grid.
     * @param yCellSize The height of each cell in the game grid.
     */
    public void updateView(final GameState gameState, final int xCellSize, final int yCellSize) {
        this.enemies = gameState.getEnemies();
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
        this.revalidate();
        this.repaint();
    }

    /**
     * Custom serialization method to save transient fields.
     *
     * @param out The ObjectOutputStream to write the object to.
     * @throws IOException If an I/O error occurs while writing the object.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /**
     * Custom deserialization method to initialize transient fields.
     *
     * @param in The ObjectInputStream to read the object from.
     * @throws IOException If an I/O error occurs while reading the object.
     * @throws ClassNotFoundException If the class of a serialized object cannot
     * be found.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.enemies = new HashSet<>();
    }

}
