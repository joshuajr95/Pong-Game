import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameWindow extends JPanel {

    // constants defining size of game window and frame rate of the animation loop
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 680;
    public static final int FRAME_RATE = 200;

    // constants determining size and starting placement of the ball
    public static final int BALL_RADIUS = 25;
    public static final int BALL_START_X_POSITION = WINDOW_WIDTH/2;
    public static final int BALL_START_Y_POSITION = WINDOW_HEIGHT/2;

    // constants defining size and placement of paddles
    public static final int PADDLE_WIDTH = 20;
    public static final int PADDLE_HEIGHT = 100;
    public static final int LEFT_PADDLE_X = 0;
    public static final int RIGHT_PADDLE_X = WINDOW_WIDTH - PADDLE_WIDTH;

    // constants defining actionMap keys for the left paddle. Helps prevent accidental typos
    public static final String LEFT_PADDLE_UP_PRESS = "Left Paddle Move Up";
    public static final String LEFT_PADDLE_UP_RELEASE = "Left Paddle Move Up Release";
    public static final String LEFT_PADDLE_DOWN_PRESS = "Left Paddle Move Down";
    public static final String LEFT_PADDLE_DOWN_RELEASE = "Left Paddle Move Down Release";

    // constants defining actionMap keys for the right paddle. Helps prevent accidental typos.
    public static final String RIGHT_PADDLE_UP_PRESS = "Right Paddle Move Up";
    public static final String RIGHT_PADDLE_UP_RELEASE = "Right Paddle Move Up Release";
    public static final String RIGHT_PADDLE_DOWN_PRESS = "Right Paddle Move Down";
    public static final String RIGHT_PADDLE_DOWN_RELEASE = "Right Paddle Move Down Release";


    /**
     * These are constants defining the speed at which the paddles move when pressed
     * as well as the sensitivity, or amount moved per frame, of the paddles.
     * These two quantities are related by the frame rate of the game window through
     * the equation speed = frame rate * sensitivity. The speed is measured in
     * pixels/second, frame rate is measured in frames/second, and sensitivity is
     * measured in pixels/frame. NOTE: constant is speed, not velocity, as it is
     * defining the number of pixels per second moved but not the direction
     */
    public static final int LEFT_PADDLE_SPEED = 400;
    public static final int LEFT_PADDLE_SENSITIVITY = LEFT_PADDLE_SPEED/FRAME_RATE;

    public static final int RIGHT_PADDLE_SPEED = 400;
    public static final int RIGHT_PADDLE_SENSITIVITY = RIGHT_PADDLE_SPEED/FRAME_RATE;


    // private variables for on screen entities
    private final PongBall ball;
    private final PongPaddle leftPaddle;
    private final PongPaddle rightPaddle;


    // private variable for background image (optional).
    private Image backgroundImage;


    // private variable for listener data type
    private GameComponentListener gameListener;


    public GameWindow() {
        //this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.ball = new PongBall(BALL_START_X_POSITION, BALL_START_Y_POSITION, BALL_RADIUS);
        this.ball.setYVelocity(1);
        this.ball.setXVelocity(1);
        this.leftPaddle = new PongPaddle(LEFT_PADDLE_X, WINDOW_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.rightPaddle = new PongPaddle(RIGHT_PADDLE_X, WINDOW_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.setBackground(Color.blue);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.drawImage(backgroundImage, 0, 0, null);
        g2.fillRect(leftPaddle.getXPosition(), leftPaddle.getYPosition(), leftPaddle.getDimensions().getXDimension(), leftPaddle.getDimensions().getYDimension());
        g2.fillRect(rightPaddle.getXPosition(), rightPaddle.getYPosition(), rightPaddle.getDimensions().getXDimension(), rightPaddle.getDimensions().getYDimension());
        g2.fillOval(ball.getXPosition(), ball.getYPosition(), ball.getDimensions().getXDimension(), ball.getDimensions().getYDimension());
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void updateLeftPaddle() {
        int leftPaddleNextPosition = leftPaddle.getYPosition() + leftPaddle.getYVelocity();
        int paddleHeight = leftPaddle.getDimensions().getYDimension();

        if(leftPaddleNextPosition < 0) {
            leftPaddleNextPosition = 0;
        }
        else if(leftPaddleNextPosition + paddleHeight > WINDOW_HEIGHT) {
            leftPaddleNextPosition = WINDOW_HEIGHT - paddleHeight;
        }
        leftPaddle.setYPosition(leftPaddleNextPosition);
    }

    public void updateRightPaddle() {
        int rightPaddleNextPosition = rightPaddle.getYPosition() + rightPaddle.getYVelocity();
        int paddleHeight = rightPaddle.getDimensions().getYDimension();

        if (rightPaddleNextPosition < 0) {
            rightPaddleNextPosition = 0;
        }
        else if (rightPaddleNextPosition + paddleHeight > WINDOW_HEIGHT) {
            rightPaddleNextPosition = WINDOW_HEIGHT - paddleHeight;
        }
        rightPaddle.setYPosition(rightPaddleNextPosition);
    }

    public void updateBall() {
        // update ball y position
        int ballNextYPosition = ball.getYPosition() + ball.getYVelocity();
        int ballRadius = ball.getDimensions().getXDimension();
        int rightPaddleHeight = rightPaddle.getDimensions().getYDimension();
        int leftPaddleHeight = leftPaddle.getDimensions().getYDimension();

        if (ballNextYPosition <= 0) {
            ballNextYPosition = 0;
            ball.setYVelocity(-1*ball.getYVelocity());
            GameComponentEvent event;
            event = new GameComponentEvent(GameComponentEvent.TOP_BOUNCE_EVENT, ball.getXPosition(), ball.getYPosition());
            gameListener.gameEventOccurred(event);
        }
        else if (ballNextYPosition + ballRadius >= WINDOW_HEIGHT) {
            ballNextYPosition = WINDOW_HEIGHT - ballRadius;
            ball.setYVelocity(-1*ball.getYVelocity());
            GameComponentEvent event;
            event = new GameComponentEvent(GameComponentEvent.BOTTOM_BOUNCE_EVENT, ball.getXPosition(), ball.getYPosition());
            gameListener.gameEventOccurred(event);
        }
        ball.setYPosition(ballNextYPosition);


        // update ball x position
        int ballNextXPosition = ball.getXPosition() + ball.getXVelocity();

        if (ballNextXPosition <= PADDLE_WIDTH && ballNextYPosition >= leftPaddle.getYPosition() && ballNextYPosition <= leftPaddle.getYPosition() + leftPaddleHeight) {
            ballNextXPosition = PADDLE_WIDTH + 1;
            ball.setXVelocity(-1*ball.getXVelocity());
            GameComponentEvent event;
            event = new GameComponentEvent(GameComponentEvent.LEFT_PADDLE_BOUNCE_EVENT, ball.getXPosition(), ball.getYPosition());
            gameListener.gameEventOccurred(event);
        }

        if (ballNextXPosition + ballRadius >= WINDOW_WIDTH - PADDLE_WIDTH && ballNextYPosition >= rightPaddle.getYPosition() && ballNextYPosition <= rightPaddle.getYPosition() + rightPaddleHeight) {
            ballNextXPosition = WINDOW_WIDTH - PADDLE_WIDTH - ballRadius;
            ball.setXVelocity(-1*ball.getXVelocity());
            GameComponentEvent event;
            event = new GameComponentEvent(GameComponentEvent.RIGHT_PADDLE_BOUNCE_EVENT, ball.getXPosition(), ball.getYPosition());
            gameListener.gameEventOccurred(event);
        }

        if (ballNextXPosition <= 0) {
            ballNextXPosition = 1;
            ball.setXVelocity(-1*ball.getXVelocity());
            GameComponentEvent event;
            event = new GameComponentEvent(GameComponentEvent.LEFT_SCORE_EVENT, ball.getXPosition(), ball.getYPosition());
            gameListener.gameEventOccurred(event);
        }
        else if(ballNextXPosition + ballRadius >= WINDOW_WIDTH) {
            ballNextXPosition = WINDOW_WIDTH - ballRadius;
            ball.setXVelocity(-1*ball.getXVelocity());
            GameComponentEvent event;
            event = new GameComponentEvent(GameComponentEvent.RIGHT_SCORE_EVENT, ball.getXPosition(), ball.getYPosition());
            gameListener.gameEventOccurred(event);
        }
        ball.setXPosition(ballNextXPosition);
    }

    public void update() {
        updateLeftPaddle();
        updateRightPaddle();
        updateBall();
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        update();
                        repaint();
                        Thread.sleep(1000/FRAME_RATE);
                    }
                    catch (InterruptedException e) {
                        System.out.print("Thread Interrupted!");
                    }

                }
            }
        }).start();
    }

    public void addLeftUpKey(final int upKey) {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(upKey, 0, false), LEFT_PADDLE_UP_PRESS);
        inputMap.put(KeyStroke.getKeyStroke(upKey, 0, true), LEFT_PADDLE_UP_RELEASE);

        ActionMap actionMap = this.getActionMap();
        actionMap.put(LEFT_PADDLE_UP_PRESS, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                leftPaddle.setYVelocity(-1*LEFT_PADDLE_SENSITIVITY);
            }
        });

        actionMap.put(LEFT_PADDLE_UP_RELEASE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                leftPaddle.setYVelocity(0);
            }
        });
    }

    public void addLeftDownKey(int downKey) {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(downKey, 0, false), LEFT_PADDLE_DOWN_PRESS);
        inputMap.put(KeyStroke.getKeyStroke(downKey, 0, true), LEFT_PADDLE_DOWN_RELEASE);

        ActionMap actionMap = this.getActionMap();
        actionMap.put(LEFT_PADDLE_DOWN_PRESS, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                leftPaddle.setYVelocity(LEFT_PADDLE_SENSITIVITY);
            }
        });

        actionMap.put(LEFT_PADDLE_DOWN_RELEASE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                leftPaddle.setYVelocity(0);
            }
        });
    }

    public void addRightUpKey(int upKey) {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(upKey, 0, false), RIGHT_PADDLE_UP_PRESS);
        inputMap.put(KeyStroke.getKeyStroke(upKey, 0, true), RIGHT_PADDLE_UP_RELEASE);

        ActionMap actionMap = this.getActionMap();
        actionMap.put(RIGHT_PADDLE_UP_PRESS, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rightPaddle.setYVelocity(-1*RIGHT_PADDLE_SENSITIVITY);
            }
        });

        actionMap.put(RIGHT_PADDLE_UP_RELEASE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rightPaddle.setYVelocity(0);
            }
        });
    }

    public void addRightDownKey(int downKey) {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(downKey, 0, false), RIGHT_PADDLE_DOWN_PRESS);
        inputMap.put(KeyStroke.getKeyStroke(downKey, 0, true), RIGHT_PADDLE_DOWN_RELEASE);

        ActionMap actionMap = this.getActionMap();
        actionMap.put(RIGHT_PADDLE_DOWN_PRESS, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rightPaddle.setYVelocity(RIGHT_PADDLE_SENSITIVITY);
            }
        });

        actionMap.put(RIGHT_PADDLE_DOWN_RELEASE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rightPaddle.setYVelocity(0);
            }
        });
    }

    public void addGameListener(GameComponentListener listener) {
        this.gameListener = listener;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        GameWindow gameWindow = new GameWindow();
        SideMenu menu = new SideMenu();
        int leftPlayerScore = 0;
        int rightPlayerScore = 0;

        gameWindow.addLeftUpKey(KeyEvent.VK_A);
        gameWindow.addLeftDownKey(KeyEvent.VK_Z);

        gameWindow.addRightUpKey(KeyEvent.VK_K);
        gameWindow.addRightDownKey(KeyEvent.VK_M);

        gameWindow.addGameListener(new GameComponentListener() {
            @Override
            public void gameEventOccurred(GameComponentEvent gameEvent) {
                if (gameEvent.getEventType() == GameComponentEvent.TOP_BOUNCE_EVENT) {
                    System.out.print("bounced off of top\n");
                }
                else if(gameEvent.getEventType() == GameComponentEvent.BOTTOM_BOUNCE_EVENT) {
                    System.out.print("bounced off of bottom\n");
                }
                else if (gameEvent.getEventType() == GameComponentEvent.LEFT_PADDLE_BOUNCE_EVENT) {
                    System.out.print("bounced off of left paddle\n");
                }
                else if (gameEvent.getEventType() == GameComponentEvent.RIGHT_PADDLE_BOUNCE_EVENT) {
                    System.out.print("bounced off of right paddle\n");
                }
                else if (gameEvent.getEventType() == GameComponentEvent.LEFT_SCORE_EVENT) {
                    System.out.print("right player scored (ball hit left side of screen)\n");
                }
                else if (gameEvent.getEventType() == GameComponentEvent.RIGHT_SCORE_EVENT) {
                    System.out.print("left player scored (ball hit right side of screen)\n");
                }
            }
        });
        
        //gameWindow.setBackgroundImage(Background_Image_Pong.jpg);

        gameWindow.setBounds(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        gameWindow.start();

        menu.setBounds(GameWindow.WINDOW_WIDTH, 0, 1200 - WINDOW_WIDTH, WINDOW_HEIGHT);

        frame.add(gameWindow);
        //frame.add(menu);
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
