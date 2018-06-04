import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Board {

    private int size;
    public int playerCount;
    private Player[] players;
    public int[][] grid = new int[size][size];
    private Logic logic;


    public Board(int size, int players) {
        this.playerCount = players;
        this.size = size;
        this.players = new Player[players];
        this.logic = new Logic(this.players);
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
        JFrame frame = new JFrame("Dot Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(createGrid());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
    }

    private JPanel createGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size, 0, 0));

        for (int r = 1; r < size; r++) {
            for (int c = 1; c < size; c++) {
                if (r%2 == 1) {
                    if (c%2 == 0) {
                        JButton button = new JButton();
//                        button.setOpaque(false);
//                        button.setContentAreaFilled(false);
//                        button.setBorderPainted(false);
                        button.addActionListener(e -> {
                            button.setBackground(logic.determineColor());
                        });
                        panel.add(button);
                    } else {
                        JPanel newPanel = new JPanel();
                        //Panel newPanel = new Panel();
                        panel.add(newPanel);
                        newPanel.setBackground(Color.BLACK);
                    }
                } else {
                    if (c%2 == 1) {
                        JButton button = new JButton();
//                        button.setOpaque(false);
//                        button.setContentAreaFilled(false);
//                        button.setBorderPainted(false);
                        button.addActionListener(e -> {
                            button.setBackground(logic.determineColor());
                            //logic.getTurn(false);
                        });
                        panel.add(button);
                    } else {
                        JPanel newPanel = new JPanel();
                        panel.add(newPanel);
                    }
                }
            }

        }
        return panel;
    }
}
