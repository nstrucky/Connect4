import java.awt.Color;

/**
 * Handles logic behind Connect4 game
 * 
 * @author Nick Struckmeyer
 */
public class Connect4 {
    
    /** Maximum number of connections to win */
    public static final int MAX_CONNECT_SIZE = 8;

    /** Minimum number of connections to win */
    public static final int MIN_CONNECT_SIZE = 4;

    /** Number of players in game */
    private int numPlayers;

    /** Connected Tokens needed to win */
    private int connectToWin;

    /** Player 1 */
    private Player p1;

    /** Player 2 */
    private Player p2;

    /** The Current Player */
    private Player currentPlayer;

    /** The winning player */
    private Player winner;

    /** Grid representing Connect4 game board */
    private Grid grid;

    /** Flag used to determine if this game is over */
    private boolean gameIsOver;

    /**
     * Constructor for Connect4 using two players
     * @param p1 player 1
     * @param p2 player 2
     * @param connectToWin connections needed to win
     */
    public Connect4(Player p1, Player p2, int connectToWin) {
        setConnectToWin(connectToWin);
        grid = new Grid(getGameBoardSize(), getGameBoardSize());
        setPlayer1(p1);
        setPlayer2(p2);
        currentPlayer = p1;
        gameIsOver = false;
        
    }

    /**
     * Constructor for Connect4 using two players(1 human,1 computer)
     * @param p1 player 1
     * @param connectToWin connections needed to win
     */
    public Connect4(Player p1, int connectToWin) {
        this(p1, new Player("Computer"), connectToWin);
        
        Color color;
        if (!this.p1.getToken().getColor().equals(Color.GREEN)) {
            color = Color.GREEN;
        } else {
            color = Color.BLUE;
        }
        
        this.p2.setToken(new Token(this.p2.getName(), color));

    }

    /**
     * Adds a token to a column in the connect 4 
     * Grid
     * @param col column number
     * @return index of row token added to, otherwise -1 
     * if unable to add token
     * @throws IllegalArgumentException if col passed is 
     * greater than the number of columns or is 
     * less than 0.
     */
    public int addTokenToColumn(int col) {
        int size = getGameBoardSize();
        if (col >= size || col < 0) {
            throw new IllegalArgumentException("Invalid Column");
        } 

        for (int r = size - 1; r >= 0; r--) {
            Token aToken = grid.getToken(r, col);

            if (aToken == null) {
                Token playerToken = currentPlayer.getToken();
                grid.setToken(r, col, playerToken);
                currentPlayer.setMaxConnection(getLongestConnection(playerToken));

                nextPlayer();
                
                return r;
            }
        }
        return -1;
    }

    /**
     * Switches the current player to the other 
     */
    private void nextPlayer() {
        if (currentPlayer.equals(p1)) {
            currentPlayer = p2;
            //if (currentPlayer.getName()
        } else {
            currentPlayer = p1;
        }
    }
    
    /**
     * Sets the number of connected pieces needed to win
     * @param connectToWin number of connections to Win
     * @throws IllegalArgumentException if the value passed
     * is less than 4 or greater than 8
     */
    public void setConnectToWin(int connectToWin) {
        if (connectToWin > MAX_CONNECT_SIZE || 
            connectToWin < MIN_CONNECT_SIZE) {
            throw new 
                IllegalArgumentException("Connections must be 4 - 8");
        }
        this.connectToWin = connectToWin;
    }

    /**
     * Returns the number of connections needed to win
     * @return connections to win
     */
    public int getConnectToWin() {
        return connectToWin;
    }

    /**
     * Sets the number of players for this game
     * @param numPlayers number of players
     * @throws IllegalArgumentException if the number of players
     * is less than 1 or greater than 2
     */
    public void setNumPlayers(int numPlayers) {
        if (numPlayers > 2 || numPlayers < 1) {
            throw new 
                IllegalArgumentException("Only 1 or 2 players allowed");
        }
        this.numPlayers = numPlayers;
    }

    /**
     * Gets the number of players for this game
     * @return number of players
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Sets the player 1 object
     * @param p1 Player 1
     * @throws NullPointerException if the p1 parameter is null
     */
    public void setPlayer1(Player p1) {
        if (p1 == null) {
            throw new 
                NullPointerException("Player 1 must not be null");
        }
        this.p1 = p1;
    }

