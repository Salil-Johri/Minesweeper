/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johri_minesweeperfinalproject;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author salil
 */
public class MinesweeperGUI implements ActionListener{
    private JLabel title, timeTotal, winner, loser, messageScore, errorMessage, numberOfBombs;
    private JLabel[] showScores;
    private JButton [][] field;
    private JButton resetButton, submitButton, playAgainButton, quitGameButton, clearDataButton;
    private JPanel contentPane, contentPaneTitle, contentPaneTime, contentPaneField, contentPaneReset, contentPaneWin, contentPaneLoss, contentPaneSubmitName, contentPaneHighScores, contentPaneButtonsHighScores, contentPaneDisplayHS, contentPaneNumBombs;
    private JFrame frame, frameResult, frameScoreWriting, frameScoreDisplay;
    private JTextField name;
    private JComboBox numBombsOption;
    private Timer time, winScreen, loseScreen, nameScreen, highScoreScreen;
    private int timeTaken, numBombsOnField = 10;
    private boolean[][] checkedGrid = new boolean[20][20];
    private String[] options;
    private Score userScore;
    private ScoreTable leaderBoard = new ScoreTable();

    /**Constructor for minesweeper that initializes grid and display for user. The constructor also initializes a GUI meant for submitting a name
     * for the scoreboard functionality
     * Pre: none
     * Post: Display is initialized for user
     */
    public MinesweeperGUI() {
        
    title = new JLabel("MINESWEEPER");
    title.setFont(new Font("Times New Roman", Font.BOLD, 30));
    contentPaneTitle = new JPanel();
    contentPaneTitle.add(title);
    
    //Initializing a blank mine field with no mines
    field = new JButton[20][20];
    contentPaneField = new JPanel();
    contentPaneField.setLayout(new GridLayout(0, 20, 0, 0));
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new JButton(" ");
                field[i][j].setSize(new Dimension(50, 50));
                field[i][j].setBackground(Color.blue);
                field[i][j].addMouseListener(new MouseListener());
                field[i][j].setActionCommand("Playing");
                contentPaneField.add(field[i][j]);
            }
        }  
        //Calls on method to set bombs
        setBombs(numBombsOnField);//Default bombs are set to 10
        //Counts neighbours and sets numbers on other buttons surrounding minefield
        setField();
        
        //Initializes label that displays the amount of time the user is taking to finish the game
        timeTotal = new JLabel("Time: " + Integer.toString(timeTaken));
        contentPaneTime = new JPanel();
        contentPaneTime.add(timeTotal);
        
        //Allows the player to reset the grid if the choose to
        resetButton = new JButton("Reset Grid");
        resetButton.setBackground(Color.yellow);
        resetButton.addActionListener(new ResetListener());
        
        contentPaneReset = new JPanel();
        contentPaneReset.add(resetButton);
        
        
        numberOfBombs = new JLabel("Number of Bombs: ");
        options = new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
        numBombsOption = new JComboBox(options);
        numBombsOption.addActionListener(new ComboBoxListener());
        
        contentPaneNumBombs = new JPanel();
        contentPaneNumBombs.add(numberOfBombs);
        contentPaneNumBombs.add(numBombsOption);
        
        
        //Initializes the content pane to go on the main frame
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(contentPaneTitle);
        contentPane.add(contentPaneField);
        contentPane.add(contentPaneTime);
        contentPane.add(contentPaneReset);
        contentPane.add(contentPaneNumBombs);
        
        //Initializes the name entering GUI if the user wins
        messageScore = new JLabel("Congratulations! Please enter your name for the scoreboard: ");
        name = new JTextField();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitNameListener());
        errorMessage = new JLabel(" ");
        
        contentPaneSubmitName = new JPanel();
        contentPaneSubmitName.setLayout(new GridLayout(2, 2, 10, 10));
        contentPaneSubmitName.add(messageScore);
        contentPaneSubmitName.add(name);
        contentPaneSubmitName.add(submitButton);
        contentPaneSubmitName.add(errorMessage);
        
        frameScoreWriting = new JFrame("Submit Name");
        frameScoreWriting.add(contentPaneSubmitName);
        frameScoreWriting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameScoreWriting.pack();

        //Initializes the main frame for the GUI where the game will be played
        frame = new JFrame("Minesweeper");
        frame.add(contentPane);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        time = new Timer(1000, this);//Timer used to keep track of time user takes to complete game
        //Initializes the multiple timers used to change between screens
        winScreen = new Timer(3000, new TimeListenerWin()); //winScreen changes the main GUI display to the "You win!" message (after 3 second delay)
        loseScreen = new Timer(3000, new TimeListenerLoss());//loseScreen changes the main GUI display to the "You lose!" message (after 3 second delay)
        nameScreen = new Timer(3000, new TimeShowWritingScore());//nameScreen changes the GUI display to name entering screen (after 3 second delay)
        highScoreScreen = new Timer(3000, new TimeHighScoreListener());//highScoreScreen changes the GUI display to display all the high scores(after 3 second delay)
        
        time.start(); //Starts timer that keeps track of amount of time user has taken to play game
}
    /**Event handler that keeps track of time and increments every 1000 tics, or one second
     * Pre: none
     * Post: Time is updated and displayed for user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        timeTaken ++;
        timeTotal.setText("Time: " + Integer.toString(timeTaken));
    }
    
    /**Sets an amount of bombs that the user chooses
     * Pre: An amount of bombs desired by the user
     * Post: Bombs are initialized on the field
     */
    public void setBombs(int nBombs) {
          
        int numBombs = 0;
        int randPos1, randPos2;
        while(numBombs != nBombs) {
            randPos1 = (int)(Math.random() * 20);
            randPos2 = (int)(Math.random() * 20);
            
            if (!field[randPos1][randPos2].getText().equals("-1")) {
                field[randPos1][randPos2].setText("-1");
                field[randPos1][randPos2].setForeground(Color.blue);
                numBombs ++;
            }
        
    }
    }
    
    /**Sets the rest of the field for the user
     * Pre: none
     * Post: Display is initialized for user
     */
    public void setField() {
        int numNeighbours = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(!field[i][j].getText().equals("-1")) {
                numNeighbours = countBombNeighbours(i, j);
                if(numNeighbours == 0) {
                    field[i][j].setText("  "); //If no bombs nearby, field has no text to display
                }else {
                    field[i][j].setText(Integer.toString(numNeighbours));
                }
                field[i][j].setForeground(Color.blue);
                }

            }
        }
    }
    
    /**Counts amount of bombs near a given position
     * Pre: A position to check 
     * Post: Neighbours are counted and returned to set to text 
     */
    public int countBombNeighbours(int cellRow, int cellCol) {
        int count = 0;
        if (field[cellRow][cellCol].getText().equals("-1")) {
            count = -1;
        }
        for (int i = cellRow - 1; i <= cellRow + 1; i++) {
            for (int j = cellCol - 1; j <= cellCol + 1; j++) {
                try {
                    if(field[i][j].getText().equals("-1")) {
                        count ++;
                    }
                }catch(ArrayIndexOutOfBoundsException e) {
                       
                        }
                }
            }
       
        return count;
    }
 
    /**Checks if user has lost by clicking a bomb
     * Pre: Button that the user clicked
     * Post: Game stops if user has clicked on a bomb
     */
    public void checkLoss(JButton clicked) {
        if(clicked.getText().equals("B") && clicked.getBackground().equals(Color.black)) {
            showBombs();
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    field[i][j].setActionCommand("Loser"); //Changes actionCommand so that user can no longer click on bomb
                }
            }
            time.stop();
            loseScreen.start();
        }
       
    }
    
    /**Checks if user has won the game (all bombs have been discovered and everything has been uncovered)
     * Pre: None
     * Post: Game stops if user has won the game
     */
    public void checkWin() {
        int counter = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j].getText().equals("-1") && (field[i][j].getBackground().equals(Color.blue) || field[i][j].getBackground().equals(Color.red))) {
                    counter ++; //Counter is incremented if bomb has not been clicked, or if bomb has been flagged
                } else if(!field[i][j].getText().equals("-1")) {
                    if(field[i][j].getBackground().equals(Color.white)) {
                        counter ++; //Counter is incremented if space that is not a bomb has been clicked
                    }
                }
            }
            
            
        }
        if(counter == 400) {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    field[i][j].setActionCommand("Winner");//sets action command to winner so user can no longer click
                }
            }
            time.stop();
            winScreen.start();
        }
    }
    /**Mouse Listener that listens to user click 
     * Pre: none
     * Post: Depending on user click, space is revealed or space is flagged.
     */
    class MouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            JButton clicked = (JButton)e.getSource();
        if (clicked.getActionCommand().equals("Playing")) { //checks to make sure game is still going on
            if(SwingUtilities.isLeftMouseButton(e)) { //If button is left clicked
                if(clicked.getText().equals("  ")) { //If the button is a zero, it goes into a recursive call that reveals all other zeros present
                    checkZeros(clicked.getY()/26, clicked.getX()/45); //Since y coordinates are multiples of 26 and x coordinates are multiples of 45, they are divided to obtain their positions on the 2d array
                } else if(clicked.getText().equals("-1")) { //Bomb display is changed to black to show user where bomb is
                    clicked.setBackground(Color.black);
                    clicked.setText("B");
                    clicked.setForeground(Color.red);
                } else {
                    clicked.setBackground(Color.white); //Sets the background color to white to reveal the space
                    clicked.setForeground(Color.blue);
                }

            }else if(SwingUtilities.isRightMouseButton(e)){ //If the button is right clicked
                if(clicked.getBackground().equals(Color.blue)) {
                    clicked.setBackground(Color.red);//flags space
                    clicked.setForeground(Color.red);
                } else if(clicked.getBackground().equals(Color.red)) {
                    clicked.setBackground(Color.blue);//unflags space if it is already flagged
                    clicked.setForeground(Color.blue);
                }
            }
            }
            checkWin();
            checkLoss(clicked);


        }
    }
    /** Recursively reveals empty fields 
     * Pre: Position of first zero clicked
     * Post: Amount of zeros in a given area are revealed for the user
     */
    public void checkZeros (int x, int y) {
        try {
            if(checkedGrid[x][y] && field[x][y].getText().equals("-1")) {
            return;
        } //if the space has already been checked, or space is a bomb, it exits recursive call
            
        if(field[x][y].getBackground().equals(Color.blue) && field[x][y].getText().equals("  ")) { //if the space is blank and hasnt been clicked
                field[x][y].setBackground(Color.white);
                checkedGrid[x][y] = true;//space has become checked
                //Surrounding area is checked recursively
                checkZeros(x+1, y);
                checkZeros(x-1, y);
                checkZeros(x, y+1);
                checkZeros(x, y-1);
                
                //Diagonals are checked recursively
                checkZeros(x+1, y+1);
                checkZeros(x-1, y+1);
                checkZeros(x+1, y-1);
                checkZeros(x-1, y-1);
        }else {
            field[x][y].setBackground(Color.white);//Sets the border of the zeros to white on its return from checking and exits method
            return;
        }
            

        }catch(ArrayIndexOutOfBoundsException e) {
            
        }

    }
    /**Reset listener that listens to whether user wants to reset game
     * Pre: none
     * Post: game is reset if user desires so
     */
    class ResetListener implements ActionListener {
        @Override 
        public void actionPerformed(ActionEvent e) {
            resetGame();
        }
    }
    /**Listener that ensures three seconds have past before displaying win message
     * Pre: none
     * Post: Win screen is shown
     */
    class TimeListenerWin implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                winScreen();

        }
    }
    
    /**Listener that ensures three seconds have past before displaying lose message
     * Pre: none
     * Post: Lose screen is shown
     */
    class TimeListenerLoss implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                loseScreen();

        }
    }
    
    /**Listener that has obtains user input for their name
     * Pre: Name must be entered for the scoreboard 
     * Post: Name and time are made into userScore object, which is then added to the leaderboard object
     */
    class SubmitNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            nameScreen.stop();
            String nameScore;
            if(!name.getText().equals("")) {
                nameScore = name.getText();
                userScore = new Score(timeTaken, nameScore);
                leaderBoard.addScore(userScore);
                leaderBoard.sortScores();
                leaderBoard.saveScores();
                leaderBoard.loadScores();
                displayHighScores();
            }else {
                errorMessage.setText("This is invalid.");
            }
        }
    }
    
    /**Listener that waits three seconds before displaying high scores after lose screen
     * Pre: None
     * Post: High score screen is displayed for the user
     */
    class TimeShowWritingScore implements ActionListener {
        public void actionPerformed(ActionEvent e){
            frameResult.setVisible(false);
            frameScoreWriting.setVisible(true);
        }
    }
    
    /**Listener that allows user to play again if they click the play again button
     * Pre: None
     * Post: Board is reset and game begins
     */
    class PlayAgainListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frameScoreDisplay.setVisible(false);
            resetGame();
            frame.setVisible(true);
        }
    }
    
    /**Listener that exits application if user wants to exit game
     * Pre: None
     * Post: Application is exited
     */
    class ExitGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    
    }
    
    /**Listener that displays a high score after three seconds
     * Pre: None
     * Post: High scores are displayed 
     */
    class TimeHighScoreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frameResult.setVisible(false);
            displayHighScores();
        }
    }
    /** Combo box listener that sets desired amount of bombs for user. Game is then reset with desired amount of bombs
     * Pre: none
     * Post: grid is reset with desired amount of bombs
     */
    class ComboBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            String choice = (String)box.getSelectedItem();
            
            switch (choice) {
                case "10":
                    numBombsOnField = 10;
                    resetGame();
                    break;
                case "20":
                    numBombsOnField = 20;
                    resetGame();
                    break;
                case "30":
                    numBombsOnField = 30;
                    resetGame();
                    break;
                case "40":
                    numBombsOnField = 40;
                    resetGame();
                    break;
                case "50":
                    numBombsOnField = 50;
                    resetGame();
                    break;
                case "60":
                    numBombsOnField = 60;
                    resetGame();
                    break;
                case "70":
                    numBombsOnField = 70;
                    resetGame();
                    break;
                case "80":
                    numBombsOnField = 80;
                    resetGame();
                    break;
                case "90":
                    numBombsOnField = 90;
                    resetGame();
                    break;
                case "100":
                    numBombsOnField = 100;
                    resetGame();
                    break;
                default:
                    break;
            }
        }
    }
    /**Clears all scores from display and from file
     * Pre: none
     * Post: All scores are deleted
     */
    class ClearDataListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            leaderBoard.deleteFile();//File and arraylist are cleared
            for (int i = 0; i < showScores.length; i++) {
                showScores[i].setText("");//Labels are cleared of scores
            }
        }
    }
    
    /**Method that initializes and displays the win message for the user
     * Pre: None
     * Post: Win message is displayed
     */
    public void winScreen() {
            Font winFont = new Font("Times New Roman", Font.BOLD, 350);
            winner = new JLabel("YOU WIN!");
            winner.setFont(winFont);
            winner.setBackground(Color.red);
        
            contentPaneWin = new JPanel();
            contentPaneWin.add(winner);
            contentPaneWin.setBackground(Color.black);
        
            frameResult = new JFrame("You Win!");
            frameResult.add(contentPaneWin);
            frame.setVisible(false);
            frameResult.setVisible(true);
            frameResult.pack();
            frameResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            winScreen.stop();
            nameScreen.start();
       
    }
    
    /**Method that initializes and displays the lose message for the user
     * Pre: None
     * Post: Lose message is displayed
     */
    public void loseScreen() {
            Font loseFont = new Font("Times New Roman", Font.BOLD, 300);
            loser = new JLabel("YOU LOSE!");
            loser.setFont(loseFont);
            loser.setBackground(Color.red);
        
            contentPaneLoss = new JPanel();
            contentPaneLoss.add(loser);
            contentPaneLoss.setBackground(Color.black);
        
            frameResult = new JFrame("You Lose!");
            frameResult.add(contentPaneLoss);
            frame.setVisible(false);
            frameResult.setVisible(true);
            frameResult.pack();
            frameResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loseScreen.stop();
            highScoreScreen.start();
    }
    
    /** Method that resets the game and all its components
     * Pre: none
     * Post: Mines and timer are reset and game is playable again
     */
    public void resetGame() {
            timeTaken = 0;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    field[i][j].setText(" ");
                    field[i][j].setBackground(Color.blue);
                    field[i][j].setForeground(Color.blue);
                    field[i][j].setActionCommand("Playing");
                }
            }  
            setBombs(numBombsOnField);
            setField();
            time.start();
    }
    /** Shows all bombs once the player has lost
     * Pre: none
     * Post: All bombs are revealed for the user
     */
    public void showBombs() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j].getText().equals("-1")) {
                    field[i][j].setBackground(Color.black);
                    field[i][j].setText("B");
                    field[i][j].setForeground(Color.red);
                }
            }
        }
    }
    
    /**Method that loads high scores and displays them for the user
     * Pre: None
     * Post: High Score GUI is displayed for the user
     */
    public void displayHighScores() {
        highScoreScreen.stop();
        frameScoreWriting.setVisible(false);
        
        playAgainButton = new JButton("Play again");
        quitGameButton = new JButton("Exit game");
        clearDataButton = new JButton("Clear Data");
        
        playAgainButton.addActionListener(new PlayAgainListener());
        quitGameButton.addActionListener(new ExitGameListener());
        clearDataButton.addActionListener(new ClearDataListener());
        showScores = new JLabel[leaderBoard.getScoreSize()];
        contentPaneHighScores = new JPanel();
        contentPaneHighScores.setLayout(new BoxLayout(contentPaneHighScores, BoxLayout.PAGE_AXIS));
        
        for (int i = 0; i < showScores.length; i++) { //Array of labels are used to display all the high scores
                showScores[i] = new JLabel("" + (i+1) + ". " + leaderBoard.getScore(i));
                contentPaneHighScores.add(showScores[i]);

        }
        contentPaneHighScores.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        contentPaneButtonsHighScores = new JPanel();
        contentPaneButtonsHighScores.add(playAgainButton);
        contentPaneButtonsHighScores.add(quitGameButton);
        contentPaneButtonsHighScores.add(clearDataButton);
                
        contentPaneDisplayHS = new JPanel();
        contentPaneDisplayHS.setLayout(new BoxLayout(contentPaneDisplayHS, BoxLayout.PAGE_AXIS));
        contentPaneDisplayHS.add(contentPaneHighScores);
        contentPaneDisplayHS.add(contentPaneButtonsHighScores);
        
        frameScoreDisplay = new JFrame("High Scores");
        frameScoreDisplay.add(contentPaneDisplayHS);
        frameScoreDisplay.pack();
        frameScoreDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameScoreDisplay.setVisible(true);
    }
    
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        MinesweeperGUI foo = new MinesweeperGUI();
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            runGUI();
        }
    });
    }
}
