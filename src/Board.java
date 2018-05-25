import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Board {

    private int size;
    public int playerCount;
    private Player[] players;
    public int[][] grid = new int[size][size];
    //private JFrame frame;
    //private ArrayList<Player> playerArrayList;

    public Board(int size, int players) {
        this.playerCount = players;
        this.size = size;
        this.players = new Player[players];
    }

    public Board() {
        playerCount = 2;
        size = 10;
        players = new Player[playerCount];
    }

    public void initPlayers() {
        Scanner console = new Scanner(System.in);

        for (int i = 0; i < playerCount; i++) {
            System.out.print("What is the name of player " + (1+i) + "? ");

            players[i] = new Player(console.next(), i);
        }
    }

    public void init() {
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(createGrid());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

//        for (int i = 1; i <= size; i++) {
//
//        }
    }

    private JPanel createGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size, 0, 0));

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Panel newPanel = new Panel();
                newPanel.addMouseListener(new ClickListener(newPanel));
                panel.add(newPanel);
            }
        }
        return panel;
    }
}
