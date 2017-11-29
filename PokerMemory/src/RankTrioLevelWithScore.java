import javax.swing.JFrame;

public class RankTrioLevelWithScore extends RankTrioLevel
{
	private long scoreLabel = 0;
	private final int LEVEL_INCREMENT = 100;
	private final int PENALTY = 5;
	
	protected RankTrioLevelWithScore(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) 
	{
		super(validTurnTime, mainFrame);

	}
	
	public void reset() 
	{
		scoreLabel = 0;
	}
	
	public void increment() 
	{
		scoreLabel += LEVEL_INCREMENT;
	}
	
	public void decrement() 
	{
		scoreLabel -= PENALTY;
	}


}
