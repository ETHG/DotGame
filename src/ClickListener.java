import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter{
    private Panel panel;
    private Player player;

    public ClickListener(Panel panel, Player player) {
        this.panel = panel;
        this.player = player;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        myClick(e);

    }

    public Panel myClick(MouseEvent e) {
        int sides = 0;
        if (sides >= 4)
            panel.setBackgroundColor(player.getPlayerColor());
        return panel;
    }
}
