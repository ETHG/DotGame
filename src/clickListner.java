import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class clickListner extends MouseAdapter{
    private Board.PixelPanel panel;

    public clickListener(Board.PixelPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        myClick(e);
    }

    public Board.PixelPanel myClick(MouseEvent e) {
        return panel;
    }
}
