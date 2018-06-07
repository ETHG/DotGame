import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Board {

    private int size;
    public int playerCount;
    private Player[] players;
    public int[][] grid;
    private Logic logic;


    public Board(int size, int players) {
        this.playerCount = players;
        this.size = size;
        this.players = new Player[players];
        this.logic = new Logic(this.players);
        this.grid =  new int[size-1][size-1];
    }

    //Default constructor if size/players are left blank
    public Board() {
        playerCount = 2;
        size = 10;
        players = new Player[playerCount];
    }

    //Populates the array of players with player objects
    public void initPlayers() {
        for (int i = 0; i < playerCount; i++) {
            String player = JOptionPane.showInputDialog("What is the name of player " + (1+i) + "? ");
            players[i] = new Player(player, i);
        }
    }

    //Creates the frame;
    public void init() {
        JFrame frame = new JFrame("Dot Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(createGrid());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
    }

    //Populates the frame w/ panels/etc
    private JPanel createGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size));

        for (int r = 1; r < size; r++) {
            for (int c = 1; c < size; c++) {
                //System.out.println("created r/c: " + r + " " + c);
                if (r%2 == 1) {
                    if (c%2 == 0) {
                        makeButton(r, c, panel);
                    } else {
                        JPanel newPanel = new JPanel();
                        panel.add(newPanel);
                        newPanel.setBackground(Color.BLACK);
                    }
                } else {
                    if (c%2 == 1) {
                        makeButton(r, c, panel);
                    } else {
                        JPanel newPanel = new JPanel();
                        panel.add(newPanel);
                    }
                }
            }
        }
        return panel;
    }
    public void reprint() {
        for (int i = 0; i < grid.length; i++)
            System.out.println(Arrays.toString(grid[i]));
    }

    public void makeButton(int r, int c, JPanel panel) {
        JButton button = new JButton();
        final int[] temp = new int[2];
        temp[0] = r-1;
        temp[1] = c-1;
        button.addActionListener(e -> {
            button.setBackground(logic.determineColor());
            System.out.println("");
            grid[temp[0]][temp[1]] = 1;
            checkForSquares(temp[0], temp[1]);
            //System.out.println("r: " + temp[0] + " c: " + temp[1]);
            reprint();
        });
        panel.add(button);
    }

    public void checkForSquares(int r, int c) {
        System.out.println("" + r + " " + c);

        if (r >= grid.length || c <= 0 || c >= grid.length) {
            System.out.println("Side thing found, not checking.");
        } else if (grid[r+1][c-1] == 1 && grid[r+1][c+1] == 1 && grid[r+2][c] == 1) {
            System.out.println("" + r + " " + c);
            System.out.println("found square");
        }
    }
}
