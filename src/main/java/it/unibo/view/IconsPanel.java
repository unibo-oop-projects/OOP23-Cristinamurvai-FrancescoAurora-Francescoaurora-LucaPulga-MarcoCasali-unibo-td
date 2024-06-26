package it.unibo.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.model.core.GameState;

/**
 * Class for icon panel construction.
 */
public class IconsPanel extends JPanel {

    private final IconLabelPanel lifePanel;
    private final IconLabelPanel roundPanel;
    private final IconLabelPanel timePanel;
    private final IconLabelPanel moneyPanel;

    /**
     * Constructor method.
     *
     * @param width panel width
     * @param height panel height
     */
    public IconsPanel(final int width, final int height) {
        this.setLayout(new GridLayout(1, 4)); // Layout con una riga e quattro colonne
        this.setPreferredSize(new Dimension(0, 100)); // Imposta l'altezza preferita a 100px

        lifePanel = new IconLabelPanel("generics/img/lives.png", "Lives");
        roundPanel = new IconLabelPanel("generics/img/round.png", "Round");
        timePanel = new IconLabelPanel("generics/img/clock.png", "Time");
        moneyPanel = new IconLabelPanel("generics/img/money.png", "Money");

        this.add(lifePanel);
        this.add(roundPanel);
        this.add(timePanel);
        this.add(moneyPanel);
        this.setPreferredSize(new Dimension(width, height)); // Set preferred height to 100px
    }

    public void update(GameState gameState) {
        this.setLifeText("Lives: " + gameState.getLives());
        this.setMoneyText("Money: " + gameState.getMoney());
        this.setTimeText("Time: " + gameState.getRoundTime());
        if (gameState.getRoundNumber() != 0) {
            this.setRoundText("Round: " + gameState.getRoundNumber());
        } else {
            this.setRoundText("Pre-round, position the towers");
        }
    }

    private static class IconLabelPanel extends JPanel {
        private final JLabel iconLabel;
        private final JLabel textLabel;

        IconLabelPanel(final String iconPath, final String text) {
            this.setLayout(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout for horizontal alignment
            this.iconLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(iconPath)));
            this.textLabel = new JLabel(text);
            this.add(iconLabel);
            this.add(textLabel);
        }

        public void setText(final String text) {
            this.textLabel.setText(text);
        }
    }

    /**
     * set the label life text.
     *
     * @param text label text
     */
    public void setLifeText(final String text) {
        lifePanel.setText(text);
    }

    /**
     * set the label round text.
     *
     * @param text label text
     */
    public void setRoundText(final String text) {
        roundPanel.setText(text);
    }

    /**
     * set the label time text.
     *
     * @param text label text
     */
    public void setTimeText(final String text) {
        timePanel.setText(text);
    }

    /**
     * set the label money text.
     *
     * @param text label text
     */
    public void setMoneyText(final String text) {
        moneyPanel.setText(text);
    }
}
