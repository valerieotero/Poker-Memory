import javax.swing.JFrame;

public class EqualPairLevelWithScore extends EqualPairLevel
{
	private long scoreLabel;
	private final int LEVEL_INCREMENT = 50;
	private final int PENALTY = 5;
	
	protected EqualPairLevelWithScore(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) 
	{
		super(validTurnTime, mainFrame);
		scoreLabel = 0;
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
	
	public long getScoreLabel() 
	{
		return scoreLabel;
	}
	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
			this.getTurnedCardsBuffer().add(card);
			if(this.getTurnedCardsBuffer().size() == getCardsToTurnUp())
			{
				// there are two cards faced up
				// record the player's turn
				this.getTurnsTakenCounter().increment();
				// get the other card (which was already turned up)
				Card otherCard = (Card) this.getTurnedCardsBuffer().get(0);
				// the cards match, so remove them from the list (they will remain face up)
				if( otherCard.getNum() == card.getNum()) 
				{
					this.getTurnedCardsBuffer().clear();
					this.increment();
					this.getMainFrame().setScore(this.getScoreLabel());
					System.out.println(this.getScoreLabel());
				}
				// the cards do not match, so start the timer to turn them down
				else 
				{
					this.getTurnDownTimer().start();
					this.decrement();
					this.getMainFrame().setScore(this.getScoreLabel());
					System.out.println(this.getScoreLabel());
				}
			}
			return true;
		}
		// there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
		return false;
	}
	
}
