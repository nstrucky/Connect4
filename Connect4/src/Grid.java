/**
*the grid in array format of the gameboard
* csc116-ce project Team(nick, patina, jmeah) project Connect 4
*@author Patina Herring
*/

public class Grid{
    
    /**number of rows in the grid*/
    private int rows;
    /**number of columns in the grid*/
    private int columns;
    /**array of tokens on grid*/
    private Token[][] tokens;
    
    /**
    *constructor for Grid
    *@param rows number of rows
    *@param columns number of columns
    */
    
    public Grid(int rows, int columns){
        if (rows < 1 || columns < 1 ){
            throw new IllegalArgumentException("Invalid row/col");
        }
        this.rows = rows;
        this.columns = columns;

        tokens = new Token[rows][columns];
        
    } 
    
    /**
    * Sets the token at the given grid coordinates
    * @param token token to place in the grid
    * @param row row coordinate
    * @param column coordinate for column
    */
    public void setToken(int row, int column, Token token){
        if (token == null){
            throw new NullPointerException("Null token");
        }
        if (row < 0 || row > this.rows ){
            throw new IllegalArgumentException("Invalid row");
        }
        if (column < 0 ||  column > this.columns){
            throw new IllegalArgumentException("Invalid col");
        }
        
        tokens[row][column] = token; 
    }
    
    /**
    * Returns a token in the 2D array at the given row and col.
    *
    * @param row row coordinate
    * @param column column coordinate
    * @return Token object at the coordinate in grid
    */
    public Token getToken(int row, int column){
        if (row < 0 || row > this.rows){
            throw new IllegalArgumentException("Invalid row");
        }
        if (column < 0 || column > this.columns){
            throw new IllegalArgumentException("Invalid col");
        }
        return tokens[row][column];
    
    }

    /**
    * prints the contents of the grid
    * @return String of the contents in the grid
    */
    public String toString(){
        String contentGrid = "";
        for (int i = 0; i < rows; i++){
            for (int j = 0 ; j < columns; j++){
                if(tokens[i][j] != null){
                    contentGrid = contentGrid + tokens[i][j].getPlayerName(); 
                    if(j != columns - 1){
                        contentGrid = contentGrid + " ";
                    }
                } else {
                    contentGrid = contentGrid + "null ";
                }
            }
            contentGrid = contentGrid + "\n";
        }

        return contentGrid;
    }

   
}