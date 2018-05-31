import java.awt.*;

public class Player {

    private String name;
    private int points;
    private int id;
    public Color playerColor;

    public Player(String name, int id) {
        this.points = 0;
        this.name = name;
        this.id = id;
        playerColor = new Color((int)(Math.random() * 0x1000000));
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

    public void addPoints(int points) {
        points += points;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Color getPlayerColor() {
        return playerColor;
    }
}
