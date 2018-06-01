import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private static final int PIXEL_SIZE = 20;

    private Color backgroundColor;

    public Panel() {
        //Middle box
        this.backgroundColor = Color.WHITE;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(10, 20));
    }

    public Panel(int width, int height) {
        this.backgroundColor = Color.WHITE;
        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(width, height));
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
