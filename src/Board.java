import javax.swing.*;
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

    public void init() {
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.add(something);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
