import java.util.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Tests Connect4 class
 * @author Nick Struckmeyer
 */
public class Connect4Test extends TestCase {
    
   
    /** Connect4 instance used for testing */
    private Connect4 connect4TwoPlayerEight;

    /** Player instance used for testing */
    private Player danys;

    /** Player instance used for testing */
    private Player john;

    /** Player Token instance used for testing */
    private Token danysToken;

    /** Player Token instance used for testing */
    private Token johnToken;

    /** Connect4 instance used for testing */
    private Connect4 connect4OnePlayerTen;

    /** Additional Player instance used for testing */
    private Player tyrion;

    /** Additional Token instance used for testing */
    private Token tyrionToken;

    /**
     * Create new two player Connect4 instance for testing size = 8
     */
    @Before
    public void setUp() {
        danys = new Player("Danys");
        john = new Player("John");
        danysToken = new Token(danys.getName(), Color.RED);
        johnToken = new Token(john.getName(), Color.BLUE);
        danys.setToken(danysToken);
        john.setToken(johnToken);
        connect4TwoPlayerEight =  new Connect4(danys, john, 4);

        tyrion = new Player("Tyrion");
        tyrionToken = new Token(tyrion.getName(), Color.YELLOW);
        tyrion.setToken(tyrionToken);
        connect4OnePlayerTen = new Connect4(tyrion, 5);
    }

    @Test
    public void testConstants() {
        assertEquals("MAX_CONNECT_SIZE", 8, Connect4.MAX_CONNECT_SIZE);
        assertEquals("MIN_CONNECT_SIZE", 4, Connect4.MIN_CONNECT_SIZE);
    }

    @Test
    public void testConstructor() {
        assertEquals("Initial Connect to Win", 4, connect4TwoPlayerEight.getConnectToWin());
        assertEquals("Initial player 1", new Player("Danys"), connect4TwoPlayerEight.getPlayer1());
        assertEquals("Initial player 2", new Player("John"), connect4TwoPlayerEight.getPlayer2());
        assertEquals("Initial current player", connect4TwoPlayerEight.getPlayer1(),
                                                connect4TwoPlayerEight.getCurrentPlayer());
        assertEquals("Initial winner", null, connect4TwoPlayerEight.getWinner());   
        assertEquals("Initial grid", new Grid(8, 8).toString(), connect4TwoPlayerEight.getGrid().toString());
        assertFalse("Initial gameIsOver", connect4TwoPlayerEight.gameIsOver());

    }

    @Test
    public void testConstructor2() {
        assertEquals("Initial Connect to Win", 5, connect4OnePlayerTen.getConnectToWin());
        assertEquals("Initial player 1", new Player("Tyrion"), connect4OnePlayerTen.getPlayer1());
        assertEquals("Initial player 2", new Player("Computer"), connect4OnePlayerTen.getPlayer2());
        assertEquals("Initial current player", connect4OnePlayerTen.getPlayer1(),
                                                connect4OnePlayerTen.getCurrentPlayer());
        assertEquals("Initial winner", null, connect4OnePlayerTen.getWinner());   
        assertEquals("Initial grid", new Grid(10, 10).toString(), connect4OnePlayerTen.getGrid().toString());
        assertFalse("Initial gameIsOver", connect4OnePlayerTen.gameIsOver());

    }

