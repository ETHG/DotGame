//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import javax.swing.*;
//import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Board {

    private int size;
    private int playerCount;
    private Player[] players;
    //private JFrame frame;
    //private ArrayList<Player> playerArrayList;

    public Board(int size, int players) {
        this.playerCount = players;
        this.size = size;
        this.players = new Player[players];
        //this.playerArrayList = new ArrayList<>();
    }

    public Board() {
        playerCount = 2;
        size = 10;
    }

    public void initPlayers() {
        Scanner console = new Scanner(System.in);

        for (int i = 0; i < playerCount; i++) {
            System.out.print("What is the name of player " + (1+i) + "? ");

            players[i] = new Player(console.next());

            //playerArrayList.add(console.next());
        }
    }

    //Not my code:

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
