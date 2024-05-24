package it.unibo.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import it.unibo.model.map.GameMap;
import it.unibo.model.map.GameMapFactory;
import it.unibo.model.map.GameMapFactoryImpl;
import it.unibo.model.map.tile.Tile;

/**
 * Test view of GameMap.
 */
public class MapViewTest extends JFrame {
    private final JPanel contentPanel = new JPanel();
    private final GameMapFactory factory = new GameMapFactoryImpl();
    private GameMap map;

    /**
     * .
     */
    public MapViewTest() {
        map = null;
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

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImages();
            }
        });
    }

    private void addTile(final Tile t) {
        final JLabel cell = new JLabel();
        cell.setIcon(new ImageIcon(ClassLoader.getSystemResource(t.getSprite())));
        this.contentPanel.add(cell);
    }

    private void resizeImages() {
        int cellWidth = contentPanel.getWidth() / map.getColumns();
        int cellHeight = contentPanel.getHeight() / map.getRows();

        for (int i = 0; i < contentPanel.getComponentCount(); i++) {
            JLabel label = (JLabel) contentPanel.getComponent(i);
            ImageIcon icon = (ImageIcon) label.getIcon();
            Image img = icon.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }

        contentPanel.revalidate();
    }
}
