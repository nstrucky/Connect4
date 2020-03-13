import org.junit.Before;
import org.junit.Test;
import java.awt.*;

import junit.framework.TestCase;

/**
 * Tests Grid class
 * converted to work with CSC116 CE project
 * based on test file by Suzanne Balik in CSC116 for Project6
 * @author Patina Herring
 */
public class GridTest extends TestCase {
    
    /** Grid for testing */
    private Grid grid;

    /**
     * Creates Grid for testing
     */
    @Before
    public void setUp() {
        grid = new Grid(2, 3);
    }


    @Test
    public void testConstructorPreConditions() {
 
        try {
            new Grid(0, 2);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid row/col",
                    e.getMessage());
        }

        try {
            new Grid(3, -4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid row/col",
                    e.getMessage());
        }      
    }

    @Test
    public void testSetTokenPreConditions() {
        try {
            grid.setToken(1, 1, null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Correct NullPointerException message", "Null token",
                    e.getMessage());
        }

        try {
            grid.setToken(5, 1, new Token("Orion", Color.red));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid row",
                    e.getMessage());
        }

        try {
            grid.setToken(0, -1, new Token("Janie", Color.black));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid col",
                    e.getMessage());
        }
    }



    @Test
    public void testGetSymbolPreConditions() {
        
        try {
            grid.getToken(5, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid row",
                    e.getMessage());
        }

        try {
            grid.getToken(0, -1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid col",
                    e.getMessage());
        }
    }

    @Test
    public void testSetAndGetSymbol() {
        grid.setToken(0, 1, new Token("Victor", Color.green));
        assertEquals("Token ", new Token("Victor", Color.green), grid.getToken(0, 1));
    }


    @Test
    public void testToString() {
        grid.setToken(0, 0, new Token("Will", Color.red));
        grid.setToken(0, 1, new Token("Sheri", Color.orange));
        grid.setToken(1, 0, new Token("Dave", Color.blue));
        grid.setToken(1, 2, new Token("Beth", Color.green));
        String expected = "Will Sheri null \nDave null Beth\n";
        assertEquals("toString  after constructed",
                     expected, grid.toString());
    }
}