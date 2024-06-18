package it.unibo.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IconsPanel extends JPanel {
    private final IconLabelPanel lifePanel;
    private final IconLabelPanel roundPanel;
    private final IconLabelPanel timePanel;
    private final IconLabelPanel moneyPanel;

    public IconsPanel(int width, int height) {
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

    private class IconLabelPanel extends JPanel {
        private final JLabel iconLabel;
        private final JLabel textLabel;

        public IconLabelPanel(String iconPath, String text) {
            this.setLayout(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout for horizontal alignment
            this.iconLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(iconPath)));
            this.textLabel = new JLabel(text);
            this.add(iconLabel);
            this.add(textLabel);
        }

        public void setText(String text) {
            this.textLabel.setText(text);
        }
    }

    public void setLifeText(String text) {
        lifePanel.setText(text);
    }

    public void setRoundText(String text) {
        roundPanel.setText(text);
    }

    public void setTimeText(String text) {
        timePanel.setText(text);
    }

    public void setMoneyText(String text) {
        moneyPanel.setText(text);
    }
}
