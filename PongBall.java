import java.awt.*;

public class PongBall implements GameComponent {

    private int xPosition;
    private int yPosition;
    private Dimension dimensions;

    private int xVelocity;
    private int yVelocity;


    public PongBall(int xPosition, int yPosition, int radius) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        this.dimensions = new Dimension(radius, radius);

        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    /*
    public boolean isTouchingX(int xCoordinate) {
        boolean isTouching = (xCoordinate <= this.xPosition + this.radius)&&(xCoordinate >= this.xPosition);
        return isTouching;
    }

    public boolean isTouchingY(int yCoordinate) {
        boolean isTouching = (yCoordinate <= this.yPosition + this.radius)&&(yCoordinate >= this.yPosition);
        return isTouching;
    }

    public boolean isInBounds(int lowXCoordinate, int highXCoordinate, int lowYCoordinate, int highYCoordinate) {
        boolean inBoundsX = (this.xPosition >= lowXCoordinate)&&(this.xPosition + this.radius <= highXCoordinate);
        boolean inBoundsY = (this.yPosition >= lowYCoordinate)&&(this.yPosition + this.radius <= highYCoordinate);
        return (inBoundsX && inBoundsY);
    }
     */

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.fillOval(xPosition, yPosition, dimensions.getXDimension(), dimensions.getYDimension());
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xCoordinate) {
        this.xPosition = xCoordinate;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yCoordinate) {
        this.yPosition = yCoordinate;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int newVelocity) {
        this.xVelocity = newVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int newVelocity) {
        this.yVelocity = newVelocity;
    }

    public Dimension getDimensions() {
        return new Dimension(dimensions.getXDimension(), dimensions.getYDimension());
    }
}
