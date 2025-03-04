import java.util.Scanner;
public class Player {

    // Instance Variables for methods + constructer 
    // Reading in scanner input as well
    private double balance;
    private double bet;
    private int guess;
    private String name;
    private double moneyWon;
    Scanner input = new Scanner(System.in);

    // Creates player object and sets balance to baseline
    public Player(String playerName)
    {
        balance = 100;
        name = playerName;
        guess = 0;
        bet = 0;
        moneyWon = 0;
    }

    // Modifies balance after you gamble
    public void withdrawl()
    {
        balance -= bet;
    }

    // Called for user to make bet
    public double bet()
    {
        System.out.println("How much money do you want to put down:");
        bet = input.nextDouble();
        input.nextLine();
        return bet;
    }

    // Makes sure that user can guess a number that is rolled
    public boolean isImpossibleGuess(int sides)
    {
        if (guess > sides || guess <= 0)
        {
            System.out.println("You cannot guess that. You must guess a number that can be rolled!");
            return true;
        }
        else
        {
            return false;
        }
    }

    // Called in order to obtain the guess
    public int guess()
    {
        System.out.println("Whats your lucky number?");
        guess = input.nextInt();
        input.nextLine();
        return guess;
    }

    /* Function called to make sure that a user does not overdraw
     * from their bank account or bet nothing
     */
    public boolean overdraw()
    {
        if (bet > balance)
        {
            System.out.println("Can't do that buddy your balance is only $" +
                    balance + ". Try Again!" );
            return true;
        }
        else if (bet == 0)
        {
            System.out.println("Come on you can't do that, take a risk!");
            return true;
        }
        else
        {
            return false;
        }
    }

    // Modifies balance after you win your money
    public void win(int multiplier)
    {
        moneyWon = multiplier * bet;
        balance += (moneyWon);
        System.out.println("You just won $" + moneyWon);
    }

    // Returns balance of player
    public double getBalance()
    {
        return balance;
    }

    public String getName() {
        return name;
    }

    public int getGuess() {
        return guess;
    }

    public double getBet() {
        return bet;
    }
    public void resetVariables()
    {
        bet = 0;
        guess = 0;
    }

    /* Returns string that informs user of their balance
     * then prompts them to keep playing
     */
    public String toString()
    {
        return (name + " you currently have $" + balance + ". Lets continue!");
    }
}