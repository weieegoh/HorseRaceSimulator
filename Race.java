import java.util.concurrent.TimeUnit;
import java.lang.Math;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author We Ee Goh
 * @version 1
 */
public class Race
{
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace() {
        boolean finished = false;
        Horse winner = null;
        
        // Reset all horses
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();
        
        while (!finished) {
            // Move horses
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);
            
            // Check for winner
            if (raceWonBy(lane1Horse)) {
                winner = lane1Horse;
                finished = true;
            } else if (raceWonBy(lane2Horse)) {
                winner = lane2Horse;
                finished = true;
            } else if (raceWonBy(lane3Horse)) {
                winner = lane3Horse;
                finished = true;
            }
            
            // Check if all horses have fallen
            if (lane1Horse.hasFallen() && lane2Horse.hasFallen() && lane3Horse.hasFallen()) {
                finished = true;
            }
            
            printRace();
            try { 
                TimeUnit.MILLISECONDS.sleep(100);
            } catch(Exception e) {}
        }
        
        // Final display
        if (winner != null) {
            // Update winner's confidence
            double newConfidence = Math.min(1.0, winner.getConfidence() + 0.1);
            winner.setConfidence(newConfidence);
            printRace();
            System.out.println("\nAnd the winner is... " + winner.getName() + "!");
        } else {
            System.out.println("\nAll horses have fallen and there is no winner. Please reset the race");
        }
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace() {
        System.out.print('\u000C');  // Clear terminal
        
        multiplePrint('=', raceLength + 3);//Top edge of track
        System.out.println();
        
        // Print each lane
        printSimpleLane(lane1Horse, 1);
        printSimpleLane(lane2Horse, 2);
        printSimpleLane(lane3Horse, 3);
        
        // Print bottom border
        multiplePrint('=', raceLength + 3);
        System.out.println();
    }
    
    private void printSimpleLane(Horse horse, int laneNumber) {
        // Print the race track part
        printLane(horse);
        
        // Just print the info after
        System.out.print(" " + laneNumber + ": " + horse.getName() + 
                       " (Current Confidence " + String.format("%.2f", horse.getConfidence()) + ")");
        
        System.out.println();
    }
    
    private void printLane(Horse theHorse) {
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        System.out.print('|');
        multiplePrint(' ', spacesBefore);
        
        if(theHorse.hasFallen()) {
            System.out.print('X');
        } else {
            System.out.print(theHorse.getSymbol());
        }
        
        multiplePrint(' ', spacesAfter);
        System.out.print('|');
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
