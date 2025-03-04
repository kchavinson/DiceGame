import java.util.Scanner;
public class DiceGame {

    // Instance Variables and reading in Scanner input as well
    private Die d1;
    private Player player1;
    private boolean isGameOver;
    private int numSides;
    private String playerName;
    private double gamble;
    private int guessedNum;
    private boolean isBadGuess;
    private DiceGameView window;
    private boolean isGameStart;
    Scanner input = new Scanner(System.in);


    // Constructor that makes a dice game object
    public DiceGame()
    {


        // Prompts user for player name
        System.out.print("What is your name?: ");
        playerName = input.nextLine();

        // Declaring all instance variables
        isGameStart = false;
        isGameOver = false;
        isBadGuess = false;
        player1 = new Player(playerName);
        numSides = 0;
        d1 = new Die(window);
        gamble = 0;
        guessedNum = 0;
        this.window = new DiceGameView(this);
        window.repaint();
    }


    // Holds the game
    public void play()
    {
        // This is the game, while this loop is running, the game is playing
        while (!isGameOver) {


            // Prints out current balance
            System.out.println(player1);


            // Updates Die and Runs Turn
            this.newDie();
            isGameStart = true;
            window.repaint();

            //resets roll
            player1.resetVariables();
            Die.setRoll(0);


            // Asks for Bet and Updates window
            gamble = player1.bet();
            this.overdrawPerchance();
            player1.withdrawl();
            window.repaint();

            //
            guessedNum = player1.guess();
            this.needGuessAgain();
            window.repaint();

            d1.roll();
            this.printRoll();
            window.repaint();

            // You win money when guessed number is equal to the roll
            if (guessedNum == Die.getRoll())
            {

                // Updates balance, then tells you how much money you won
                player1.win(numSides);
                window.repaint();

                // You win if you get more than 200 so game ends and you win!   
                if (player1.getBalance() > 200)
                {
                    this.youWon();
                    window.repaint();
                }
            }
            // If you don't guess right and you have no money, you lose!
            else if (player1.getBalance() == 0)
            {
                this.youLost();
                window.repaint();
            }
            // The while loop has not been broken so game continues! + Die is reset
            else
            {
                numSides = 0;
                System.out.println("Not Quite! Guess Again.");
            }

        }
    }

    // If you win, break the loop by setting isGameOver to true 
    public void youWon()
    {
        isGameOver = true;
        System.out.println("Congratulations, now get out of here before you" +
                "win more money >:(");
    }

    /* If you lose, break the loop by setting isGameOver to true
     * and give final message
     */
    public void youLost()
    {
        isGameOver = true;
        System.out.println("You Lose. Better Luck Next Time!");
    }

    // Responsible for printing your guess compared to randomized roll
    public void printRoll()
    {
        System.out.println("Your Guess: " + guessedNum + "\n" + "Dice Rolled: " + Die.getRoll());
    }

    /* Makes sure the player did not overdraw
     * prompts for a re inupt of gamble if they did overdraw
     */
    public void overdrawPerchance()
    {
        boolean didOverdraw = player1.overdraw();
        while (didOverdraw)
        {
            gamble = player1.bet();
            didOverdraw = player1.overdraw();
        }
    }

    /* Ensures that player guessed a possible number
     * if not, prompts user for another guess
     */
    // TODO Move this functionality into the guess() method in Player
    public void needGuessAgain() {
        isBadGuess = player1.isImpossibleGuess(numSides);
        while (isBadGuess) {
            guessedNum = player1.guess();
            isBadGuess = player1.isImpossibleGuess(numSides);
        }
    }

    // Prompts user for number of sides than updates 
    public void newDie() {
        boolean isValidDie = false;
        while (!isValidDie) {
            System.out.print("How many sides do you want your die to be?: ");
            numSides = input.nextInt();
            input.nextLine();
            if (numSides == 8 || numSides == 6) {
                d1.setSides(numSides);
                System.out.println(d1);
                isValidDie = true;
            }
        }
    }


    public boolean isGameStart() {
        return isGameStart;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Die getD1() {
        return d1;
    }

    public boolean isGameOver() {
        return isGameOver;
    }



    // Main function where all of the magic happens
    public static void main(String[] args)
    {


        // Creates dice game object
        DiceGame game1 = new DiceGame();

        // Runs through game
        game1.play();

    }
}