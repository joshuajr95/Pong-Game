import javax.swing.*;
import java.awt.*;

public class Scorecard extends JPanel {

    private JLabel playerOneScoreCard;
    private JLabel playerTwoScoreCard;

    private Integer playerOneScore;
    private Integer playerTwoScore;

    private Integer maxScore;

    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;

    public Scorecard(int xCoordinate, int yCoordinate, int width, int height) {
        // initialize scores to 0
        playerOneScore = 0;
        playerTwoScore = 0;

        // initialize the two scorecards
        this.playerOneScoreCard = new JLabel();
        this.playerTwoScoreCard = new JLabel();
        playerOneScoreCard.setText(playerOneScore.toString());
        playerTwoScoreCard.setText(playerTwoScore.toString());
        playerOneScoreCard.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        playerTwoScoreCard.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        playerOneScoreCard.setHorizontalAlignment(JLabel.CENTER);
        playerTwoScoreCard.setHorizontalAlignment(JLabel.CENTER);

        // initialize the two labels for the scorecards
        this.playerOneLabel = new JLabel();
        this.playerTwoLabel = new JLabel();
        playerOneLabel.setText("Player 1");
        playerTwoLabel.setText("Player 2");
        playerOneLabel.setHorizontalAlignment(JLabel.CENTER);
        playerTwoLabel.setHorizontalAlignment(JLabel.CENTER);


        setLayout(new GridLayout(2, 2));


        this.add(playerOneLabel);
        this.add(playerTwoLabel);
        this.add(playerOneScoreCard);
        this.add(playerTwoScoreCard);

        setBackground(Color.GREEN);
        setBounds(xCoordinate, yCoordinate, width, height);
    }

    public void incrementPlayerOneScore() {
        this.playerOneScore++;
        playerOneScoreCard.setText(playerOneScore.toString());
    }

    public void incrementPlayerTwoScore() {
        this.playerTwoScore++;
        playerTwoScoreCard.setText(playerTwoScore.toString());
    }

    public void resetScores() {
        this.playerOneScore = 0;
        this.playerTwoScore = 0;

        playerOneScoreCard.setText(playerOneScore.toString());
        playerTwoScoreCard.setText(playerTwoScore.toString());
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
