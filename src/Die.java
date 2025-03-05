import javax.swing.*;
import java.awt.*;

public class Die
{
    // Holds number of sides
    private int sides;
    private static int roll;
    private final Image[] eightDieImages;
    private final Image[] sixDieImages;
    private DiceGameView frontend;
    private final int RECT_HIEGHT = 400;
    private final int RECT_WIDTH = 600;
    private final int RECT_OFFSET_X = 100;
    private final int RECT_OFFSET_Y = 300;
    private final int DIE_HEIGHT = 100;
    private final int DIE_WITDH = 100;

    // Creates new die
    public Die(DiceGameView frontend)
    {
        // Declaring frontend object, number of sides on the die, and the image arrays
        this.frontend = frontend;
        sides = 0;
        this.sixDieImages = new Image[]{
                new ImageIcon("Resources/sixDieOne.png").getImage(),
                new ImageIcon("Resources/sixDieTwo.png").getImage(),
                new ImageIcon("Resources/sixDieThree.png").getImage(),
                new ImageIcon("Resources/sixDieFour.png").getImage(),
                new ImageIcon("Resources/sixDieFive.png").getImage(),
                new ImageIcon("Resources/sixDieSix.png").getImage()
        };

        this.eightDieImages = new Image[]{
                new ImageIcon("Resources/eightDieOne.png").getImage(),
                new ImageIcon("Resources/eightDieTwo.png").getImage(),
                new ImageIcon("Resources/eightDieThree.png").getImage(),
                new ImageIcon("Resources/eightDieFour.png").getImage(),
                new ImageIcon("Resources/eightDieFive.png").getImage(),
                new ImageIcon("Resources/eightDieSix.png").getImage(),
                new ImageIcon("Resources/eightDieSeven.png").getImage(),
                new ImageIcon("Resources/eightDieEight.png").getImage(),
        };
    }

    // Updates number of sides
    public void setSides(int numSides)
    {
        sides = numSides;
    }
    // Returns the number of sides on the Die.
    public int getSides()
    {
        return sides;
    }

    // Returns a random int between 1 and the number of sides on the Die
    // Also getters and setters for static variable
    public void roll()
    {
        roll = (int)(sides * Math.random() + 1);
    }
    public static void setRoll(int x)
    {
        roll = x;
    }
    public static int getRoll() {
        return roll;
    }

    // Returns a string that gives the odds of you wining and how much money you could possibly win
    public String toString()
    {
        return ("Great choice, your odds of winning are 1/" + sides + "\n"
                + "This means that if you win you will " + sides + "x your money");
    }

    // Prints die in a random location within the white box on the game screen
    public void draw(Graphics g)
    {
        if (sides == 8)
        {
            g.drawImage(eightDieImages[roll - 1],(int)(Math.random() * RECT_WIDTH)
                    + RECT_OFFSET_X, (int)(Math.random() * RECT_HIEGHT) + RECT_OFFSET_Y, DIE_WITDH,
                    DIE_HEIGHT, frontend);
        }
        if (sides == 6)
        {
            g.drawImage(sixDieImages[roll - 1],(int)(Math.random() * RECT_WIDTH)
                            + RECT_OFFSET_X, (int)(Math.random() * RECT_HIEGHT) + RECT_OFFSET_Y, DIE_WITDH,
                    DIE_HEIGHT, frontend);
        }
    }
}