    /**
     * Sets the player 2 object
     * @param p2 Player 2
     */
    public void setPlayer2(Player p2) {
        this.p2 = p2;
    }
    
    /**
     * Returns player 1 object
     * @return player 1
     */
    public Player getPlayer1() {
        return p1;
    }

    /**
     * Returns player 2 object
     * @return player 2
     */
    public Player getPlayer2() {
        return p2;
    }

    /** 
     * Gets the name of the player currently in play
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the winning player
     * @return the winner
     */
    public Player getWinner() {
        return winner;
    }


    /**
     * Returns the longest connection on the game board
     * for a specified player token
     * @param token the player's token to check
     * @return longest connection
     */
    public int getLongestConnection(Token token) {
        int count = 0;
        int longest = 0;
        int size = getGameBoardSize();  
        int diagMax = size;
        int diagMin = 0;

        //Horizontal Check
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Token gridToken = grid.getToken(r, c);

                if (gridToken != null && gridToken.equals(token)) {
                    count++;
                    if (count > longest) longest = count;

                } else {
                    count = 0;
                }
            }
        }

        count = 0;

        //Vertical Check
        for (int c = 0; c < size; c++) {
            for (int r = 0; r < size; r++) {
                Token gridToken = grid.getToken(r, c);

                if (gridToken != null && gridToken.equals(token)) {
                    count++;
                    if (count > longest) longest = count;

                } else {
                    count = 0;
                }
            }
        }

        count = 0;

        //Diagonal (bottom) top left to bottom right
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size - r; c++) {
                Token gridToken = grid.getToken(r + c, c);

                if (gridToken != null && gridToken.equals(token)) {
                    count++;
                    if (count > longest) longest = count;

                } else {
                    count = 0;
                }
            }
            count = 0;
        }

        count = 0;

        //Diagonal (top) bottom right to top left
        for (int r = size - 1; r >= 0; r--) {
            for (int c = size - 1; c >= size - r - 1; c--) {
                Token gridToken = grid.getToken(r - (size - c - 1), c);
                
                if (gridToken != null && gridToken.equals(token)) {
                    count++;
                    if (count > longest) longest = count;

                } else {
                    count = 0;
                }
            }
            count = 0;
        }

        count = 0;

        //Diagonal (top) bottom left to top right
        for (int r = size - 1; r >= 0; r--) {
            for (int c = 0; c <= r; c++) {
                Token gridToken = grid.getToken(r - c, c);

                if (gridToken != null && gridToken.equals(token)) {
                    count++;
                    if (count > longest) longest = count;

                } else {
                    count = 0;
                }
            }
            count = 0;
        }

        count = 0;

        // Diagonal (bottom) top right to bottom left
        for (int r = 0; r < size; r++) {
            for (int c = size - 1; c >= r; c--) {
                Token gridToken = grid.getToken(r + (size - c - 1), c);

                if (gridToken != null && gridToken.equals(token)) {
                    count++;
                    if (count > longest) longest = count;

                } else {
                    count = 0;
                }
            }
            count = 0;
        }

        return longest;
    }

    /**
     * Determines if the game is over
     * @return true if game is over
     */
    public boolean gameIsOver() {
        if (getLongestConnection(p1.getToken()) >= connectToWin) {
            if (!gameIsOver) {
                p1.incrementWins();
                gameIsOver = true;
            }
            winner = p1;
            resetPlayerData();

        } else if (getLongestConnection(p2.getToken()) >= connectToWin) {
            if (!gameIsOver) {
                p2.incrementWins();
                gameIsOver = true;
            }
            winner = p2;
            resetPlayerData();
        }

        return gameIsOver;
    }

    /**
     * resets the players' connections and increments
     * games played
     */
    private void resetPlayerData() {
        p1.setMaxConnection(0);
        p2.setMaxConnection(0);
        p1.incrementGamesPlayed();
        p2.incrementGamesPlayed();
    }

    /**
     * Sets all of the Tokens on the game board to a 
     * neutral value (null Token)
     */
    public void resetGameBoard() {
        int size = getGameBoardSize();
        
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid.setToken(r, c, null);
            }
        }
    }

    /**
     * Returns the size of the game board as 2 times 
     * the number of connections needed to win
     * @return game board size
     */
    public int getGameBoardSize() {
        return connectToWin * 2;
    }

    /**
     * Used for testing, returns the Grid 
     * @return Grid object
     */
    public Grid getGrid() {
        return grid;
    }
}