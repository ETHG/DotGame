import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter{
    private Panel panel;

    public ClickListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        myClick(e);
    }

    public Panel myClick(MouseEvent e) {
        return panel;
    }
}
