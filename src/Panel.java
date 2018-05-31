import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private static final int PIXEL_SIZE = 20;

    private Color backgroundColor;

    public Panel(int type) {
        //Middle box
        if (type == 1) {
            this.setPreferredSize(new Dimension(20, 20));
        } else if (type == 2) {
            //Vert line
            this.setPreferredSize(new Dimension(5, 20));
        } else if (type == 3) {
            //horz line
            this.setPreferredSize(new Dimension(20, 5));
        } else if (type == 4) {
            //midle of lines square
            this.setPreferredSize(new Dimension(5, 5));
        }
        this.backgroundColor = Color.WHITE;
        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
