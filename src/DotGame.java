import java.awt.*;
import java.util.Scanner;

public class DotGame {
    public static void main(String[] args) {
        int players = players();
        Board board = new Board(boardSize(), players);
        Logic logicSystem = new Logic(players);
        EventQueue.invokeLater(new Runnable() { public void run() {
            board.initPlayers();
            board.init();
        } });

    }

    public static int players() {

        Scanner console = new Scanner(System.in);

        System.out.print("How many players will be playing this game? ");
        int players = console.nextInt();
        System.out.println("");

        return players;
    }

    public static int boardSize() {
        Scanner console = new Scanner(System.in);

        System.out.print("How big do you want the board to be? ");
        int size = console.nextInt();
        System.out.println("");

        return size;
    }
}