    @Test
    public void testAddTokenToColumnPreconditions() {
        try {
            connect4TwoPlayerEight.addTokenToColumn(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid Column",
                e.getMessage());
        }

        try {
            connect4TwoPlayerEight.addTokenToColumn(8);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Invalid Column",
                e.getMessage());
        }
    }

    @Test
    public void testSetConnectToWinPreconditions() {
        try {
            connect4TwoPlayerEight.setConnectToWin(3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Connections must be 4 - 8",
                e.getMessage());
        }

        try {
            connect4TwoPlayerEight.setConnectToWin(9);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Connections must be 4 - 8",
                e.getMessage());
        }
    }

    @Test
    public void testSetPlayer1Preconditions() {
        try {
            connect4TwoPlayerEight.setPlayer1(null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Correct NullPointerException message", "Player 1 must not be null",
                e.getMessage());
        }
    }

    @Test
    public void testSetNumPlayersPreconditions() {
        try {
            connect4TwoPlayerEight.setNumPlayers(3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Only 1 or 2 players allowed",
                e.getMessage());
        }

        try {
            connect4TwoPlayerEight.setNumPlayers(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Correct IllegalArgumentException message", "Only 1 or 2 players allowed",
                e.getMessage());
        }
    }

    @Test
    public void testPlayerTurn() {
        assertEquals("Player 1's turn", "Danys",
                      connect4TwoPlayerEight.getCurrentPlayer().getName());
        connect4TwoPlayerEight.addTokenToColumn(0);
        assertEquals("Player 2's turn", "John", 
                      connect4TwoPlayerEight.getCurrentPlayer().getName());
        connect4TwoPlayerEight.addTokenToColumn(0);
        assertEquals("Player 1's turn", "Danys",
                      connect4TwoPlayerEight.getCurrentPlayer().getName());
        connect4TwoPlayerEight.addTokenToColumn(1);
        assertEquals("Player 2's turn", "John", 
                      connect4TwoPlayerEight.getCurrentPlayer().getName());

    }

    @Test
    public void testgetLongestConnection0() {
        assertEquals("Longest Connection 3", 0, 
            connect4TwoPlayerEight.getLongestConnection(danysToken));
        assertEquals("Longest Connection 3", 0, 
            connect4TwoPlayerEight.getLongestConnection(johnToken));
    }

    @Test
    public void testgetLongestConnection1() {
        connect4TwoPlayerEight.addTokenToColumn(0);
        assertEquals("Longest Connection 1", 1, 
            connect4TwoPlayerEight.getLongestConnection(danysToken));

    }

    @Test
    public void testgetLongestConnectionHorizontal() {
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(3);
        connect4TwoPlayerEight.addTokenToColumn(3);
        assertEquals("Longest Connection 4", 4, 
            connect4TwoPlayerEight.getLongestConnection(danysToken));

    }

    @Test
    public void testgetLongestConnectionHorizontalFive() {
        connect4OnePlayerTen.addTokenToColumn(0);
        connect4OnePlayerTen.addTokenToColumn(0);
        connect4OnePlayerTen.addTokenToColumn(1);
        connect4OnePlayerTen.addTokenToColumn(1);
        connect4OnePlayerTen.addTokenToColumn(2);
        connect4OnePlayerTen.addTokenToColumn(2);
        connect4OnePlayerTen.addTokenToColumn(3);
        connect4OnePlayerTen.addTokenToColumn(3);
        connect4OnePlayerTen.addTokenToColumn(4);
        assertEquals("Longest Connection 5", 5, 
            connect4OnePlayerTen.getLongestConnection(tyrionToken));

    }

    @Test
    public void testgetLongestConnectionVertical() {
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(0);

        assertEquals("Longest Connection 4", 4, 
            connect4TwoPlayerEight.getLongestConnection(danysToken));

    }

    @Test
    public void testgetLongestConnectionDiagonal1() {
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(3);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(3);
        connect4TwoPlayerEight.addTokenToColumn(3);
        connect4TwoPlayerEight.addTokenToColumn(4);
        connect4TwoPlayerEight.addTokenToColumn(3);

        assertEquals("Longest Connection 4", 4, 
            connect4TwoPlayerEight.getLongestConnection(danysToken));

    }

    @Test
    public void testgameIsOver1() {
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(2);
        assertFalse("Game is Over 1", connect4TwoPlayerEight.gameIsOver());
        connect4TwoPlayerEight.addTokenToColumn(3);
        assertTrue("Game is Over 1", connect4TwoPlayerEight.gameIsOver());
        assertEquals("Game is Over player 1 wins", danys.getName(), 
            connect4TwoPlayerEight.getWinner().getName());

    }

    @Test
    public void testgameIsOver2() {
        connect4TwoPlayerEight.addTokenToColumn(0);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(1);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(2);
        connect4TwoPlayerEight.addTokenToColumn(3);
        connect4TwoPlayerEight.addTokenToColumn(3);
        assertFalse("Game is Over 2", connect4TwoPlayerEight.gameIsOver());
        connect4TwoPlayerEight.addTokenToColumn(4);
        assertTrue("Game is Over 2", connect4TwoPlayerEight.gameIsOver());
        assertEquals("player 2 wins", john.getName(), 
            connect4TwoPlayerEight.getWinner().getName());

    }

    @Test
    public void testgameIsOver3Five() {
        connect4OnePlayerTen.addTokenToColumn(0);
        connect4OnePlayerTen.addTokenToColumn(1);
        connect4OnePlayerTen.addTokenToColumn(1);
        connect4OnePlayerTen.addTokenToColumn(2);
        connect4OnePlayerTen.addTokenToColumn(2);
        connect4OnePlayerTen.addTokenToColumn(3);
        connect4OnePlayerTen.addTokenToColumn(3);
        connect4OnePlayerTen.addTokenToColumn(4);
        connect4OnePlayerTen.addTokenToColumn(4);
        assertFalse("Game is Over 3", connect4OnePlayerTen.gameIsOver());
        connect4OnePlayerTen.addTokenToColumn(5);
        assertTrue("Game is Over 3", connect4OnePlayerTen.gameIsOver());
        assertEquals("player 2 wins", connect4OnePlayerTen.getPlayer2().getName(), 
            connect4OnePlayerTen.getWinner().getName());

    }

}
