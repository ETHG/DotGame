public class Logic {
    int players;

    public Logic(int players) {
        this.players = players;
    }

    int turn = 0;
    public int getTurn(boolean gotSquare) {
        if (gotSquare = true)
            return turn;
        else {
            turn++;
            if (turn > players)
                return turn%players;
            else
                return turn;
        }
        //Todo make it so that when a player gets a square, they get an extra turn
    }
}
