package it.unibo.view.defense;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;

/**
 * Defense panel displayed on the right side of the screen.
 */
public class DefensePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final transient Logger logger = LoggerFactory.getLogger(DefensePanel.class);
    private transient Set<Tower> towers; // All fields must either be serializable, but towers will not be serialized.
    private transient Set<Bullet> bullets; // All fields must either be serializable, but bullets will not be serialized.
    private int xCellSize;
    private int yCellSize;

    /**
     * Base Constructor.
     *
     * @param towers set of towers.
     * @param bullets set of bullets.
     * @param xCellSize size of a cell in x direction.
     * @param yCellSize size of a cell in y direction.
     */
    public DefensePanel(final Set<Tower> towers, final Set<Bullet> bullets, final int xCellSize, final int yCellSize) {
        this.towers = new HashSet<>(towers);
        this.bullets = new HashSet<>(bullets);
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
    }

    /**
     * Painting components such as {@link Tower} and {@link Bullet} on screen.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (final Tower entity : this.towers) {
            try {
                final BufferedImage towerImage = ImageIO.read(ClassLoader.getSystemResource(entity.getPath()));
                g.drawImage(getScaledImage(towerImage, this.xCellSize, this.yCellSize * 2),
                        (int) (entity.getPosition().x() * this.xCellSize),
                        (int) (entity.getPosition().y() * this.yCellSize), this);
            } catch (IOException e) {
                logger.error("An error occured while painting TOWER components: " + e);
            }
        }
        for (final Bullet bullet : this.bullets) {
            try {
                final BufferedImage bulletImage = ImageIO.read(ClassLoader.getSystemResource(bullet.getPath()));
                g.drawImage(getScaledImage(bulletImage, this.xCellSize / 2, this.yCellSize / 2),
                        (int) (bullet.getPosition().x() * this.xCellSize),
                        (int) (bullet.getPosition().y() * this.yCellSize), this);
            } catch (IOException e) {
                logger.error("An error occured while painting BULLET components: " + e);
            }
        }
    }

    /**
     * Update view components.
     *
     * @param gameState current game state.
     * @param xCellSize size of a cell in x direction.
     * @param yCellSize size of a cell in y direction.
     */
    public void updateView(final GameState gameState, final int xCellSize, final int yCellSize) {
        this.towers = new HashSet<>(gameState.getTowers());
        this.bullets = new HashSet<>(gameState.getBullets());
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
        this.revalidate();
        this.repaint();
    }

    /**
     * Scale images.
     *
     * @param srcImg source image.
     * @param width target width.
     * @param height target height.
     * @return a new scaled image.
     */
    private BufferedImage getScaledImage(final Image srcImg, final int width, final int height) {
        final BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return resizedImg;
    }

    /**
     * Custom serialization methods to handle transient fields.
     * @param oos
     * @throws IOException
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(new HashSet<>(towers));
        oos.writeObject(new HashSet<>(bullets));
    }

    /**
     * Custom serialization methods to handle transient fields.
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        towers = (Set<Tower>) ois.readObject();
        bullets = (Set<Bullet>) ois.readObject();
        if (towers == null) {
            towers = new HashSet<>();
        }
        if (bullets == null) {
            bullets = new HashSet<>();
        }
    }
}
