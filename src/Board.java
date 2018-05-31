import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Board {

    private int size;
    public int playerCount;
    private Player[] players;
    public int[][] grid = new int[size][size];
    private Logic logic;
    //private JFrame frame;
    //private ArrayList<Player> playerArrayList;

    public Board(int size, int players) {
        this.playerCount = players;
        this.size = size;
        this.players = new Player[players];
        this.logic = new Logic(players);
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
    }

    private JPanel createGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size, 0, 0));
        Player thisPlayer = players[0];
        for (int evenR = 0; 0 < size; evenR+=2) {
            for (int evenC = 0; evenC < size; evenC+=2) {
                Panel newHPanel = new Panel(3);
                panel.add(newHPanel);
                System.out.println("created x" + evenC);
            }
            for (int oddC = 1; oddC < size; oddC+=2) {
                Panel newMidBoxPanel = new Panel(4);
                panel.add(newMidBoxPanel);
            }
        }
        for (int oddR = 1; 0 < size; oddR+=2f) {
            for (int evenC = 0; evenC < size; evenC+=2) {
                Panel newVPanel = new Panel(2);
                panel.add(newVPanel);
            }
            for (int oddC = 1; oddC < size; oddC+=2) {
                Panel newFillBoxPanel = new Panel(1);
                panel.add(newFillBoxPanel);
            }
        }

//        for (int row = 0; row < size; row++) {
//            for (int column = 0; column < size; column++) {
//                Panel newPanel = new Panel();
////                for (int i = 1; i < playerCount; i++) {
//////                    if (i == players[i].getId()) {
//////                        thisPlayer = players[i];
//////                    }
//////                }
//////                newPanel.addMouseListener(new ClickListener(newPanel, thisPlayer));
//                panel.add(newPanel);
//            }
//        }
        return panel;
    }
}
