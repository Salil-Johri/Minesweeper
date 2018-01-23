/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johri_minesweeperfinalproject;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salil
 */
public class ScoreTest {

    /**
     * Test of getTime method, of class Score.
     */
    @Test
    public void testGetTime() {
        System.out.println("Testing getTime method");
        Score instance = new Score(10, "Salil");
        int expResult = 10;
        int result = instance.getTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Score.
     */
    @Test
    public void testCompareTo() {
        System.out.println("Testing compareTo method");
        Score c = new Score(5, "Salil");
        Score instance = new Score(5, "Blergh");
        int expResult = 0;
        int result = instance.compareTo(c);
        assertEquals(expResult, result);

        
        Score d = new Score(4, "Salil");
        int expResult2 = 1;
        int result2 = instance.compareTo(d);
        assertEquals(expResult2, result2);
        
        Score e = new Score(6, "Blrgh");
        int expResult3 = -1;
        int result3 = instance.compareTo(e);
        assertEquals(expResult3, result3);
        
        
    }

    /**
     * Test of toString method, of class Score.
     */
    @Test
    public void testToString() {
        System.out.println("Testing toString method");
        Score instance = new Score(3, "Salil");
        String expResult = "Name: Salil  Score: 3";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
