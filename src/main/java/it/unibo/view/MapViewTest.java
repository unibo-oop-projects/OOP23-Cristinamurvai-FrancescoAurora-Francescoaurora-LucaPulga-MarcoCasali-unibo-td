package it.unibo.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.model.map.GameMap;
import it.unibo.model.map.GameMapFactory;
import it.unibo.model.map.GameMapFactoryImpl;
import it.unibo.model.map.tile.Tile;
import java.awt.GridLayout;

/**
 * Test view of GameMap.
 */
public class MapViewTest extends JFrame {
    private final JPanel contentPanel = new JPanel();
    private final GameMapFactory factory = new GameMapFactoryImpl();

    /**
     * .
     */
    public MapViewTest() {
        GameMap map = null;
        try {
            map = factory.fromName("test");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        contentPanel.setLayout(new GridLayout(map.getRows(), map.getColumns()));
        map.getTiles().forEach(t -> addTile(t));


        this.add(contentPanel);
        this.pack();
        this.revalidate();
        this.setVisible(true);
    }

    private void addTile(final Tile t) {
        final JLabel cell = new JLabel();
        cell.setIcon(new ImageIcon(ClassLoader.getSystemResource(t.getSprite())));
        this.contentPanel.add(cell);
    }
}
