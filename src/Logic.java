public class Logic {
    int players;

    public Logic(int players) {
        this.players = players;
    }

    int turn = 0;
    public int getTurn() {
        turn++;
        return turn;
    }

    public void something() {
        for (int t = 1; t <= players; t++) {

        }
    }

    private void turn() {
    }
}
