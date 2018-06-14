import javax.swing.*;

public class Panel extends JPanel {
    private int r;
    private int c;


    public Panel(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

}
