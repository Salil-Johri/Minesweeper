/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johri_minesweeperfinalproject;

import java.io.*;

/**
 *
 * @author salil
 */
public class Score implements Comparable, Serializable {
    protected int timeTaken;
    protected String name;
    
    /**Constructor for the score class that initializes the users "score" (time taken) and their name
     * Pre: A time and a name for the user
     * Post: Variables are saved
     */
    public Score(int time, String n) {
        timeTaken = time;
        name = n;
    }
    
    /** Gets the time the user has achieved in the game
     * Pre: none
     * Post: Time is returned
     * @return 
     */
    public int getTime() {
        return timeTaken;
    }
    
    
    /** Compare to method that compares the scores of each user
     * Pre: Object to compare to
     * Post: 1 is returned if time is higher, 0 is returned if times are equal, and -1 is returned if the inputted object has a lower time
     * @return 
     */
    @Override
    public int compareTo(Object c) {
        Score testScore = (Score) c;
        
        if(timeTaken > testScore.getTime()) {
            return 1;
        } else if(timeTaken == testScore.getTime()) {
            return 0;
        }else {
            return -1;
        }
    }
    
    /** To string method that returns user's name and score(time taken)
     * Pre: None
     * Post: Name and score are returned
     * @return 
     */
    @Override
    public String toString() {
        return ("Name: " + name +"  Score: " + timeTaken);
    }
}
