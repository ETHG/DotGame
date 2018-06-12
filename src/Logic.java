import java.awt.*;

public class Logic {
    private Player[] players;
    int turn;

    public Logic(Player[] players) {
        this.players = players;
        turn = 0;
    }

    public int getTurn() {
        return turn;
    }

    public void advanceTurn() {
        turn += 1;
    }

    //Determines what a given players color is, as set in their object creation.
    public Color determineColor() {
        Color color = new Color(Color.OPAQUE);
        for (int i = 0; i < players.length; i++) {
            if (turn%players.length == players[i].getId()) {
                color = players[i].getPlayerColor();
            }
        }
        return color;
    }
}
