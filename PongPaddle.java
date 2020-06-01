import java.awt.*;
import java.awt.event.KeyEvent;

public class PongPaddle implements GameComponent {

    private int xPosition;
    private int yPosition;
    private Dimension dimensions;

    private int yVelocity;

    public PongPaddle(int xPosition, int yPosition, int width, int height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.dimensions = new Dimension(width, height);
    }


    /*
    public boolean isTouchingX(int xCoordinate) {
        boolean isTouching = (xCoordinate <= this.xPosition + this.width)&&(xCoordinate >= this.xPosition);
        return isTouching;
    }

    public boolean isTouchingY(int yCoordinate) {
        boolean isTouching = (yCoordinate <= this.yPosition + this.height)&&(yCoordinate >= this.yPosition);
        return isTouching;
    }

    public boolean isInBounds(int lowXValue, int highXValue) {
        boolean inBounds = (this.yPosition >= lowXValue)&&(this.yPosition + this.height <= highXValue);
        return inBounds;
    }
    */

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getYVelocity() {
        return this.yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public Dimension getDimensions() {
        return new Dimension(this.dimensions.getXDimension(), this.dimensions.getYDimension());
    }
}
