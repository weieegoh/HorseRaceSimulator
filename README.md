# HorseRaceSimulator

Project Overview

This Java application simulates a horse race. The simulation includes horse movement based on confidence levels and provides a visual representation of the race in the console.

Setup Instructions
Prerequisites
Java Development Kit (JDK) 11 or later

Running a Basic Race

Create horses with symbols, names, and confidence levels (0.0 to 1.0).

Add horses to lanes.

Start the race.

Example code to run the race:


public class RaceTester {
    public static void main(String[] args) {
        // Create a new race with a track length of 30
        Race race = new Race(15);
        
        // Create horses with different symbols, names, and confidence levels
        Horse horse1 = new Horse('A', "Turtle", 0.1);
        Horse horse2 = new Horse('B', "Dog", 0.5);
        Horse horse3 = new Horse('C', "Cheetah", 0.9);
    
        // Add horses to the race in their respective lanes
        race.addHorse(horse1,1);
        race.addHorse(horse2,2);
        race.addHorse(horse3,3);
        
        // Start the race
        race.startRace();
    }
}

