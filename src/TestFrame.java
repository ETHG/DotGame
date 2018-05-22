import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame implements Runnable {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new TestFrame());
    }

    @Override
    public void run() {
        initGUI();
    }

    public void initGUI() {
        frame = new JFrame("Pixel Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createPixels());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createPixels() {
        int width = 30;
        int height = 20;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height, width, 0, 0));

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                PixelPanel pixelPanel = new PixelPanel();
                pixelPanel.addMouseListener(new ColorListener(pixelPanel));
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

    public class ColorListener extends MouseAdapter {

        private PixelPanel panel;

        public ColorListener(PixelPanel panel) {
            this.panel = panel;
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                panel.setBackgroundColor(Color.BLUE);
                panel.repaint();
            } else if (event.getButton() == MouseEvent.BUTTON3) {
                panel.setBackgroundColor(Color.WHITE);
                panel.repaint();
            }
        }
    }

}