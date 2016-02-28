//cbasurto Final Project Tic Tac Toe with Restart button

/**
   TicTacToe game class
   Only partially implemented
   --Sets up a square 2D array of buttons
   --attaches a listener to each which wrtites an X or an O on the
   button 
   --missing: action to check for a win each time button is clicked
              action to restart the game, and a button to do that.
   --optional:a computerPlays method to have the computer play against
   a single player.
 */


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.awt.geom.*;

public class TTTboard{
	//fields
	JFrame frame;
	JPanel panel;
	JButton[][] jb;
	String player = "X";
	JButton restart;
	JTextArea area;
	
	JFrame restartFrame; 

	//************** ACTION LISTENER CLASS **************
	//inner class listener will play game each time a button is clicked
	//putting an X or an O on the button in the process


	/**
   How to install an Action Listener as an inner 
class so that it has access to the parameters available to main.
It is thus able to change the TEXT of a button when it is clicked.

	 */


    class PlayListener implements ActionListener
    {
	//performed every time a button is clicked
	public void actionPerformed(ActionEvent event)
	{
	    //get the button that was clicked
	    JButton button = (JButton) event.getSource();
	    button.setFont(new Font("SansSerif",0,60));
	    
	    //mark it, make it inert, check for a win
			//else change player
	    
	    
			//label button with X or O (whoever is up)
	    button.setText(player);
	    button.setEnabled(false);//make button inert so
	    //it can't be clicked again
	    boolean hasWon = checkForWin(player); //if player wins announce it in text and stop game by disabling buttons
	    if(hasWon) {
		
		restartFrame.setVisible(true);
		
		// if the player has won, disable all the tic-tac-toe buttons
		for(int i=0;i<3;i++) { // row
		    for(int j=0;j<3;j++) { // column
			if(jb[i][j].isEnabled()) {
			    jb[i][j].setEnabled(false);
			}
		    }
		}
		/*Note---not sure if you wanted the player to be X/O or 1/2. 
		  if (player.equals("X")) {
		  area.setText(" Congrats!!! Player 1 WON."); // set the relevant text in textarea
		  }
		  else {
		  area.setText(" Congrats!!! Player 2 WON."); // set the relevant text in textarea
		  }*/
		area.setText(" Congrats!!! Player '"+player+"' WON."); // set the relevant text in textarea
			}
	    else { // if no player won, then there is a chance that a draw might have happened, hence check for that
								
		boolean isDraw = checkForDraw();
		
		if( isDraw ) { // if draw happened, set relevant message in textarea
		    
		    restartFrame.setVisible(true);
					
		    area.setText(" It's a Draw !");
				}
		else { // if the game was not won and was not a draw, then continue the game by switching the turn
		    changePlayer();
		}
	    }
	}
	
