import java.awt.*;

public class Logic {
    private Player[] players;

    public Logic(Player[] players) {
        this.players = players;
    }

    //Calculates whos turn it is, adding one to advance the turn, unless the player gets a point.
    public int getTurn(boolean gotSquare) {
        int turn = 0;
        if (gotSquare = true)
            return turn;
        else {
            turn++;
            if (turn > players.length)
                return turn%players.length;
            else
                return turn;
        }
    }

    //Determines what a given players color is, as set in their object creation.
    public Color determineColor() {
        int turn = getTurn(false);
        Color color = new Color(Color.TRANSLUCENT);
        for (int i = 0; i < players.length; i++) {
            if (turn == players[i].getId()) {
                color = players[i].getPlayerColor();
            }
        }
        return color;
    }
}
