package it.unibo.view.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.entities.enemies.EnemyState;

/**
 * A JPanel subclass responsible for rendering enemies on the game board.
 */
public class EnemiesPanel extends JPanel {

    private Set<Enemy> enemies;
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

        for (Enemy enemy : this.enemies) {
            if (enemy.getState().equals(EnemyState.MOVING) || enemy.getState().equals(EnemyState.PAUSED)) {
                try {
                    BufferedImage enemyImage = ImageIO.read(new File(enemy.getImagePath()));
                    g.drawImage(enemyImage, (int) (enemy.getPosition().x() * this.xCellSize),
                            (int) (enemy.getPosition().y() * this.yCellSize), this);
                } catch (IOException e) {
                    e.printStackTrace();
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

}
