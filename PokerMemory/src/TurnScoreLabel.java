

import javax.swing.JLabel;

public class TurnScoreLabel extends JLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// data fields
	private int scoreLabel = 0;
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
	
	public int getScoreLabel(){
		return this.scoreLabel;
		}
	
	/**
	 * Update the text label with the current counter value
	*/
	private void update()
	{
		this.setText("foo " + this.getScoreLabel());
		System.out.println(this.getText() + " Clicks kk");
		//setHorizontalTextPosition(JLabel.LEFT);
	}
	
	/**
	 * Default constructor, starts counter at 0
	*/
	
	/**
	 * Increments the counter and updates the text label
	*/
	public void increment()
	{
		this.scoreLabel++;
		update();
	}
	
	/**
	 * Resets the counter to zero and updates the text label
	*/
	public void reset()
	{
		this.scoreLabel = 0;
		System.out.println("Reset called");
		update();
	}
}