import javax.swing.*;
import java.awt.*;

public class MoreTesting {
    public static void main(String[] args) {
        init();
    }


    public void init() {
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.add(createPixels());
        frame.setVisible(true);
    }

    private JPanel createPixels() {
        int width = 30;
        int height = 20;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height, width, 0, 0));

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                MoreTesting.PixelPanel pixelPanel = new MoreTesting.PixelPanel();
                panel.add(pixelPanel);
            }
        }

        return panel;
    }

    public class PixelPanel extends JPanel {

        private static final long serialVersionUID = 8465814529701152253L;

        private static final int PIXEL_SIZE = 20;

        private Color backgroundColor;

        public PixelPanel() {
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
}
