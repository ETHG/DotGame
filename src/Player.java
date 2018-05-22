public class Player {

    private String name;
    private int points;

    public Player(String name) {
        this.points = 0;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoint() {
        points += 1;
    }

    public String getName() {
        return name;
    }
}
