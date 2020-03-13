/**
*object for a player in a game
*part of csc116-ce exercise Connect4 Team project(Patina,Jmeah,Nick)
*@author Patina Herring
*/

public class Player{
    
    /**name of player*/
    private String name;

    /**maximum connections player currently has*/
    private int maxConnections;

    /**number of wins player has in the current session*/
    private int wins;

    /**number of games player has played in the current session*/
    private int gamesPlayed;

    /**color of the player's representation in the game*/
    Token token;

    /**
    *constructor for a player
    *parseInt statement from learn-java-by-example.com
    *@param pname the name of the player
    *@throws NullPointerException when no player name is provided
    *@throws IllegalArgumentException when name is less than 2 characters or 
    * only integers
    */
    public Player(String pname){
        if (pname == null){
            throw new NullPointerException("Null name");
        }
        if (pname.length() < 2){
            throw new IllegalArgumentException("Please use a name with at least 2 characters");
        }
        
        try {
            Integer.parseInt(pname);
            throw new IllegalArgumentException("Names must include letters");
        } catch (NumberFormatException ex){
            //Do nothing
        }
        
        name = pname;
        maxConnections = 0;
        wins = 0;
        gamesPlayed = 0;


    }
    /**
    *returns the player's name
    *@return String of player's name
    */
    public String getName(){
        return name;
    }
    
    /**
    *sets the player's name
    *@param playerName player's name 
    */
    public void setName(String playerName){
        name = playerName;
    }
    
    /**
    *returns an integer representing the largest set of contiguous pieces for the player
    *@return integer of largest number of contiguous pieces
    */
    public int getMaxConnections(){
        return maxConnections;
    }
    
    /**
    *sets an integer representing the largest set of contiguous pieces the player has
    *@param maxConnects max amount of connects
    */
    public void setMaxConnection(int maxConnects){
        maxConnections = maxConnects;
    }
    
    /**
    *returns the number of games the player has won
    *@return integer of games won
    */
    public int getWins(){
        return wins;
    }

    /**
    *increases the number of games won 
    */
    public void incrementWins(){
        wins++;
    }

    
    /**
    *increases the number of games played by 1
    */
    public void incrementGamesPlayed(){
        gamesPlayed++;
    }

    /**
     * Gets the number of games this player has
     * played in
     * @return number of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    
    /**
    *gives the player's token
    *@return Token object for this player
    */
    public Token getToken(){
        return token;
    }
    
    /**
     * Sets the token for this player
     * @param token a Token
     * @throws NullPointerException if token passed 
     * is null
     */
    public void setToken(Token token) {
        if (token == null) {
            throw new 
                NullPointerException("Token cannot be null");
        }
        this.token = token;
    }


    /**
    *determines if this object is equal to another
    *based on lecture from csc116-ce
    *@param o the object to see if this player is equal to 
    *@return true if all fields(name,wins,maxConnections) are equal
    */
    public boolean equals(Object o){
        if (o instanceof Player){
            Player p = (Player)o;
            return name.equals(p.getName()) && wins == p.getWins() && maxConnections == 
            p.getMaxConnections();
        } else {
            return false;
        }
    }
    
    /**
    *creates a String representation of this object
    *@return String version of Player
    */
    public String toString(){
        return name + " " + maxConnections + " " + wins + " " + gamesPlayed + " " + token;
    }
    
    
}
