package it.unibo.view.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.entities.enemies.EnemyState;

import java.util.stream.Collectors;

public class EnemiesPanel extends JPanel {

    private ArrayList<Enemy> enemies;
    private int xCellSize;
    private int yCellSize;

    public EnemiesPanel(ArrayList<Enemy> enemies, int xCellSize, int yCellSize) {
        this.enemies = enemies;
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Enemy enemy : this.enemies) {
            if(enemy.getState().equals(EnemyState.MOVING)) {
                try {
                    BufferedImage enemyImage = ImageIO.read(new File(enemy.getImagePath()));
                    g.drawImage(enemyImage, enemy.getPosition().x() * this.xCellSize, enemy.getPosition().y() * this.yCellSize, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }

    public void updateView(GameState gameState, int xCellSize, int yCellSize) {
        this.enemies = (ArrayList<Enemy>) gameState.getEnemies().stream().collect(Collectors.toList());
        this.xCellSize = xCellSize;
        this.yCellSize = yCellSize;
        this.revalidate();
        this.repaint();
    }
    
}
