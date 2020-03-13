import java.awt.Color;

/**
 * Represents a Connect4 Token or 
 * game piece that would occupy the game
 * board.
 *
 * @author Nick Struckmeyer
 */
public class Token {

    /** Player who owns token, name */
    private String playerName;

    /** Color of token */
    private Color color;

    /**
     * Constructor for the Token 
     * @param playerName player's name (owner)
     * @param color color of Token
     * @throws NullPointerException if playerName is null
     */
    public Token(String playerName, Color color) {
        if (playerName == null) {
            throw new 
                NullPointerException("playerName is null");
        }

        this.playerName = playerName;
        this.color = color;
    }

    /**
     * Gets the name of the player who owns 
     * this token
     * @return player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the color of the Token
     * @return color of th etoken
     */
    public Color getColor() {
        return color;
    }

    /**
     * Determines if this Token is equal to 
     * another object
     * @param o object
     * @return true if all fields are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Token) {
            Token other = (Token) o;
            return playerName.equals(other.getPlayerName()) &&
                    color.equals(other.getColor());
        }

        return false;
    }

    /**
     * Returns String representation of this object
     * @return String of this object
     */
    public String toString() {
        return getPlayerName() + " " + getColor();
    }

}