/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johri_minesweeperfinalproject;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author salil
 */
public class ScoreTable implements Serializable {
    ArrayList<Comparable> listScores = new ArrayList();
    File scoreFile = new File("scores.dat");
    
    /**Constructor for ScoreTable class that initializes the list of scores if there is one written to a file
     * Pre: none
     * Post: If the score file exists, the score file is read and the list of scores is saved
     */
    public ScoreTable() {
        if(scoreFile.exists()) {
            
            try {
                FileInputStream in = new FileInputStream(scoreFile);
                ObjectInputStream readScore = new ObjectInputStream(in);
                
                listScores = (ArrayList<Comparable>)readScore.readObject();
                in.close();
                readScore.close();
            }catch(FileNotFoundException e) {
                System.err.println(e.getMessage());
            }catch(IOException e) {
                System.err.println(e.getMessage());
            }catch(ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            
        }
    }
    
    /**Saves scores that are obtained by user
     * Pre: Score to save
     * Post: All scores are saved to file 
     */
    public void saveScores() {
        try {
            FileOutputStream out = new FileOutputStream(scoreFile);
            ObjectOutputStream writeAcc = new ObjectOutputStream(out);
            
            writeAcc.writeObject(listScores);
            out.close();
            writeAcc.close();
        }catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
        }catch(IOException e) {
            System.err.println(e.getMessage());
        }

    }
    
    /**Loads scores frome the score file
     * Pre: None
     * Post: All scores are loaded from the file and the array list is now equal to all scores on the file
     */
    public void loadScores() {
            try {
                FileInputStream in = new FileInputStream(scoreFile);
                ObjectInputStream readScore = new ObjectInputStream(in);
                
                listScores = (ArrayList<Comparable>)readScore.readObject();
                in.close();
                readScore.close();
            }catch(FileNotFoundException e) {
                System.err.println(e.getMessage());
            }catch(IOException e) {
                System.err.println(e.getMessage());
            }catch(ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
    }
        
    /**Adds a name and score to the current list of scores
     * Pre: Score to add
     * Post: Score is added 
     * @param result 
     */
    public void addScore(Score result) {
        listScores.add(result);
    }
    /**Sorts all the scores in the array list from lowest to highest
     * Pre: none
     * Post: All scores are sorted from lowest to highest
     */
    public void sortScores() {
        Sorts.mergeSort(listScores, 0, listScores.size() - 1);
    }
    
    /**Deletes the file that the scores are written to, and clears array list of all scores
     * Pre: none
     * Post: All Scores have been deleted from memory
     */
    public void deleteFile() {
        if(scoreFile.exists()) {
            scoreFile.delete();
        }
        listScores.clear();

    }
    
    /**Returns the size of the score list
     * Pre: none
     * Post: The size of the array list of scores is returned to the user
     */
    public int getScoreSize() {
        return listScores.size();
    }
    
    /**Returns the score at a certain position of the array list
     * Pre: none
     * Post: The score at position pos is returned 
     */
    public Comparable getScore(int pos) {
        return listScores.get(pos);
    }
    
    /**Prints out the array list
     * Pre:None
     * Post: Array list is printed out
     */
    public String toString() {
        String result = "";
        
        for (int i = 0; i < listScores.size(); i++) {
            result +=("" + (i+1) + ". ");
            result += listScores.get(i).toString();
            result += "\n";
        }
        
        return result;
    }

}
