import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.text.*;
import javax.swing.event.*;

/**
 * Graphical user interface for the Connect4 game
 * 
 * @author Nick STruckmeyer
 * @author Patina Herring
 */
public class Connect4Gui extends JFrame implements ActionListener {

    /** Width of GUI Window */
    public static final int WIDTH = 800;

    /** Height of GUI Window */
    public static final int HEIGHT = 750;

    /** X Location for GUI */
    public static final int X = 100;

    /** Y Location for GUI */
    public static final int Y = 100;

    /** Horizontal gap for grid */
    public static final int H_GAP = 2;

    /** Vertical gap for grid */
    public static final int V_GAP = 2;

    /** Other panels' vertical and horizontal gap */
    public static final int MISC_GAP = 10;

    /** Info panel border width */
    public static final int INFO_BORDER = 20;

    /** Connect4 game where logic is performed */
    private Connect4 game;
    
    /**determines if testing or regular play*/
    private boolean testing;
    
    /**Random number for computer's turn*/
    private Random random;

    /** Panel containing player stats and game info */
    private JPanel informationPanel;

    /** Panel containing game grid */
    private JPanel gridPanel;

    /** Array of JPanels visually representing game board */
    private JPanel[][] panelGrid;

    /** Player 1 stats label */
    private JLabel player1Label;

    /** Player 2 stats label */
    private JLabel player2Label;

    /** Player stats panel */
    private JPanel playerStatsaPanel;

    /** Contains players' colors */
    private JPanel playerColorPanel;

    /** Player 1's color panel */
    private JPanel p1ColorPanel;

    /** Player 2's color panel */
    private JPanel p2ColorPanel;

    /** Label with player's turn called out */
    private JLabel turnLabel;
    
    /** Number of games played label */
    private JLabel gamesPlayedLabel;

    /** Panel containing turn info and games played */
    private JPanel turnInfoPanel;

    /** Panel containing buttons for reset/quit */
    private JPanel buttonPanel;

    /** Quit button */
    private JButton quitButton;

    /** Reset button */
    private JButton resetButton;

    /** Buttons above each column */
    private JButton[] columnButtons;

    /** Panel containing column buttons */
    private JPanel colButtonPanel;

    /** Container for game window */
    private Container c;

    /** Panel containing the game board */
    private JPanel gameBoardPanel;

    /** Button used to select single player mode */
    private JRadioButton singlePlayer;

    /** Button used to select multi-player mode */
    private JRadioButton twoPlayers;

    /** Field used to enter player 1's name */
    private JTextField p1NameField;

    /** Field used to enter player 2's name */
    private JTextField p2NameField;

    
    /**
     * Constructor for the graphical user interface
     * @param testing true if in testing mode, false otherwise
     */
    public Connect4Gui(boolean testing) {
        super("Connect4!");
        this.testing = testing;
        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        c = getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new BorderLayout());

        //Player statistics and Turn info
        informationPanel = new JPanel();
        informationPanel.setLayout(new GridLayout());

        playerStatsaPanel = new JPanel();
        playerStatsaPanel.setLayout(new GridLayout(2, 1,
                                                   MISC_GAP,
                                                   MISC_GAP));

        player1Label = new JLabel("Player 1 label");
        player2Label = new JLabel("Player 2 label");
        playerStatsaPanel.add(player1Label);
        playerStatsaPanel.add(player2Label);

        playerColorPanel = new JPanel(new GridLayout(2, 1,
                                                     MISC_GAP,
                                                     MISC_GAP));
        p1ColorPanel = new JPanel();
        p2ColorPanel = new JPanel();

        playerColorPanel.add(p1ColorPanel);
        playerColorPanel.add(p2ColorPanel);

        turnInfoPanel = new JPanel();
        turnInfoPanel.setLayout(new GridLayout(2, 1,
                                               MISC_GAP,
                                               MISC_GAP));

        turnLabel = new JLabel("No one's turn!");
        gamesPlayedLabel = new JLabel("Game 0");
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        gamesPlayedLabel.setHorizontalAlignment(JLabel.CENTER);
        turnInfoPanel.add(turnLabel);
        turnInfoPanel.add(gamesPlayedLabel);

        informationPanel.add(playerStatsaPanel);
        informationPanel.add(playerColorPanel);
        informationPanel.add(turnInfoPanel);
        informationPanel.setBorder(
            BorderFactory.createEmptyBorder(INFO_BORDER,
                                            INFO_BORDER,
                                            INFO_BORDER,
                                            INFO_BORDER));

