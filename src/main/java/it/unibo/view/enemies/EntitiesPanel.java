package it.unibo.view.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.defense.tower.Tower;
/**
 * .
 */
public class EntitiesPanel extends JPanel {

    private Set<Tower> entities;
    private int xCellSize;
    private int yCellSize;

    /**
     * .
     * @param enemies
     * @param xCellSize
     * @param yCellSize
     */
    public EntitiesPanel(final Set<Tower> entities, final int xCellSize, final int yCellSize) {
        this.entities = entities;
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        // for (Entity entity : this.entities) {
        //     if (enemy.getState().equals(EnemyState.MOVING)) {
        //         try {
        //             BufferedImage enemyImage = ImageIO.read(new File(enemy.getImagePath()));
        //             g.drawImage(enemyImage, enemy.getPosition().x() * this.xCellSize, enemy.getPosition().y() 
        //             * this.yCellSize, this);
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // }

        for (Tower entity : this.entities) {
            try {
                BufferedImage enemyImage = ImageIO.read(ClassLoader.getSystemResource(entity.getPath()));
                g.drawImage(enemyImage, entity.getPosition().x() * this.xCellSize, entity.getPosition().y() * this.yCellSize, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * .
     * @param gameState
     * @param xCellSize
     * @param yCellSize
     */
    public void updateView(final GameState gameState, final int xCellSize, final int yCellSize) {
        this.entities = gameState.getTowers();
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
        this.revalidate();
        this.repaint();
    }
}
