import org.junit.Before;
import org.junit.Test;
import java.awt.*;

import junit.framework.TestCase;

/**
 * Tests Player class
 * based on tests by S. Balik in CSC116 Project 6
 * @author Patina Herring
 */

public class PlayerTest extends TestCase {

    /** Player Ben for testing */
    private Player ben;

    /**
    * Create Players for testing
    */
    @Before
    public void setUp() {
        ben = new Player("ben");
             
    }

    @Test
    public void testConstructorPreConditions() {
        try {
            new Player(null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Correct NullPointerException message", "Null name",
                    e.getMessage());
        }

    
        try {
            new Player("f");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", 
                "Please use a name with at least 2 characters",
                    e.getMessage());
        }

        try {
            new Player("12");
            fail();
        } catch (IllegalArgumentException e){
            assertEquals("Correct IllegalArgumentException message", "Names must include letters",
                e.getMessage());
     
        }       

    }

    
    @Test
    public void testGetNameBen() {
        assertEquals("ben name", "ben", ben.getName());
    }

    @Test
    public void testGetMaxConnections(){
        assertEquals("ben's max Connections", 0, ben.getMaxConnections());
    }

    @Test
    public void testSetMaxConnection() {
        ben.setMaxConnection(3);
        assertEquals("ben's updated max Connections", 3, ben.getMaxConnections());

    }

    @Test
    public void testToStringBen(){
        assertEquals("ben's toString", "ben 0 0 0 null", ben.toString());
    }
    
    @Test
    public void testGetToken(){
        assertEquals("ben's token", null, ben.getToken());

    }

    @Test
    public void testSetToken(){
        Token tr = new Token ("ben", Color.red);
        ben.setToken(tr);
        assertEquals("ben's updated token", tr, ben.getToken());
    }
    
    @Test
    public void testEqualsBen() {
        assertTrue("ben equals with same instance", ben.equals(ben));
        assertFalse("ben with different name", ben.equals(new Player("june")));
        assertFalse("ben compared to null object", ben.equals(null));
        assertFalse("ben compared to String", ben.equals("ben"));
    }
    
    @Test
    public void testIncrementWinsGetWins(){
        ben.incrementWins();
        assertEquals("ben's wins incremented", 1, ben.getWins());
    }
    

    
    
   



}
