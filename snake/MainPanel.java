package snake;

import javax.swing.JPanel;

import java.awt.Point;
import java.awt.Color;

public class MainPanel extends JPanel {
    Snake snake;

    public MainPanel(Main parent) {

        this.setLayout(null);
        this.setSize(parent.getSize());
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        snake = new Snake(new Point(this.getWidth() / 2, this.getHeight() / 2), 300, 0, this);
        snake.moveSnake.start();
    }

    public Snake getSanke() {
        return this.snake;
    }
}