	// boolean checks if draw happened
	private boolean checkForDraw() {
	    // even if there is a single button that is enabled, then draw has NOT happened
	    for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
		    if (jb[i][j].isEnabled())
			return false;
		}
	    }
	    return true;
	}
	
	// boolean checks if the player won
	private boolean checkForWin(String player) {
	    
	    // Note: I could not do the foreground red color for disabled
	    //buttons once the player has won so I put the red as
	    //background color.
	    
	    //Check for all winning combinations. 
	    
	    // check horizontal - row 1
	    if(jb[0][0].getText().equals(player) && jb[0][1].getText().equals(player) && jb[0][2].getText().equals(player)) {
		jb[0][0].setBackground(Color.RED);
		jb[0][1].setBackground(Color.RED);
		jb[0][2].setBackground(Color.RED);
				return true;
	    }
	    // check horizontal - row 2
	    if(jb[1][0].getText().equals(player) && jb[1][1].getText().equals(player) && jb[1][2].getText().equals(player)) {
		jb[1][0].setBackground(Color.RED);
		jb[1][1].setBackground(Color.RED);
		jb[1][2].setBackground(Color.RED);
		return true;
	    }
	    // check horizontal - row 3
	    if(jb[2][0].getText().equals(player) && jb[2][1].getText().equals(player) && jb[2][2].getText().equals(player)) {
		jb[2][0].setBackground(Color.RED);
		jb[2][1].setBackground(Color.RED);
		jb[2][2].setBackground(Color.RED);
		return true;
	    }
	    
	    //check vertical - column 1
	    if(jb[0][0].getText().equals(player) && jb[1][0].getText().equals(player) && jb[2][0].getText().equals(player)) {
		jb[0][0].setBackground(Color.RED);
		jb[1][0].setBackground(Color.RED);
		jb[2][0].setBackground(Color.RED);
				return true;
	    }
	    //check vertical - column 1
	    if(jb[0][1].getText().equals(player) && jb[1][1].getText().equals(player) && jb[2][1].getText().equals(player)) {
		jb[0][1].setBackground(Color.RED);
		jb[1][1].setBackground(Color.RED);
		jb[2][1].setBackground(Color.RED);
		return true;
	    }
	    //check vertical - column 1
	    if(jb[0][2].getText().equals(player) && jb[1][2].getText().equals(player) && jb[2][2].getText().equals(player)) {
		jb[0][2].setBackground(Color.RED);
		jb[1][2].setBackground(Color.RED);
		jb[2][2].setBackground(Color.RED);
		return true;
	    }
	    
	    //check diagonal -						\
	    if(jb[0][0].getText().equals(player) && jb[1][1].getText().equals(player) && jb[2][2].getText().equals(player)) {
		jb[0][0].setBackground(Color.RED);
		jb[1][1].setBackground(Color.RED);
		jb[2][2].setBackground(Color.RED);
		return true;
	    }
	    //check diagonal - /
	    if(jb[0][2].getText().equals(player) && jb[1][1].getText().equals(player) && jb[2][0].getText().equals(player)) {
		jb[0][2].setBackground(Color.RED);
		jb[1][1].setBackground(Color.RED);
		jb[2][0].setBackground(Color.RED);
		return true;
	    }
	    
	    return false;
	}
    }
    //***************** END LISTENER ********************
    
    
    //******************* BEGIN RESTART LISTENER *****************
	//listener that will restart the game when the restart button is
	//clicked. It should wipe the board clean and re-enable the buttons.
    class RestartListener implements ActionListener
    {
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    
	    restartFrame.setVisible(false);
	    
	    JButton oldcolor = new JButton(); // oldcolor button will be used to get its default background color
	    
	    for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++){
		    jb[i][j].setEnabled(true); // enabling the button
		    jb[i][j].setText(""); // resetting its text
		    jb[i][j].setBackground(oldcolor.getBackground()); // resetting its background color
		}
	    }
	    
	    area.setText(""); // resetting the textarea which holds the result
	    
	    player = "X"; // resetting the player
	    
	}
    }
    //***************** END LISTENER ********************
    
    //In the following constructor, you need to 
    //create a restart button, with "Restart" on the back
    //attach the restart listener to it.
    
    public TTTboard(){
	frame= new JFrame();
		panel=new JPanel();
		panel.setLayout(new GridLayout(3,3));
		jb = new JButton[3][3];
		
		restart = new JButton("RESTART");
		restart.setForeground(Color.BLUE);
		restart.setFont(new Font("SansSerif",0,20));
		
		area = new JTextArea();
		area.setEditable(false);
		
		ActionListener pl = new PlayListener();//same listener for all buttons
		
		for(int i=0;i<3;i++)
		    for(int j=0;j<3;j++){
			jb[i][j] = new JButton();
			
			jb[i][j].addActionListener(pl);
			panel.add(jb[i][j]);
		    }
		
		ActionListener rl = new RestartListener(); // restart listener
		restart.addActionListener(rl);
		
		//panel.add(restart);
		//panel.add(area);
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,400);
		frame.setVisible(true);
		
		restartFrame = new JFrame();
		restartFrame.setLayout(new GridLayout(2,1));
		restartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		restartFrame.add(area);
		restartFrame.add(restart);
		
		restartFrame.setSize(200,200);
		restartFrame.setVisible(false);
		
    }
    
    public void changePlayer()
    {
	if (player.equals("X"))
	    player = "O";
	else
	    player = "X";
    }
    public static void main(String[] args){
	TTTboard tx = new TTTboard();
	}
}
