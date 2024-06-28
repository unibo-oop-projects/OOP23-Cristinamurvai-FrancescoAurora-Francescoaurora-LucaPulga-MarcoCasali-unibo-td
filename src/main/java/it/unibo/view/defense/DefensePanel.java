package it.unibo.view.defense;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;

/**
 * Defense panel displayed on the right side of the screen.
 */
public class DefensePanel extends JPanel {

    private Set<Tower> towers;
    private Set<Bullet> bullets;
    private int xCellSize;
    private int yCellSize;

    /**
     * Base Constructor.
     *
     * @param bullets
     * @param towers
     * @param xCellSize
     * @param yCellSize
     */
    public DefensePanel(final Set<Tower> towers, final Set<Bullet> bullets, final int xCellSize, final int yCellSize) {
        this.towers = towers;
        this.bullets = bullets;
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
    }

    /**
     * Painting components such as {@link Tower} and {@link Bullet} on screen.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (Tower entity : this.towers) {
            try {
                BufferedImage towerImage = ImageIO.read(ClassLoader.getSystemResource(entity.getPath()));
                g.drawImage(getScaledImage(towerImage, this.xCellSize, this.yCellSize * 2), (int) (entity.getPosition().x()
                        * this.xCellSize), (int) (entity.getPosition().y() * this.yCellSize), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Bullet bullet : this.bullets) {
            try {
                BufferedImage bulletImage = ImageIO.read(ClassLoader.getSystemResource(bullet.getPath()));
                g.drawImage(getScaledImage(bulletImage, this.xCellSize / 2, this.yCellSize / 2), (int) (bullet.getPosition().x()
                        * this.xCellSize), (int) (bullet.getPosition().y() * this.yCellSize), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update view components.
     *
     * @param gameState
     * @param xCellSize
     * @param yCellSize
     */
    public void updateView(final GameState gameState, final int xCellSize, final int yCellSize) {
        this.towers = gameState.getTowers();
        this.bullets = gameState.getBullets();
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
        this.revalidate();
        this.repaint();
    }

    /**
     * Scale images.
     *
     * @param srcImg
     * @param width
     * @param height
     * @return a new scaled image.
     */
    private BufferedImage getScaledImage(final Image srcImg, final int width, final int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return resizedImg;
    }
}
