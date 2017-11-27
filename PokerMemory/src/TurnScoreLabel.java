
import javax.swing.JLabel;

public class TurnScoreLabel extends JLabel
{
	
	private static final long serialVersionUID = 1L;
	
	// data fields
	private int turnScoreLabel = 0;
	//private  String DESCRIPTION;
	
	public TurnScoreLabel()
	{
		super();
		reset();
	}
	
	public void setDifficultyModeLabel(String difficultyMode){
		//DESCRIPTION = "Turns Taken: ";
		//setHorizontalTextPosition(JLabel.LEFT);
	}
	
	public int getScoreOfTurns(){
		return this.turnScoreLabel;
		}
	
	public void setTurnScoreLabel(int scoreLabel)
	{
		turnScoreLabel = scoreLabel;
	}
	
	/**
	 * Update the text label with the current counter value
	*/
	private void update()
	{
		this.setText("" + this.getScoreOfTurns());
	}
	
	/**
	 * Default constructor, starts counter at 0
	*/
	
	/**
	 * Increments the counter and updates the text label
	*/
	public void increment()
	{
		this.turnScoreLabel++;
		update();
	}
	
	/**
	 * Resets the counter to zero and updates the text label
	*/
	public void reset()
	{
		this.turnScoreLabel = 0;
		update();
	}
}