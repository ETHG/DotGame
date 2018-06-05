import javax.swing.*;

public class DotGame {
    public static void main(String[] args) {
        int players = players();
        Board board = new Board(boardSize(), players);
        board.initPlayers();
        board.init();
    }

    //Gets user input for how many players
    public static int players() {
        String players = JOptionPane.showInputDialog("How many players will be playing this game? ");

        int parsedPlayerInt = Integer.parseInt(players);

        return parsedPlayerInt;
    }

    //Gets user input for board size
    public static int boardSize() {
        String size = JOptionPane.showInputDialog("How big do you want the board to be? ");

        int parsedSizeInt = Integer.parseInt(size) *2;

        return parsedSizeInt;
    }
}
