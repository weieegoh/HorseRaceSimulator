
/**
 * Write a description of class Horse here.
 * 
 * @author Wei Ee Goh
 * @version 1
 */
public class Horse
{
    //Fields of class Horse
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;
    
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.distanceTravelled = 0;
        this.hasFallen = false;
        
        // Validate confidence is between 0 and 1
        if (horseConfidence < 0) {
            this.confidence = 0;
        } else if (horseConfidence > 1) {
            this.confidence = 1;
        } else {
            this.confidence = horseConfidence;
        }
    }
    
    public void fall()
    {
        this.hasFallen = true;
        // Decrease confidence slightly when horse falls
        this.confidence = Math.max(0, this.confidence - 0.1);
    }
    
    public double getConfidence()
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public char getSymbol()
    {
        return this.symbol;
    }
    
    public boolean hasFallen()
    {
        return this.hasFallen;
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    

    public void moveForward()
    {
        this.distanceTravelled++;
    }

    public void setConfidence(double newConfidence)
    {
        if (newConfidence >= 0 && newConfidence <= 1) {
            this.confidence = newConfidence;
        }
        else if (newConfidence > 1) {
            this.confidence = 1;
        } else {
            this.confidence = 0;
        }
    }
    
    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }
    
}
