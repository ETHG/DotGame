import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private static final int PIXEL_SIZE = 20;

    private Color backgroundColor;

    public Panel() {
        this.backgroundColor = Color.WHITE;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getBackgroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
