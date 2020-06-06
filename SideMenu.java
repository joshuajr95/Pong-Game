import javax.swing.*;

public class SideMenu extends JPanel {

    public Scorecard scorecard;

    public SideMenu(int xCoordinate, int yCoordinate, int width, int height) {
        this.scorecard = new Scorecard(xCoordinate, yCoordinate, width, height);
        this.add(scorecard);
    }
}
