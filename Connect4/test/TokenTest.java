import org.junit.Before;
import org.junit.Test;
import java.awt.*;

import junit.framework.TestCase;

/**
 * Tests Symbol class
 * based on test file created by Suzanne Balik in CSC116
 * @author Patina Herring
 */
 
public class TokenTest extends TestCase {

    /** Token token1 for testing */
    private Token token1;

        
    
    /**
     * Create Symbols for testing
     */
    @Before
    public void setUp() {
        token1 = new Token("ryan", Color.orange);
        
        
    }
    
    @Test
    public void testConstructorPreConditions() {
        try {
            new Token(null, Color.orange);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Correct NullPointerException message", "playerName is null",
                    e.getMessage());
        }
    }
    
    @Test
    public void testGetNameToken1() {
        assertEquals("token1 name", "ryan", token1.getPlayerName());
    }

    @Test
    public void testGetColorToken1() {
        assertEquals("token1 color", Color.orange, token1.getColor());
    }
    

    @Test
    public void testToStringToken1() {
        assertEquals("token1 toString", "ryan" + " " + Color.orange , token1.toString());
    }
    
    @Test
    public void testEqualsToken1() {
        assertTrue("token1 equals with same instance", token1.equals(token1));
        assertTrue("token1 equals with different instances", 
                   token1.equals(new Token("ryan", Color.orange)));
        assertFalse("token1 with different name", token1.equals(new Token("jill", Color.orange)));
        assertFalse("token1 with different name and points", 
                    token1.equals(new Token("terri", Color.green)));
        assertFalse("token1 compared to null object", token1.equals(null));
        assertFalse("token1 compared to String", token1.equals("ryan"));
    }
    
    
}    