        makeGrid(Connect4.MIN_CONNECT_SIZE * 2);

        c.add(informationPanel, BorderLayout.NORTH);
        c.add(gameBoardPanel, BorderLayout.CENTER);
        
        //Game play buttons
        buttonPanel = new JPanel();
        quitButton = new JButton("QUIT");
        quitButton.addActionListener(this);
        resetButton = new JButton("RESET");
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);
        buttonPanel.add(quitButton);
        c.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

    }

    /** 
     * Creates the grid including the column buttons and 
     * panels representing tokens on the gameboard. 
     *
     * @param gridSize the number of rows and columns 
     */
    public void makeGrid(int gridSize) {
        colButtonPanel = new JPanel(new GridLayout(1, gridSize, H_GAP, V_GAP));
        columnButtons = new JButton[gridSize];

        for (int h = 0; h < gridSize; h++) {
            columnButtons[h] = new JButton(h + 1 + "");
            columnButtons[h].addActionListener(this);
            colButtonPanel.add(columnButtons[h]);
        }

        gridPanel = new JPanel(new GridLayout(gridSize, gridSize, H_GAP, V_GAP));
        panelGrid = new JPanel[gridSize][gridSize];

        for (int i = 0; i < panelGrid.length; i++) {
            for (int j = 0; j < panelGrid[i].length; j++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                panelGrid[i][j] = panel;
                gridPanel.add(panel);
            }
        }

        gameBoardPanel.add(colButtonPanel, BorderLayout.NORTH);
        gameBoardPanel.add(gridPanel,BorderLayout.CENTER);
    }
       
        
    /**
     * Handles game play based on events generated by buttons (columns, reset, quit)
     * @param e event that controls game play.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            displayOptions();

        } else if (e.getSource() == quitButton) {
            System.exit(1);

        } else if (e.getSource() == twoPlayers) {
            p2NameField.setEnabled(true);
        } else if (e.getSource() == singlePlayer) {
            p2NameField.setEnabled(false);
        }

        //Determines which column button was clicked
        
        for (int i = 0; i < panelGrid.length; i++) {

            if (e.getSource() == columnButtons[i]) {
                Player currentPlayer = game.getCurrentPlayer();
                Token token = currentPlayer.getToken();
                int row = game.addTokenToColumn(i);
                //Token added successfully
                if (row != -1) { 
                    panelGrid[row][i].setBackground(token.getColor()); 
                    updateInfoLabels();
                    //for playing against the computer
                    if(game.getNumPlayers() == 0 && 
                        game.getCurrentPlayer()
                            .getName()
                            .equals("Computer")){
                        
                        if(!testing){
                            random = new Random();
                            int compCol = random.nextInt(panelGrid.length);
                            columnButtons[compCol].doClick();
                        } else {
                            columnButtons[2].doClick();
                        }
                    }
                        
                    if (game.gameIsOver()) {
                        displayEndOptions();
                    }

                } 
            }
        }
    }

    /**
     * Displays the winner and options to either
     * Continue, Reset, or quit the program
     */
    private void displayEndOptions() {
        Object[] options = {"Continue", "Reset", "Quit"};
        int choice = JOptionPane.showOptionDialog(
            c, 
            game.getWinner().getName() + " is the winner!", 
            "Game Over",
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            options,
            options[2]);

        if (choice == JOptionPane.YES_OPTION) { //Continue
            nextGame();
        } else if (choice == JOptionPane.NO_OPTION) { //Reset
            displayOptions();
        } else if (choice == JOptionPane.CANCEL_OPTION) { //Quit
            System.exit(1);
        }
    }

    /**
     * Creates a new game and resets the game board
     */
    private void nextGame() {
        game = new Connect4(game.getPlayer1(), game.getPlayer2(), 
                                game.getConnectToWin());
        gameBoardPanel.remove(gridPanel);
        gameBoardPanel.remove(colButtonPanel);
        makeGrid(game.getGameBoardSize());
        updateInfoLabels();
        setVisible(true);
    }

    /**
     * Displays the game options prior to playing 
     */
    private void displayOptions() {
        
        //Number of players
        ButtonGroup radios = new ButtonGroup();
        singlePlayer = new JRadioButton("Single Player");
        twoPlayers = new JRadioButton("Two Players");
        singlePlayer.addActionListener(this);
        twoPlayers.addActionListener(this);
        singlePlayer.setSelected(true);
        
        radios.add(singlePlayer);
        radios.add(twoPlayers);

        //Connections to win
        String[] connections = {"4", "5", "6", "7", "8"};
        JComboBox<String> connectionsOptions = 
            new JComboBox<String>(connections);

        //Player names
        p1NameField = new JTextField();
        p2NameField = new JTextField();
        p2NameField.setEnabled(false);

        //Layout
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(singlePlayer);
        panel.add(twoPlayers);
        panel.add(new JLabel("Connections to Win: "));
        panel.add(connectionsOptions);
        panel.add(new JLabel("Player 1:"));
        panel.add(p1NameField);
        panel.add(new JLabel("Player 2:"));
        panel.add(p2NameField);
        
        boolean checking = true;
            
        do {
            int result = JOptionPane.showConfirmDialog(null, panel,
                                          "Game Options",
                                          JOptionPane.OK_CANCEL_OPTION,
                                          JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int maxConnect = Integer.valueOf((String) connectionsOptions.getSelectedItem());
                int gridSize = 2 * maxConnect;

                gameBoardPanel.remove(gridPanel);
                gameBoardPanel.remove(colButtonPanel);
                makeGrid(gridSize);
                String p1NameText = p1NameField.getText();
                String p2NameText = p2NameField.getText();
                p1ColorPanel.setBackground(Color.RED);

                if (singlePlayer.isSelected() && validatePlayerName(p1NameText)) {
                    Player p1 = makeP1(p1NameText);
                    game = new Connect4(p1, maxConnect);
                    p2ColorPanel.setBackground(Color.GREEN);
                    checking = false;
                    
                } else if (validatePlayerName(p1NameText) && 
                            validatePlayerName(p2NameText) ) {
                    Player p1 = makeP1(p1NameText);
                    Player p2 = new Player(p2NameField.getText());
                    p2.setToken(new Token(p2.getName(), Color.BLUE));
                    game = new Connect4(p1, p2, maxConnect);
                    p2ColorPanel.setBackground(Color.BLUE);
                    checking = false;
                    
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Names must contain at least 1 letter and be longer than 1 character");
                }

            } else {
                checking = false;
                if (game == null) {
                    System.exit(1);
                } else if (game.gameIsOver()) {
                    displayEndOptions();
                }
            }
        } while (checking);

        updateInfoLabels();
        setVisible(true);
    }

    /**
     * Creates player 1 object and sets the token
     * @param name player name
     * @return player 1 object
     */
    private Player makeP1(String name) {
        Player p1 = new Player(name);
        p1.setToken(new Token(p1.getName(), Color.RED));
        return p1;
    }


    /**
     * Validates whether the player name conforms to program 
     * @param playerName player's name
     * @return true if is not null, less that 2 characters, and not
     * only numbers
     */
    private boolean validatePlayerName(String playerName) {
        if (playerName == null) {
            return false;
        } else if (playerName.length() < 2) {
            return false;
        } 

        for (int i = 0; i < playerName.length(); i++) {
            if (Character.isLetter(playerName.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Updates the labels on the information panel
     */
    private void updateInfoLabels() {
        Player p1 = game.getPlayer1();
        Player p2 = game.getPlayer2();

        String player1Text = String.format("%s -- Max Connections - %d, Wins - %d",
                                            p1.getName(), p1.getMaxConnections(),
                                            p1.getWins()); 
        String player2Text = String.format("%s -- Max Connections - %d, Wins - %d",
                                            p2.getName(), p2.getMaxConnections(),
                                            p2.getWins()); 

        turnLabel.setText(game.getCurrentPlayer().getName() + "'s turn!");
        gamesPlayedLabel.setText("Games Played " +
            game.getCurrentPlayer().getGamesPlayed());
        player1Label.setText(player1Text);
        player2Label.setText(player2Text);
    }

    /**
     * Starts the Connect4 GUI 
     * has a testing mode based on CSC116 Project6 code due to 1 player options
     * @param args Command line arguments testing
     */
    public static void main(String[] args) {
                
        if (args.length == 0) {
            new Connect4Gui(false).displayOptions(); //Normal game mode
        }
        else if (args.length == 1 && args[0].equals("-t")) {
            new Connect4Gui(true).displayOptions();  //Testing mode
        }
        else {
            System.out.println("Usage: java Connect4Gui [-t]");
        }
        
    }

}