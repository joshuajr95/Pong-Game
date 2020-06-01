public class GameComponentEvent {

    public static final int TOP_BOUNCE_EVENT = 0;
    public static final int BOTTOM_BOUNCE_EVENT = 1;
    public static final int LEFT_PADDLE_BOUNCE_EVENT = 2;
    public static final int RIGHT_PADDLE_BOUNCE_EVENT = 3;
    public static final int LEFT_SCORE_EVENT = 4;
    public static final int RIGHT_SCORE_EVENT = 5;


    private int eventType;
    private int xPosition;
    private int yPosition;

    public GameComponentEvent(int eventType, int xPosition, int yPosition) {
        this.eventType = eventType;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getEventType() {
        return eventType;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }
}
