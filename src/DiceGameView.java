import javax.swing.*;
import java.awt.*;

public class DiceGameView extends JFrame {

    // Declaring all instance variables
    private Image background;
    private Image winningBackground;
    private Image losingBackground;

    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private DiceGame backend;
    private final int RULES_OFFSET = 75;
    private final int RECT_HIEGHT = 400;
    private final int RECT_WIDTH = 600;
    private final int RECT_OFFSET_X = 200;
    private final int RECT_OFFSET_Y = 400;



    public DiceGameView(DiceGame backend) {

        // Initializing background photos
        this.background = new ImageIcon("Resources/background.png").getImage();
        this.winningBackground = new ImageIcon("Resources/winningBackground.png").getImage();
        this.losingBackground = new ImageIcon("Resources/losingBackground.png").getImage();

        // Creating backend object
        this.backend = backend;


        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Odds");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {

        // Rules Screen
        if (!backend.isGameStart()) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Intructions for Dice Game", RULES_OFFSET, RULES_OFFSET);
            g.drawString("Welcome to Vegas, this game is called Odds. ", RULES_OFFSET, 2*RULES_OFFSET);
            g.drawString("You are trying to guess to correct number on the die! ", RULES_OFFSET, 3*RULES_OFFSET);
            g.drawString("Now the fun part, you get to choose how many sides are on the die: either 6 or 8", RULES_OFFSET, 4*RULES_OFFSET);
            g.drawString("Get over $200 and you win, or you could lose it all... ", RULES_OFFSET, 5*RULES_OFFSET);
            g.drawString("Choose the die you want to begin", RULES_OFFSET, 6*RULES_OFFSET);
        }
        // Loser's screen
        else if(backend.getPlayer1().getBalance() == 0 && backend.isGameOver())
        {
            g.drawImage(losingBackground, 0, 0, WINDOW_WIDTH,WINDOW_HEIGHT,this);
        }
        // Winner's screen
        else if(backend.getPlayer1().getBalance() >= 200)
        {
            g.drawImage(winningBackground, 0, 0, WINDOW_WIDTH,WINDOW_HEIGHT,this);
        }
        // Game Screen
        else
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.setColor(Color.WHITE);
            g.drawString("Player Name: " + backend.getPlayer1().getName(), RULES_OFFSET, RULES_OFFSET);
            g.drawString("Balance: "+ backend.getPlayer1().getBalance(), RULES_OFFSET, 2*RULES_OFFSET);
            g.drawString("Num Sides:  " + backend.getD1().getSides(), RULES_OFFSET, 3*RULES_OFFSET);
            g.drawString("Bet: " + backend.getPlayer1().getBet(), RULES_OFFSET, 4*RULES_OFFSET);
            g.drawString("Guess: " + backend.getPlayer1().getGuess(), RULES_OFFSET, 5*RULES_OFFSET);
            g.fillRect(RECT_OFFSET_X, RECT_OFFSET_Y, RECT_WIDTH,RECT_HIEGHT);
            if (Die.getRoll() != 0)
            {
                backend.getD1().draw(g);
            }

        }
    }
}

